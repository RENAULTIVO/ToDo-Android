package com.renaultivo.todo.elements;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.renaultivo.todo.R;

import org.json.JSONException;
import org.json.JSONObject;

public class ToDoElement extends LinearLayout {

    Context context;
    JSONObject itemData;

    ToDoStateButton toDoStateButton;
    TextView itemName;

    int density;

    public ToDoStateButton getStateButton() {
        return toDoStateButton;
    }

    public ToDoElement(Context context, JSONObject itemData) {
        super(context);
        this.context = context;
        this.itemData = itemData;
        density = (int) context.getResources().getDisplayMetrics().density;
        createItems();
        setStyles();
        setTextStyle();
    }

    void createItems() {

        try {

            toDoStateButton = new ToDoStateButton(
                    context,
                    itemData.getBoolean("state")
            );

            itemName = new TextView(context);
            itemName.setText(itemData.getString("name"));

            addView(toDoStateButton);
            addView(itemName);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    void setStyles() {

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );

        layoutParams.setMargins(20 * density, 10 * density, 20 * density, 10 * density);
        setLayoutParams(layoutParams);

        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);
        setBackground(context.getDrawable(R.drawable.todo_element));

    }

    void setTextStyle() {

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
            LayoutParams.WRAP_CONTENT,
            LayoutParams.WRAP_CONTENT
        );

        itemName.setLayoutParams(layoutParams);

        itemName.setLayoutParams(layoutParams);
        itemName.setTextColor(Color.parseColor("#DDDDDD"));
        itemName.setPadding(10 * density, 10 * density, 10 * density, 10 * density);
        itemName.setTextSize(18);

    }

}
