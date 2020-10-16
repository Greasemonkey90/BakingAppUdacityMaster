package com.example.bakingappudacity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.IdlingResource;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    RecyclerView recipeCardRV;
    private RecipeCardAdapter adapter;
    private RecipeApiService recipeApiService;
    Recipe currentRecipe;
    Context context;
    ProgressBar progressBar;
    TextView errorMessage;
    @Nullable
    private SimpleIdlingResource idlingResource;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recipeCardRV = findViewById(R.id.recipe_card_RV);
        context = this;
        progressBar = findViewById(R.id.loading_bar);
        errorMessage = findViewById(R.id.error_message);
        final RetrofitInstance retrofitInstance = new RetrofitInstance();
        recipeApiService = retrofitInstance.getRetrofit().create(RecipeApiService.class);
        final Call<List<Recipe>> responseCall = recipeApiService.getRecipes("https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json");
        responseCall.enqueue(getResponseCallback());
        getIdlingResource();
    }

    private Callback<List<Recipe>> getResponseCallback() {

        return new Callback<List<Recipe>>() {

            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                if (response.isSuccessful()) {
                    List<Recipe> recipes = response.body();
                    if (recipes != null) {
                        initAdapter(recipes);
                        errorMessage.setVisibility(View.GONE);
                        progressBar.setVisibility(View.GONE);
                    }
                }

            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
                Log.v("MAIN ACTIVITY", "Retrofit error");
                errorMessage.setText(context.getString(R.string.no_internet));
                errorMessage.setVisibility(View.GONE);
                errorMessage.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);

            }
        };

    }

    private void initAdapter(List<Recipe> recipes) {
        adapter = new RecipeCardAdapter(recipes, getInterface(), context);
        recipeCardRV.setAdapter(adapter);
        recipeCardRV.setLayoutManager(new LinearLayoutManager(this));

    }

    private RecipeInterface getInterface() {
        return new RecipeInterface() {
            @Override
            public void onRecipeClicked(Recipe theRecipe) {
                currentRecipe = theRecipe;
                Intent intent = new Intent(context, RecipeDetailActivity.class);
                intent.putExtra(RecipeDetailActivity.PASSING_RECIPE, theRecipe);
                startActivity(intent);
            }
        };


    }

    @VisibleForTesting
    @NonNull
    public IdlingResource getIdlingResource() {
        if (idlingResource == null) {
            idlingResource = new SimpleIdlingResource();
        }
        return idlingResource;
    }

}