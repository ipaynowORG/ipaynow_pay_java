package cn.ipaynow.pay.sdk.resp;

/**
 * Created by ipaynow1130 on 2017/10/13.
 */
public class Result {







    public enum RespResult{


        SUCESS("A001","成功"),
        FAIL("A002","失败"),
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

        RespResult(String code, String desc) {
            this.code = code;
            this.desc = desc;
        }
    }


    private RespResult respResult;
    private String responseMsg;
    private String mhtOrderNo;
    private String nowPayOrderNo;
    private String responseTime;



    public String getResponseMsg() {
        return responseMsg;
    }

    public void setResponseMsg(String responseMsg) {
        this.responseMsg = responseMsg;
    }

    public RespResult getRespResult() {
        return respResult;
    }

    public void setRespResult(RespResult respResult) {
        this.respResult = respResult;
    }

    public String getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(String responseTime) {
        this.responseTime = responseTime;
    }

    public String getNowPayOrderNo() {
        return nowPayOrderNo;
    }

    public void setNowPayOrderNo(String nowPayOrderNo) {
        this.nowPayOrderNo = nowPayOrderNo;
    }

    public String getMhtOrderNo() {
        return mhtOrderNo;
    }

    public void setMhtOrderNo(String mhtOrderNo) {
        this.mhtOrderNo = mhtOrderNo;
    }


    @Override
    public String toString() {
        return "Result{" +
                "  respResult=" + respResult  + '\'' +
                ", responseMsg='" + responseMsg + '\'' +
                ", mhtOrderNo='" + mhtOrderNo + '\'' +
                ", nowPayOrderNo='" + nowPayOrderNo + '\'' +
                ", responseTime='" + responseTime + '\'' +
                '}';
    }
}
