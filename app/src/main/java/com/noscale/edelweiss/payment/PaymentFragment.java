package com.noscale.edelweiss.payment;

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
import com.noscale.edelweiss.data.Booking;
import com.noscale.edelweiss.data.PaymentType;
import com.noscale.edelweiss.payment.complete.CompletePaymentActivity;
import java.util.List;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 16/08/20.
 */
public class PaymentFragment extends BaseFragment implements PaymentContract.View {

    private ArrayAdapter<PaymentType> mAdapter;

    private ArrayAdapter<Booking> mBookingIdAdapter;

    private EditText etReceipt;

    public static PaymentFragment newInstance () {
        return new PaymentFragment();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mMainView = view.findViewById(R.id.cl_payment_container);

        EditText etAmount = view.findViewById(R.id.et_payment_amount);

        view.findViewById(R.id.b_payment_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String receipt = etReceipt.getText().toString();
                String amount = etAmount.getText().toString();

                boolean isValidated = UICommon.isInputStringValidated(
                        receipt,
                        amount
                );

                if (!isValidated) return;

                showProgressView(true);
                ((PaymentContract.Presenter) mPresenter).submit(receipt, Float.parseFloat(amount));
            }
        });

        showProgressView(true);
    }

    @Override
    protected int getResLayout() {
        return R.layout.fragment_payment;
    }

    @Override
    public void setPresenter(PaymentContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void appendData(List<PaymentType> types, List<Booking> bookings) {
        showProgressView(false);

        mAdapter = new ArrayAdapter<PaymentType>(getContext(), android.R.layout.simple_spinner_dropdown_item, types) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                PaymentType type = types.get(position);

                TextView tvTitle = view.findViewById(android.R.id.text1);
                tvTitle.setText(type.getPaymentName());

                return view;
            }

            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);

                PaymentType type = types.get(position);

                TextView tvTitle = view.findViewById(android.R.id.text1);
                tvTitle.setText(type.getPaymentName());

                return view;
            }
        };

        mBookingIdAdapter = new ArrayAdapter<Booking>(getContext(), android.R.layout.simple_spinner_dropdown_item, bookings) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                Booking booking = bookings.get(position);

                TextView tvTitle = view.findViewById(android.R.id.text1);
                tvTitle.setText(booking.getBookingNumber());

                return view;
            }

            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);

                Booking booking = bookings.get(position);

                TextView tvTitle = view.findViewById(android.R.id.text1);
                tvTitle.setText(booking.getBookingNumber());

                return view;
            }
        };

        AppCompatSpinner bSpinner = getView().findViewById(R.id.sp_booking_options);
        AppCompatSpinner spinner = getView().findViewById(R.id.sp_payment_options);
        etReceipt = getView().findViewById(R.id.et_payment_receipt);

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

        spinner.setAdapter(mAdapter);
        bSpinner.setAdapter(mBookingIdAdapter);
        etReceipt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
            }
        });
    }

    @Override
    public void showErrorMessage(String message, Runnable r) {
        showMessage(getString(R.string.error_title_txt), message, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();

                showProgressView(true);
                r.run();
            }
        });
    }

    @Override
    public void showSuccessMessage() {
        showMessage(getString(R.string.success_txt), getString(R.string.payment_success_txt), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();

                Intent intent = new Intent(getContext(), CompletePaymentActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if ((requestCode == 1) && resultCode == getActivity().RESULT_OK) {
            Uri selectedImage = data.getData();
            etReceipt.setText(selectedImage.getPath());
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
