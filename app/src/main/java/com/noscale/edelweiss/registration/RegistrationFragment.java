package com.noscale.edelweiss.registration;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.noscale.edelweiss.BaseFragment;
import com.noscale.edelweiss.BasePresenter;
import com.noscale.edelweiss.R;
import com.noscale.edelweiss.common.UICommon;
import com.noscale.edelweiss.login.LoginActivity;

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

        mMainView = view.findViewById(R.id.cl_registration_container);

        TextView tvFirstName = view.findViewById(R.id.et_registration_first_name);
        TextView tvLastName = view.findViewById(R.id.et_registration_last_name);
        TextView tvEmail = view.findViewById(R.id.et_registration_email);
        TextView tvPassword = view.findViewById(R.id.et_registration_password);
        TextView tvConfirmPassword = view.findViewById(R.id.et_registration_repassword);

        view.findViewById(R.id.b_registration_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstName = tvFirstName.getText().toString();
                String lastName = tvLastName.getText().toString();
                String email = tvEmail.getText().toString();
                String password = tvPassword.getText().toString();
                String confirmPassword = tvConfirmPassword.getText().toString();

                boolean isFormValidated = UICommon.isInputStringValidated(firstName, lastName, email);
                boolean isPasswordValidated = UICommon.isInputStringValidated(password, confirmPassword) && password.equals(confirmPassword);

                if (!isFormValidated && !isPasswordValidated) return;

                showProgressView(true);
                ((RegistrationContract.Presenter) mPresenter).signUp(
                        firstName,
                        lastName,
                        email,
                        password
                );
            }
        });

        view.findViewById(R.id.tv_registration_signin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToLogin();
            }
        });
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
    public void goToLogin() {
        Intent i = new Intent(getContext(), LoginActivity.class);
        startActivity(i);
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
