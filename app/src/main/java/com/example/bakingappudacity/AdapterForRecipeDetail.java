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

public class AdapterForRecipeDetail extends ArrayAdapter<Ingredient> {
        LayoutInflater layoutInflater;
        public List<Ingredient> theIngredients;

    public AdapterForRecipeDetail(@NonNull Context context, List<Ingredient>ingredientList) {
        super(context,0,ingredientList);
        theIngredients = ingredientList;
        layoutInflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            final ViewHolderForLV holder;
            if(convertView == null){
                convertView =layoutInflater.inflate(R.layout.list_item_for_list_view,parent,false);
                holder = new ViewHolderForLV();

                holder.ingredient = convertView.findViewById(R.id.ingredient_TV);
                holder.quantity = convertView.findViewById(R.id.quantity_TV);

                Ingredient ingredient = theIngredients.get(position);
                holder.ingredient.setText(ingredient.singleIng);
                holder.quantity.setText(ingredient.ingQuantity);
            }

        return convertView;
    }

    class ViewHolderForLV{
        TextView ingredient;
        TextView quantity;
    }
}
