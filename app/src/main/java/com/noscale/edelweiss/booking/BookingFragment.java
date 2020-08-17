package com.noscale.edelweiss.booking;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatSpinner;
import com.noscale.edelweiss.BaseFragment;
import com.noscale.edelweiss.R;
import com.noscale.edelweiss.booking.complete.CompleteBookingActivity;
import com.noscale.edelweiss.common.UICommon;
import com.noscale.edelweiss.common.configuration.AppConfiguration;
import com.noscale.edelweiss.data.Category;
import com.noscale.edelweiss.data.WeddingPackage;
import com.noscale.edelweiss.wp.WeddingPackageContract;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 16/08/20.
 */
public class BookingFragment extends BaseFragment implements BookingContract.View {

    private ArrayAdapter<WeddingPackage> mWpAdapter;

    private ArrayAdapter<Category> mCategoryAdapter;

    public static BookingFragment newInstance () {
        return new BookingFragment();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mMainView = view.findViewById(R.id.cl_booking_container);

        EditText tvAddress = view.findViewById(R.id.et_booking_address);
        EditText tvPhoneNumber = view.findViewById(R.id.et_booking_phone);
        EditText tvEventDate = view.findViewById(R.id.et_booking_date);
        EditText tvEventTime = view.findViewById(R.id.et_booking_time);
        EditText tvBookingFee = view.findViewById(R.id.et_booking_fee);

        view.findViewById(R.id.b_booking_submit).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int userId = AppConfiguration.getInstance(getContext()).getUserId();
                        String address = tvAddress.getText().toString();
                        String phoneNumber = tvPhoneNumber.getText().toString();
                        String eventDate = tvEventDate.getText().toString();
                        String eventTime = tvEventTime.getText().toString();
                        String bookingFee = tvBookingFee.getText().toString();

                        boolean isValidated = UICommon.isInputStringValidated(
                                address,
                                phoneNumber,
                                eventDate,
                                eventTime,
                                bookingFee
                        );

                        if (!isValidated) return;

                        showProgressView(true);
                        ((BookingContract.Presenter) mPresenter).submit(
                                userId,
                                address,
                                phoneNumber,
                                eventDate,
                                eventTime,
                                bookingFee
                        );
                    }
                }
        );

        BookingContract.Presenter p = (BookingContract.Presenter) mPresenter;

        String myFormat = "YYYY-MM-dd"; //In which you need put here
        int hourOfDay = p.getCalendar().get(Calendar.HOUR_OF_DAY);
        int minutes = p.getCalendar().get(Calendar.MINUTE);

        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.getDefault());

        tvEventDate.setText(sdf.format(p.getCalendar().getTime()));
        tvEventTime.setText(hourOfDay+":"+minutes);

        DatePickerDialog.OnDateSetListener dateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

                p.getCalendar().set(Calendar.YEAR, i);
                p.getCalendar().set(Calendar.MONTH, i1);
                p.getCalendar().set(Calendar.DAY_OF_MONTH, i2);

                String myFormat = "YYYY-MM-dd"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.getDefault());

                tvEventDate.setText(sdf.format(p.getCalendar().getTime()));
            }
        };

        TimePickerDialog.OnTimeSetListener timeListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                p.getCalendar().set(Calendar.HOUR_OF_DAY, i);
                p.getCalendar().set(Calendar.MINUTE, i1);

                tvEventTime.setText(i+":"+i1);
            }
        };

        tvEventTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new TimePickerDialog(
                        getContext(),
                        timeListener,
                        p.getCalendar().get(Calendar.HOUR_OF_DAY),
                        p.getCalendar().get(Calendar.MINUTE),
                        true
                        ).show();
            }
        });


        tvEventDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BookingContract.Presenter p = (BookingContract.Presenter) mPresenter;

                int year = p.getCalendar().get(Calendar.YEAR);
                int month = p.getCalendar().get(Calendar.MONTH);
                int dayOfMonth = p.getCalendar().get(Calendar.DAY_OF_MONTH);

                new DatePickerDialog(getContext(), dateListener, year, month, dayOfMonth)
                        .show();
            }
        });

        showProgressView(true);
    }

    @Override
    protected int getResLayout() {
        return R.layout.fragment_booking;
    }

    @Override
    public void setPresenter(BookingContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void completeBooking() {
        showProgressView(false);

        Intent i = new Intent(getContext(), CompleteBookingActivity.class);
        startActivity(i);
    }

    @Override
    public void appendCategory(List<Category> categories) {
        mCategoryAdapter = new ArrayAdapter<Category>(getContext(), android.R.layout.simple_spinner_dropdown_item, categories) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                Category type = categories.get(position);

                TextView tvTitle = view.findViewById(android.R.id.text1);
                tvTitle.setText(type.getName());

                return view;
            }

            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);

                Category type = categories.get(position);

                TextView tvTitle = view.findViewById(android.R.id.text1);
                tvTitle.setText(type.getName());

                return view;
            }
        };

        AppCompatSpinner spCategory = getView().findViewById(R.id.sp_booking_category);
        spCategory.setAdapter(mCategoryAdapter);

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
    }

    @Override
    public void appendPackage(List<WeddingPackage> packages) {
        mWpAdapter = new ArrayAdapter<WeddingPackage>(getContext(), android.R.layout.simple_spinner_dropdown_item, packages) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                WeddingPackage type = packages.get(position);

                TextView tvTitle = view.findViewById(android.R.id.text1);
                tvTitle.setText(type.getName());

                return view;
            }

            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);

                WeddingPackage type = packages.get(position);

                TextView tvTitle = view.findViewById(android.R.id.text1);
                tvTitle.setText(type.getName());

                return view;
            }
        };

        AppCompatSpinner spWeddingPackage = getView().findViewById(R.id.sp_booking_package);
        spWeddingPackage.setAdapter(mWpAdapter);

        spWeddingPackage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                WeddingPackage item = packages.get(i);
                ((BookingContract.Presenter) mPresenter).setSelectedWeddingPackage(item);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void notifyProgressDone() {
        showProgressView(false);
    }

    @Override
    public void showSingleErrorMessage(String message) {
        if (isProgressShown) return;

        showMessage(getString(R.string.error_title_txt), message, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();

                showProgressView(true);

                ((BookingContract.Presenter) mPresenter).getCategories();
                ((BookingContract.Presenter) mPresenter).getPackages();
            }
        });
    }

    @Override
    public void showErrorMessage(String message) {
        showMessage(getString(R.string.error_title_txt), message);
    }
}
