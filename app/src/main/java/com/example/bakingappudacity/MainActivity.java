package com.example.bakingappudacity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView recipeCardRV;
    private RecipeCardAdapter adapter;
    private List<Recipe> recipeCardsList;
    private RecipeApiService recipeApiService;
    Recipe currentRecipe;
    Context context;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recipeCardRV = findViewById(R.id.recipe_card_RV);
        context = this;
        final RetrofitInstance retrofitInstance = new RetrofitInstance();
        recipeApiService = retrofitInstance.getRetrofit().create(RecipeApiService.class);
        final Call<List<Recipe>> responseCall = recipeApiService.getRecipes("https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json");
        responseCall.enqueue(getResponseCallback());
    }

    private Callback<List<Recipe>> getResponseCallback(){
        return new Callback<List<Recipe>>(){

            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                if(response.isSuccessful()){
                    List<Recipe> recipes = response.body();
                    if(recipes != null){
                        recipeCardsList = recipes;
                        initAdapter(recipes);
                        Log.v("MAIN RECIPE", "Recipe response = " + response.body().toString());
                    }
                }

            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
                Log.v("MAIN ACTIVITY","Retrofit error");

            }
        };
    }

    private void initAdapter(List<Recipe> recipes){
        adapter = new RecipeCardAdapter(recipes,getInterface());
        recipeCardRV.setAdapter(adapter);
        recipeCardRV.setLayoutManager(new LinearLayoutManager(this));

    }

    private RecipeInterface getInterface() {
        return new RecipeInterface() {
            @Override
            public void onRecipeClicked(Recipe theRecipe) {
                currentRecipe = theRecipe;
                Intent intent = new Intent(context,RecipeDetailActivity.class);
                intent.putExtra("TESTING",theRecipe);
                startActivity(intent);
            }
        };

    }

}