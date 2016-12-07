package rubikstudio.apienrillia.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import rubikstudio.apienrillia.R;
import rubikstudio.apienrillia.helper.CircleTransformHelper;
import rubikstudio.apienrillia.model.response.ProviderRes;

/**
 * Created by kiennguyen on 12/7/16.
 */

public class ProviderAdapter extends ArrayAdapter<ProviderRes>{
    private LayoutInflater layoutInflater;

    public ProviderAdapter(Context context, List<ProviderRes> providerResList) {
        super(context, 0, providerResList);
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.adaper_provider, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.ivAvatar = (ImageView) convertView.findViewById(R.id.avatar);
            viewHolder.tvBusinessName = (TextView) convertView.findViewById(R.id.businessName);
            viewHolder.tvAddress = (TextView) convertView.findViewById(R.id.address);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        ProviderRes providerRes = getItem(position);

        if (providerRes.getBusinessName() != null && !providerRes.getBusinessName().isEmpty()) {
            viewHolder.tvBusinessName.setText("Business Name : " + providerRes.getBusinessName());
        }

        if (providerRes.getAddress() != null && !providerRes.getAddress().isEmpty()) {
            viewHolder.tvAddress.setText("Address : " + providerRes.getAddress());
        }

        if (!providerRes.getAvatar().isEmpty()) {
            Picasso.with(getContext())
                    .load(providerRes.getAvatar())
                    .transform(new CircleTransformHelper())
                    .into(viewHolder.ivAvatar);
        }
        return convertView;
    }

    public class ViewHolder {
        public ImageView ivAvatar;
        public TextView tvBusinessName;
        public TextView tvAddress;
    }
}
