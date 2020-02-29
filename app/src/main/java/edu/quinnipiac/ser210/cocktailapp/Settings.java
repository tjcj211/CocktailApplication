/*
 * Author: Timothy Carta
 * Date Created: 2/29/2020
 * Description: Allows the user to set the background color of CocktailDisplay
 */

package edu.quinnipiac.ser210.cocktailapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

public class Settings extends AppCompatActivity {
    public static final int RESULT_CODE = 0;
    private Intent data = new Intent();
    private int theme = android.R.style.Theme_DeviceDefault;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    public void setDarkTheme(View view) {
        data.putExtra("theme", Color.LTGRAY);
        setResult(RESULT_CODE, data);
        finish();
    }

    public void setLightTheme(View view) {
        data.putExtra("theme", Color.WHITE);
        setResult(RESULT_CODE, data);
        finish();
    }
}
