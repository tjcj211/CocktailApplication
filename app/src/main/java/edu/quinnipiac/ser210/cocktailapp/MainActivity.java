/*
 * Author: Timothy Carta
 * Date Created: 2/29/2020
 * Description: Allows the user to press a button that finds them a random drink.
 */

package edu.quinnipiac.ser210.cocktailapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private String url1 = "https://the-cocktail-db.p.rapidapi.com/random.php"; // the cocktail databases random
    private CocktailHandler cocktailHandler = new CocktailHandler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToIngredient(View view) { //Will be used in the future to allow searching by name
        Intent intent = new Intent(this, IngredientsSearch.class);
        startActivity(intent);
    }

    public void goToName(View view) {//Will be used in the future to allow searching by ingredient
        Intent intent = new Intent(this, NameSearch.class);
        startActivity(intent);
    }

    public void goToDrinkWithRandom(View view) { //Gets a random drink and displays it
        new FetchCocktail().execute();
    }


    //Accesses the cocktail db and starts a new intent with information about a drink
    private class FetchCocktail extends AsyncTask<String,Void,String[]> {


        @Override
        protected String[] doInBackground(String... strings) {
            HttpURLConnection urlConnection = null;
            String[] cocktail = {"", "", "", ""};

            try{
                URL url = new URL(url1);

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setRequestProperty("X-RapidAPI-Key","UygwA3LnI1mshAPcqbrTdu6rvUkxp1Kd1q6jsnETjeLq2t3LzS");

                urlConnection.connect();


                if (urlConnection.getInputStream() == null) {
                    Log.e("no connection", "no connection");
                    return null;
                }
                cocktail = getStringFromBuffer(
                        new BufferedReader(new InputStreamReader(urlConnection.getInputStream())));
                Log.d("cocktailName", cocktail[0]);
                Log.d("cocktailRecipe", cocktail[1]);
                Log.d("cocktailThumbnail", cocktail[2]);
                Log.d("cocktailIngredients", cocktail[3]);
            }catch (Exception e){
                return null;
            }finally {
                if(urlConnection != null)
                    urlConnection.disconnect();
            }

            return cocktail;
        }

        private String[] getStringFromBuffer(BufferedReader bufferedReader) throws Exception {
            StringBuffer buffer = new StringBuffer();
            String line;

            while((line = bufferedReader.readLine()) != null){
                buffer.append(line + '\n');

            }
            if (bufferedReader !=null){
                try{
                    bufferedReader.close();
                }catch (IOException e){
                    Log.e("MainActivity","Error" + e.getMessage());
                    return null;
                }
            }
            Log.d("Data", buffer.toString());
            String[] cocktailData = {"", "", "", ""};
            cocktailData[0] = cocktailHandler.getCocktailName(buffer.toString()); //Add all of the individual data to cocktailData
            cocktailData[1] = cocktailHandler.getCocktailInstructions(buffer.toString());
            cocktailData[2] = cocktailHandler.getCocktailImage(buffer.toString());
            cocktailData[3] = cocktailHandler.getCocktailIngredients(buffer.toString());
            return cocktailData;
        }

        @Override
        protected void onPostExecute(String[] result) { //Places all components of a drink and passes to CocktailDisplay
            if(result != null){
                Intent intent = new Intent(MainActivity.this,CocktailDisplay.class);
                intent.putExtra("name",result[0]);
                intent.putExtra("recipe", result[1]);
                intent.putExtra("image", result[2]);
                intent.putExtra("ingredients", result[3]);
                startActivity(intent);
            }

        }
    }
}
