package com.noscale.edelweiss.booking;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
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
import com.noscale.edelweiss.BaseFragment;
import com.noscale.edelweiss.R;
import com.noscale.edelweiss.booking.complete.CompleteBookingActivity;
import com.noscale.edelweiss.common.UICommon;
import com.noscale.edelweiss.common.configuration.AppConfiguration;
import com.noscale.edelweiss.common.widget.EdelweissButton;
import com.noscale.edelweiss.data.Building;
import com.noscale.edelweiss.data.Category;
import com.noscale.edelweiss.data.WeddingPackage;
import java.util.List;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 16/08/20.
 */
public class BookingFragment extends BaseFragment implements BookingContract.View {

    public static BookingFragment newInstance () {
        return new BookingFragment();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        BookingContract.Presenter p = (BookingContract.Presenter) mPresenter;

        EditText tvAddress = view.findViewById(R.id.et_booking_address);
        EditText tvPhoneNumber = view.findViewById(R.id.et_booking_phone);
        EditText tvEventDate = view.findViewById(R.id.et_booking_date);
        EditText tvEventTime = view.findViewById(R.id.et_booking_time);

        view.findViewById(R.id.b_booking_submit).setOnClickListener((v) -> {
            int userId = AppConfiguration.getInstance(getContext()).getAuthenticatedId();
            String address = tvAddress.getText().toString();
            String phoneNumber = tvPhoneNumber.getText().toString();
            String eventDate = tvEventDate.getText().toString();
            String eventTime = tvEventTime.getText().toString();

            boolean isValidated = UICommon.isInputStringValidated(
                    address,
                    phoneNumber,
                    eventDate,
                    eventTime
            );

            if (!isValidated) return;

            showProgressView(true);
            p.submit(
                    userId,
                    address,
                    phoneNumber,
                    eventDate,
                    eventTime
            );
        });

        DatePickerDialog.OnDateSetListener dateListener = (datePicker, i, i1, i2) -> {
            p.setDateInput(i, i1, i2);
            String date = p.getDateInput();

            tvEventDate.setText(date);
        };

        TimePickerDialog.OnTimeSetListener timeListener = (timePicker, i, i1) -> {
            p.setTimeInput(i,i1);
            String time = p.getTimeInput();

            tvEventTime.setText(time);
        };

        tvEventTime.setOnClickListener((v) -> {
            String[] timeArray = p.getTimeInput().split(":");

            int hour = Integer.parseInt(timeArray[0]);
            int minutes = Integer.parseInt(timeArray[1]);

            new TimePickerDialog(
                    getContext(),
                    timeListener,
                    hour,
                    minutes,
                    true
            ).show();
        });

        tvEventDate.setOnClickListener((v) -> {
            String[] dateArray = p.getDateInput().split("-");

            int year = Integer.parseInt(dateArray[0]);
            int month = Integer.parseInt(dateArray[1]);
            int dayOfMonth = Integer.parseInt(dateArray[2]);

            new DatePickerDialog(getContext(), dateListener, year, month, dayOfMonth)
                    .show();
        });

        tvEventDate.setText(p.getDateInput());
        tvEventTime.setText(p.getTimeInput());
    }

    @Override
    protected int getResLayout() {
        return R.layout.fragment_booking;
    }

    @Override
    protected boolean isAccessTypeAccepted() {
        return true;
    }

    @Override
    public void setPresenter(BookingContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void goToCompletionBooking() {
        showProgressView(false);

        Intent i = new Intent(getContext(), CompleteBookingActivity.class);
        startActivityForResult(i, CompleteBookingActivity.COMPLETE_BOOKING_REQUEST_CODE);
    }

    @Override
    public void appendCategory(List<Category> categories) {
        AppCompatSpinner spCategory = getView().findViewById(R.id.sp_booking_category);

        ArrayAdapter<Category> categoryAdapter = new ArrayAdapter<Category>(getContext(), R.layout.item_edelweiss_spinner, R.id.text1, categories) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                Category type = categories.get(position);

                TextView tvTitle = view.findViewById(R.id.text1);
                tvTitle.setText(type.getName());

                return view;
            }

            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);

                Category type = categories.get(position);

                TextView tvTitle = view.findViewById(R.id.text1);
                tvTitle.setText(type.getName());

                return view;
            }
        };

        spCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Category item = categories.get(i);
                ((BookingContract.Presenter) mPresenter).setSelectedCategory(item);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spCategory.setAdapter(categoryAdapter);
        showProgressView(false);
    }

    @Override
    public void appendBuilding(List<Building> data) {
        AppCompatSpinner spBuilding = getView().findViewById(R.id.sp_booking_building);
        EdelweissButton bSubmit = getView().findViewById(R.id.b_booking_submit);

        boolean isEmpty = data.isEmpty();

        if (isEmpty) {
            showProgressView(false);

            spBuilding.setEnabled(false);
            bSubmit.setEnabled(false);

            ((BookingContract.Presenter) mPresenter).setBuilding(null);
            return;
        }

        spBuilding.setEnabled(true);
        bSubmit.setEnabled(true);

        ArrayAdapter<Building> buildingAdapter = new ArrayAdapter<Building>(getContext(), R.layout.item_edelweiss_spinner_2_lines, R.id.text1, data) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                Building type = getItem(position);

                TextView tvTitle = view.findViewById(R.id.text1);
                TextView tvSubtitle = view.findViewById(R.id.text2);

                tvTitle.setText(type.getName());
                tvSubtitle.setText(type.getAddress());

                return view;
            }

            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);

                Building type = getItem(position);

                TextView tvTitle = view.findViewById(R.id.text1);
                TextView tvSubtitle = view.findViewById(R.id.text2);

                tvTitle.setText(type.getName());
                tvSubtitle.setText(type.getAddress());

                return view;
            }
        };

        spBuilding.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Building item = data.get(i);
                ((BookingContract.Presenter) mPresenter).setBuilding(item);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spBuilding.setAdapter(buildingAdapter);
        showProgressView(false);
    }

    @Override
    public void appendPackage(List<WeddingPackage> packages) {
        AppCompatSpinner spWeddingPackage = getView().findViewById(R.id.sp_booking_package);

        ArrayAdapter<WeddingPackage> wpAdapter = new ArrayAdapter<WeddingPackage>(getContext(), R.layout.item_edelweiss_spinner, R.id.text1, packages) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                WeddingPackage type = packages.get(position);

                TextView tvTitle = view.findViewById(R.id.text1);
                tvTitle.setText(type.getName());

                return view;
            }

            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);

                WeddingPackage type = packages.get(position);

                TextView tvTitle = view.findViewById(R.id.text1);
                tvTitle.setText(type.getName());

                return view;
            }
        };

        spWeddingPackage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                showProgressView(true);

                WeddingPackage item = packages.get(i);
                ((BookingContract.Presenter) mPresenter).setSelectedWeddingPackage(item);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spWeddingPackage.setAdapter(wpAdapter);
        showProgressView(false);
    }

    @Override
    protected void showProgressView(boolean isShow) {
        boolean isDataSuccessFulLoad = ((BookingContract.Presenter) mPresenter).isDataSuccessfulLoad();

        if (isDataSuccessFulLoad && !isShow) {
            super.showProgressView(false);
            return;
        }

        super.showProgressView(true);
    }

    @Override
    public void showErrorMessage(String message, Runnable runnable) {
        boolean isDataSuccessFulLoad = ((BookingContract.Presenter) mPresenter).isDataSuccessfulLoad();

        if (!isDataSuccessFulLoad) return;

        showMessage(getString(R.string.error_title_txt), message, (dialogInterface, i) -> {
            showProgressView(true);
            runnable.run();
        }, (dialogInterface, i) -> {
            getActivity().setResult(Activity.RESULT_CANCELED);
            getActivity().finish();
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if ((requestCode == CompleteBookingActivity.COMPLETE_BOOKING_REQUEST_CODE) && (resultCode == CompleteBookingActivity.RESULT_OK)) {
            getActivity().setResult(BookingActivity.RESULT_OK);
            getActivity().finish();
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
