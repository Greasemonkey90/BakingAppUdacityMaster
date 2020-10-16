package com.example.bakingappudacity;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.util.ArrayList;
import java.util.List;


public class WidgetRemoteService extends RemoteViewsService {

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {

        ArrayList<Ingredient> ingredientArrayList = RecipePreferences.getSavedIngredients(getApplicationContext());

        return new WidgetViewAdapter(this.getApplicationContext(), ingredientArrayList);
    }

    static class WidgetViewAdapter implements RemoteViewsFactory {
        List<Ingredient> ingredientList;
        Context mContext;

        public WidgetViewAdapter(Context context, ArrayList<Ingredient> ingredientList) {
            mContext = context;
            this.ingredientList = ingredientList;

        }

        @Override
        public void onCreate() {

        }

        @Override
        public void onDataSetChanged() {
            ArrayList<Ingredient> ingredientArrayList = RecipePreferences.getSavedIngredients(mContext);
            ingredientList = ingredientArrayList;
        }

        @Override
        public void onDestroy() {

        }

        @Override
        public int getCount() {
            if (ingredientList != null) {
                return ingredientList.size();
            } else return 0;

        }

        @Override
        public RemoteViews getViewAt(int position) {
            RemoteViews remoteViews = new RemoteViews(mContext.getPackageName(), R.layout.widget_list_item);
            remoteViews.setTextViewText(R.id.widget_recipe_ing, ingredientList.get(position).singleIng);

            return remoteViews;
        }

        @Override
        public RemoteViews getLoadingView() {
            return null;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

    }
}
