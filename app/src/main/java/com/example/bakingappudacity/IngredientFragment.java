package com.example.bakingappudacity;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class IngredientFragment extends Fragment {

    private List<Ingredient> ingredientList = new ArrayList<>();
    private ListView listView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Recipe recipe = RecipePreferences.getRecipe(Objects.requireNonNull(getContext()));
        ingredientList = recipe.recipeIngredients;

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ingredients, container, false);
         listView = view.findViewById(R.id.ingredients_LV);
        AdapterForIngredients adapter = new AdapterForIngredients(Objects.requireNonNull(getContext()), ingredientList);
        listView.setAdapter(adapter);
        return view;
    }




}
