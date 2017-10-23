package cn.ipaynow.ipaynow_pay_demo.server;

import cn.ipaynow.ipaynow_pay_demo.util.CommonUtil;
import cn.ipaynow.pay.sdk.PaySdk;
import cn.ipaynow.pay.sdk.req.App;
import cn.ipaynow.pay.sdk.req.OrderDetail;
import cn.ipaynow.pay.sdk.req.ResultType;
import cn.ipaynow.pay.sdk.resp.ResultScan05;
import cn.ipaynow.pay.sdk.resp.ResultScan08;
import com.aspire.nm.component.miniServer.Controll;
import com.aspire.nm.component.miniServer.annotation.Controller;
import com.aspire.nm.component.miniServer.annotation.Controller.MethodType;
import com.aspire.nm.component.miniServer.annotation.cls.Controllers;
import com.aspire.nm.component.miniServer.annotation.method.*;
import com.aspire.nm.component.miniServer.handle.Redirect;
import com.aspire.nm.component.miniServer.handle.ViewModel;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;



//  http://127.0.0.1:7072/paytest/index.html
//  https://op-tester.ipaynow.cn/paytest/index.html


@Controllers(path="paytest/")
public class PaytestImpl implements Controll {

    private PaySdk paySdk = new PaySdk();


    //微信被扫
    @Controller(pathPattern = "wx_scan_05",methodType=MethodType.POST)
    public ViewModel wx_scan_05(@Req String postDate1){
        Map<String,String> map = CommonUtil.form2Map(postDate1);
        ResultScan05 resultScan05 = paySdk.wx_scan_05(
                new App(map.get("appId"),map.get("appKey")),
                new OrderDetail(map.get("mhtOrderName"),map.get("mhtOrderDetail"),Integer.parseInt(map.get("mhtOrderAmt"))),
                map.get("notifyUrl"),
                map.get("channelAuthCode"));


        Map vmMap = new HashMap();
        vmMap.put("result", resultScan05);
        return new ViewModel("paytest/wx_scan_05_template",vmMap);

// appId=150753082825470
// &channelOrderNo=4200000026201710178563691122
// &deviceType=05&funcode=N001
// &mhtCharset=UTF-8
// &mhtCurrencyType=156
// &mhtOrderAmt=1
// &mhtOrderName=%E6%B5%8B%E8%AF%95%E5%95%86%E5%93%81%21%40%23
// &mhtOrderNo=gSWjK1XE7epRd
// &mhtOrderStartTime=20171017105251
// &mhtOrderTimeOut=2000
// &mhtOrderType=01
// &mhtSubMchId=
// &nowPayOrderNo=200701201710171052521078180
// &payChannelType=13
// &payConsumerId=oeMyBjlVC2s9swo3vyAw_lFG4Dxw
// &payTime=20171017105305
// &signType=MD5
// &signature=579ada44a4a2e0dc074854837c995e45
// &transStatus=A001
// &version=1.0.0
    }



    //微信主扫
    @Controller(pathPattern = "wx_scan_08",methodType=MethodType.POST)
    public ViewModel wx_scan_08(@Req String postDate1){
        Map<String,String> map = CommonUtil.form2Map(postDate1);
        ResultScan08 resultScan08 = paySdk.wx_scan_08(
                new App(map.get("appId"),map.get("appKey")),
                new OrderDetail(map.get("mhtOrderName"),map.get("mhtOrderDetail"),Integer.parseInt(map.get("mhtOrderAmt"))),
                map.get("notifyUrl"),
                ResultType.PIC);


        Map vmMap = new HashMap();
        vmMap.put("result", resultScan08);
        return new ViewModel("paytest/wx_scan_08_template",vmMap);
//appId=150753086263684
// &channelOrderNo=4200000029201710178563824222
// &deviceType=08
// &discountAmt=0
// &funcode=N001
// &mhtCharset=UTF-8
// &mhtCurrencyType=156
// &mhtOrderAmt=1
// &mhtOrderName=%E6%B5%8B%E8%AF%95%E5%95%86%E5%93%81%21%40%23
// &mhtOrderNo=J6wO3VRT241rd
// &mhtOrderStartTime=20171017105508
// &mhtOrderTimeOut=2000
// &mhtOrderType=01
// &nowPayOrderNo=200301201710171055080804734
// &oriMhtOrderAmt=1
// &payChannelType=13
// &payConsumerId=oeMyBjlVC2s9swo3vyAw_lFG4Dxw
// &payTime=20171017105526
// &signType=MD5
// &signature=3e8cf9d0f33175ffd60dc7429ca7c8ed
// &transStatus=A001
// &version=1.0.0
    }


    //微信公众号
    @Controller(pathPattern = "wx_p_account",methodType=MethodType.POST)
    public Redirect wx_p_account(@Req String postDate1){
        Map<String,String> map = CommonUtil.form2Map(postDate1);
        String redirectUrl = paySdk.wx_p_account(
                new App(map.get("appId"),map.get("appKey")),
                new OrderDetail(map.get("mhtOrderName"),map.get("mhtOrderDetail"),Integer.parseInt(map.get("mhtOrderAmt"))),
                map.get("notifyUrl"),
                map.get("frontNotifyUrl"));
        return new Redirect(redirectUrl);

//appId=150753094138037
// &channelOrderNo=4200000020201710178565407835
// &deviceType=0600
// &funcode=N001
// &mhtCharset=UTF-8
// &mhtCurrencyType=156
// &mhtOrderAmt=1
// &mhtOrderName=%E6%B5%8B%E8%AF%95%E5%95%86%E5%93%81%21%40%23
// &mhtOrderNo=AUuruWBzXTJBT
// &mhtOrderStartTime=20171017105852
// &mhtOrderTimeOut=2000
// &mhtOrderType=01
// &nowPayOrderNo=200406201710171058520924776
// &payChannelType=13
// &payConsumerId=oeMyBjlVC2s9swo3vyAw_lFG4Dxw
// &payTime=20171017105901
// &signType=MD5
// &signature=7b65dc16078347dd3128b4ebe64b7f97
// &transStatus=A001
// &version=1.0.0
    }


    //支付宝主扫
    @Controller(pathPattern = "ali_scan_08",methodType=MethodType.POST)
    public ViewModel ali_scan_08(@Req String postDate1){
        Map<String,String> map = CommonUtil.form2Map(postDate1);
        ResultScan08 resultScan08 = paySdk.ali_scan_08(
                new App(map.get("appId"),map.get("appKey")),
                new OrderDetail(map.get("mhtOrderName"),map.get("mhtOrderDetail"),Integer.parseInt(map.get("mhtOrderAmt"))),
                map.get("notifyUrl"),ResultType.PIC);


        Map vmMap = new HashMap();
        vmMap.put("result", resultScan08);
        return new ViewModel("paytest/ali_scan_08_template",vmMap);
//appId=150753086263684
// &channelOrderNo=2017101721001004315200087738
// &deviceType=08
// &discountAmt=0
// &funcode=N001
// &mhtCharset=UTF-8
// &mhtCurrencyType=156
// &mhtOrderAmt=1
// &mhtOrderName=%E6%B5%8B%E8%AF%95%E5%95%86%E5%93%81%21%40%23
// &mhtOrderNo=hyeYhgArmDIOs
// &mhtOrderStartTime=20171017111612
// &mhtOrderTimeOut=2000
// &mhtOrderType=01
// &nowPayOrderNo=200301201710171116120804789
// &oriMhtOrderAmt=1
// &payChannelType=12
// &payConsumerId=2088102421818315
// &payTime=20171017111627
// &signType=MD5
// &signature=221859f52220eee1ffcbb873675acb1f
// &transStatus=A001
// &version=1.0.0
    }


    //支付宝被扫
    @Controller(pathPattern = "ali_scan_05",methodType=MethodType.POST)
    public ViewModel ali_scan_05(@Req String postDate1){
        Map<String,String> map = CommonUtil.form2Map(postDate1);
        ResultScan05 resultScan05 = paySdk.ali_scan_05(
                new App(map.get("appId"),map.get("appKey")),
                new OrderDetail(map.get("mhtOrderName"),map.get("mhtOrderDetail"),Integer.parseInt(map.get("mhtOrderAmt"))),
                map.get("notifyUrl"),
                map.get("channelAuthCode"));


        Map vmMap = new HashMap();
        vmMap.put("result", resultScan05);
        return new ViewModel("paytest/ali_scan_05_template",vmMap);
    }


    //支付宝公众号
    @Controller(pathPattern = "ali_p_account",methodType=MethodType.POST)
    public Redirect ali_p_account(@Req String postDate1){
        Map<String,String> map = CommonUtil.form2Map(postDate1);
        String redirectUrl = paySdk.ali_p_account(
                new App(map.get("appId"),map.get("appKey")),
                new OrderDetail(map.get("mhtOrderName"),map.get("mhtOrderDetail"),Integer.parseInt(map.get("mhtOrderAmt"))),
                map.get("notifyUrl"),
                map.get("frontNotifyUrl"));

        return new Redirect(redirectUrl);
//appId=150753094138037
// &channelOrderNo=2017101721001004315206431968
// &deviceType=0600
// &funcode=N001
// &mhtCharset=UTF-8
// &mhtCurrencyType=156
// &mhtOrderAmt=1
// &mhtOrderName=%E6%B5%8B%E8%AF%95%E5%95%86%E5%93%81%21%40%23
// &mhtOrderNo=OLR5Ur5DslmcY
// &mhtOrderStartTime=20171017115446
// &mhtOrderTimeOut=2000
// &mhtOrderType=01
// &nowPayOrderNo=200402201710171154461037072
// &payChannelType=12
// &payConsumerId=2088102421818315
// &payTime=20171017115528
// &signType=MD5
// &signature=e9a68a3608f38e1ceabb41dedc2b04e3
// &transStatus=A001
// &version=1.0.0
    }












    //支付宝h5(商户协议已到期)
    @Controller(pathPattern = "ali_h5",methodType=MethodType.POST)
    public Redirect ali_h5(@Req String postDate1) throws UnsupportedEncodingException {
        Map<String,String> map = CommonUtil.form2Map(postDate1);
        String result =  paySdk.ali_h5(
                new App(map.get("appId"),map.get("appKey")),
                new OrderDetail(map.get("mhtOrderName"),map.get("mhtOrderDetail"),Integer.parseInt(map.get("mhtOrderAmt"))),
                map.get("notifyUrl"),
                map.get("frontUrl"));

        return new Redirect(URLDecoder.decode(result,"UTF-8"));
    }




    //微信h5
    @Controller(pathPattern = "wx_h5",methodType=MethodType.POST)
    public Redirect wx_h5(@Req String postDate1) throws UnsupportedEncodingException {
        Map<String,String> map = CommonUtil.form2Map(postDate1);
        String result =  paySdk.wx_h5(
                new App(map.get("appId"),map.get("appKey")),
                new OrderDetail(map.get("mhtOrderName"),map.get("mhtOrderDetail"),Integer.parseInt(map.get("mhtOrderAmt"))),
                map.get("consumerCreateIp"),
                map.get("notifyUrl"),
                map.get("frontUrl"));

        if(result != null) {
            return new Redirect(URLDecoder.decode(result, "UTF-8"));
        }
        return null;

//appId=1482402994841173
// &channelOrderNo=4200000026201710178625253241
// &deviceType=0601
// &discountAmt=0
// &funcode=N001
// &mhtCharset=UTF-8
// &mhtCurrencyType=156
// &mhtOrderAmt=1
// &mhtOrderName=%E6%B5%8B%E8%AF%95%E5%95%86%E5%93%81%21%40%23
// &mhtOrderNo=SH5J3Uap8wrEz
// &mhtOrderStartTime=20171017162819
// &mhtOrderTimeOut=2000
// &mhtOrderType=01
// &nowPayOrderNo=200601201710171628199554027
// &oriMhtOrderAmt=1
// &payChannelType=13
// &payConsumerId=o0kRqwEjD0sT7Ub19o-jyqn5s5qM
// &payTime=20171017162854
// &signType=MD5
// &signature=b4a7b142d153c75fb8ab1e935c62f9a1
// &transStatus=A001
// &version=1.0.0

    }









    //支付宝网页web
    @Controller(pathPattern = "ali_web",methodType=MethodType.POST)
    public Redirect ali_web(@Req String postDate1) throws UnsupportedEncodingException {
        Map<String,String> map = CommonUtil.form2Map(postDate1);
        String result =  paySdk.ali_web(
                new App(map.get("appId"),map.get("appKey")),
                new OrderDetail(map.get("mhtOrderName"),map.get("mhtOrderDetail"),Integer.parseInt(map.get("mhtOrderAmt"))),
                map.get("notifyUrl"));
        if(result != null) {
            return new Redirect(URLDecoder.decode(result, "UTF-8"));
        }
        return null;

//appId=150753107733024
// &channelOrderNo=2017101821001004310279577396
// &deviceType=04
// &funcode=N001
// &mhtCharset=UTF-8
// &mhtCurrencyType=156
// &mhtOrderAmt=1
// &mhtOrderName=%E6%B5%8B%E8%AF%95%E5%95%86%E5%93%81%21%40%23
// &mhtOrderNo=AnJl8mlizvt2k
// &mhtOrderStartTime=20171018163352
// &mhtOrderTimeOut=2000
// &mhtOrderType=05
// &nowPayOrderNo=202101201710181633511460574
// &payChannelType=12
// &payTime=20171018163532
// &signType=MD5
// &signature=013cc744b7dc574713dfa186da4d1162
// &transStatus=A001
// &version=1.0.0

    }






    @Controller(pathPattern = "notify",methodType=MethodType.POST)
    public String notify(@Req String postDate){

        System.out.println(postDate);
        return "success=Y";
    }




}

