package cn.ipaynow.pay.sdk.req;

/**
 * Created by ipaynow1130 on 2017/10/12.
 */
public class OrderDetail {

    public OrderDetail(String mhtOrderName, String mhtOrderDetail, Integer mhtOrderAmt,String mhtGoodsTag) {
        this.mhtOrderName = mhtOrderName;
        this.mhtOrderDetail = mhtOrderDetail;
        this.mhtOrderAmt = mhtOrderAmt;
        this.mhtGoodsTag = mhtGoodsTag;
    }

    private String mhtOrderName;
    private String mhtOrderDetail;
    private  Integer mhtOrderAmt;
    private String mhtGoodsTag;


    public String getMhtGoodsTag() {
        return mhtGoodsTag;
    }

    public void setMhtGoodsTag(String mhtGoodsTag) {
        this.mhtGoodsTag = mhtGoodsTag;
    }

    public String getMhtOrderName() {
        return mhtOrderName;
    }

    public void setMhtOrderName(String mhtOrderName) {
        this.mhtOrderName = mhtOrderName;
    }

    public String getMhtOrderDetail() {
        return mhtOrderDetail;
    }

    public void setMhtOrderDetail(String mhtOrderDetail) {
        this.mhtOrderDetail = mhtOrderDetail;
    }

    public Integer getMhtOrderAmt() {
        return mhtOrderAmt;
    }

    public void setMhtOrderAmt(Integer mhtOrderAmt) {
        this.mhtOrderAmt = mhtOrderAmt;
    }
}
