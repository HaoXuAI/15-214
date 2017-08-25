package edu.cmu.cs.cs214.hw6.sequential.sentiment;

import org.eclipse.egit.github.core.IRepositoryIdProvider;

/**
 * Represents a github repository
 */
public class Repository implements IRepositoryIdProvider {

    private final String org;
    private final String repository;

    public Repository(String org, String repository) {
        this.org = org;
        this.repository = repository;
    }

    public String getOrg() {
        return org;
    }

    public String getRepository() {
        return repository;
    }

    public String getIdentifier() {
        return org + "/" + repository;
    }


    @Override
    public String generateId() {
        return getIdentifier();
    }
}
