package com.noscale.edelweiss.common;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import com.noscale.edelweiss.R;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 13/08/20.
 */
public class UICommon {

    public static void showProgressView (View mainView, View progressView, boolean isShow) {
        if (isShow) {
            mainView.setVisibility(View.GONE);
            progressView.setVisibility(View.VISIBLE);
            return;
        }

        mainView.setVisibility(View.VISIBLE);
        progressView.setVisibility(View.GONE);
    }

    public static void showDialog (Context context, String resTitle, String resContent) {
        showDialog(context, resTitle, resContent, null);
    }

    public static void showDialog (Context context, String resTitle, String resContent, DialogInterface.OnClickListener listener) {
        AlertDialog dialog = new AlertDialog.Builder(context)
                .setCancelable(false)
                .setTitle(resTitle)
                .setMessage(resContent)
                .setPositiveButton(R.string.ok_txt, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (null == listener) {
                            dialogInterface.dismiss();
                            return;
                        }

                        listener.onClick(dialogInterface, i);
                    }
                })
                .create();

        dialog.show();
    }

    public static boolean isInputStringValidated (String... input) {
        for (String i : input) {
            boolean isValidated = (null != i) && !i.isEmpty();

            if (!isValidated) {
                return false;
            }
        }

        return true;
    }

}
