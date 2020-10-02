package com.example.bakingappudacity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeDetailActivity extends AppCompatActivity {

    private RecipeApiService recipeApiService;
    private List<Ingredient> ingredientList;
    private List<Step> stepList;
    private ListView theListView;
    private Recipe currentRecipe;
    private Ingredient currentIng;
    private AdapterForRecipeDetail adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ingredients_and_steps);
        theListView = findViewById(R.id.ingAndStepsLV);
        final RetrofitInstance retrofitInstance = new RetrofitInstance();
        recipeApiService = retrofitInstance.getRetrofit().create(RecipeApiService.class);
        currentRecipe = getIntent().getParcelableExtra("TESTING");
        ingredientList = currentRecipe.recipeIngredients;
        for(int i = 0; i<ingredientList.size();i++){
            currentIng = ingredientList.get(i);
            initAdapter(ingredientList);
        }
        //final Call<List<Ingredient>> responseCall = recipeApiService.getIngredients("https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json");
        //responseCall.enqueue(getResponseCallback());

    }

//    private Callback<List<Ingredient>> getResponseCallback() {
//        return new Callback<List<Ingredient>>() {
//
//            @Override
//            public void onResponse(Call<List<Ingredient>> call, Response<List<Ingredient>> response) {
//                if (response.isSuccessful()) {
//                    List<Ingredient> ingredients = response.body();
//                    if (ingredients != null) {
//                                initAdapter(ingredientList);
//                        for (currentRecipe.recipeIngredients:
//                             ) {
//
//                        }
//                                Log.v("Ingredients ACT", "Ingredients response = " + response.body().toString());
//                            }
//
//                    }
//                }
//
//                @Override
//                public void onFailure (Call < List < Ingredient >> call, Throwable t){
//                    Log.v("MAIN ACTIVITY", "Retrofit error");
//
//                }
//            };
//        }

        private void initAdapter (List < Ingredient > theIngList) {
            //ingredientList = theIngList;
            adapter = new AdapterForRecipeDetail(this,theIngList);
            theListView.setAdapter(adapter);
        }
    }

