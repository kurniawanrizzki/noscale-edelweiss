package com.noscale.edelweiss.common;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import androidx.core.content.FileProvider;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.noscale.edelweiss.BuildConfig;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 05/09/20.
 */
public class PDFCommon {

    public static void createPdf (Context context, PDFContainerListener listener) {
        Document doc = new Document();
        File file = null;

        try {
            String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Edelwish";

            File dir = new File(path);

            if (!dir.exists()) dir.mkdirs();

            file = new File(dir, System.currentTimeMillis() + ".pdf");
            FileOutputStream out = new FileOutputStream(file);

            PdfWriter.getInstance(doc, out);

            doc.open();

            Paragraph content = listener.getContent();
            doc.add(content);
        } catch (DocumentException e) {
            e.printStackTrace();
            listener.showError(e.getLocalizedMessage());
        } catch (IOException e) {
            e.printStackTrace();
            listener.showError(e.getLocalizedMessage());
        } finally {
            doc.close();
            previewPdf(context, file, listener);
        }
    }

    private static void previewPdf (Context context, File f, PDFContainerListener listener) {
        if (null == f || ((null != f) && !f.exists())) {
            listener.showError("Error has been occurred while displaying document");
            return;
        }

        // Setting the intent for pdf reader
        Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
        pdfIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        Uri uri = FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + ".provider", f);
        pdfIntent.setDataAndType(uri, "application/pdf");

        try {
            context.startActivity(pdfIntent);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
            listener.showError(e.getLocalizedMessage());
        }
    }

    public interface PDFContainerListener {
        Paragraph getContent ();
        void showError (String message);
    }
}
