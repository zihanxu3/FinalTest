package com.example.myfinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    /** final parameters.*/
    final protected int recipeAmount = 3;

    /**
     * user input.
     */
    protected String ingredients;

    protected String ingredientsURL;
    /**
     * edit text.
     */
    EditText ingredientInput;

    /**
     * cook button.
     */
    ImageButton cookButton;

    /**
     * set up error message.a
     */
    CharSequence text = "Invalid Inputs";
    int duration = Toast.LENGTH_LONG;

    /**
     * default logging tag.
     */
    protected static final String TAG = "RECIPE";

    /**
     * Request queue for our WEb API request;
     */
    protected static RequestQueue requestQueue;

    /**
     * First meal's Info.
     */
    protected String firstTitle;
    protected String firstImage;

    /**
     * Second meal's info.
     */
    protected String secondTitle;
    protected String secondImage;

    /**
     * Third meal's info.
     */
    protected String thirdTitle;
    protected String thirdImage;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set up a queue for our Volley Request
        requestQueue = Volley.newRequestQueue(this);

        // Load the main layout for the our activity
        setContentView(R.layout.activity_main);

        //Initialize all inputs
        ingredientInput = findViewById(R.id.editText);

        //set up button and disable it
        cookButton = findViewById(R.id.Cook);
        cookButton.setEnabled(false);

        //add Textwatcher to monitor user input
        ingredientInput.addTextChangedListener(watcher);

        //set button handler
        cookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ingredients = ingredientInput.getText().toString();
                String[] parts = ingredients.split(",");
                for (int i = 0; i < parts.length; i++) {
                    if (i == parts.length - 1) {
                        ingredientsURL = ingredientsURL + parts[i];
                    } else {
                        ingredientsURL = ingredientsURL + parts[i] + "%2C";
                    }
                }
                //We want to cast some exceptions
                try {
                    if (ingredientInput == null) {
                        Toast.makeText(MainActivity.this, text, duration).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, text, duration).show();
                }
                try {
                    startAPICall();
                } catch (Exception e) {
                    e.getStackTrace();
                }

                //Change to display activity.
                Intent intent = new Intent(MainActivity.this, Recipes.class);
                startActivity(intent);

                //change page
                /**
                 * Set the activity content from a layout resource.  The resource will be
                 * inflated, adding all top-level views to the activity.
                 *
                 * @param layoutResID Resource ID to be inflated.
                 *
                 * @see #setContentView(android.view.View)
                 * @see #setContentView(android.view.View, android.view.ViewGroup.LayoutParams)
                 */
                //setContentView(R.layout.page2);

            }
        });
    }
    /**
     * Make an API call.
     */
    protected void startAPICall() {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    "https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/findByIngredients?number=3&ingredients=" + ingredientsURL,
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(final JSONObject response) {
                            Log.d(TAG, response.toString());

                            //initiate Json Parser
                            JsonParser parser = new JsonParser();
                            JsonObject result = parser.parse(response.toString()).getAsJsonObject();
                            JsonArray recipes = result.getAsJsonArray("meals");

                            //Retrieve first meals' title, image
                            try {
                                JsonObject firstMeal = recipes.get(0).getAsJsonObject();
                                firstImage = firstMeal.get("image").getAsString();
                                firstTitle = firstMeal.get("title").getAsString();
                            } catch (Exception e) {
                                Log.d("Something goes wrong", "GG");
                            }
                            try {
                                JsonObject secondMeal = recipes.get(1).getAsJsonObject();
                                secondImage = secondMeal.get("image").getAsString();
                                secondTitle = secondMeal.get("title").getAsString();
                            } catch (Exception e) {
                                Log.d(TAG, "Ggggggg");
                            }
                            try {
                                JsonObject thirdMeal = recipes.get(2).getAsJsonObject();
                                thirdImage = thirdMeal.get("image").getAsString();
                                thirdTitle = thirdMeal.get("title").getAsString();
                            } catch (Exception e) {
                                Log.d(TAG, "Wo si le");
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(final VolleyError error) {
                    Log.d("ERROR", "hello?");
                    Log.w(TAG, error.toString());
                }
            })  {
                @Override
                public Map<String, String> getHeaders() {
                    Map<String, String> parameters = new HashMap<>();
                    parameters.put("X-RapidAPI-Key", "c7234139c1msh5bfe23b1dedb7dcp10fac2jsn5046d27005d6");
                    Log.d(TAG, parameters.toString());
                    return parameters;
                }
            };
            requestQueue.add(jsonObjectRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** check if user input are eligible */
    private boolean isInputEmpty(EditText edit) {
        return edit.getText().toString().length() == 0;
    }
    /** update ImageButton */
    private final TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}
