package ca.ualberta.cs.lonelytwitter;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by czeto on 1/17/17.
 */
public abstract class Tweet implements Tweetable {
    private Date date;

    private String message;

    /**
     * Instantiates a new Tweet.
     * Takes in string variable for message and user set date.
     *
     * @param date    the date
     * @param message the message
     * @throws TweetTooLongException the tweet too long exception
     */
    public Tweet(Date date, String message) throws TweetTooLongException {
        this.date = date;
        this.setMessage(message);
    }

    /**
     * Instantiates a new Tweet.
     * Takes in string variable for message , set date based on current date.
     * @param message the message
     * @throws TweetTooLongException the tweet too long exception
     */
    public Tweet(String message) throws TweetTooLongException {
        this.setMessage(message);
        this.date =  new Date(); //current time and date
    }

    /**
     * Is the tweet important?
     *
     * @return the boolean
     */
    public abstract Boolean isImportant();

    public Date getDate() {
        return date;
    }

    /**
     * Sets date.
     *
     * @param date the date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }


    /**
     * Sets message.
     *
     * @param message the message
     * @throws TweetTooLongException the tweet too long exception
     */
    public void setMessage(String message) throws TweetTooLongException {
        if (message.length() > 144){
            throw new TweetTooLongException();
        }

        else{
            this.message = message;
        }
    }

    @Override

    /**
     * Returns string form of date and message combined.
     * @return Formatted string.
     */
    public String toString(){
        return date.toString() + " | " + message;
    }
}



