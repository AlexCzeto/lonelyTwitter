package ca.ualberta.cs.lonelytwitter;

/**
 * Created by czeto on 1/17/17.
 * Tweet too long is thrown when a tweet's character count exceeds acceptable limits.
 */
public class TweetTooLongException extends Exception {
    /**
     * Instantiates a new Tweet too long exception.
     */
    public TweetTooLongException() {
    }

    /**
     * Instantiates a new Tweet too long exception.
     *
     * @param detailMessage the detail message
     */
    public TweetTooLongException(String detailMessage) {
        super(detailMessage);
    }
}
