package edu.cmu.cs.cs214.rec13.util;

import edu.cmu.cs.cs214.rec13.ServerInfo;

import java.io.Serializable;
import java.util.*;

/**
 * A container for aggregate tasks
 */
public class TaskList implements Serializable {
    private final Map<ServerInfo, List<String>> tasks;

    private TaskList(Map<ServerInfo, List<String>> tasks) {
        this.tasks = tasks;
    }

    /**
     * Gets servers.
     *
     * @return the servers
     */
    public Set<ServerInfo> getServers() {
        return Collections.unmodifiableSet(tasks.keySet());
    }

    /**
     * Gets files for server.
     *
     * @param server the server
     * @return the files for server
     */
    public List<String> getFilesForServer(ServerInfo server) {
        return tasks.getOrDefault(server, Collections.emptyList());
    }

    /**
     * The Builder type for task list.
     */
    public static class Builder {
        private Map<ServerInfo, List<String>> tasks = new HashMap<>();
        private List<String> currentList = null;

        /**
         * begins adding files for a specific server.
         *
         * @param server the server
         * @return the builder
         */
        public Builder forServer(ServerInfo server) {
            currentList = tasks.getOrDefault(server, new ArrayList<>());
            tasks.put(server, currentList);
            return this;
        }

        /**
         * adds file for the current server.
         *
         * @param filename the filename
         * @return the builder
         */
        public Builder addTask(String filename) {
            currentList.add(filename);
            return this;
        }

        /**
         * Build task list.
         *
         * @return the task list
         */
        public TaskList build() {
            TaskList result = new TaskList(tasks);
            tasks = null;
            return result;
        }
    }
}
