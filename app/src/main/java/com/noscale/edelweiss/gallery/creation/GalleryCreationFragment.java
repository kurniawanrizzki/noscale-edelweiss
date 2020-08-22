package com.noscale.edelweiss.gallery.creation;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.noscale.edelweiss.BaseFragment;
import com.noscale.edelweiss.R;
import com.noscale.edelweiss.common.UICommon;
import com.noscale.edelweiss.data.Category;
import com.squareup.picasso.Picasso;
import java.io.File;
import java.util.List;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 19/08/20.
 */
public class GalleryCreationFragment extends BaseFragment implements GalleryCreationContract.View {

    public static GalleryCreationFragment newInstance () {
        return new GalleryCreationFragment();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.iv_gallery_picker).setOnClickListener((v) -> openGallery());

        view.findViewById(R.id.b_gallery_submit).setOnClickListener((v) -> {
            TextView tvPath = view.findViewById(R.id.tv_gallery_path);
            String photoUrl = tvPath.getText().toString();

            boolean isValidated = UICommon.isInputStringValidated(photoUrl);

            if (!isValidated) return;

            showProgressView(true);
            ((GalleryCreationContract.Presenter) mPresenter).submit(photoUrl);
        });
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
    public void openGallery() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {
            if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto , GalleryCreationActivity.GALLERY_PICK_REQUEST_CODE);
                return;
            }

            ActivityCompat.requestPermissions(getActivity(), new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE
            }, GalleryCreationActivity.GALLERY_PICK_REQUEST_CODE);
        }
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
        showMessage(getString(R.string.success_txt), getString(R.string.success_txt), (dialogInterface, i) -> {
            getActivity().setResult(GalleryCreationActivity.RESULT_OK);
            getActivity().finish();
        });
    }

    @Override
    public void showErrorMessageView(String message, Runnable runnable) {
        showMessage(getString(R.string.error_title_txt), message, (dialogInterface, i) -> {
            showProgressView(true);
            runnable.run();
        }, (dialogInterface, i) -> getActivity().finish());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if ((requestCode == GalleryCreationActivity.GALLERY_PICK_REQUEST_CODE) && resultCode == getActivity().RESULT_OK) {
            Uri selectedImage = data.getData();
            String path = UICommon.getImageFilePath(getContext(), selectedImage);

            AppCompatImageView ivPhoto = getView().findViewById(R.id.iv_gallery_picker);
            TextView tvPath = getView().findViewById(R.id.tv_gallery_path);

            Picasso.get().load(new File(path)).into(ivPhoto);
            tvPath.setText(path);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == GalleryCreationActivity.GALLERY_PICK_REQUEST_CODE && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(pickPhoto , GalleryCreationActivity.GALLERY_PICK_REQUEST_CODE);
            return;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
