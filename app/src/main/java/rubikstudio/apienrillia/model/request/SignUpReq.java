package rubikstudio.apienrillia.model.request;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import java.util.Map;

import rubikstudio.apienrillia.App;
import rubikstudio.apienrillia.api.ApiConfig;
import rubikstudio.apienrillia.api.BaseRequest;
import rubikstudio.apienrillia.api.GsonRequest;
import rubikstudio.apienrillia.api.IResponse;
import rubikstudio.apienrillia.constant.Constant;
import rubikstudio.apienrillia.model.response.SignUpRes;

/**
 * Created by kiennguyen on 12/7/16.
 */

public class SignUpReq extends BaseRequest {

    public SignUpReq(Map<String, String> params) {
        super(params);
    }

    @Override
    public void excute(final IResponse callback) {
        if (callback == null) {
            return;
        }

        GsonRequest signRequest = new GsonRequest(Request.Method.POST,
                ApiConfig.getSignUpUrl(), params, SignUpRes.class, null,
                new Response.Listener<SignUpRes>() {
                    @Override
                    public void onResponse(SignUpRes signUpRes) {
                        if (signUpRes.status) {
                            callback.onSuccessResponse(signUpRes);
                        } else {
                            callback.onErrorResponse(Constant.StatusCode.OK, null);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        handleResponseError(error);
                    }
                }
        );
        Volley.newRequestQueue(App.getApp()).add(signRequest);
    }
}
