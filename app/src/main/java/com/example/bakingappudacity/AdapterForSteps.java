package com.example.bakingappudacity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class AdapterForSteps extends ArrayAdapter<Step> {

    private List<Step> mStepsList;
    LayoutInflater inflater;

    public AdapterForSteps(@NonNull Context context, @NonNull List<Step> stepsList) {
        super(context, 0, stepsList);
        mStepsList = stepsList;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        final ViewHolderForSteps holder;
        Step currentStep = mStepsList.get(position);


        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item_for_steps, parent, false);
            holder = new ViewHolderForSteps();

            holder.step = convertView.findViewById(R.id.steps_TV);
            holder.step.setPaintFlags(holder.step.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
            holder.step.setText(currentStep.description);


        }
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), VideoPlayerActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("step_desc", currentStep.description);
                intent.putExtra("video_url", currentStep.videoUrl);
                intent.putExtra("step_id", currentStep.stepId);

                getContext().startActivity(intent);
            }
        });
        return convertView;
    }

    static class ViewHolderForSteps {
        TextView step;
    }

}
