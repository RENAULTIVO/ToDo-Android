package com.renaultivo.todo.DialogScreens;

import android.content.Context;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.renaultivo.todo.R;

public class AddItemDialog extends DefaultDialogScreen {

    @RequiresApi(api = Build.VERSION_CODES.R)
    public AddItemDialog(@NonNull Context context) {
        super(context);
        setContentView(R.layout.add_item_dialog);
        masterContainer = findViewById(R.id.masterContainer);

        create();
        show();
        open();

    }

}
