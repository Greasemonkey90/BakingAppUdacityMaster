package com.example.bakingappudacity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.List;

public class StepsFragment extends Fragment {

    private List<Step> stepList;
    private ListView listView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Recipe currentRecipe = RecipePreferences.getRecipe(getContext());
        stepList = currentRecipe.recipeSteps;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.steps, container, false);
        listView = view.findViewById(R.id.steps_LV);
        AdapterForSteps adapter = new AdapterForSteps(getContext(), stepList);
        listView.setAdapter(adapter);
        return view;
    }


}