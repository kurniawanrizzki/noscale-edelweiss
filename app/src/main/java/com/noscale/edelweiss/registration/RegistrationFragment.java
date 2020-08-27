package com.noscale.edelweiss.registration;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.noscale.edelweiss.BaseFragment;
import com.noscale.edelweiss.BasePresenter;
import com.noscale.edelweiss.R;
import com.noscale.edelweiss.common.UICommon;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 13/08/20.
 */
public class RegistrationFragment extends BaseFragment implements RegistrationContract.View {

    public static RegistrationFragment newInstance () {
        return new RegistrationFragment();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        EditText etFirstName = view.findViewById(R.id.et_registration_first_name);
        EditText etLastName = view.findViewById(R.id.et_registration_last_name);
        EditText etEmail = view.findViewById(R.id.et_registration_email);
        EditText etPassword = view.findViewById(R.id.et_registration_password);
        EditText etConfirmPassword = view.findViewById(R.id.et_registration_repassword);

        view.findViewById(R.id.b_registration_submit).setOnClickListener((v) -> {
            String firstName = etFirstName.getText().toString();
            String lastName = etLastName.getText().toString();
            String email = etEmail.getText().toString();
            String password = etPassword.getText().toString();
            String confirmPassword = etConfirmPassword.getText().toString();

            boolean isFormValidated = UICommon.isInputStringValidated(firstName, lastName, email);
            boolean isPasswordValidated = UICommon.isInputStringValidated(password, confirmPassword) && password.equals(confirmPassword);

            if (!isFormValidated) {
                UICommon.showDialog(getContext(), getString(R.string.warning_title_txt), getString(R.string.warning_registration_txt));
                return;
            }
            if (!isPasswordValidated) {
                UICommon.showDialog(getContext(), getString(R.string.warning_title_txt), getString(R.string.warning_registration_password_confirmation_txt));
                return;
            }

            showProgressView(true);
            ((RegistrationContract.Presenter) mPresenter).signUp(
                    firstName,
                    lastName,
                    email,
                    password
            );
        });

        view.findViewById(R.id.tv_registration_signin).setOnClickListener((v) -> goToLogin());
    }

    @Override
    public void setPresenter(BasePresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    protected int getResLayout() {
        return R.layout.fragment_registration;
    }

    @Override
    protected boolean isAccessTypeAccepted() {
        return true;
    }

    @Override
    public void goToLogin() {
        getActivity().onBackPressed();
    }

    @Override
    public void showSuccessfulSignUp() {
        showMessage(getString(R.string.success_txt), getString(R.string.success_registration_txt), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                goToLogin();
            }
        });
    }

    @Override
    public void showFailureSignUp(String message) {
        showMessage(getString(R.string.warning_title_txt), message);
    }

    @Override
    public void showErrorMessage(String message) {
        showMessage(getString(R.string.error_title_txt), message);
    }
}
