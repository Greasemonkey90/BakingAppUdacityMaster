package com.example.bakingappudacity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class AdapterForIngredients extends ArrayAdapter<Ingredient> {
    LayoutInflater layoutInflater;
    public List<Ingredient> theIngredients;

    public AdapterForIngredients(@NonNull Context context, @NonNull List<Ingredient> ingredientList) {
        super(context, 0, ingredientList);
        theIngredients = ingredientList;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final ViewHolderForIngredients holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_item_for_ingredients, parent, false);
            holder = new ViewHolderForIngredients();

            holder.ingredient = convertView.findViewById(R.id.ingredient_TV);
            holder.quantity = convertView.findViewById(R.id.quantity_TV);
            holder.measure = convertView.findViewById(R.id.measure_TV);

            Ingredient ingredient = theIngredients.get(position);
            holder.ingredient.setText(ingredient.singleIng);
            holder.quantity.setText(ingredient.ingQuantity);
            holder.measure.setText(ingredient.ingMeasure);

        }

        return convertView;
    }

    static class ViewHolderForIngredients {
        TextView ingredient;
        TextView quantity;
        TextView measure;
    }
}
