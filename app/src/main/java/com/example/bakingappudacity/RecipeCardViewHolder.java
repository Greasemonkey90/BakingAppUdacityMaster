package com.example.bakingappudacity;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecipeCardViewHolder extends RecyclerView.ViewHolder {

    private RecipeInterface recipeInterface;
    public ImageView recipeImage;
    public TextView recipeCard;
    public Recipe cardName;
    Context context;

    public RecipeCardViewHolder(@NonNull View itemView, RecipeInterface recipeInterface1, Context context) {
        super(itemView);
        recipeCard = itemView.findViewById(R.id.recipe_card_name);
        recipeImage = itemView.findViewById(R.id.recipe_IV);
        recipeInterface = recipeInterface1;
        this.context = context;
        itemView.setOnClickListener(getOnClickListener());
    }

    public void bind(Recipe recipe) {
        cardName = recipe;
        String recipeNameForImage = recipe.recipeName;
        recipeCard.setText(recipe.recipeName);
        switch (recipeNameForImage) {
            case ("Brownies"):
                recipeImage.setImageResource(R.drawable.brownie);
                break;
            case ("Nutella Pie"):
                recipeImage.setImageResource(R.drawable.nutella_pie);
                break;
            case ("Yellow Cake"):
                recipeImage.setImageResource(R.drawable.yellow_cake);
                break;
            case ("Cheesecake"):
                recipeImage.setImageResource(R.drawable.cheesecake);
            default:
                break;
        }
    }

    private View.OnClickListener getOnClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recipeInterface.onRecipeClicked(cardName);
                RecipePreferences.saveRecipe(context,cardName);

            }
        };
    }
}
