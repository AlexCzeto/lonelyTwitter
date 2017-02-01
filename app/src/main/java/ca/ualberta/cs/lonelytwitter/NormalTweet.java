package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

/**
 * Created by czeto on 1/17/17.
 */
public class NormalTweet extends Tweet {
    /**
     * Instantiates a new Normal tweet.
     * Takes in string variable for message and user set date.
     *
     * @param date    the date of the tweet
     * @param message the message
     * @throws TweetTooLongException the tweet too long exception
     */
    public NormalTweet(Date date, String message) throws TweetTooLongException {
        super(date, message);
    }

    /**
     * Instantiates a new Normal tweet.
     * Takes in string variable for message , set date based on current date.
     *
     * @param message the message
     * @throws TweetTooLongException the tweet too long exception
     */
    public NormalTweet(String message) throws TweetTooLongException {
        super(message);
    }

    /**
     * Determines if a tweet is important or not.
     * @return - will always return false.
     */
    public Boolean isImportant() {
        return Boolean.FALSE;
    }


}
