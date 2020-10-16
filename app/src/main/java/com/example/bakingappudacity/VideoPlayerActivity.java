package com.example.bakingappudacity;


import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;

import java.util.List;


public class VideoPlayerActivity extends AppCompatActivity {

    PlayerView mPlayerView;
    List<Step> stepsList;
    private TextView currentStepTV;
    private Step currentStep;
    private Step nextStep;
    private Step prevStep;
    SimpleExoPlayer exoPlayer;
    private TextView noVideo;
    private Button prevButton;
    private Button nextButton;
    boolean isTwoPane;
    boolean playerIsReady;
    Long playbackPosition;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_player);
        Recipe currentRecipe =  RecipePreferences.getRecipe(this);
        stepsList = currentRecipe.recipeSteps;

        if (findViewById(R.id.only_tablet_layout) != null) {
            isTwoPane = true;

        } else {
            isTwoPane = false;
        }

        if (savedInstanceState != null) {
            if (isTwoPane) {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            }
            playerIsReady = savedInstanceState.getBoolean("player_is_ready");
            playbackPosition = savedInstanceState.getLong("current_playback_pos");
            currentStep = savedInstanceState.getParcelable("current_step");
            int differentId = savedInstanceState.getInt("currentStepId");

            buildCurrentAndNextStep(differentId);

        } else {
            if (getIntent() != null) {
                int stepId = getIntent().getIntExtra("step_id", 0);
                buildCurrentAndNextStep(stepId);
            }
        }

        mPlayerView = findViewById(R.id.player_view);
        mPlayerView.setPlayer(exoPlayer);
        currentStepTV = findViewById(R.id.current_step_TV);
        noVideo = findViewById(R.id.noVideo_TV);
        prevButton = findViewById(R.id.prev_button);
        nextButton = findViewById(R.id.next_button);
        noVideo.setVisibility(View.INVISIBLE);
        currentStepTV.setText(currentStep.description);
        ActionBar actionBar = getSupportActionBar();
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            nextButton.setVisibility(View.GONE);
            prevButton.setVisibility(View.GONE);
            currentStepTV.setVisibility(View.GONE);
            if (actionBar != null) {
                actionBar.hide();
            }
        } else {
            if (actionBar != null) {
                actionBar.show();
            }
        }
        checkIfStepHasVideo();
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNextButtonClick();
            }
        });

        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPrevButtonClick();
            }
        });

    }

    @Override
    protected void onPostResume() {
        if (playbackPosition != null) {
            playerIsReady = false;
            exoPlayer.seekTo(playbackPosition);
        }
        super.onPostResume();

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (exoPlayer == null) {
            intializePlayer();
        }
        if (exoPlayer != null) {
            if (playbackPosition != null) {
                exoPlayer.seekTo(playbackPosition);
                playerIsReady = false;
            }
        }

    }

    @Override
    protected void onPause() {
        exoPlayer.setPlayWhenReady(false);
        playbackPosition = exoPlayer.getCurrentPosition();

        super.onPause();
    }

    private void intializePlayer() {
        if (checkIfStepHasVideo()) {
            if (exoPlayer == null) {
                TrackSelector trackSelector = new DefaultTrackSelector();
                exoPlayer = ExoPlayerFactory.newSimpleInstance(this, trackSelector);
                mPlayerView.setPlayer(exoPlayer);

            }
            exoPlayer.prepare(currentStep.getMediaSource(this));
            exoPlayer.setPlayWhenReady(true);

        } else {
            checkIfStepHasVideo();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (exoPlayer != null) {
            exoPlayer.setPlayWhenReady(false);
            playbackPosition = exoPlayer.getCurrentPosition();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        exoPlayer.release();
        exoPlayer = null;
    }

    private boolean checkIfStepHasVideo() {
        boolean hasVideo;
        if (TextUtils.isEmpty(currentStep.videoUrl)) {
            mPlayerView.setVisibility(View.INVISIBLE);
            noVideo.setVisibility(View.VISIBLE);
            hasVideo = false;
        } else {
            mPlayerView.setVisibility(View.VISIBLE);
            noVideo.setVisibility(View.INVISIBLE);
            hasVideo = true;
        }
        return hasVideo;
    }

    private void buildCurrentAndNextStep(int newStepId) {

        for (int i = 0; i < stepsList.size(); i++) {

            if (stepsList.get(i).stepId == newStepId) {
                currentStep = stepsList.get(i);

                if ((i + 1) < stepsList.size() && stepsList.get(i + 1) != null) {
                    nextStep = stepsList.get(i + 1);
                }
                if (i != 0) {
                    prevStep = stepsList.get(i - 1);
                } else {
                    prevStep = stepsList.get(0);
                }
            }
        }
    }


    private void onNextButtonClick() {
        buildCurrentAndNextStep(nextStep.stepId);
        if (exoPlayer != null) {
            exoPlayer.stop();
        }
        checkIfStepHasVideo();
        currentStepTV.setText(currentStep.description);
        intializePlayer();


    }

    private void onPrevButtonClick() {
        buildCurrentAndNextStep(prevStep.stepId);
        if (exoPlayer != null) {
            exoPlayer.stop();
        }
        checkIfStepHasVideo();
        currentStepTV.setText(currentStep.description);
        intializePlayer();

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putParcelable("current_step", currentStep);
        bundle.putInt("currentStepId", currentStep.stepId);
        bundle.putLong("current_playback_pos", exoPlayer.getCurrentPosition());
        bundle.putBoolean("player_is_ready", exoPlayer.getPlayWhenReady());
    }

    @Override
    public void setRequestedOrientation(int requestedOrientation) {
        super.setRequestedOrientation(requestedOrientation);
    }
}
