/*
 * Author: Timothy Carta
 * Date Created: 2/29/2020
 * Description: Shows a drink to the user. It will show the name, thumbnail, ingredients, and instructions on how to create it.
 */

package edu.quinnipiac.ser210.cocktailapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.squareup.picasso.Picasso;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ShareActionProvider;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class CocktailDisplay extends AppCompatActivity {
    private final int REQUEST_CODE = 0;
    private ShareActionProvider provider;
    private String name;
    private String recipe;
    private String ingredients;
    private String url;
    private LinearLayout layout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Add toolbar
        setContentView(R.layout.activity_cocktail_display);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        layout = findViewById(R.id.layoutCocktailDisplay); //get reference to linearLayout

        name = (String) getIntent().getExtras().get("name"); //Get the name
        TextView nameTextView = (TextView) findViewById(R.id.txtName);
        nameTextView.setText(name);

        recipe = (String) getIntent().getExtras().get("recipe"); //Get the recipe
        TextView recipeTextView = (TextView) findViewById(R.id.txtRecipe);
        recipeTextView.setText(recipe);

        ingredients = (String) getIntent().getExtras().get("ingredients"); //Get the ingredients
        TextView ingredientsTextView = (TextView) findViewById(R.id.txtIngredients);
        ingredientsTextView.setText(ingredients);

        url = (String) getIntent().getExtras().get("image"); //Get the ingredients
        ImageView thumbnail = (ImageView) findViewById(R.id.imgCocktailThumbnail); //Get the thumbnail
        try {
            //Loading image using Picasso
            Picasso.get().load(url).into(thumbnail);
        } catch (Exception e) {
            Log.e("CocktailDisplay","Error" + e.getMessage());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        setBackgroundColor(data.getIntExtra("theme", Color.WHITE)); //gets the background color from settings
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem shareItem = menu.findItem(R.id.action_share);
        provider = (ShareActionProvider) MenuItemCompat.getActionProvider(shareItem);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, Settings.class);
            startActivityForResult(intent, REQUEST_CODE);
        }

        if (id == R.id.action_share) { //Share the cocktail
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, name);
            intent.putExtra(Intent.EXTRA_TEXT, ingredients);
            intent.putExtra(Intent.EXTRA_TEXT, recipe);
            provider.setShareIntent(intent);
        }

        if (id == R.id.action_help) { //say what the app is about
            Toast.makeText(getApplicationContext(),
                    "This app is meant to generate random drinks for you!\nIt uses 'The Cocktail DB' by theapiguy",
                    Toast.LENGTH_LONG).show();
        }

        return super.onOptionsItemSelected(item);
    }

    private void setBackgroundColor(int color) { //Allows the background color to be changed
        layout.setBackgroundColor(color);
    }
}
