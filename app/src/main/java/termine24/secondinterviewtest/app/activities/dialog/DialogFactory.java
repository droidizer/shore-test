package termine24.secondinterviewtest.app.activities.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import termine24.secondinterviewtest.R;


/**
 * Created by shanemurphy on 14/01/2016.
 * Copyright (c) 2016 Shore. All rights reserved.
 */
public class DialogFactory {


    public static void showErrorDialog(Context ctx, String messageRes) {

        //if there is no error message then set the unknown error message so we dont show an empty dialog
        if (messageRes == null || messageRes.isEmpty()) {
            messageRes = ctx.getString(R.string.error_unknown_error);
        }

        AlertDialog.Builder diagBuilder = new AlertDialog.Builder(ctx);
        diagBuilder.setTitle(R.string.dialog_title_error);
        diagBuilder.setMessage(messageRes);
        diagBuilder.setNegativeButton(R.string.button_OK, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        diagBuilder.create().show();
    }


}

