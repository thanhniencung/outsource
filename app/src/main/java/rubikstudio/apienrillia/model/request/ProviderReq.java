package rubikstudio.apienrillia.model.request;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import rubikstudio.apienrillia.App;
import rubikstudio.apienrillia.api.ApiConfig;
import rubikstudio.apienrillia.api.BaseRequest;
import rubikstudio.apienrillia.api.GsonRequest;
import rubikstudio.apienrillia.api.IResponse;
import rubikstudio.apienrillia.constant.Constant;
import rubikstudio.apienrillia.helper.SharedPreferenceHelper;
import rubikstudio.apienrillia.model.response.ProviderRes;

/**
 * Created by kiennguyen on 12/7/16.
 */

public class ProviderReq extends BaseRequest {

    public ProviderReq(Map<String, String> params) {
        super(params);
    }

    @Override
    public void excute(final IResponse callback) {
        if (callback == null) {
            return;
        }

        String token = SharedPreferenceHelper.getSharedPreferenceString(App.getApp(), Constant.Pref.TOKEN, null);
        if (token.equals(null)) {
            return;
        }

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + token);

        GsonRequest<ProviderRes[]> signRequest = new GsonRequest(Request.Method.POST,
                ApiConfig.getProvidersUrl(), params, ProviderRes[].class, headers,
                new Response.Listener<ProviderRes[]>() {
                    @Override
                    public void onResponse(ProviderRes[] providerRes) {
                        callback.onSuccessResponse(providerRes);
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
