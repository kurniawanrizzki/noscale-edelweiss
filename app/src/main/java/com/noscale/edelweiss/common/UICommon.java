package com.noscale.edelweiss.common;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;
import android.widget.LinearLayout;
import androidx.core.content.ContextCompat;
import com.noscale.edelweiss.R;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 13/08/20.
 */
public class UICommon {

    public static void applyMarginToView (View v, int topBottomMargin) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, topBottomMargin, 0, topBottomMargin);

        v.setLayoutParams(params);
    }

    public static void showDialog (Context context, String resTitle, String resContent) {
        showDialog(context, resTitle, resContent, null);
    }

    public static void showDialog (Context context, String resTitle, String resContent, DialogInterface.OnClickListener listener) {
        showDialog(context, resTitle, resContent, listener, null);
    }

    public static void showDialog (Context context, String resTitle, String resContent, DialogInterface.OnClickListener positiveListener, DialogInterface.OnClickListener negativeListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.AppTheme_AlertDialog)
                .setCancelable(false)
                .setTitle(resTitle)
                .setMessage(resContent)
                .setPositiveButton(R.string.ok_txt, (dialogInterface, i) -> {
                    dialogInterface.dismiss();

                    if (null == positiveListener) {
                        return;
                    }

                    positiveListener.onClick(dialogInterface, i);
                });

        if (null != negativeListener) {
            builder.setNegativeButton(R.string.no_txt, (dialogInterface, i) -> {
                dialogInterface.dismiss();
                negativeListener.onClick(dialogInterface, i);
            });
        }

        AlertDialog dialog = builder.create();
        dialog.show();

        dialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(ContextCompat.getColor(context, R.color.colorBlack));
        dialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(ContextCompat.getColor(context, R.color.colorBlue));
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

    public static String getImageFilePath(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = {MediaStore.Images.Media.DATA};
            cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();

            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

}
