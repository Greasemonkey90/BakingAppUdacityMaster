package com.example.bakingappudacity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecipeCardAdapter extends RecyclerView.Adapter<RecipeCardViewHolder> {

    List<Recipe> recipeCards;
    RecipeInterface recipeInterface;

    public RecipeCardAdapter(List<Recipe> recipeCardsList, RecipeInterface recipeInterface1){
        recipeCards = recipeCardsList;
        recipeInterface = recipeInterface1;
    }

    @NonNull
    @Override
    public RecipeCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        int layoutForListItem = R.layout.recipe_card_list_item;
        View view = layoutInflater.inflate(layoutForListItem,parent,false);
        return new RecipeCardViewHolder(view,recipeInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeCardViewHolder holder, int position) {
        holder.bind(recipeCards.get(position));
    }

    @Override
    public int getItemCount() {
        return recipeCards.size();
    }

    public void setRecipeCards(List<Recipe> recipeCardList){
        recipeCards.clear();
        recipeCards.addAll(recipeCardList);
        notifyDataSetChanged();
    }
}
