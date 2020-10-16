package com.example.bakingappudacity;

import android.content.Context;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.google.gson.annotations.SerializedName;

public class Step implements Parcelable {

    @SerializedName("id")
    public int stepId;

    @SerializedName("shortDescription")
    public String shortDesc;

    @SerializedName("description")
    public String description;

    @SerializedName("videoURL")
    public String videoUrl;

    @SerializedName("thumbnailURL")
    public String thumbnailUrl;

    private MediaSource mediaSource;

    public MediaSource getMediaSource(Context context) {
        if(mediaSource == null){
            String userAgent = Util.getUserAgent(context, "BakingAppUdacity");
            DefaultDataSourceFactory defaultDataSourceFactory = new DefaultDataSourceFactory(context,userAgent);
           mediaSource = new ExtractorMediaSource.Factory(defaultDataSourceFactory).createMediaSource(
                    Uri.parse(videoUrl));
            //mediaSource = mediaSource1;
        }
        return mediaSource;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.stepId);
        dest.writeString(this.shortDesc);
        dest.writeString(this.description);
        dest.writeString(this.videoUrl);
        dest.writeString(this.thumbnailUrl);
    }

    public Step() {
    }

    protected Step(Parcel in) {
        this.stepId = in.readInt();
        this.shortDesc = in.readString();
        this.description = in.readString();
        this.videoUrl = in.readString();
        this.thumbnailUrl = in.readString();
    }

    public static final Parcelable.Creator<Step> CREATOR = new Parcelable.Creator<Step>() {
        @Override
        public Step createFromParcel(Parcel source) {
            return new Step(source);
        }

        @Override
        public Step[] newArray(int size) {
            return new Step[size];
        }
    };

    @Override
    public String toString() {
        return "Step{" +
                "stepId=" + stepId +
                ", shortDesc='" + shortDesc + '\'' +
                ", description='" + description + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                ", thumbnailUrl='" + thumbnailUrl + '\'' +
                '}';
    }
}
