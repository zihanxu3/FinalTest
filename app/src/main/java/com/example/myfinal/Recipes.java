package com.example.myfinal;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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



import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;

public class Recipes extends MainActivity {
    /** Default logging tag for messages from the main activity. */
    private static final String TAG = "FinalProject";

    //Output image
    ImageView firstRecipe;
    ImageView secondRecipe;
    ImageView thirdRecipe;

    TextView recipeOne;
    TextView recipeTwo;
    TextView recipeThree;

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

        recipeOne = findViewById(R.id.recipe1);
        recipeTwo = findViewById(R.id.recipe2);
        recipeThree = findViewById(R.id.recipe3);

        firstRecipe = findViewById(R.id.recipeYi);
        secondRecipe = findViewById(R.id.recipeEr);
        thirdRecipe = findViewById(R.id.recipeSan);

        System.out.println("then" + firstTitle);
        if (firstTitle == null || secondTitle == null || thirdTitle == null) {
            Toast.makeText(Recipes.this, text, duration).show();
        }
        try {
            CharSequence one = "Name: " + firstTitle;
            CharSequence two = "Name: " + secondTitle;
            CharSequence three = "Name: " + thirdTitle;
            recipeOne.setText(one);
            recipeTwo.setText(two);
            recipeThree.setText(three);

            firstRecipe.setImageDrawable(LoadImageFromWebOperations(firstImage));
            secondRecipe.setImageDrawable(LoadImageFromWebOperations(secondImage));
            thirdRecipe.setImageDrawable(LoadImageFromWebOperations(thirdImage));
        } catch (Exception e) {
            Log.d(TAG, "Wut the hack??");
        }

        /*
         * Set up handlers for each button in our UI. These run when the buttons are clicked.
         */
        /*final ImageButton openFile = findViewById(R.id.openFile);
        openFile.setOnClickListener(v -> {
            Log.d(TAG, "Open file button clicked");
            startOpenFile();
        });*/
    }
    public static Drawable LoadImageFromWebOperations(String url) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            return d;
        } catch (Exception e) {
            return null;
        }
    }
}