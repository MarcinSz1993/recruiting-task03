package com.marcinsz.recruitingtask03.exception;

public class GitHubUserNotExistException extends RuntimeException{
    public GitHubUserNotExistException(String username) {
        super("A user with username " + username + " does not exist on GitHub.");
    }
}
