package cn.ipaynow.pay.sdk;


import cn.ipaynow.pay.BasePay;
import cn.ipaynow.pay.sdk.req.App;
import cn.ipaynow.pay.sdk.req.OrderDetail;
import cn.ipaynow.pay.sdk.req.OrderDetail4WxApp;
import cn.ipaynow.pay.sdk.req.ResultType;
import cn.ipaynow.pay.sdk.resp.Result;
import cn.ipaynow.pay.sdk.resp.ResultScan05;
import cn.ipaynow.pay.sdk.resp.ResultScan08;
import cn.ipaynow.util.DateUtil;
import cn.ipaynow.util.HttpKit;
import cn.ipaynow.util.RandomUtil;
import cn.ipaynow.util.SecretUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ipaynow1130 on 2017/10/9.
 */
public class PaySdk extends BasePay {

    /**
     * 微信被扫支付
     * @param app appId(应用ID)和appKey ,
     * 登录商户后台 : https://mch.ipaynow.cn ->商户中心->应用信息可以新增应用或查看appKey
     * @param orderDetail 商品名称,商品描述,商品价格(单位分),商品标记(用于营销活动)
     * @param mhtSubAppId 微信子号对应多个公众号的时候必填,如果只对应一个公众号则不传
     * @param notifyUr 后台通知地址
     * @param channelAuthCode 支付码
     * @return  respResult 应答码 responseMsg 应答消息 mhtOrderNo 商户订单号 nowPayOrderNo 现在支付订单号 responseTime 相应时间 payResult 支付结果
     */
    public ResultScan05 wx_scan_05(App app, OrderDetail orderDetail,String mhtSubAppId, String notifyUr, String channelAuthCode){
        String resp = pay(app, orderDetail,channelAuthCode,null,null,
                "05",mhtSubAppId,null,null,notifyUr,"","13",null);

        ResultScan05 resultScan05 = new ResultScan05();

        Map<String,String> map = form2Map(resp);
        Result result = getResultByMap(map,app);

        resultScan05.setMhtOrderNo(result.getMhtOrderNo());
        resultScan05.setNowPayOrderNo(result.getNowPayOrderNo());
        resultScan05.setResponseMsg(result.getResponseMsg());
        resultScan05.setResponseTime(result.getResponseTime());
        resultScan05.setRespResult(result.getRespResult());

        if(map.get("transStatus") != null && !map.get("transStatus").equals("")){
            if(map.get("transStatus").equals(ResultScan05.PayResult.SUCESS.getCode())){
                resultScan05.setPayResult(ResultScan05.PayResult.SUCESS);
            }else if(map.get("transStatus").equals(ResultScan05.PayResult.FAIL.getCode())){
                resultScan05.setPayResult(ResultScan05.PayResult.FAIL);
            }else if(map.get("transStatus").equals(ResultScan05.PayResult.TOBESUCESS.getCode())){
                resultScan05.setPayResult(ResultScan05.PayResult.TOBESUCESS);
            }else if(map.get("transStatus").equals(ResultScan05.PayResult.CLOSE.getCode())){
                resultScan05.setPayResult(ResultScan05.PayResult.CLOSE);
            }else{
                resultScan05.setPayResult(ResultScan05.PayResult.UNKNOWN);
            }
        }
        return resultScan05;
    }


    /**
     * 支付宝被扫支付
     * @param app appId(应用ID)和appKey ,
     * 登录商户后台 : https://mch.ipaynow.cn ->商户中心->应用信息可以新增应用或查看appKey
     * @param orderDetail 商品名称,商品描述,商品价格(单位分),商品标记(用于营销活动)
     * @param notifyUr 后台通知地址
     * @param channelAuthCode 支付码
     * @return  respResult 应答码 responseMsg 应答消息 mhtOrderNo 商户订单号 nowPayOrderNo 现在支付订单号 responseTime 相应时间 payResult 支付结果
     */
    public ResultScan05 ali_scan_05(App app, OrderDetail orderDetail, String notifyUr, String channelAuthCode) {
        String resp = pay(app, orderDetail,channelAuthCode,null,null,
                "05",null,null,null,notifyUr,null,"12",null);

        ResultScan05 resultScan05 = new ResultScan05();

        Map<String,String> map = form2Map(resp);
        Result result = getResultByMap(map,app);

        resultScan05.setMhtOrderNo(result.getMhtOrderNo());
        resultScan05.setNowPayOrderNo(result.getNowPayOrderNo());
        resultScan05.setResponseMsg(result.getResponseMsg());
        resultScan05.setResponseTime(result.getResponseTime());
        resultScan05.setRespResult(result.getRespResult());

        if(map.get("transStatus") != null && !map.get("transStatus").equals("")){
            if(map.get("transStatus").equals(ResultScan05.PayResult.SUCESS.getCode())){
                resultScan05.setPayResult(ResultScan05.PayResult.SUCESS);
            }else if(map.get("transStatus").equals(ResultScan05.PayResult.FAIL.getCode())){
                resultScan05.setPayResult(ResultScan05.PayResult.FAIL);
            }else if(map.get("transStatus").equals(ResultScan05.PayResult.TOBESUCESS.getCode())){
                resultScan05.setPayResult(ResultScan05.PayResult.TOBESUCESS);
            }else if(map.get("transStatus").equals(ResultScan05.PayResult.CLOSE.getCode())){
                resultScan05.setPayResult(ResultScan05.PayResult.CLOSE);
            }else{
                resultScan05.setPayResult(ResultScan05.PayResult.UNKNOWN);
            }
        }
        return resultScan05;
    }




















    /**
     * 微信主扫支付
     * @param app appId(应用ID)和appKey ,
     * 登录商户后台 : https://mch.ipaynow.cn ->商户中心->应用信息可以新增应用或查看appKey
     * @param orderDetail 商品名称,商品描述,商品价格(单位分),商品标记(用于营销活动)
     * @param mhtSubAppId 微信子号对应多个公众号的时候必填,如果只对应一个公众号则不传
     * @param notifyUrl 后台通知地址
     * @param resultType PIC: tn为二维码图片(data:..格式)  URL : tn为支付链接
     * @return  respResult 应答码 responseMsg 应答消息 mhtOrderNo 商户订单号 nowPayOrderNo 现在支付订单号 responseTime 相应时间
     * tn 二维码图片, 或者支付链接
     */
    public ResultScan08 wx_scan_08(App app, OrderDetail orderDetail, String mhtSubAppId,String notifyUrl,ResultType resultType){
        String resp = pay(app, orderDetail,null,null,null,
                "08",mhtSubAppId,null,null,notifyUrl,"","13",resultType == ResultType.PIC?0:1);
        ResultScan08 resultScan08 = new ResultScan08();

        Map<String,String> map = form2Map(resp);
        Result result = getResultByMap(map,app);

        resultScan08.setMhtOrderNo(result.getMhtOrderNo());
        resultScan08.setNowPayOrderNo(result.getNowPayOrderNo());
        resultScan08.setResponseMsg(result.getResponseMsg());
        resultScan08.setResponseTime(result.getResponseTime());
        resultScan08.setRespResult(result.getRespResult());

        if(map.get("tn") != null && !map.get("tn").equals("")){
            resultScan08.setTn(map.get("tn"));
        }

        return resultScan08;
    }

    /**
     * 支付宝主扫支付
     * @param app appId(应用ID)和appKey ,
     * 登录商户后台 : https://mch.ipaynow.cn ->商户中心->应用信息可以新增应用或查看appKey
     * @param orderDetail 商品名称,商品描述,商品价格(单位分),商品标记(用于营销活动)
     * @param notifyUrl 后台通知地址
     * @param resultType PIC: tn为二维码图片(data:..格式)  URL : tn为支付链接
     * @return  respResult 应答码 responseMsg 应答消息 mhtOrderNo 商户订单号 nowPayOrderNo 现在支付订单号 responseTime 相应时间
     * tn 二维码图片, 或者支付链接
     */
    public ResultScan08 ali_scan_08(App app, OrderDetail orderDetail, String notifyUrl,ResultType resultType) {
        String resp = pay(app, orderDetail,null,null,null,
                "08",null,null,null,notifyUrl,null,"12",resultType == ResultType.PIC?0:1);
        ResultScan08 resultScan08 = new ResultScan08();
        Map<String,String> map = form2Map(resp);
        Result result = getResultByMap(map,app);

        resultScan08.setMhtOrderNo(result.getMhtOrderNo());
        resultScan08.setNowPayOrderNo(result.getNowPayOrderNo());
        resultScan08.setResponseMsg(result.getResponseMsg());
        resultScan08.setResponseTime(result.getResponseTime());
        resultScan08.setRespResult(result.getRespResult());

        if(map.get("tn") != null && !map.get("tn").equals("")){
            resultScan08.setTn(map.get("tn"));
        }

        return resultScan08;
    }


















    /**
     * 微信公众号支付
     * @param app appId(应用ID)和appKey ,
     * 登录商户后台 : https://mch.ipaynow.cn ->商户中心->应用信息可以新增应用或查看appKey
     * @param orderDetail 商品名称,商品描述,商品价格(单位分),商品标记(用于营销活动)
     * @param notifyUrl 后台通知地址
     * @param frontNotifyUrl 前台页面跳转地址
     * @return  重定向地址
     */
    public String wx_p_account(App app, OrderDetail orderDetail,String notifyUrl,String frontNotifyUrl){
        return pay(app, orderDetail,null,null,null,
                "0600",null,null,null,notifyUrl,frontNotifyUrl,"13",0);
    }


    /**
     * 微信公众号支付(获取支付要素)
     * @param app appId(应用ID)和appKey ,
     * 登录商户后台 : https://mch.ipaynow.cn ->商户中心->应用信息可以新增应用或查看appKey
     * @param orderDetail 商品名称,商品描述,商品价格(单位分),商品标记(用于营销活动)
     * @param mhtSubAppId 微信子号对应多个公众号的时候必填,如果只对应一个公众号则不传
     * @param consumerId 消费者ID,相当于公众号下的OPENID
     * @return
     * 返回支付信息，格式为:
     * timeStamp%3dtimeStamp%26nonceStr%3dnonceStr%26prepay_id%3dprepay_id%26wxAppId%3dwxAppId%26paySign%3dpaySign%26signType%3dMD5(需要URLDecode)
     */
    public String wx_p_account_getParams(App app, OrderDetail orderDetail,String mhtSubAppId,String consumerId){
        return pay(app, orderDetail,null,null,null,
                "0600",mhtSubAppId,consumerId,null,null,null,"13",1);
    }



    /**
     * 支付宝公众号支付
     * @param app appId(应用ID)和appKey ,
     * 登录商户后台 : https://mch.ipaynow.cn ->商户中心->应用信息可以新增应用或查看appKey
     * @param orderDetail 商品名称,商品描述,商品价格(单位分),商品标记(用于营销活动)
     * @param notifyUrl 后台通知地址
     * @param frontNotifyUrl 前台页面跳转地址
     * @return  重定向地址
     */
    public  String ali_p_account(App app, OrderDetail orderDetail,String notifyUrl,String frontNotifyUrl) {
        return pay(app, orderDetail,null,null,null,
                "0600",null,null,null,notifyUrl,frontNotifyUrl,"12",0);
    }

    /**
     * 支付宝公众号支付(获取支付要素)
     * @param app appId(应用ID)和appKey ,
     * 登录商户后台 : https://mch.ipaynow.cn ->商户中心->应用信息可以新增应用或查看appKey
     * @param orderDetail 商品名称,商品描述,商品价格(单位分),商品标记(用于营销活动)
     * @param consumerId 消费者ID,相对于支付宝账号的userid
     * 返回支付信息，格式为:
     * timeStamp%3dtimeStamp%26nonceStr%3dnonceStr%26prepay_id%3dprepay_id%26wxAppId%3dwxAppId%26paySign%3dpaySign%26signType%3dMD5(需要URLDecode)
     */
    public  String ali_p_account_getParams(App app, OrderDetail orderDetail,String consumerId) {
        return pay(app,orderDetail,null,null,null,
                "0600",null,consumerId,null,null,null,"12",1);
    }












    /**
     * 微信H5
     * @param app appId(应用ID)和appKey ,
     * 登录商户后台 : https://mch.ipaynow.cn ->商户中心->应用信息可以新增应用或查看appKey
     * @param orderDetail 商品名称,商品描述,商品价格(单位分),商品标记(用于营销活动)
     * @param consumerCreateIp 用户支付IP
     * @param notifyUrl 后台通知地址
     * @param frontNotifyUrl 前台页面跳转地址
     * @return  重定向地址
     */
    public String wx_h5(App app, OrderDetail orderDetail,String consumerCreateIp,String notifyUrl,String frontNotifyUrl){
        String result = pay(app,orderDetail,null,consumerCreateIp,null,
                "0601",null,null,null,notifyUrl,frontNotifyUrl,"13",1);
        //outType 0 tn 获取微信支付链接,支付后不跳转
        //outType 1 tn 获取现在支付链接,调起微信支付,支付后按照frontNotifyUrl跳转
        //outTpye 2 直接返回现在支付链接（和outType = 1 中的tn相同），无需从tn中获取
        Map<String,String> map = form2Map(result);
        return map.get("tn");
    }




    /**
     * 支付宝H5
     * @param app appId(应用ID)和appKey ,
     * 登录商户后台 : https://mch.ipaynow.cn ->商户中心->应用信息可以新增应用或查看appKey
     * @param orderDetail 商品名称,商品描述,商品价格(单位分),商品标记(用于营销活动)
     * @param notifyUrl 后台通知地址
     * @param frontNotifyUrl 前台页面跳转地址
     * @return  重定向地址
     */
    public  String ali_h5(App app, OrderDetail orderDetail,String notifyUrl,String frontNotifyUrl) {
        String result = pay(app,orderDetail,null,null,null,
                "0601",null,null,null,notifyUrl,frontNotifyUrl,"12",1);

        Map<String,String> map = form2Map(result);
        return map.get("tn");
    }













    /**
     * 支付宝网页web
     * @param app appId(应用ID)和appKey ,
     * 登录商户后台 : https://mch.ipaynow.cn ->商户中心->应用信息可以新增应用或查看appKey
     * @param orderDetail 商品名称,商品描述,商品价格(单位分),商品标记(用于营销活动)
     * @param notifyUrl 后台通知地址
     * @return  重定向地址
     */
    public String ali_web(App app, OrderDetail orderDetail, String notifyUrl){
        String result = pay(app,orderDetail,null,null,null,
                "04",null,null,null,notifyUrl,null,"12",0);
        //tn 0 返回支付宝跳转地址，tn获取
        // tn 1 直接返回支付宝跳转地址，无需tn获取
        Map<String,String> map = form2Map(result);
        return map.get("tn");
    }




    /**
     * 微信小程序支付
     * @param app appId(应用ID)和appKey ,
     * 登录商户后台 : https://mch.ipaynow.cn ->商户中心->应用信息可以新增应用或查看appKey
     * @param orderDetail   商品名称,商品描述,商品价格(单位分),商品标记(用于营销活动),
     * @param consumerId  用户openId
     * @param notifyUrl 后台通知地址
     * @return  支付要素
     */
    public String wx_app(App app, OrderDetail4WxApp orderDetail, String consumerId,String notifyUrl){
        String result = pay(app,orderDetail,null,null,null,
                "14",null,consumerId,null,notifyUrl,null,"13",1);
        //tn 0 返回支付宝跳转地址，tn获取
        // tn 1 直接返回支付宝跳转地址，无需tn获取
        Map<String,String> map = form2Map(result);
        return map.get("tn");
    }
























    private Result getResultByMap(Map<String,String> map, App app){
        String respSignature = map.get("signature");
        map.remove("signature");
        String sign = SecretUtil.ToMd5(postFormLinkReport(map) +"&" + SecretUtil.ToMd5(app.getAppKey(),"UTF-8",null),"UTF-8",null);

        if(!sign.equals(respSignature)) {
//            throw new RuntimeException("can resp signature");
        }
        if(!map.get("appId").equals(app.getAppId())) {
            throw new RuntimeException("resp appId err");
        }
        if(!map.get("signType").equals("MD5")) {
            throw new RuntimeException("resp signType err");
        }
        if(!map.get("funcode").equals("WP001")) {
            throw new RuntimeException("resp funcode err");
        }
        if(!map.get("version").equals("1.0.0")) {
            throw new RuntimeException("resp version err");
        }



        Result result = new Result();
        if(map.get("mhtOrderNo") != null && !map.get("mhtOrderNo").equals("")){
            result.setMhtOrderNo(map.get("mhtOrderNo"));
        }
        if(map.get("nowPayOrderNo") != null && !map.get("nowPayOrderNo").equals("")){
            result.setNowPayOrderNo(map.get("nowPayOrderNo"));
        }
        if(map.get("responseTime") != null && !map.get("responseTime").equals("")){
            result.setResponseTime(map.get("responseTime"));
        }
        if(map.get("responseMsg") != null && !map.get("responseMsg").equals("")){
            result.setResponseMsg(map.get("responseMsg"));
        }
        if(map.get("responseCode") != null && !map.get("responseCode").equals("")){
            if(map.get("responseCode").equals(ResultScan05.RespResult.SUCESS.getCode())){
                result.setRespResult(ResultScan05.RespResult.SUCESS);
            }else if(map.get("responseCode").equals(ResultScan05.RespResult.FAIL.getCode())){
                result.setRespResult(ResultScan05.RespResult.FAIL);
            }else{
                result.setRespResult(ResultScan05.RespResult.UNKNOWN);
            }
        }
        return result;
    }





    /**
     * 商户支付订单查询
     * @param mhtOrderNo    商户订单号
     * @param app appId(应用ID)和appKey ,
     * 登录商户后台 : https://mch.ipaynow.cn ->商户中心->应用信息可以新增应用或查看appKey
     * @param deviceType    被扫05，主扫08，公众号传0600，h5传0601，网页04
     * @return
     */
    public Map queryOrder(String mhtOrderNo,App app,String deviceType){

        Map<String,String> map = new HashMap<>();

        map.put("funcode","MQ002");
        map.put("version","1.0.0");
        map.put("deviceType",deviceType);
        map.put("appId",app.getAppId());
        map.put("mhtOrderNo", mhtOrderNo);
        map.put("mhtCharset","UTF-8");
        map.put("mhtSignType","MD5");

        String sign = SecretUtil.ToMd5(postFormLinkReport(map) +"&" + SecretUtil.ToMd5(app.getAppKey(),"UTF-8",null),"UTF-8",null);
        map.put("mhtSignature",sign);

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
            result = HttpKit.postRequest("https://pay.ipaynow.cn",content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return form2Map(result);
    }

//&funcode=MQ002
//&version=1.0.0
//&appId=150753086263684
//&mhtOrderNo=QkMsFD70giC5J
//&mhtOrderName=测试商品!@#
//mhtSubMchId
//&mhtOrderType=01
//&mhtCurrencyType=156
//&mhtOrderAmt=1
//&oriMhtOrderAmt=1
//&discountAmt=0
//&mhtOrderTimeOut=2000
//&mhtOrderStartTime=20171020112739
//&mhtCharset=UTF-8
//&deviceType=08
//&payChannelType=13
//&nowPayOrderNo=200301201710201127400839408
//&channelOrderNo=4200000012201710209160081310
//&payTime=20171020112755
//&payConsumerId=oeMyBjlVC2s9swo3vyAw_lFG4Dxw
//&transStatus=A001
//mhtReserved
//bankType
//cardType
//&responseTime=20171020112946
//&responseCode=A001
//&responseMsg=E000#成功[成功]
//&signType=MD5
//&signature=ba6e58581182f4023ff9b684bbdc8623











    public static void main(String [] args){
//        System.out.println(new PaySdk().refundOrder("150753082825470","8jTST7ywIBY0QQ3RlcxWEl08Xj9gaYyQ","sC31JGZh4XD6e",1,"somereason"));

//        System.out.println(new PaySdk().refundQuery("150753082825470","8jTST7ywIBY0QQ3RlcxWEl08Xj9gaYyQ","aB4SafbCGN1HN3mKUROP"));

//        System.out.println(new PaySdk().backOrder("150753082825470","8jTST7ywIBY0QQ3RlcxWEl08Xj9gaYyQ","6Idked0OrrGnY","somereason"));

//        System.out.println(new PaySdk().backQuery("150753082825470","8jTST7ywIBY0QQ3RlcxWEl08Xj9gaYyQ","lftc2Ca019HdpFKUuXFQ"));

    }


    /**
     * 退款
     * @param app appId(应用ID)和appKey ,
     * 登录商户后台 : https://mch.ipaynow.cn ->商户中心->应用信息可以新增应用或查看appKeyey
     * @param mhtOrderNo    商户订单号
     * @param amount    退款金额(分)
     * @param reason    退款原因
     * @return
     */
    public Map refundOrder(App app,String mhtOrderNo,Integer amount,String reason){
        Map<String,String> map = new HashMap<>();

        map.put("funcode","R001");
        map.put("appId",app.getAppId());
        map.put("mhtOrderNo",mhtOrderNo);
        map.put("mhtRefundNo", RandomUtil.getRandomStr(20));
        map.put("amount",String.valueOf(amount));
        if(reason != null && !reason.trim().equals("")) {
            map.put("reason", reason);
        }
        map.put("mhtCharset","UTF-8");


        String sign = SecretUtil.ToMd5(postFormLinkReport(map) +"&" + SecretUtil.ToMd5(app.getAppKey(),"UTF-8",null),"UTF-8",null);
        map.put("mhtSignature",sign);
        map.put("signType","MD5");

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
            result = HttpKit.postRequest("https://pay.ipaynow.cn/refund/refundOrder",content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return form2Map(result);
//        amount=1&appId=150753082825470&funcode=R001&mhtCharset=UTF-8&mhtOrderNo=sC31JGZh4XD6e&mhtRefundNo=aB4SafbCGN1HN3mKUROP&nowPayOrderNo=200801201710261101140355397&reason=somereason&responseCode=R000&responseMsg=%E6%88%90%E5%8A%9F&responseTime=20171026110114&signType=MD5&signature=913601d72cc265ee6b4351b3e3f6ec00&tradeStatus=A004

    }


    /**
     * 退款查询
     * @param app appId(应用ID)和appKey ,
     * 登录商户后台 : https://mch.ipaynow.cn ->商户中心->应用信息可以新增应用或查看appKeyey
     * @param mhtRefundNo   商户退款单号
     * @return
     */
    public Map refundQuery(App app,String mhtRefundNo){
        Map<String,String> map = new HashMap<>();

        map.put("funcode","Q001");
        map.put("appId",app.getAppId());
        map.put("mhtRefundNo",mhtRefundNo);
        map.put("mhtCharset","UTF-8");


        String sign = SecretUtil.ToMd5(postFormLinkReport(map) +"&" + SecretUtil.ToMd5(app.getAppKey(),"UTF-8",null),"UTF-8",null);
        map.put("mhtSignature",sign);
        map.put("signType","MD5");

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
            result = HttpKit.postRequest("https://pay.ipaynow.cn/refund/refundQuery",content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return form2Map(result);
//        amount=&appId=150753082825470&funcode=Q001&mhtCharset=UTF-8&mhtOrderNo=&mhtRefundNo=aB4SafbCGN1HN3mKUROP&nowPayOrderNo=200801201710261101140355397&reason=&responseCode=R000&responseMsg=%E6%88%90%E5%8A%9F&responseTime=20171026110452&signType=MD5&signature=ed77af86a394037e16ce5bac5ce7de5b&tradeStatus=A001

    }






    /**
     * 撤销(只能撤销当天的交易,且无论成功失败(逻辑包含退款))
     * @param app appId(应用ID)和appKey ,
     * 登录商户后台 : https://mch.ipaynow.cn ->商户中心->应用信息可以新增应用或查看appKeyey
     * @param mhtOrderNo    商户订单号
     * @param reason    退款原因
     * @return
     */
    public Map backOrder(App app,String mhtOrderNo,String reason){
        Map<String,String> map = new HashMap<>();

        map.put("funcode","R002");
        map.put("appId",app.getAppId());
        map.put("mhtOrderNo",mhtOrderNo);
        map.put("mhtRefundNo", RandomUtil.getRandomStr(20));

        if(reason != null && !reason.trim().equals("")) {
            map.put("reason", reason);
        }
        map.put("mhtCharset","UTF-8");


        String sign = SecretUtil.ToMd5(postFormLinkReport(map) +"&" + SecretUtil.ToMd5(app.getAppKey(),"UTF-8",null),"UTF-8",null);
        map.put("mhtSignature",sign);
        map.put("signType","MD5");

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
            result = HttpKit.postRequest("https://pay.ipaynow.cn/refund/refundOrder",content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return form2Map(result);
//        amount=&appId=150753082825470&funcode=R002&mhtCharset=UTF-8&mhtOrderNo=6Idked0OrrGnY&mhtRefundNo=lftc2Ca019HdpFKUuXFQ&nowPayOrderNo=200802201710261108530355402&reason=somereason&responseCode=R000&responseMsg=%E6%88%90%E5%8A%9F&responseTime=20171026110853&signType=MD5&signature=5cca50410f20a31c2ea5b76f13375823&tradeStatus=A004

    }






    /**
     * 撤销查询
     * @param app appId(应用ID)和appKey ,
     * 登录商户后台 : https://mch.ipaynow.cn ->商户中心->应用信息可以新增应用或查看appKeyey
     * @param mhtRefundNo   商户退款单号
     * @return
     */
    public Map backQuery(App app,String mhtRefundNo){
        Map<String,String> map = new HashMap<>();

        map.put("funcode","Q002");
        map.put("appId",app.getAppId());
        map.put("mhtRefundNo",mhtRefundNo);
        map.put("mhtCharset","UTF-8");

        String sign = SecretUtil.ToMd5(postFormLinkReport(map) +"&" + SecretUtil.ToMd5(app.getAppKey(),"UTF-8",null),"UTF-8",null);
        map.put("mhtSignature",sign);
        map.put("signType","MD5");

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
            result = HttpKit.postRequest("https://pay.ipaynow.cn/refund/refundQuery",content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return form2Map(result);
//        amount=&appId=150753082825470&funcode=Q002&mhtCharset=UTF-8&mhtOrderNo=&mhtRefundNo=lftc2Ca019HdpFKUuXFQ&nowPayOrderNo=200802201710261108530355402&reason=&responseCode=R000&responseMsg=%E6%88%90%E5%8A%9F&responseTime=20171026111003&signType=MD5&signature=9cca5c1c06b9fa8dbcbbd706eb14e075&tradeStatus=A001
    }
}
