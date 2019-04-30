package com.example.myfinal;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;

public class Recipes extends MainActivity {
    /** Default logging tag for messages from the main activity. */
    private static final String TAG = "RECIPE";

    //Output image
    ImageView firstRecipe;
    ImageView secondRecipe;
    ImageView thirdRecipe;

    protected TextView recipeOne;
    protected TextView fmnnn;
    protected TextView like1;
    protected TextView recipeTwo;
    protected TextView smnnn;
    protected TextView like2;
    protected TextView recipeThree;
    protected TextView tmnnn;
    protected TextView like3;

    protected Button backBut;

    // error message
    CharSequence text = "Request failed, please uninstall this app";
    int duration = Toast.LENGTH_LONG;

    /**
     * Run when our activity comes into view.
     *
     * @param savedInstanceState state that was saved by the activity last time it was paused
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        requestQueue = Volley.newRequestQueue(this);

        super.onCreate(savedInstanceState);

        // Load the main layout for our activity
        setContentView(R.layout.page2);


        Intent intent = getIntent();
        firstTitle = intent.getStringExtra("firstT");
        firstImage = intent.getStringExtra("firstI");
        fmn = intent.getStringExtra("fmnn");
        likeOne = intent.getIntExtra("l", -1);
        String likeOn = Integer.toString(likeOne);
        secondTitle = intent.getStringExtra("secondT");
        secondImage = intent.getStringExtra("secondI");
        smn = intent.getStringExtra("smnn");
        likeTwo = intent.getIntExtra("ll", -1);
        String likeTw = Integer.toString(likeTwo);
        thirdTitle = intent.getStringExtra("thirdT");
        thirdImage = intent.getStringExtra("thirdI");
        tmn = intent.getStringExtra("tmnn");
        likeThree = intent.getIntExtra("lll", -1);
        String likeTh = Integer.toString(likeThree);

        recipeOne = findViewById(R.id.recipe1);
        fmnnn = findViewById(R.id.fmn);
        like1 = findViewById(R.id.likeO);
        recipeTwo = findViewById(R.id.recipe2);
        smnnn = findViewById(R.id.fmn2);
        like2 = findViewById(R.id.likeTw);
        recipeThree = findViewById(R.id.recipe3);
        tmnnn = findViewById(R.id.fmn3);
        like3 = findViewById(R.id.likeTh);

        firstRecipe = findViewById(R.id.recipeYi);
        secondRecipe = findViewById(R.id.recipeEr);
        thirdRecipe = findViewById(R.id.recipeSan);

        backBut = findViewById(R.id.but);

        // Make our missed ingredients scrollable
        fmnnn.setMovementMethod(new ScrollingMovementMethod());
        smnnn.setMovementMethod(new ScrollingMovementMethod());
        tmnnn.setMovementMethod(new ScrollingMovementMethod());

        //System.out.println("then" + firstTitle);
        if (firstTitle == null || secondTitle == null || thirdTitle == null) {
            Toast.makeText(Recipes.this, text, duration).show();
        }
        try {
            CharSequence one = "Name: " + firstTitle;
            CharSequence two = "Name: " + secondTitle;
            CharSequence three = "Name: " + thirdTitle;
            CharSequence un = "Likes: " + likeOn;
            CharSequence deux = "Likes: " + likeTw;
            CharSequence trois = "Likes: " + likeTh;
            recipeOne.setText(one);
            recipeTwo.setText(two);
            recipeThree.setText(three);
            fmnnn.setText(fmn);
            smnnn.setText(smn);
            tmnnn.setText(tmn);
            like1.setText(un);
            like2.setText(deux);
            like3.setText(trois);

            Picasso.with(this).load(firstImage)
                    .into(firstRecipe);

            Picasso.with(this).load(secondImage)
                    .into(secondRecipe);

            Picasso.with(this).load(thirdImage)
                    .into(thirdRecipe);


        } catch (Exception e) {
            Log.d(TAG, "Wut the hack??");
        }

        backBut.setOnClickListener(v -> {
            Log.d(TAG, "Back button clicked");
            Intent intention = new Intent(this, MainActivity.class);
            startActivity(intention);
        });
    }
}