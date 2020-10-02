package com.example.bakingappudacity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


import java.util.ArrayList;
import java.util.List;

public class Recipe implements Parcelable {

    @SerializedName("id")
    public int recipeId;

    @SerializedName("name")
    public String recipeName;

    @SerializedName("servings")
    public int servingSize;

    @SerializedName("image")
    public String recipeImageUrl;

    @SerializedName("ingredients")
    public List<Ingredient> recipeIngredients;

    @SerializedName("steps")
    public List<Step> recipeSteps;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.recipeId);
        dest.writeString(this.recipeName);
        dest.writeInt(this.servingSize);
        dest.writeString(this.recipeImageUrl);
        dest.writeList(this.recipeIngredients);
        dest.writeList(this.recipeSteps);
    }

    public Recipe() {
    }

    protected Recipe(Parcel in) {
        this.recipeId = in.readInt();
        this.recipeName = in.readString();
        this.servingSize = in.readInt();
        this.recipeImageUrl = in.readString();
        this.recipeIngredients = new ArrayList<Ingredient>();
        in.readList(this.recipeIngredients, Ingredient.class.getClassLoader());
        this.recipeSteps = new ArrayList<Step>();
        in.readList(this.recipeSteps, Step.class.getClassLoader());
    }

    public static final Parcelable.Creator<Recipe> CREATOR = new Parcelable.Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel source) {
            return new Recipe(source);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };

    @Override
    public String toString() {
        return "Recipe{" +
                "recipeId=" + recipeId +
                ", recipeName='" + recipeName + '\'' +
                ", servingSize=" + servingSize +
                ", recipeImageUrl='" + recipeImageUrl + '\'' +
                ", recipeIngredients=" + recipeIngredients +
                ", recipeSteps=" + recipeSteps +
                '}';
    }
}
