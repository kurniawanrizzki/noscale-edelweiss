package com.noscale.edelweiss.payment;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.core.content.ContextCompat;
import com.noscale.edelweiss.BaseFragment;
import com.noscale.edelweiss.R;
import com.noscale.edelweiss.common.EdelweissCurrencyEditText;
import com.noscale.edelweiss.common.UICommon;
import com.noscale.edelweiss.data.Booking;
import com.noscale.edelweiss.data.PaymentType;
import com.noscale.edelweiss.payment.complete.CompletePaymentActivity;
import com.squareup.picasso.Picasso;
import java.io.File;
import java.math.BigDecimal;
import java.util.List;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 16/08/20.
 */
public class PaymentFragment extends BaseFragment implements PaymentContract.View {

    public static PaymentFragment newInstance () {
        return new PaymentFragment();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView tvPath = view.findViewById(R.id.tv_payment_path);
        EdelweissCurrencyEditText etAmount = view.findViewById(R.id.et_payment_amount);
        ImageView ivPhoto = view.findViewById(R.id.iv_payment_pick);

        view.findViewById(R.id.b_payment_submit).setOnClickListener((v) -> {
            PaymentContract.Presenter p = (PaymentContract.Presenter) mPresenter;
            String receipt = tvPath.getText().toString();
            String amount = etAmount.getText().toString();

            boolean isValidated = UICommon.isInputStringValidated(
                    receipt,
                    amount
            );

            if (!isValidated) return;
            if (receipt.equalsIgnoreCase("path")) return;

            if (((null != p.getBooking()) && !p.getBooking().isEmpty()) && (p.getPaymentType() > 0) && (etAmount.getDoubleValue() > 0)) {
                showProgressView(true);
                p.submit(receipt, BigDecimal.valueOf(etAmount.getDoubleValue()).toBigInteger());
            }
        });

        ivPhoto.setOnClickListener((v) -> openGallery());
    }

    @Override
    protected int getResLayout() {
        return R.layout.fragment_payment;
    }

    @Override
    protected boolean isAccessTypeAccepted() {
        return true;
    }

    @Override
    public void setPresenter(PaymentContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void openGallery() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {
            if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto , PaymentActivity.PAYMENT_PICK_REQUEST_CODE);
                return;
            }

            requestPermissions(new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE
            }, PaymentActivity.PAYMENT_PICK_REQUEST_CODE);
        }
    }

    @Override
    public void appendData(List<PaymentType> types, List<Booking> bookings) {
        showProgressView(false);

        AppCompatSpinner bSpinner = getView().findViewById(R.id.sp_payment_booking_options);
        AppCompatSpinner spinner = getView().findViewById(R.id.sp_payment_options);

        ArrayAdapter<PaymentType> adapter = new ArrayAdapter<PaymentType>(getContext(), R.layout.item_edelweiss_spinner, R.id.text1, types) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                PaymentType type = types.get(position);

                TextView tvTitle = view.findViewById(R.id.text1);
                tvTitle.setText(type.getPaymentName());

                return view;
            }

            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);

                PaymentType type = types.get(position);

                TextView tvTitle = view.findViewById(R.id.text1);
                tvTitle.setText(type.getPaymentName());

                return view;
            }
        };

        ArrayAdapter<Booking> bookingIdAdapter = new ArrayAdapter<Booking>(getContext(), R.layout.item_edelweiss_spinner, R.id.text1, bookings) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                Booking booking = bookings.get(position);

                TextView tvTitle = view.findViewById(R.id.text1);
                tvTitle.setText(booking.getBookingNumber());

                return view;
            }

            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);

                Booking booking = bookings.get(position);

                TextView tvTitle = view.findViewById(R.id.text1);
                tvTitle.setText(booking.getBookingNumber());

                return view;
            }
        };

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                PaymentType type = types.get(i);
                ((PaymentContract.Presenter) mPresenter).setPaymentType(type);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        bSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Booking booking = bookings.get(i);
                ((PaymentContract.Presenter) mPresenter).setBooking(booking);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner.setAdapter(adapter);
        bSpinner.setAdapter(bookingIdAdapter);
    }

    @Override
    public void showErrorMessage(String message, Runnable r) {
        showMessage(getString(R.string.error_title_txt), message, (dialogInterface, i) -> {
            showProgressView(true);
            r.run();
        }, (dialogInterface, i) -> {
            getActivity().setResult(PaymentActivity.RESULT_CANCELED);
            getActivity().finish();
        });
    }

    @Override
    public void showSuccessMessage() {
        showMessage(getString(R.string.success_txt), getString(R.string.payment_success_txt), (dialogInterface, i) -> {
            showProgressView(false);

            Intent intent = new Intent(getContext(), CompletePaymentActivity.class);
            startActivityForResult(intent, CompletePaymentActivity.COMPLETE_PAYMENT_REQUEST_CODE);
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if ((requestCode == PaymentActivity.PAYMENT_PICK_REQUEST_CODE) && resultCode == getActivity().RESULT_OK) {
            Uri selectedImage = data.getData();
            String path = UICommon.getImageFilePath(getContext(), selectedImage);

            TextView tvPath = getView().findViewById(R.id.tv_payment_path);
            ImageView ivPath = getView().findViewById(R.id.iv_payment_pick);

            tvPath.setText(path);
            Picasso.get().load(new File(path)).into(ivPath);
        } else if ((requestCode == CompletePaymentActivity.COMPLETE_PAYMENT_REQUEST_CODE) && (resultCode == getActivity().RESULT_OK)) {
            getActivity().setResult(Activity.RESULT_OK);
            getActivity().finish();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PaymentActivity.PAYMENT_PICK_REQUEST_CODE && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(pickPhoto , PaymentActivity.PAYMENT_PICK_REQUEST_CODE);
            return;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
