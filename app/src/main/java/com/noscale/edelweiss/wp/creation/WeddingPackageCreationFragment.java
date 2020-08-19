package com.noscale.edelweiss.wp.creation;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.recyclerview.widget.RecyclerView;
import com.noscale.edelweiss.BaseFragment;
import com.noscale.edelweiss.R;
import com.noscale.edelweiss.common.UICommon;
import com.noscale.edelweiss.common.widget.DynamicSimpleRecyclerAdapter;
import com.noscale.edelweiss.common.widget.SimpleRecyclerAdapter;
import com.noscale.edelweiss.data.Bonus;
import com.noscale.edelweiss.data.BuffetType;
import com.noscale.edelweiss.data.WeddingPackageDetail;
import com.noscale.edelweiss.wp.WeddingPackageActivity;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 18/08/20.
 */
public class WeddingPackageCreationFragment extends BaseFragment implements WeddingPackageCreationContract.View {

    private SimpleRecyclerAdapter<WeddingPackageDetail> mWpAdapter;

    private DynamicSimpleRecyclerAdapter<Bonus> mBonusAdapter;

    public static WeddingPackageCreationFragment newInstance () {
        return new WeddingPackageCreationFragment();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mMainView = view.findViewById(R.id.cl_package_container);

        List<Bonus> bonuses = new ArrayList<>();

        bonuses.add(new Bonus());
        bonuses.add(null);

        EditText tvName = view.findViewById(R.id.et_package_name);
        EditText tvPrice = view.findViewById(R.id.et_package_price);
        EditText tvTotalBuffet = view.findViewById(R.id.et_package_total);

        ScrollView svMainView = view.findViewById(R.id.sv_package_container);
        view.findViewById(R.id.b_package_create).setOnClickListener((v) -> {
            String packageName = tvName.getText().toString();
            String packagePrice = tvPrice.getText().toString();
            String totalBuffet = tvTotalBuffet.getText().toString();

            boolean isValidated = UICommon.isInputStringValidated(
                    packageName,
                    packagePrice,
                    totalBuffet
            );

            boolean isPackageDetailIsNotEmpty = ((WeddingPackageCreationContract.Presenter) mPresenter).isDetailPackageNotEmpty();

            if (!isValidated && !isPackageDetailIsNotEmpty) return;

            ((WeddingPackageCreationContract.Presenter) mPresenter).submit(
                    packageName,
                    packagePrice,
                    totalBuffet,
                    mBonusAdapter.getMainData()
            );
        });

        mBonusAdapter = new DynamicSimpleRecyclerAdapter<>(
                bonuses,
                R.layout.item_addition_label,
                R.layout.item_edittext,
                (holder, item) -> {
                    View v = holder.itemView;
                    v.findViewById(R.id.tv_addition_label).setOnClickListener((c) -> {
                        int index = mBonusAdapter.getItemCount() - 1;

                        mBonusAdapter.addItemAt(index, new Bonus());
                        mBonusAdapter.notifyItemInserted(index);

                        svMainView.fullScroll(View.FOCUS_DOWN);
                    });
                },
                (holder, item) -> {
                    View v = holder.itemView;
                    EditText etBonus = v.findViewById(R.id.et_widget);
                    etBonus.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                        }

                        @Override
                        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                        }

                        @Override
                        public void afterTextChanged(Editable editable) {
                            String content = editable.toString();
                            item.setContent(content);
                        }
                    });
                }
        );

        RecyclerView rvBonus = view.findViewById(R.id.rv_package_bonus);
        rvBonus.setAdapter(mBonusAdapter);
    }

    @Override
    protected int getResLayout() {
        return R.layout.fragment_package_create;
    }

    @Override
    protected boolean isAccessTypeAccepted() {
        return true;
    }

    @Override
    public void setPresenter(WeddingPackageCreationContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void appendBuffetType(List<BuffetType> buffetTypes) {
        ArrayAdapter<BuffetType> adapter = new ArrayAdapter<BuffetType>(getContext(), android.R.layout.simple_spinner_dropdown_item, buffetTypes) {
            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                BuffetType type = getItem(position);
                View view = super.getDropDownView(position, convertView, parent);

                TextView tvText = view.findViewById(android.R.id.text1);
                tvText.setText(type.getName());

                return view;
            }

            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                BuffetType type = getItem(position);
                View view = super.getView(position, convertView, parent);

                TextView tvText = view.findViewById(android.R.id.text1);
                tvText.setText(type.getName());

                return view;
            }
        };

        AppCompatSpinner spBuffetType = getView().findViewById(R.id.sp_package_buffet);
        spBuffetType.setAdapter(adapter);

        spBuffetType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                BuffetType type = buffetTypes.get(i);
                ((WeddingPackageCreationContract.Presenter) mPresenter).setBuffetType(type);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void appendPackageDetails(List<WeddingPackageDetail> details) {
        mWpAdapter = new SimpleRecyclerAdapter<>(details, R.layout.item_form_wp_option, (holder,item) -> {
            View view = holder.itemView;

            List<String> itemDetails = item.getDetails();

            SimpleRecyclerAdapter<String> detailsAdapter = new SimpleRecyclerAdapter<>(
                    itemDetails, android.R.layout.simple_spinner_dropdown_item, (cHolder, cItem) -> {
                        View cView = cHolder.itemView;

                        TextView tvText = cView.findViewById(android.R.id.text1);
                        tvText.setText(cItem);
            });

            CheckBox cbMain = view.findViewById(R.id.cb_form_wp_main);
            RecyclerView rvChild = view.findViewById(R.id.rv_form_wp_child);

            cbMain.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    WeddingPackageCreationContract.Presenter p = (WeddingPackageCreationContract.Presenter) mPresenter;

                    if (b) {
                        p.addDetailPackage(item.getId());
                        return;
                    }

                    p.removeDetailPackage(item.getId());
                }
            });

            cbMain.setText(item.getName());

            if ((null != itemDetails) && !itemDetails.isEmpty()) {
                rvChild.setAdapter(detailsAdapter);
                return;
            }

            rvChild.setVisibility(View.GONE);
        });

        RecyclerView rvWeddingPackageDetail = getView().findViewById(R.id.rv_package_detail);
        rvWeddingPackageDetail.setAdapter(mWpAdapter);

    }

    @Override
    public void showSuccessMessage() {
        showMessage(getString(R.string.success_txt), getString(R.string.package_success_txt), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();

                Intent intent = new Intent(getContext(), WeddingPackageActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void showErrorMessage(String message, Runnable runnable) {
        showMessage(getString(R.string.error_title_txt), message, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                runnable.run();
            }
        });
    }
}
