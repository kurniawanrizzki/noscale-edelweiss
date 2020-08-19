package com.noscale.edelweiss.testimonial.creation;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.noscale.edelweiss.BaseFragment;
import com.noscale.edelweiss.R;
import com.noscale.edelweiss.common.UICommon;
import com.noscale.edelweiss.common.configuration.AppConfiguration;
import com.noscale.edelweiss.testimonial.TestimonialActivity;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 18/08/20.
 */
public class TestimonialCreateFragment extends BaseFragment implements TestimonialCreateContract.View {

    public static TestimonialCreateFragment newInstance () {
        return new TestimonialCreateFragment();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mMainView = view.findViewById(R.id.cl_testimonial_container);
        mProgressView = view.findViewById(R.id.inc_fragment_progress);

        TextView tvContent = view.findViewById(R.id.et_testimonial_content);
        TextView tvTitle = view.findViewById(R.id.tv_fragment_title);
        tvTitle.setText(getString(R.string.testimonial_txt));

        view.findViewById(R.id.b_testimonial_submit).setOnClickListener(
                (v) -> {
                    int userId = AppConfiguration.getInstance(getContext()).getAuthenticatedId();
                    String content = tvContent.getText().toString();

                    boolean isValidated = UICommon.isInputStringValidated(content);

                    if (!isValidated) return;

                    showProgressView(true);
                    ((TestimonialCreateContract.Presenter) mPresenter).submit(
                            userId,
                            content
                    );
                }
        );
    }

    @Override
    protected int getResLayout() {
        return R.layout.fragment_testimonial_create;
    }

    @Override
    protected boolean isAccessTypeAccepted() {
        return true;
    }

    @Override
    public void setPresenter(TestimonialCreateContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showSuccessMessage() {
        showMessage(getString(R.string.success_txt), getString(R.string.testimonial_success_txt), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();

                Intent intent = new Intent(getContext(), TestimonialActivity.class);
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
