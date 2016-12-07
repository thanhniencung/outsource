package rubikstudio.apienrillia.model.response;

import com.google.gson.annotations.SerializedName;

import rubikstudio.apienrillia.model.BaseRes;

/**
 * Created by kiennguyen on 12/7/16.
 */

public class ProviderRes extends BaseRes {
    @SerializedName("service")
    private String service;

    @SerializedName("businessname")
    private String businessName;

    @SerializedName("avatar")
    private String avatar;

    @SerializedName("address1")
    private String address;

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
