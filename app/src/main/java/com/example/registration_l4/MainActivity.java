package com.example.registration_l4;

import android.os.Bundle;
import android.os.Build;
import android.graphics.Color;
import android.view.Window;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {



    private EditText etEmail, etPassword;
    private Button btnLogin;
    private TextView tvWelcome, tvSubtitle, tvForgotPassword;
    private RelativeLayout rootView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.setStatusBarColor(Color.TRANSPARENT);
        }

        EditText etEmail = findViewById(R.id.et_email);
        EditText etPassword = findViewById(R.id.et_password);
        Button btnLogin = findViewById(R.id.btn_login);
        TextView tvWelcome = findViewById(R.id.tv_welcome);
        TextView tvSubtitle = findViewById(R.id.tv_subtitle);
        TextView tvForgotPassword = findViewById(R.id.tv_forgot_password);
        RelativeLayout rootView = findViewById(R.id.root_view);

        View.OnClickListener hideViewsOnClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etEmail.getText().toString().equals("admin") &&
                        etPassword.getText().toString().equals("admin")) {
                    Toast.makeText(MainActivity.this, "Вы успешно зарегистрировались", Toast.LENGTH_SHORT).show();
                    tvWelcome.setVisibility(View.VISIBLE);
                    tvSubtitle.setVisibility(View.GONE);
                    etEmail.setVisibility(View.GONE);
                    etPassword.setVisibility(View.GONE);
                    btnLogin.setVisibility(View.GONE);
                    tvForgotPassword.setVisibility(View.GONE);
                } else {
                    Toast.makeText(MainActivity.this, "Неправильный логин и пароль", Toast.LENGTH_SHORT).show();
                }
            }
        };

        btnLogin.setOnClickListener(hideViewsOnClick);

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String emailInput = etEmail.getText().toString().trim();
                String passwordInput = etPassword.getText().toString().trim();

                if (!emailInput.isEmpty() && !passwordInput.isEmpty()) {
                    btnLogin.setEnabled(true);
                    btnLogin.setBackgroundResource(R.color.orange);
                } else {
                    btnLogin.setEnabled(false);
                    btnLogin.setBackgroundResource(R.color.gray);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        };

        etEmail.addTextChangedListener(textWatcher);
        etPassword.addTextChangedListener(textWatcher);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
    }
}