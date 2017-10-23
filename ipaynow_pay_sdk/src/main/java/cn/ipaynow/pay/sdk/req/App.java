package cn.ipaynow.pay.sdk.req;

/**
 * Created by ipaynow1130 on 2017/10/12.
 */
public class App {

    private String appId;
    private String appKey;


    public App(String appId, String appKey) {
        this.appId = appId;
        this.appKey = appKey;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }
}
