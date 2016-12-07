package rubikstudio.apienrillia;

import android.app.Application;

/**
 * Created by kiennguyen on 12/7/16.
 */

public class App extends Application {
    private static App app = null;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
    }

    public static App getApp() {
        return app;
    }
}
