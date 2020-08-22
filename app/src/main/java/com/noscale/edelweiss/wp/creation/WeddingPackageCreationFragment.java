package com.noscale.edelweiss.wp.creation;

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
import com.noscale.edelweiss.data.WeddingBuffet;
import com.noscale.edelweiss.data.WeddingPackageDetail;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 18/08/20.
 */
public class WeddingPackageCreationFragment extends BaseFragment implements WeddingPackageCreationContract.View {

    private DynamicSimpleRecyclerAdapter<Bonus> mBonusAdapter;

    public static WeddingPackageCreationFragment newInstance () {
        return new WeddingPackageCreationFragment();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<Bonus> bonuses = new ArrayList<>();

        bonuses.add(new Bonus());
        bonuses.add(null);

        EditText tvName = view.findViewById(R.id.et_package_name);
        EditText tvPrice = view.findViewById(R.id.et_package_price);
        EditText tvTotalBuffet = view.findViewById(R.id.et_package_total);

        ScrollView svMainView = view.findViewById(R.id.sv_package_container);
        view.findViewById(R.id.b_package_create).setOnClickListener((v) -> {
            boolean isPackageDetailIsNotEmpty = ((WeddingPackageCreationContract.Presenter) mPresenter).isDetailPackageNotEmpty();
            boolean isBuffetDetailIsNotEmpty = ((WeddingPackageCreationContract.Presenter) mPresenter).isBuffetDetailNotEmpty();
            boolean isEdited = ((WeddingPackageCreationContract.Presenter) mPresenter).isEdited();

            if (!isEdited) {
                String packageName = tvName.getText().toString();
                String packagePrice = tvPrice.getText().toString();
                String totalBuffet = tvTotalBuffet.getText().toString();

                boolean isValidated = UICommon.isInputStringValidated(
                        packageName,
                        packagePrice,
                        totalBuffet
                );

                if (!isValidated && !isPackageDetailIsNotEmpty) return;

                showProgressView(true);
                ((WeddingPackageCreationContract.Presenter) mPresenter).submit(
                        packageName,
                        packagePrice,
                        totalBuffet,
                        mBonusAdapter.getMainData()
                );

                return;

            }

            if (!isBuffetDetailIsNotEmpty && !isPackageDetailIsNotEmpty) return;

            showProgressView(true);
            ((WeddingPackageCreationContract.Presenter) mPresenter).edit();
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

        hideWhenEdit();
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
    public void appendBuffetType(List<BuffetType> buffetTypes, BuffetType selectedType) {
        SimpleRecyclerAdapter<WeddingBuffet> wbAdapter = new SimpleRecyclerAdapter<>(new ArrayList<>(), R.layout.item_edelweiss_cb_spinner, (holder,item) -> {
            View view = holder.itemView;
            CheckBox cbItem = view.findViewById(R.id.text1);
            cbItem.setText(item.getName());
            cbItem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (b) {
                        ((WeddingPackageCreationContract.Presenter) mPresenter).addDetailBuffet(item.getId());
                        return;
                    }

                    ((WeddingPackageCreationContract.Presenter) mPresenter).removeDetailBuffet(item.getId());
                }
            });

            if ((null != selectedType) && (((WeddingPackageCreationContract.Presenter) mPresenter).getBuffetTypeId() == selectedType.getId())) {
                cbItem.setChecked(selectedType.getDetailBuffets().contains(item));
            }
        });

        ArrayAdapter<BuffetType> adapter = new ArrayAdapter<BuffetType>(getContext(), R.layout.item_edelweiss_spinner, R.id.text1, buffetTypes) {
            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                BuffetType type = getItem(position);
                View view = super.getDropDownView(position, convertView, parent);

                TextView tvText = view.findViewById(R.id.text1);
                tvText.setText(type.getName());

                return view;
            }

            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                BuffetType type = getItem(position);
                View view = super.getView(position, convertView, parent);

                TextView tvText = view.findViewById(R.id.text1);
                tvText.setText(type.getName());

                return view;
            }
        };

        RecyclerView rvBuffetDetail = getView().findViewById(R.id.rv_package_buffet_detail);
        AppCompatSpinner spBuffetType = getView().findViewById(R.id.sp_package_buffet);

        rvBuffetDetail.setAdapter(wbAdapter);
        spBuffetType.setAdapter(adapter);

        if (null != selectedType) {
            spBuffetType.setSelection(buffetTypes.indexOf(selectedType));
        }

        spBuffetType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                BuffetType type = buffetTypes.get(i);
                ((WeddingPackageCreationContract.Presenter) mPresenter).setBuffetType(type);

                wbAdapter.setMainData(type.getDetailBuffets());
                wbAdapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        showProgressView(false);
    }

    @Override
    public void appendPackageDetails(List<WeddingPackageDetail> details, List<WeddingPackageDetail> orderDetails) {
        SimpleRecyclerAdapter<WeddingPackageDetail> wpAdapter = new SimpleRecyclerAdapter<>(details, R.layout.item_form_wp_option, (holder,item) -> {
            View view = holder.itemView;
            List<String> itemDetails = item.getDetails();

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

            if (null != orderDetails) {
                cbMain.setChecked(orderDetails.contains(item));
            }

            SimpleRecyclerAdapter<String> detailsAdapter = new SimpleRecyclerAdapter<>(itemDetails, R.layout.item_edelweiss_spinner, (cHolder, cItem) -> {
                View cView = cHolder.itemView;
                TextView tvText = cView.findViewById(R.id.text1);
                tvText.setText(cItem);
            });

            if ((null != itemDetails) && !itemDetails.isEmpty()) {
                rvChild.setAdapter(detailsAdapter);
                return;
            }

            rvChild.setVisibility(View.GONE);
        });

        RecyclerView rvWeddingPackageDetail = getView().findViewById(R.id.rv_package_detail);
        rvWeddingPackageDetail.setAdapter(wpAdapter);
        showProgressView(false);
    }

    @Override
    public void showSuccessMessage() {
        showMessage(getString(R.string.success_txt), getString(R.string.package_success_txt), (dialogInterface, i) -> {
            getActivity().setResult(WeddingPackageCreationActivity.RESULT_OK);
            getActivity().finish();
        });
    }

    @Override
    public void showErrorMessage(String message, Runnable runnable) {
        showMessage(getString(R.string.error_title_txt), message, (dialogInterface, i) -> runnable.run(), (dialogInterface, i) -> {
            getActivity().setResult(WeddingPackageCreationActivity.RESULT_CANCELED);
            getActivity().finish();
        });
    }

    @Override
    public void hideWhenEdit() {
        boolean isEdited = ((WeddingPackageCreationContract.Presenter) mPresenter).isEdited();

        if (!isEdited) return;

        getView().findViewById(R.id.et_package_name).setVisibility(View.GONE);
        getView().findViewById(R.id.et_package_price).setVisibility(View.GONE);
        getView().findViewById(R.id.et_package_total).setVisibility(View.GONE);
        getView().findViewById(R.id.rv_package_bonus).setVisibility(View.GONE);
        getView().findViewById(R.id.tv_package_bonus).setVisibility(View.GONE);
        getView().findViewById(R.id.tv_package_buffet_detail).setVisibility(View.VISIBLE);
        getView().findViewById(R.id.nsv_package_buffet_detail_container).setVisibility(View.VISIBLE);
    }

    @Override
    protected void showProgressView(boolean isShow) {
        boolean isDataSuccessFulLoad = ((WeddingPackageCreationContract.Presenter) mPresenter).isSuccessfulLoad();
        boolean isDataSuccessFulEdited= ((WeddingPackageCreationContract.Presenter) mPresenter).isSuccessfulEdited();

        if ((isDataSuccessFulLoad || isDataSuccessFulEdited) && !isShow) {
            super.showProgressView(false);
            return;
        }

        super.showProgressView(true);
    }
}
