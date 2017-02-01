package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

/**
 * Created by czeto on 1/17/17.
 * This class is a subclass of Tweet. This class is used for tweets that are deemed important.
 */
public class ImportantTweet extends Tweet {
    /**
     * Instantiates a new Important tweet.
     *
     * @param date    the date
     * @param message the message
     * @throws TweetTooLongException the tweet too long exception
     */
    public ImportantTweet(Date date, String message) throws TweetTooLongException {
        super(date, message);
    }

    /**
     * Instantiates a new Important tweet.
     * Takes in string variable for message , set date based on current date.
     *
     * @param message the message
     * @throws TweetTooLongException the tweet too long exception
     */
    public ImportantTweet(String message) throws TweetTooLongException {
        super(message);
    }

    /**
     * Returns whether the tweet is deemed importatn
     * @return - Will always return true
     */
    public Boolean isImportant(){
        return Boolean.TRUE;
    }
    @Override

    /**
     * Returns text of tweet.
     * Adds exclamation to the end of the message because it's important
     */
    public String getMessage(){
        return super.getMessage() + "!!!";
    }
}
