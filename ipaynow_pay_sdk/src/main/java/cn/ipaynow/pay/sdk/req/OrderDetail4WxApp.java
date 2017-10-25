package cn.ipaynow.pay.sdk.req;

/**
 * Created by ipaynow1130 on 2017/10/25.
 */
public class OrderDetail4WxApp extends OrderDetail{

    private Integer oriMhtOrderAmt;//原始金额,小程序支付
    private Integer discountAmt;//优惠金额,小程序支付


    public OrderDetail4WxApp(String mhtOrderName, String mhtOrderDetail, Integer mhtOrderAmt, String mhtGoodsTag,Integer oriMhtOrderAmt,Integer discountAmt) {
        super(mhtOrderName, mhtOrderDetail, mhtOrderAmt, mhtGoodsTag);
        this.oriMhtOrderAmt = oriMhtOrderAmt;
        this.discountAmt = discountAmt;
    }

    public Integer getOriMhtOrderAmt() {
        return oriMhtOrderAmt;
    }

    public void setOriMhtOrderAmt(Integer oriMhtOrderAmt) {
        this.oriMhtOrderAmt = oriMhtOrderAmt;
    }

    public Integer getDiscountAmt() {
        return discountAmt;
    }

    public void setDiscountAmt(Integer discountAmt) {
        this.discountAmt = discountAmt;
    }

}
