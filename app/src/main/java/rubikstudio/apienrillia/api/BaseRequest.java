package rubikstudio.apienrillia.api;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.Map;

import rubikstudio.apienrillia.constant.Constant;
import rubikstudio.apienrillia.model.BaseRes;

/**
 * Created by kiennguyen on 12/7/16.
 */

public abstract class BaseRequest {
    protected IResponse callback;
    protected Map<String, String> params;

    public BaseRequest(Map<String, String> params) {
        this.params = params;
    }

    public abstract void excute(IResponse callback);

    public void setCallback(IResponse callback) {
        this.callback = callback;
    }

    protected void handleResponseError(VolleyError error) {
        // > 400
        if (error != null) {
            callback.onErrorResponse(error.networkResponse.statusCode, null);
        } else {
            callback.onErrorResponse(Constant.StatusCode.UNKNOWN, null);
        }
    }
}
