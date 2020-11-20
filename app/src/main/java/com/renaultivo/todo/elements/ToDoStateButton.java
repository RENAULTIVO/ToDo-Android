package com.renaultivo.todo.elements;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.view.animation.ScaleAnimation;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.renaultivo.todo.R;

public class ToDoStateButton extends androidx.appcompat.widget.AppCompatImageButton {

    Context context;

    int density;
    boolean state;

    Drawable icon;

    public void setState(boolean state) {

        if (state == this.state) {
            return;
        }

        this.state = state;

        setIcon();
        changeStateAnimation();

    }

    public ToDoStateButton(Context context, boolean state) {
        super(context);
        this.context = context;
        density = (int) context.getResources().getDisplayMetrics().density;

        setStyles();

        setIcon();
        setBackground(icon);

    }

    void setStyles() {

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(40 * density, 40* density);
        layoutParams.setMargins(10 * density, 10 * density, 10 * density, 10 * density);
        setLayoutParams(layoutParams);

    }

    void setIcon() {

        if (state) {
            icon = context.getDrawable(R.drawable.icon_check);
        } else {
            icon = context.getDrawable(R.drawable.icon_cancel);
        }

    }

    void changeStateAnimation() {

        ScaleAnimation zoomOutAnimation = new ScaleAnimation(
                1f, 0f,
                1f, 0f,
                ScaleAnimation.RELATIVE_TO_SELF, 0.5f,
                ScaleAnimation.RELATIVE_TO_SELF, 0.5f
        );

        zoomOutAnimation.setDuration(150);

        startAnimation(zoomOutAnimation);

        new Handler(Looper.getMainLooper()).postDelayed((Runnable) () -> {

            setBackground(icon);

            ScaleAnimation zoomInAnimation = new ScaleAnimation(
                    0f, 1f,
                    0f, 1f,
                    ScaleAnimation.RELATIVE_TO_SELF, 0.5f,
                    ScaleAnimation.RELATIVE_TO_SELF, 0.5f
            );

            zoomInAnimation.setDuration(150);

            startAnimation(zoomInAnimation);

        }, 150);

    }

}
