package com.noscale.edelweiss.gallery.creation;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.noscale.edelweiss.BaseFragment;
import com.noscale.edelweiss.R;
import com.noscale.edelweiss.common.UICommon;
import com.noscale.edelweiss.data.Category;
import com.noscale.edelweiss.gallery.GalleryActivity;
import java.util.List;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 19/08/20.
 */
public class GalleryCreationFragment extends BaseFragment implements GalleryCreationContract.View {

    private EditText mEtGalleryPhoto;

    public static GalleryCreationFragment newInstance () {
        return new GalleryCreationFragment();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mMainView = view.findViewById(R.id.cl_gallery_container);
        mEtGalleryPhoto = view.findViewById(R.id.et_gallery_photo);

        mEtGalleryPhoto.setOnClickListener((v) -> {
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {
                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickPhoto , 1);
                    return;
                }

                ActivityCompat.requestPermissions(getActivity(), new String[]{
                        Manifest.permission.READ_EXTERNAL_STORAGE
                }, 1);
            }
        });

        view.findViewById(R.id.b_gallery_submit).setOnClickListener((v) -> {
            String photoUrl = mEtGalleryPhoto.getText().toString();

            boolean isValidated = UICommon.isInputStringValidated(photoUrl);

            if (!isValidated) return;

            showProgressView(true);
            ((GalleryCreationContract.Presenter) mPresenter).submit(photoUrl);
        });

        showProgressView(true);
    }

    @Override
    protected int getResLayout() {
        return R.layout.fragment_gallery_creation;
    }

    @Override
    protected boolean isAccessTypeAccepted() {
        return true;
    }

    @Override
    public void setPresenter(GalleryCreationContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void appendCategories(List<Category> categories) {
        showProgressView(false);

        ArrayAdapter<Category> categoryAdapter = new ArrayAdapter<Category>(getContext(), android.R.layout.simple_spinner_dropdown_item, categories) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                Category category = getItem(position);
                View view = super.getView(position, convertView, parent);

                TextView tvTitle = view.findViewById(android.R.id.text1);
                tvTitle.setText(category.getName());

                return view;
            }

            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                Category category = getItem(position);
                View view = super.getDropDownView(position, convertView, parent);

                TextView tvTitle = view.findViewById(android.R.id.text1);
                tvTitle.setText(category.getName());

                return view;
            }
        };

        AppCompatSpinner spCategory = getView().findViewById(R.id.sp_gallery_category);
        spCategory.setAdapter(categoryAdapter);

        spCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Category category = categories.get(i);
                ((GalleryCreationContract.Presenter) mPresenter).setCategory(category);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void showSuccessMessageView() {
        showMessage(getString(R.string.success_txt), getString(R.string.success_txt), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();

                Intent intent = new Intent(getContext(), GalleryActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void showErrorMessageView(String message, Runnable runnable) {
        showMessage(getString(R.string.error_title_txt), message, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();

                showProgressView(true);
                runnable.run();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if ((requestCode == 1) && resultCode == getActivity().RESULT_OK) {
            Uri selectedImage = data.getData();
            String path = UICommon.getImageFilePath(getContext(), selectedImage);

            mEtGalleryPhoto.setText(path);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(pickPhoto , 1);
            return;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
