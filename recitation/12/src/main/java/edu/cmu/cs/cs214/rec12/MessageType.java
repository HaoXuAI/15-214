package edu.cmu.cs.cs214.rec12;

/**
 * The type of message to be sent to the server
 */
public enum MessageType {
    /**
     * Indicates that the message is a fetch request. Must be followed by a String object
     * that is the path to the file on the server. The contents of the file should be written back in bytes.
     */
    FETCH,
    /**
     * Indicates that the message is an exec request. Must be followed by a Function<FileInputStream, ?> object
     * that is the task to be performed. The result of the operation should be written back as an object.
     */
    EXEC
}