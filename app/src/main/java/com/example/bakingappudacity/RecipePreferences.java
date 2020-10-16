package com.example.bakingappudacity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class RecipePreferences {

    public static final String RECIPE_KEY = "passing recipe";
    public static final String INGREIDENT_LIST_KEY = "passing list";
    public static final String PUTTING_RECIPE = "current recipe";

    public static Recipe loadRecipes(Intent intent) {

        String jsonString = intent.getStringExtra(RECIPE_KEY);
        return new Gson().fromJson(jsonString, (Type) Recipe.class);
    }

    public static void saveIngredients(Context context, List<Ingredient> ingredientList) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(INGREIDENT_LIST_KEY, Context.MODE_PRIVATE);
        String convertingIngredients = new Gson().toJson(ingredientList);
        sharedPreferences.edit().putString(RECIPE_KEY, convertingIngredients).apply();
    }

    public static ArrayList<Ingredient> getSavedIngredients(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(INGREIDENT_LIST_KEY, Context.MODE_PRIVATE);
        String ingredients = sharedPreferences.getString(RECIPE_KEY, "");
        ArrayList<Ingredient> ingredientArrayList = new Gson().fromJson(ingredients, new TypeToken<ArrayList<Ingredient>>() {
        }.getType());
        return ingredientArrayList;
    }

    public static void saveRecipe(Context context, Recipe recipe) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(INGREIDENT_LIST_KEY, Context.MODE_PRIVATE);
        String currentRecipe = new Gson().toJson(recipe);
        sharedPreferences.edit().putString(PUTTING_RECIPE, currentRecipe).apply();

    }

    public static Recipe getRecipe(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(INGREIDENT_LIST_KEY, Context.MODE_PRIVATE);
        String currentRecipeString = sharedPreferences.getString(PUTTING_RECIPE, "");
        Recipe currentRecipe = new Gson().fromJson(currentRecipeString, Recipe.class);

        return currentRecipe;

    }
}
