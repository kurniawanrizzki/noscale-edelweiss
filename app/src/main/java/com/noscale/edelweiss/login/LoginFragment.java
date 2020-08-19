package com.noscale.edelweiss.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.noscale.edelweiss.BaseFragment;
import com.noscale.edelweiss.R;
import com.noscale.edelweiss.common.UICommon;
import com.noscale.edelweiss.dashboard.DashboardActivity;
import com.noscale.edelweiss.registration.RegistrationActivity;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 13/08/20.
 */
public class LoginFragment extends BaseFragment implements LoginContract.View {

    public static LoginFragment newInstance () {
        return new LoginFragment();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mMainView = view.findViewById(R.id.ll_login_container);

        EditText etEmail = view.findViewById(R.id.et_login_email);
        EditText etPassword = view.findViewById(R.id.et_login_password);

        view.findViewById(R.id.tv_login_signup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), RegistrationActivity.class);
                startActivity(i);
            }
        });

        view.findViewById(R.id.b_login_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();

                if (!UICommon.isInputStringValidated(email, password)) return;

                showProgressView(true);
                ((LoginContract.Presenter) mPresenter).signIn(email, password);
            }
        });
    }

    @Override
    protected int getResLayout() {
        return R.layout.fragment_login;
    }

    @Override
    protected boolean isAccessTypeAccepted() {
        return true;
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void goToDashboard() {
        showProgressView(false);

        Intent i = new Intent(getContext(), DashboardActivity.class);
        startActivity(i);
    }

    @Override
    public void showFailureLogin() {
        showMessage(getString(R.string.warning_title_txt), getString(R.string.authenticated_failure_txt));
    }

    @Override
    public void showErrorMessage(String message) {
        showMessage(getString(R.string.error_title_txt), message);
    }
}
