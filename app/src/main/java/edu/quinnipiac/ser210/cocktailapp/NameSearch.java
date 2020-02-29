package edu.quinnipiac.ser210.cocktailapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class NameSearch extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_search);
    }

    public void goToDrinkWithName(View view) {
        Intent intent = new Intent(this, CocktailDisplay.class);
        startActivity(intent);
    }

}
