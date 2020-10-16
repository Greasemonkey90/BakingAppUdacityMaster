package com.example.bakingappudacity;

import android.os.Bundle;

import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.List;

public class RecipeDetailActivity extends AppCompatActivity {

    public List<Ingredient> ingredientList;
    public List<Step> stepList;
    private ListView theListView;
    public  Recipe currentRecipe;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPageAdapter viewPageAdapter;
    private TextView selectedTitle;
    public static final String PASSING_RECIPE = "the recipe details";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_layout);
        theListView = findViewById(R.id.ingredients_LV);
        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);
        selectedTitle = findViewById(R.id.selected_title);
        viewPageAdapter = new ViewPageAdapter(getSupportFragmentManager());
        viewPageAdapter.addFragment(new IngredientFragment(), "Ingredients");
        viewPageAdapter.addFragment(new StepsFragment(), "Steps");
        viewPager.setAdapter(viewPageAdapter);
        tabLayout.setupWithViewPager(viewPager);
        currentRecipe = getIntent().getParcelableExtra(PASSING_RECIPE);
        assert currentRecipe != null;
        stepList = currentRecipe.recipeSteps;
        ingredientList = currentRecipe.recipeIngredients;
        selectedTitle.setText(currentRecipe.recipeName);

    }
}