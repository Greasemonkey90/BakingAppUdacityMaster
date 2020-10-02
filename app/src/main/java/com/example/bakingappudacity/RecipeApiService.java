package com.example.bakingappudacity;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface RecipeApiService {
    @GET
    Call<List<Recipe>>getRecipes(@Url String url);

    @GET
    Call<List<Ingredient>> getIngredients(@Url String url);

}
