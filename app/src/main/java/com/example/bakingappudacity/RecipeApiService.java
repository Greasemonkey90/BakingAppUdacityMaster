package com.example.bakingappudacity;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface RecipeApiService {
    @GET
    Call<List<Recipe>>getRecipes(@Url String url);

}
