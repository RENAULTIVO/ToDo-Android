package com.renaultivo.todo;

import android.app.Activity;
import android.content.ContentValues;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.RequiresApi;

import com.renaultivo.todo.DialogScreens.AddItemDialog;
import com.renaultivo.todo.elements.ToDoElement;
import com.renaultivo.todo.tools.DBManager;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends Activity {

    Activity activity;
    DBManager dbManager;

    void startDB() {

        dbManager = new DBManager(this, "TODO_LIST", new String[][] {
                { "id", "INTEGER PRIMARY KEY AUTOINCREMENT" },
                { "data", "TEXT" }
        });

    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        activity = this;
        setContentView(R.layout.main_activity);

        startDB();

        LinearLayout itemList = findViewById(R.id.itemList);

        Button addItemButton = findViewById(R.id.addItemButton);

        addItemButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.R)
            @Override
            public void onClick(View v) {
                new AddItemDialog(activity);
            }
        });

        for (int i=0; i<10; i++) {

            // fake data to test
            JSONObject itemData = new JSONObject();

            try {
                itemData.put("name", "Item name "+i);
                itemData.put("state", i % 2 == 0);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            ToDoElement toDoElement = new ToDoElement(this, itemData);

            boolean[] state = { false };

            toDoElement.getStateButton().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    state[0] = !state[0];
                    toDoElement.getStateButton().setState(state[0]);
                }
            });

            itemList.addView(toDoElement);

        }

    }

}
