package rubikstudio.apienrillia;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import rubikstudio.apienrillia.api.IResponse;
import rubikstudio.apienrillia.constant.Constant;
import rubikstudio.apienrillia.helper.SharedPreferenceHelper;
import rubikstudio.apienrillia.model.request.LoginReq;
import rubikstudio.apienrillia.model.response.LoginRes;

public class LoginActivity extends AppCompatActivity implements IResponse<LoginRes> {
    private ProgressDialog dialog;
    private TextView tvStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvStatus = (TextView) findViewById(R.id.status);

        TextView txtSignUp = (TextView) findViewById(R.id.textSignUp);
        SpannableString content = new SpannableString("Sign Up");
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        txtSignUp.setText(content);

        txtSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(i);
                finish();
            }
        });

        findViewById(R.id.btnLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvStatus.setVisibility(View.GONE);
                String email = ((EditText) findViewById(R.id.email)).getText().toString().trim();
                String password = ((EditText) findViewById(R.id.password)).getText().toString().trim();

                if (!email.isEmpty() && !password.isEmpty()) {
                    login(email, password);
                } else {
                    tvStatus.setVisibility(View.VISIBLE);
                    tvStatus.setText(R.string.login_error);
                }
            }
        });
    }

    private void login(String email, String password) {

        Map<String, String> params = new HashMap<>();
        params.put("email", email);
        params.put("password", password);
        LoginReq loginReq = new LoginReq(params);
        loginReq.setCallback(this);
        loginReq.excute(this);

        dialog = ProgressDialog.show(this, "",
                "Loading...", true);
    }

    @Override
    public void onErrorResponse(int statusCode, String message) {
        tvStatus.setVisibility(View.VISIBLE);
        if (message != null && !message.isEmpty()) {
            tvStatus.setText(message);
        } else {
            tvStatus.setText(R.string.inter_error);
        }
        dialog.dismiss();
    }


    @Override
    public void onSuccessResponse(LoginRes loginRes) {
        dialog.dismiss();
        if(loginRes.getToken() != null && !loginRes.getToken().isEmpty()) {
            SharedPreferenceHelper.setSharedPreferenceString(this, Constant.Pref.TOKEN, loginRes.getToken());

            Intent i = new Intent(this, ProviderActivity.class);
            startActivity(i);
            finish();
        }
    }
}
