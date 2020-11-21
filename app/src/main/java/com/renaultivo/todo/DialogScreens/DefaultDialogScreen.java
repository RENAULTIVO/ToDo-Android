package com.renaultivo.todo.DialogScreens;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.renaultivo.todo.BuildConfig;

public class DefaultDialogScreen extends Dialog {

    RelativeLayout masterContainer;
    boolean closed = false;

    @RequiresApi(api = Build.VERSION_CODES.R)
    public DefaultDialogScreen(@NonNull Context context) {
        super(context);

        if (BuildConfig.VERSION_CODE >= Build.VERSION_CODES.R) {
            getWindow().setDecorFitsSystemWindows(false);
        } else {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        }

        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);

    }

    void open() {

        ScaleAnimation scaleAnimation = new ScaleAnimation(
                0.9f, 1f,
                0.9f, 1f,
                ScaleAnimation.RELATIVE_TO_SELF, 0.5f,
                ScaleAnimation.RELATIVE_TO_SELF, 0.5f
        );
        scaleAnimation.setDuration(300);

        AlphaAnimation alphaAnimation = new AlphaAnimation(0f, 1f);
        alphaAnimation.setDuration(300);

        AnimationSet animationSet = new AnimationSet(true);

        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(alphaAnimation);

        animationSet.setDuration(300);

        masterContainer.startAnimation(animationSet);
    }

    public void close() {

        ScaleAnimation scaleAnimation = new ScaleAnimation(
                1f, 0.9f,
                1f, 0.9f,
                ScaleAnimation.RELATIVE_TO_SELF, 0.5f,
                ScaleAnimation.RELATIVE_TO_SELF, 0.5f
        );
        scaleAnimation.setDuration(200);

        AlphaAnimation alphaAnimation = new AlphaAnimation(1f, 0f);
        alphaAnimation.setDuration(200);

        AnimationSet animationSet = new AnimationSet(true);

        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(alphaAnimation);

        animationSet.setDuration(200);

        masterContainer.startAnimation(animationSet);

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                closed = true;
                cancel();
            }
        }, 190);

    }

    @Override
    public void onBackPressed() {
        close();
    }

    @Override
    public void cancel() {

        if (!closed) {
            close();
        } else {
            super.cancel();
        }

    }

}
