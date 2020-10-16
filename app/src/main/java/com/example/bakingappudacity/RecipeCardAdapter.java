package com.example.bakingappudacity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import static com.example.bakingappudacity.RecipePreferences.RECIPE_KEY;

public class RecipeCardAdapter extends RecyclerView.Adapter<RecipeCardViewHolder> {

    List<Recipe> recipeCards;
    RecipeInterface recipeInterface;
    Context mContext;

    public RecipeCardAdapter(List<Recipe> recipeCardsList, RecipeInterface recipeInterface1, Context context) {
        recipeCards = recipeCardsList;
        recipeInterface = recipeInterface1;
        mContext = context;
    }

    @NonNull
    @Override
    public RecipeCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        int layoutForListItem = R.layout.recipe_card_list_item;
        View view = layoutInflater.inflate(layoutForListItem, parent, false);
        return new RecipeCardViewHolder(view, recipeInterface,mContext);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeCardViewHolder holder, int position) {
        holder.bind(recipeCards.get(position));
        holder.recipeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gson gson = new GsonBuilder().serializeNulls().create();

                Recipe recipeTest = recipeCards.get(position);

                String parseToJson = gson.toJson(recipeTest);

                Intent intent = new Intent("android.appwidget.action.APPWIDGET_UPDATE");
                intent.setComponent(new ComponentName(mContext, BakingWidgetProvider.class));

                intent.putExtra(RECIPE_KEY, parseToJson);
                mContext.sendBroadcast(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return recipeCards.size();
    }

}
