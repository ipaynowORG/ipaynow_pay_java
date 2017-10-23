package cn.ipaynow.pay.sdk.resp;

/**
 * Created by ipaynow1130 on 2017/10/13.
 */
public class ResultScan08 extends Result{


    private String tn;

    public String getTn() {
        return tn;
    }

    public void setTn(String tn) {
        this.tn = tn;
    }


    @Override
    public String toString() {
        return super.toString() + " ResultScan08{" +
                "tn='" + tn + '\'' +
                '}';
    }
}
