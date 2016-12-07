package rubikstudio.apienrillia.api;

/**
 * Created by kiennguyen on 12/7/16.
 */

public interface IResponse<T> {
    void onErrorResponse(int statusCode, String message);
    void onSuccessResponse(T data);
}
