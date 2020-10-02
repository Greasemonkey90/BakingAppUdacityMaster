package com.example.bakingappudacity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Ingredient implements Parcelable {

    @SerializedName("quantity")
    public String ingQuantity;

    @SerializedName("measure")
    public String ingMeasure;

    @SerializedName("ingredient")
    public String singleIng;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.ingQuantity);
        dest.writeString(this.ingMeasure);
        dest.writeString(this.singleIng);
    }

    public Ingredient() {
    }

    protected Ingredient(Parcel in) {
        this.ingQuantity = in.readString();
        this.ingMeasure = in.readString();
        this.singleIng = in.readString();
    }

    public static final Parcelable.Creator<Ingredient> CREATOR = new Parcelable.Creator<Ingredient>() {
        @Override
        public Ingredient createFromParcel(Parcel source) {
            return new Ingredient(source);
        }

        @Override
        public Ingredient[] newArray(int size) {
            return new Ingredient[size];
        }
    };

    @Override
    public String toString() {
        return "Ingredient{" +
                "ingQuantity='" + ingQuantity + '\'' +
                ", ingMeasure='" + ingMeasure + '\'' +
                ", singleIng='" + singleIng + '\'' +
                '}';
    }
}
