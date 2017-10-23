package cn.ipaynow.pay.sdk.resp;

/**
 * Created by ipaynow1130 on 2017/10/13.
 */
public class ResultScan05 extends Result{



    public enum PayResult{
        SUCESS("A001","支付成功"),
        FAIL("A002","支付失败"),
        TOBESUCESS("A004","受理成功"),
        CLOSE("A006","交易关闭"),
        UNKNOWN("","未知");

        private String code;
        private String desc;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        PayResult(String code, String desc) {
            this.code = code;
            this.desc = desc;
        }
    }






    private PayResult payResult;


    public PayResult getPayResult() {
        return payResult;
    }

    public void setPayResult(PayResult payResult) {
        this.payResult = payResult;
    }

    @Override
    public String toString() {
        return super.toString() + "     ResultScan05{" +
                "   payResult=" + payResult +
                '}';
    }
}
