/*
 * Author: Timothy Carta
 * Date Created: 2/29/2020
 * Description: This class parses the JSON file to find various information about the drink.
 */

package edu.quinnipiac.ser210.cocktailapp;

import org.json.JSONException;
import org.json.JSONObject;

public class CocktailHandler {

    public String getCocktailName(String cocktailJsonStr) throws JSONException {
        JSONObject cocktailJSONObj = new JSONObject(cocktailJsonStr);
        JSONObject drinksJSONObj = cocktailJSONObj.getJSONArray("drinks").getJSONObject(0);
        return drinksJSONObj.getString("strDrink");
    }

    public String getCocktailInstructions(String cocktailJsonStr) throws JSONException {
        JSONObject cocktailJSONObj = new JSONObject(cocktailJsonStr);
        JSONObject drinksJSONObj = cocktailJSONObj.getJSONArray("drinks").getJSONObject(0);
        return drinksJSONObj.getString("strInstructions");
    }

    public String getCocktailImage(String cocktailJsonStr) throws JSONException {
        JSONObject cocktailJSONObj = new JSONObject(cocktailJsonStr);
        JSONObject drinksJSONObj = cocktailJSONObj.getJSONArray("drinks").getJSONObject(0);
        return drinksJSONObj.getString("strDrinkThumb");
    }

    public String getCocktailIngredients(String cocktailJsonStr) throws JSONException {
        JSONObject cocktailJSONObj = new JSONObject(cocktailJsonStr);
        JSONObject drinksJSONObj = cocktailJSONObj.getJSONArray("drinks").getJSONObject(0);
        //Add all ingredients
        String ingredients  = drinksJSONObj.getString("strMeasure1") + " " + drinksJSONObj.getString("strIngredient1") +
                ", " + drinksJSONObj.getString("strMeasure2") + " " + drinksJSONObj.getString("strIngredient2") +
                ", " + drinksJSONObj.getString("strMeasure3") + " " + drinksJSONObj.getString("strIngredient3")+
                ", " + drinksJSONObj.getString("strMeasure4") + " " + drinksJSONObj.getString("strIngredient4")+
                ", " + drinksJSONObj.getString("strMeasure5") + " " + drinksJSONObj.getString("strIngredient5")+
                ", " + drinksJSONObj.getString("strMeasure6") + " " + drinksJSONObj.getString("strIngredient6")+
                ", " + drinksJSONObj.getString("strMeasure7") + " " + drinksJSONObj.getString("strIngredient7")+
                ", " + drinksJSONObj.getString("strMeasure8") + " " + drinksJSONObj.getString("strIngredient8")+
                ", " + drinksJSONObj.getString("strMeasure9") + " " + drinksJSONObj.getString("strIngredient9")+
                ", " + drinksJSONObj.getString("strMeasure10") + " " + drinksJSONObj.getString("strIngredient10")+
                ", " + drinksJSONObj.getString("strMeasure11") + " " + drinksJSONObj.getString("strIngredient11")+
                ", " + drinksJSONObj.getString("strMeasure12") + " " + drinksJSONObj.getString("strIngredient12")+
                ", " + drinksJSONObj.getString("strMeasure13") + " " + drinksJSONObj.getString("strIngredient13")+
                ", " + drinksJSONObj.getString("strMeasure14") + " " + drinksJSONObj.getString("strIngredient14")+
                ", " + drinksJSONObj.getString("strMeasure15") + " " + drinksJSONObj.getString("strIngredient15");

        ingredients = ingredients.substring(0,ingredients.indexOf("null")-2); //Cuts off all of the null values
        return ingredients;
    }
}
