package rubikstudio.apienrillia;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import rubikstudio.apienrillia.adapter.ProviderAdapter;
import rubikstudio.apienrillia.api.IResponse;
import rubikstudio.apienrillia.model.request.ProviderReq;
import rubikstudio.apienrillia.model.response.ProviderRes;

/**
 * Created by kiennguyen on 12/7/16.
 */

public class ProviderActivity extends AppCompatActivity {
    private ListView listView;
    private Toolbar toolbar;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider);

        listView = (ListView) findViewById(R.id.listView);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        toolbar.setNavigationIcon(R.drawable.ic_menu_black_24dp);

        getProvider();
    }

    private void getProvider() {
        dialog = ProgressDialog.show(this, "",
                "Loading...", true);

        Map<String, String> params = new HashMap<>();
        params.put("lng", "106.6530728");
        params.put("lat", "10.8428967");
        ProviderReq providerReq = new ProviderReq(params);
        providerReq.excute(new IResponse<ProviderRes[]>() {
            @Override
            public void onErrorResponse(int statusCode, String message) {
                dialog.dismiss();
                Toast.makeText(ProviderActivity.this, String.valueOf(statusCode), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccessResponse(ProviderRes[] providerRes) {
                dialog.dismiss();
                ProviderAdapter adapter = new ProviderAdapter(ProviderActivity.this, Arrays.asList(providerRes));
                listView.setAdapter(adapter);
            }
        });
    }
}
