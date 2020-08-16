package com.noscale.edelweiss;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import com.noscale.edelweiss.common.configuration.AppConfiguration;
import com.noscale.edelweiss.dashboard.DashboardActivity;
import com.noscale.edelweiss.login.LoginActivity;

public class SplashActivity extends AppCompatActivity {

    private static final long INTERVAL_SPLASH = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (AppConfiguration.getInstance(SplashActivity.this).isAuthenticated()) {
                    Intent i = new Intent(SplashActivity.this, DashboardActivity.class);
                    startActivity(i);
                } else {
                    Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(i);
                }

                finish();
            }
        }, INTERVAL_SPLASH);
    }
}