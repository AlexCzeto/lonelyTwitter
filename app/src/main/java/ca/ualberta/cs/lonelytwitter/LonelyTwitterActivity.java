package ca.ualberta.cs.lonelytwitter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * This is the main view class of the project. <br> In this class, user interaction and
 * file manipulation is performed.
 * All files are in the form of "json" files that are store in Emulator's accessable files
 * <pre>
 *      pre-formated text : <br>
 *          File Explorer -> data -> ca.ualberta.cs.lonelyTwitter -> files -> file.sav
 *  </pre>
 * <code> begin <br>
 * some pseudo code <br>
 * end.</code>
 * The file name is indicated in the &nbsp &nbsp FILENAME constant.
 * <ul>
 * <li>item 1</li>
 * <li>item 2</li>
 * <li>item 3</li>
 * <p>
 * </ul>
 * <ol>
 * <li>item 1</li>
 * <li>item 2</li>
 * <li>item 3</li>
 * <p>
 * </ol>
 *
 * @author czeto
 * @version 1.0
 * @see Tweet
 * @since 0.5
 */
public class LonelyTwitterActivity extends Activity {
	/**
	 * The file that all tweets are save into. The format of the file is JSON.
	 * @see #loadFromFile()
	 * @see #saveInFile()
	 */

	private static final String FILENAME = "file.sav";
	private EditText bodyText;
	private enum TweetListOrdering{
        /**
         * Date ascending tweet list ordering.
         */
        DATE_ASCENDING, /**
         * Date descending tweet list ordering.
         */
        DATE_DESCENDING , /**
         * Text ascending tweet list ordering.
         */
        TEXT_ASCENDING, /**
         * Text descending tweet list ordering.
         */
        TEXT_DESCENDING}
	private ListView oldTweetsList;
	private ArrayList<Tweet> tweetList;
	private ArrayAdapter<Tweet> adapter;

	/** Called when the activity is first created.
     * Gets id's of clear and save button.
     * Sets up listener for save button and clear button
     * */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		bodyText = (EditText) findViewById(R.id.body);
		Button saveButton = (Button) findViewById(R.id.save);
		Button clearButton = (Button) findViewById(R.id.clear);
		oldTweetsList = (ListView) findViewById(R.id.oldTweetsList);


		saveButton.setOnClickListener(new View.OnClickListener() {

            /**
             * @param v
             * Saves tweet into tweet list when save button clicked.
             */
			public void onClick(View v) {
				setResult(RESULT_OK);
				String text = bodyText.getText().toString();
				text = trimExtraSpaces(text);

                Tweet tweet = null;
                try {
                    tweet = new NormalTweet(text);
                } catch (TweetTooLongException e) {
                    throw new RuntimeException();
                }
                tweetList.add(tweet);

				adapter.notifyDataSetChanged();

				saveInFile();

			}
		});


        clearButton.setOnClickListener(new View.OnClickListener(){
            /**
             * Clear tweet list and delete file.
             * @param v
             */
            public void onClick(View v) {
                setResult(RESULT_OK);
                tweetList.clear();
                adapter.notifyDataSetChanged();
                deleteFile(FILENAME);

            }

        });
	}

	@Override
    /**
     * Load tweets from existing file and create adapter
     */
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		loadFromFile();
		adapter = new ArrayAdapter<Tweet>(this,
				R.layout.list_item, tweetList);
		oldTweetsList.setAdapter(adapter);
	}
	/**
	 * Loads tweets from specified file
	 * @throws TweetTooLongException if the text is too long
	 * @exception FileNotFoundException if file is not created/
	 */
	private void loadFromFile() {
		try {
			FileInputStream fis = openFileInput(FILENAME);
			BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();
            // Taken from http://stackoverflow.com/questions/27014417/how-to-use-gson-to-convert-json-to-arraylist-if-the-list-contain-different-class
            // 2017-01-24 18:19
            Type listType = new TypeToken<ArrayList<NormalTweet>>(){}.getType();
            tweetList = gson.fromJson(in,listType);

		} catch (FileNotFoundException e) {
            tweetList = new ArrayList<Tweet>();
		} catch (IOException e) {
			// TODO FIX
            throw new RuntimeException();
		}

	}

	/**
	 * Trims extra spaces using regular expression
	 * @param inputString string that needs to be cleared of extra spaces
	 * @return resulting string
     */
	private String trimExtraSpaces(String inputString){
		inputString = inputString.replace("\\s+"," ");
		return inputString;
	}

	/**
	 * This method sorts the tweets list and refreshed the adapter.
	 * @param ordering order to be used
     */

	private void sortTweetListItems(TweetListOrdering ordering){

	}

	/**
	 * Saves a tweet to a specified file in JSON format.
	 * @throws FileNotFoundException if file folder does not exist
	 */

	private void saveInFile() {
		try {
			FileOutputStream fos = openFileOutput(FILENAME,
					Context.MODE_PRIVATE);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

            Gson gson = new Gson();
            gson.toJson(tweetList,out);
            out.flush();

			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Handle the Exception properly later
			throw new RuntimeException();
		} catch (IOException e) {
            // TODO Handle the Exception properly later
            throw new RuntimeException();
		}
	}
}