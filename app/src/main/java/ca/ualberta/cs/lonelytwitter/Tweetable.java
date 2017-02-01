package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

/**
 * Created by czeto on 1/17/17.
 * Interface for tweets.
 */
public interface Tweetable {
    /**
     * Gets message.
     *
     * @return the message
     */
    public String getMessage();

    /**
     * Gets date.
     *
     * @return the date
     */
    public Date getDate();
}
