package ca.ualberta.cs.lonelytwitter;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class EditTweetActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_tweet);

        Bundle extras = getIntent().getExtras();
        String tweetString = extras.getString("TWEET");

        Gson gson = new Gson();
        Type tweetType = new TypeToken<NormalTweet>(){}.getType();
        NormalTweet tweet = gson.fromJson(tweetString,tweetType);

        final TextView text = (TextView) findViewById(R.id.textView);

        text.setText(tweet.getMessage());

    }
}
