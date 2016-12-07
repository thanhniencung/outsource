package rubikstudio.apienrillia;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import rubikstudio.apienrillia.api.IResponse;
import rubikstudio.apienrillia.model.request.SignUpReq;
import rubikstudio.apienrillia.model.response.SignUpRes;

/**
 * Created by kiennguyen on 12/7/16.
 */

public class SignUpActivity extends AppCompatActivity {
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        findViewById(R.id.btnSignUp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = ((EditText) findViewById(R.id.name)).getText().toString().trim();
                String email = ((EditText) findViewById(R.id.email)).getText().toString().trim();
                String password = ((EditText) findViewById(R.id.password)).getText().toString().trim();
                String address = ((EditText) findViewById(R.id.address)).getText().toString().trim();
                String phone = ((EditText) findViewById(R.id.phone)).getText().toString().trim();

                signUp(name, email, password, address, phone);
            }
        });
    }

    private void signUp(String name, String email, String password, String address, String phone) {
        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || address.isEmpty() || phone.isEmpty()) {
            return;
        }

        dialog = ProgressDialog.show(this, "",
                "Loading...", true);

        Map<String, String> params = new HashMap<>();
        params.put("name", email);
        params.put("email", email);
        params.put("password", password);
        params.put("address", address);
        params.put("cellphone", phone);

        SignUpReq signUpReq = new SignUpReq(params);
        signUpReq.excute(new IResponse<SignUpRes>() {
            @Override
            public void onErrorResponse(int statusCode, String message) {
                dialog.dismiss();
                Toast.makeText(SignUpActivity.this, String.valueOf(statusCode), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccessResponse(SignUpRes signUpRes) {
                dialog.dismiss();
                Intent i = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

}
