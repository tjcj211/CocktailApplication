package edu.quinnipiac.ser210.cocktailapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class CocktailDisplayFragment extends Fragment {
    private String name;
    private String recipe;
    private String ingredients;
    private String url;

    public CocktailDisplayFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cocktail_display, container, false);

        name = (String) getArguments().getString("name"); //Get the name
        TextView nameTextView = (TextView) view.findViewById(R.id.txtName);
        nameTextView.setText(name);

        recipe = (String) getArguments().getString("recipe"); //Get the recipe
        TextView recipeTextView = (TextView) view.findViewById(R.id.txtRecipe);
        recipeTextView.setText(recipe);

        ingredients = (String) getArguments().getString("ingredients"); //Get the ingredients
        TextView ingredientsTextView = (TextView) view.findViewById(R.id.txtIngredients);
        ingredientsTextView.setText(ingredients);

        url = (String) getArguments().getString("image"); //Get the ingredients
        ImageView thumbnail = (ImageView) view.findViewById(R.id.imgCocktailThumbnail); //Get the thumbnail
        try {
            //Loading image using Picasso
            Picasso.get().load(url).into(thumbnail);
        } catch (Exception e) {
            Log.e("CocktailDisplay","Error" + e.getMessage());
        }

        // Inflate the layout for this fragment
        return view;
    }

    public String getName() {
        return name;
    }
}
