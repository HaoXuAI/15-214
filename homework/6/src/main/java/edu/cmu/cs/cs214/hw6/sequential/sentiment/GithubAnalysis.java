package edu.cmu.cs.cs214.hw6.sequential.sentiment;

import org.eclipse.egit.github.core.CommitUser;
import org.eclipse.egit.github.core.RepositoryCommit;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.client.PageIterator;
import org.eclipse.egit.github.core.service.CommitService;
import org.eclipse.egit.github.core.service.RepositoryService;

import java.io.IOException;
import java.util.*;


public class GithubAnalysis {


    public static void main(String[] args) throws Exception {
        new GithubAnalysis().build();
    }

    private static final int MAX_COMMIT_PAGES = 2;

    private List<Repository> getRepositories() {
        List<Repository> repositories = new ArrayList<>();
        repositories.add(new Repository("google", "material-design-icons"));
        repositories.add(new Repository("google", "guava"));
        repositories.add(new Repository("google", "web-starter-kit"));
//        repositories.add(new Repository("google", "protobuf"));
//        repositories.add(new Repository("google", "material-design-lite"));
        repositories.add(new Repository("mozilla", "pdf.js"));
        repositories.add(new Repository("mozilla", "BrowserQuest"));
        repositories.add(new Repository("mozilla", "metrics-graphics"));
//        repositories.add(new Repository("mozilla", "firefox-ios"));
//        repositories.add(new Repository("mozilla", "localForage"));
        repositories.add(new Repository("facebook", "react"));
        repositories.add(new Repository("facebook", "react-native"));
        repositories.add(new Repository("facebook", "immutable-js"));
//        repositories.add(new Repository("facebook", "hhvm"));
//        repositories.add(new Repository("facebook", "pop"));
//        repositories.add(new Repository("travis-ci", "travis-ci"));
//        repositories.add(new Repository("travis-ci", "travis.rb"));
//        repositories.add(new Repository("travis-ci", "travis-cookbooks"));
//        repositories.add(new Repository("travis-ci", "dpl"));
//        repositories.add(new Repository("travis-ci", "travis-web"));
//        repositories.add(new Repository("Microsoft", "vscode"));
//        repositories.add(new Repository("Microsoft", "TypeScript"));
//        repositories.add(new Repository("Microsoft", "CNTK"));
//        repositories.add(new Repository("Microsoft", "dotnet"));
//        repositories.add(new Repository("Microsoft", "ChakraCore"));
        return repositories;
    }

    /**
     * data structures to store messages and sentiment results
     */
    private final Map<Author, List<Message>> messages_author = new HashMap<>();
    private final Map<String, List<Message>> messages_lang = new HashMap<>();
    private final Map<String, List<Message>> messages_org = new HashMap<>();
    private final Map<Author, SentimentResult> sentiments_author = new HashMap<>();
    private final Map<String, SentimentResult> sentiments_lang = new HashMap<>();
    private final Map<String, SentimentResult> sentiments_org = new HashMap<>();


    private void build() throws InterruptedException, IOException {
        GitHubClient github = new GitHubClient();
        //set up with oauth token for higher rate limit
//        github.setOAuth2Token("<yourtoken>");

        //repositories to analyze
        List<Repository> repositories = getRepositories();

        for (Repository repo : repositories) {
            System.out.printf("Downloading data for %s%n", repo.getIdentifier());

            //looking up most popular language in repository
            String language = getTopLanguage(github, repo);

            //looking up commits (paged), up to 5 pages
            CommitService commitService = new CommitService(github);
            PageIterator<RepositoryCommit> commitPages = commitService.pageCommits(repo);
            int pageCounter = 1;
            for (Collection<RepositoryCommit> commitsOnPage : commitPages) {
                System.out.printf(" - downloading page %d%n", pageCounter);
                for (RepositoryCommit commit : commitsOnPage) {
                    //skip merge commits (with more than 1 parents)
                    if (commit.getParents().size() > 1)
                        continue;

                    //for each nonmerge commit, collect messages_author by author, language, and organization
                    CommitUser gitAuthor = commit.getCommit().getAuthor();
                    Author author = new Author(gitAuthor.getName(), gitAuthor.getEmail());
                    Message message = new Message(commit.getCommit().getMessage());

                    addToData(messages_author, author, message);
                    addToData(messages_lang, language, message);
                    addToData(messages_org, repo.getOrg(), message);
                }

                if (++pageCounter > MAX_COMMIT_PAGES) break;
            }
        }

        System.out.println("Running sentiment analysis");
        computeSentiments(messages_author, sentiments_author);
        computeSentiments(messages_lang, sentiments_lang);
        computeSentiments(messages_org, sentiments_org);

        System.out.println("Results by author");
        printSentimentsSorted(sentiments_author);
        System.out.println("Results by language");
        printSentimentsSorted(sentiments_lang);
        System.out.println("Results by organization");
        printSentimentsSorted(sentiments_org);
    }


    /**
     * will print all the entries in the map, sorted from the most negative sentiment to
     * the most positive
     */
    private <K> void printSentimentsSorted(Map<K, SentimentResult> sentiments) {
        ArrayList<Map.Entry<K, SentimentResult>> results = new ArrayList<>(sentiments.entrySet());
        Collections.sort(results, (a, b) -> a.getValue().average() - b.getValue().average() > 0 ? 1 : -1);
        for (Map.Entry<K, SentimentResult> result : results)
            System.out.printf(" - %s: %s%n", result.getKey().toString(), result.getValue().summary());
    }

    /**
     * run the sentiment analysis on a list of messages (grouped by some K) and store
     * the aggregated results by K in the result map.
     *
     * @param messages input messages for which sentiments should be computed
     * @param result   map holding the result
     */
    private <K> void computeSentiments(Map<K, List<Message>> messages, Map<K, SentimentResult> result) throws IOException {
        for (Map.Entry<K, List<Message>> messageList : messages.entrySet())
            if (messageList.getValue().size() >= 10) {
                //computing sentiments for all messages, grouping them by author
                System.out.printf("  - computing sentiments for %d messages%n", messageList.getValue().size());
                SentimentResult sentiment = new SentimentResult();
                for (Message msg : messageList.getValue()) {
                    sentiment.mergeWith(msg.getSentiment());
                }

                result.put(messageList.getKey(), sentiment);
            }
    }

    /**
     * run query from Github to get the most popular programming language from the
     * repository (as detected by Github)
     */
    private String getTopLanguage(GitHubClient github, Repository repo) throws IOException {
        RepositoryService repoService = new RepositoryService(github);
        Map<String, Long> languages = repoService.getLanguages(repo);
        String language = "";
        long maxLoc = 0;
        for (Map.Entry<String, Long> lang : languages.entrySet())
            if (lang.getValue() > maxLoc) {
                language = lang.getKey();
                maxLoc = lang.getValue();
            }
        return language;
    }


    /**
     * helper method to add data to a list inside a map
     *
     * @param map  map to be changed
     * @param key  key to identify entry where to add data
     * @param data data to be added
     * @param <K>  type of the keys
     * @param <D>  type of the entries in the value's list
     */
    private <K, D> void addToData(Map<K, List<D>> map, K key, D data) {
        List<D> values = map.getOrDefault(key, new ArrayList<>());
        values.add(data);
        map.put(key, values);
    }

}
