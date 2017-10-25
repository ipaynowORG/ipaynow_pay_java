package cn.ipaynow.pay;

import cn.ipaynow.pay.sdk.req.App;
import cn.ipaynow.pay.sdk.req.OrderDetail;
import cn.ipaynow.pay.sdk.req.OrderDetail4WxApp;
import cn.ipaynow.util.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;

/**
 * Created by ipaynow1130 on 2017/10/12.
 */
public class BasePay {

    private static final String URL = "https://pay.ipaynow.cn/";


    /**
     * @param channelAuthCode  支付授权码(被扫)
     * @param consumerCreateIp 用户支付IP(微信H5)
     * @param app
     * @param mhtSubMchId 服务商(闪慧)模式的门店ID
     * @param orderDetail
     * @param deviceType 设备类型
     * @param mhtSubAppId 对于微信公众号,子号对应多个公众号的时候必填
     * @param mhtReserved 商户保留域,平台类商户使用(平台类商户在使用微信支付时，需要上送mchBankId字段，值为在现在支付备案的子商户编号；格式：mchBankId=123&商户保留域参数。)
     * @param notifyUrl 后台通知地址
     * @param frontNotifyUrl 前台通知地址
     * @param payChannelType 微信支付宝手Q等
     */
    protected String pay(App app, OrderDetail orderDetail,String channelAuthCode, String consumerCreateIp, String mhtSubMchId, String deviceType,
                         String mhtSubAppId, String consumerId, String mhtReserved
            , String notifyUrl, String frontNotifyUrl, String payChannelType, Integer outputType){

        Map<String,String> map = new HashMap<>();
        if(channelAuthCode != null && !channelAuthCode.equals("")){
            map.put("channelAuthCode",channelAuthCode);
        }
        if(consumerCreateIp != null && !consumerCreateIp.equals("")){
            map.put("consumerCreateIp", consumerCreateIp);
        }

        map.put("funcode","WP001");
        map.put("version","1.0.0");
        map.put("mhtCurrencyType","156");//人民币
        map.put("mhtOrderType","01");//交易类型-目前这个字段没有意义(交易已经通过后续逻辑获取类型)
        map.put("mhtOrderTimeOut","2000");//订单超时时间
        map.put("mhtCharset","UTF-8");
        map.put("mhtSignType","MD5");
        map.put("mhtOrderStartTime", DateUtil.getCurDateTimeFormat(DateUtil.DATE_FORMAT_COMPACTFULL));//订单开始时间
        map.put("mhtLimitPay","0");//no_credit,不能使用信用卡
        if(outputType != null) {
            map.put("outputType", String.valueOf(outputType));
        }
        if(mhtSubMchId != null && !mhtSubMchId.trim().equals("")){
            map.put("mhtSubMchId", mhtSubMchId);
        }
        if(orderDetail.getMhtGoodsTag() != null && !orderDetail.getMhtGoodsTag().trim().equals("")){
            map.put("mhtGoodsTag", orderDetail.getMhtGoodsTag());
        }
        //微信小程序
        if(orderDetail instanceof OrderDetail4WxApp){
            if(((OrderDetail4WxApp)orderDetail).getDiscountAmt() != null){
                map.put("discountAmt", String.valueOf(((OrderDetail4WxApp)orderDetail).getDiscountAmt()));
            }
            if(((OrderDetail4WxApp)orderDetail).getOriMhtOrderAmt() != null){
                map.put("oriMhtOrderAmt", String.valueOf(((OrderDetail4WxApp)orderDetail).getOriMhtOrderAmt()));
            }
        }

        if(mhtSubAppId != null && !mhtSubAppId.trim().equals("")){
            map.put("mhtSubAppId", mhtSubAppId);
        }
        if(consumerId != null && !consumerId.trim().equals("")){
            map.put("consumerId", consumerId);
        }
        if(mhtReserved != null && !mhtReserved.trim().equals("")){
            map.put("mhtReserved", mhtSubAppId);
        }

        map.put("appId",app.getAppId());
        map.put("mhtOrderNo", RandomUtil.getRandomStr(13));//订单号
        map.put("mhtOrderName",orderDetail.getMhtOrderName());
        map.put("mhtOrderAmt",String.valueOf(orderDetail.getMhtOrderAmt()));//金额
        map.put("mhtOrderDetail",orderDetail.getMhtOrderDetail());
        if(notifyUrl != null && !notifyUrl.equals("")){
            map.put("notifyUrl",notifyUrl);
        }
        if(frontNotifyUrl != null && !frontNotifyUrl.equals("")){
            map.put("frontNotifyUrl", frontNotifyUrl);
        }
        map.put("deviceType",deviceType);
        map.put("payChannelType",payChannelType);//13微信 12支付宝 25手Q

        String sign = SecretUtil.ToMd5(postFormLinkReport(map) +"&" + SecretUtil.ToMd5(app.getAppKey(),"UTF-8",null),"UTF-8",null);
        map.put("mhtSignature",sign);
        map.put("appKey",app.getAppKey());

        String content = "";
        for (Map.Entry<String, String> entry : map.entrySet()) {
            content += entry.getKey()+"=";
            try {
                content += URLEncoder.encode(entry.getValue(),"UTF-8")+"&";
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        content = content.substring(0,content.length()-1);
        String result = null;
        try {
            result = HttpKit.postRequest(URL,content);
        } catch (Exception e) {
            e.printStackTrace();
        }


        if(!deviceType.equals("0600") && !deviceType.equals("0601")  && !deviceType.equals("04")){
            try {
                return URLDecoder.decode(result,"UTF-8");
            } catch (UnsupportedEncodingException e) {
                return null;
            }
        }
        return result;
    }





    protected String postFormLinkReport(Map dataMap) {
        StringBuilder reportBuilder = new StringBuilder();

        List<String> keyList = new ArrayList<String>(dataMap.keySet());
        Collections.sort(keyList);

        for (String key : keyList) {
            reportBuilder.append(key + "=" + dataMap.get(key) + "&");
        }

        reportBuilder.deleteCharAt(reportBuilder.lastIndexOf("&"));

        return reportBuilder.toString();
    }


    protected Map form2Map(String s) {
        Map result = new HashMap();
        for(String tmp : s.split("&")){
            result.put(tmp.split("=")[0],tmp.split("=")[1]);
        }
        return result;
    }
}
