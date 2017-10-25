package cn.ipaynow.pay;

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
     * @param appId 应用ID
     * @param appKey 应用KEY
     * @param mhtOrderName 商品名称
     * @param mhtOrderDetail 商品详情
     * @param mhtOrderAmt 金额(分)
     * @param deviceType 设备类型
     * @param notifyUrl 后台通知地址
     * @param frontNotifyUrl 前台通知地址
     * @param payChannelType 微信支付宝手Q等
     */
    protected String pay(String channelAuthCode,String consumerCreateIp,String appId,String appKey,String mhtOrderName,String mhtOrderDetail , Integer mhtOrderAmt,String deviceType
            ,String notifyUrl,String frontNotifyUrl,String payChannelType,Integer outputType){

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
        map.put("mhtOrderType","01");//交易类型-普通消费
        map.put("mhtOrderTimeOut","2000");//订单超时时间
        map.put("mhtCharset","UTF-8");
        map.put("mhtSignType","MD5");
        map.put("mhtOrderStartTime", DateUtil.getCurDateTimeFormat(DateUtil.DATE_FORMAT_COMPACTFULL));//订单开始时间
        map.put("mhtLimitPay","0");//no_credit,不能使用信用卡
        if(outputType != null) {
            map.put("outputType", String.valueOf(outputType));
        }


        map.put("appId",appId);
        map.put("mhtOrderNo", RandomUtil.getRandomStr(13));//订单号
        map.put("mhtOrderName",mhtOrderName);
        map.put("mhtOrderAmt",String.valueOf(mhtOrderAmt));//金额
        map.put("mhtOrderDetail",mhtOrderDetail);
        map.put("notifyUrl",notifyUrl);
        if(frontNotifyUrl != null && !frontNotifyUrl.equals("")){
            map.put("frontNotifyUrl", frontNotifyUrl);
        }
        map.put("deviceType",deviceType);
        map.put("payChannelType",payChannelType);//13微信 12支付宝 25手Q

        String sign = SecretUtil.ToMd5(postFormLinkReport(map) +"&" + SecretUtil.ToMd5(appKey,"UTF-8",null),"UTF-8",null);
        map.put("mhtSignature",sign);
        map.put("appKey",appKey);

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
