package com.example.bakingappudacity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecipeCardViewHolder extends RecyclerView.ViewHolder {

    private RecipeInterface recipeInterface;
    public TextView recipeCard;
    public Recipe cardName;

    public RecipeCardViewHolder(@NonNull View itemView,RecipeInterface recipeInterface1) {
        super(itemView);
        recipeCard = itemView.findViewById(R.id.recipe_card_name);
        recipeInterface = recipeInterface1;
        itemView.setOnClickListener(getOnClickListener());
    }

    public void bind(Recipe recipe){
        cardName = recipe;
        recipeCard.setText(recipe.recipeName);

    }

    private View.OnClickListener getOnClickListener(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recipeInterface.onRecipeClicked(cardName);
                Log.v("VIEWHOLDER CLASS","Recipe clicked: " +cardName);
            }
        };
    }
}
