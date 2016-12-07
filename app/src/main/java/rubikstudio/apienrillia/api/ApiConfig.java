package rubikstudio.apienrillia.api;

/**
 * Created by kiennguyen on 12/7/16.
 */

public class ApiConfig {
    private static final String ROOT_API_URL = "";
    private static final String SIGN_IN_API = "signin";
    private static final String SIGN_UP_API = "signup";
    private static final String PROVIDERS_API = "providers";

    public static String getSignInUrl() {
        return ROOT_API_URL + SIGN_IN_API;
    }

    public static String getSignUpUrl() {
        return ROOT_API_URL + SIGN_UP_API;
    }

    public static String getProvidersUrl() {
        return ROOT_API_URL + PROVIDERS_API;
    }
}
