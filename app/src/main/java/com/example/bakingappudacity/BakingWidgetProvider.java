package com.example.bakingappudacity;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import java.util.ArrayList;
import java.util.List;

public class BakingWidgetProvider extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        for (int appWidgetId : appWidgetIds) {

        }
        super.onUpdate(context, appWidgetManager, appWidgetIds);

    }

    @Override
    public void onReceive(Context context, Intent intent) {

        Recipe currentRecipe = RecipePreferences.loadRecipes(intent);

        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_list_view);
        int[] appWidgetIds = AppWidgetManager.getInstance(context).getAppWidgetIds
                (new ComponentName(context, BakingWidgetProvider.class));
        for (int appWidgetId : appWidgetIds) {
            String title = context.getString(R.string.no_recipes_found);
            if (currentRecipe != null) {
                title = currentRecipe.recipeName;
                List<Ingredient> ingredientList = currentRecipe.recipeIngredients;
                RecipePreferences.saveIngredients(context, ingredientList);
                Intent intentService = new Intent(context, WidgetRemoteService.class);
                remoteViews.setRemoteAdapter(R.id.widget_LV, intentService);
            } else {
                RecipePreferences.saveIngredients(context, new ArrayList<>());
            }

            remoteViews.setTextViewText(R.id.widget_recipe_name, title);
            Intent mainActivityIntent = new Intent(context, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, mainActivityIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            remoteViews.setOnClickPendingIntent(R.id.widget_recipe_name, pendingIntent);

            AppWidgetManager.getInstance(context).updateAppWidget(appWidgetId, remoteViews);
            AppWidgetManager.getInstance(context).notifyAppWidgetViewDataChanged(appWidgetIds, R.id.widget_LV);
        }
        super.onReceive(context, intent);

    }

}
