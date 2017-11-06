
# 聚合支付SDK使用说明 #

## 版本变更记录 ##


- 1.0.0 : 初稿

- 1.1.0 : 增加退款接口

- 1.1.1 : 接口参数调整

- 1.1.2 : sdk增加可选的商户订单号参数,支付订单查询分成多个方法,增加微信支付宝外的渠道方法

- 1.1.3 : 文档增加目录及支付结果说明,使用httpclient



## 目录 ##

[1. 概述](#1)

&nbsp;&nbsp;&nbsp;&nbsp;[1.1 简介](#1.1)

&nbsp;&nbsp;&nbsp;&nbsp;[1.2 如何获取](#1.2)

[2. API](#2)

&nbsp;&nbsp;&nbsp;&nbsp;[2.1 聚合交易API](#2.1)

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[微信被扫支付](#2.1.1)

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[支付宝被扫支付](#2.1.2)

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[手Q被扫支付](#2.1.3)

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[京东被扫支付](#2.1.4)

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[银联被扫支付](#2.1.5)

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[微信主扫支付](#2.1.6)

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[支付宝主扫支付](#2.1.7)

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[手Q主扫支付](#2.1.8)

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[京东主扫支付](#2.1.9)

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[银联主扫支付](#2.1.10)

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[微信公众号支付](#2.1.11)

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[微信公众号支付(获取支付要素)](#2.1.12)

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[支付宝公众号支付](#2.1.13)

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[支付宝公众号支付(获取支付要素)](#2.1.14)

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[手Q公众号支付](#2.1.15)

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[手Q公众号支付(获取支付要素)](#2.1.16)

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[微信H5](#2.1.17)

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[支付宝H5](#2.1.18)

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[银联H5](#2.1.19)

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[招行一网通H5](#2.1.20)

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[手Q H5](#2.1.21)

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[支付宝网页web](#2.1.22)

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[银联网页web](#2.1.23)

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[微信小程序支付](#2.1.24)

&nbsp;&nbsp;&nbsp;&nbsp;[2.2 支付结果通知](#2.2)

&nbsp;&nbsp;&nbsp;&nbsp;[2.3 订单查询API](#2.3)

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[商户被扫支付订单查询](#2.3.1)

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[商户主扫支付订单查询](#2.3.2)

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[商户公众号支付订单查询](#2.3.3)

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[商户H5支付订单查询](#2.3.4)

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[商户网页支付订单查询](#2.3.5)

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[商户微信App支付订单查询](#2.3.6)

&nbsp;&nbsp;&nbsp;&nbsp;[2.4 退款API](#2.4)

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[退款](#2.4.1)

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[退款查询](#2.4.2)

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[撤销](#2.4.3)

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[撤销查询](#2.4.4)

[3. 完整DEMO](#3)


<h2 id='1'> 1. 概述 </h2>

<h4 id='1.1'> 1.1 简介 </h4>

- 聚合支付SDK。

<h4 id='1.2'> 1.2 如何获取 </h4>

[获取源码](https://github.com/ipaynowORG/ipaynow_pay)

[demo源码](https://github.com/ipaynowORG/ipaynow_pay)

Maven坐标如下

	<dependency>
	       <groupId>com.github.ipaynow</groupId>
           <artifactId>ipaynow_pay_sdk</artifactId>
           <version>1.1.3</version>
	</dependency>





<h2 id='2'> 2. API </h2>

业务客户端使用SDK的相关类: cn.ipaynow.pay.sdk.PaySdk

<h4 id='2.1'> 2.1 聚合交易API </h4>

<h5 id='2.1.1'></h4>

- 微信被扫支付

        /**
         * 微信被扫支付
         * @param app appId(应用ID)和appKey ,
         * 登录商户后台 : https://mch.ipaynow.cn ->商户中心->应用信息可以新增应用或查看appKey
         * @param orderDetail 商品名称,商品描述,商品价格(单位分),商品标记(用于营销活动)
         * @param mhtSubAppId 微信子号对应多个公众号的时候必填,如果只对应一个公众号则不传
         * @param notifyUrl 后台通知地址,详见2.2
         * @param channelAuthCode 支付码
         * @param mhtOrderNo 商户订单号,如果为空则自动生成商户订单号
         * @return  respResult 应答码 responseMsg 应答消息 mhtOrderNo 商户订单号 nowPayOrderNo 现在支付订单号 responseTime 相应时间 payResult 支付结果
         */
        public ResultScan05 wx_scan_05(App app, OrderDetail orderDetail,String mhtSubAppId, String notifyUrl, String channelAuthCode,String mhtOrderNo)

<h5 id='2.1.2'></h4>

- 支付宝被扫支付

        /**
         * 支付宝被扫支付
         * @param app appId(应用ID)和appKey ,
         * 登录商户后台 : https://mch.ipaynow.cn ->商户中心->应用信息可以新增应用或查看appKey
         * @param orderDetail 商品名称,商品描述,商品价格(单位分),商品标记(用于营销活动)
         * @param notifyUrl 后台通知地址,详见2.2
         * @param channelAuthCode 支付码
         * @param mhtOrderNo 商户订单号,如果为空则自动生成商户订单号
         * @return  respResult 应答码 responseMsg 应答消息 mhtOrderNo 商户订单号 nowPayOrderNo 现在支付订单号 responseTime 相应时间 payResult 支付结果
         */
        public ResultScan05 ali_scan_05(App app, OrderDetail orderDetail, String notifyUrl, String channelAuthCode,String mhtOrderNo)

<h5 id='2.1.3'></h4>

- 手Q被扫支付

        /**
         * 手Q被扫支付
         * @param app appId(应用ID)和appKey ,
         * 登录商户后台 : https://mch.ipaynow.cn ->商户中心->应用信息可以新增应用或查看appKey
         * @param orderDetail 商品名称,商品描述,商品价格(单位分),商品标记(用于营销活动)
         * @param notifyUrl 后台通知地址,详见2.2
         * @param channelAuthCode 支付码
         * @param mhtOrderNo 商户订单号,如果为空则自动生成商户订单号
         * @return  respResult 应答码 responseMsg 应答消息 mhtOrderNo 商户订单号 nowPayOrderNo 现在支付订单号 responseTime 相应时间 payResult 支付结果
         */
        public ResultScan05 handq_scan_05(App app, OrderDetail orderDetail, String notifyUrl, String channelAuthCode,String mhtOrderNo)

<h5 id='2.1.4'></h4>

- 京东被扫支付

        /**
         * 京东被扫支付
         * @param app appId(应用ID)和appKey ,
         * 登录商户后台 : https://mch.ipaynow.cn ->商户中心->应用信息可以新增应用或查看appKey
         * @param orderDetail 商品名称,商品描述,商品价格(单位分),商品标记(用于营销活动)
         * @param notifyUrl 后台通知地址,详见2.2
         * @param channelAuthCode 支付码
         * @param mhtOrderNo 商户订单号,如果为空则自动生成商户订单号
         * @return  respResult 应答码 responseMsg 应答消息 mhtOrderNo 商户订单号 nowPayOrderNo 现在支付订单号 responseTime 相应时间 payResult 支付结果
         */
        public ResultScan05 jd_scan_05(App app, OrderDetail orderDetail, String notifyUrl, String channelAuthCode,String mhtOrderNo)

<h5 id='2.1.5'></h4>

- 银联被扫支付

        /**
         * 银联被扫支付
         * @param app appId(应用ID)和appKey ,
         * 登录商户后台 : https://mch.ipaynow.cn ->商户中心->应用信息可以新增应用或查看appKey
         * @param orderDetail 商品名称,商品描述,商品价格(单位分),商品标记(用于营销活动)
         * @param notifyUrl 后台通知地址,详见2.2
         * @param channelAuthCode 支付码
         * @param mhtOrderNo 商户订单号,如果为空则自动生成商户订单号
         * @return  respResult 应答码 responseMsg 应答消息 mhtOrderNo 商户订单号 nowPayOrderNo 现在支付订单号 responseTime 相应时间 payResult 支付结果
         */
        public ResultScan05 union_scan_05(App app, OrderDetail orderDetail, String notifyUrl, String channelAuthCode,String mhtOrderNo)

<h5 id='2.1.6'></h4>

- 微信主扫支付

        /**
         * 微信主扫支付
         * @param app appId(应用ID)和appKey ,
         * 登录商户后台 : https://mch.ipaynow.cn ->商户中心->应用信息可以新增应用或查看appKey
         * @param orderDetail 商品名称,商品描述,商品价格(单位分),商品标记(用于营销活动)
         * @param mhtSubAppId 微信子号对应多个公众号的时候必填,如果只对应一个公众号则不传
         * @param notifyUrl 后台通知地址,详见2.2
         * @param resultType PIC: tn为二维码图片(data:..格式)  URL : tn为支付链接
         * @param mhtOrderNo 商户订单号,如果为空则自动生成商户订单号
         * @return  respResult 应答码 responseMsg 应答消息 mhtOrderNo 商户订单号 nowPayOrderNo 现在支付订单号 responseTime 相应时间
         * tn 二维码图片, 或者支付链接
         */
        public ResultScan08 wx_scan_08(App app, OrderDetail orderDetail, String mhtSubAppId,String notifyUrl,ResultType resultType,String mhtOrderNo)

<h5 id='2.1.7'></h4>

- 支付宝主扫支付

        /**
         * 支付宝主扫支付
         * @param app appId(应用ID)和appKey ,
         * 登录商户后台 : https://mch.ipaynow.cn ->商户中心->应用信息可以新增应用或查看appKey
         * @param orderDetail 商品名称,商品描述,商品价格(单位分),商品标记(用于营销活动)
         * @param notifyUrl 后台通知地址,详见2.2
         * @param resultType PIC: tn为二维码图片(data:..格式)  URL : tn为支付链接
         * @param mhtOrderNo 商户订单号,如果为空则自动生成商户订单号
         * @return  respResult 应答码 responseMsg 应答消息 mhtOrderNo 商户订单号 nowPayOrderNo 现在支付订单号 responseTime 相应时间
         * tn 二维码图片, 或者支付链接
         */
        public ResultScan08 ali_scan_08(App app, OrderDetail orderDetail, String notifyUrl,ResultType resultType,String mhtOrderNo)

<h5 id='2.1.8'></h4>

- 手Q主扫支付

        /**
         *手q主扫支付
         * @param app appId(应用ID)和appKey ,
         * 登录商户后台 : https://mch.ipaynow.cn ->商户中心->应用信息可以新增应用或查看appKey
         * @param orderDetail 商品名称,商品描述,商品价格(单位分),商品标记(用于营销活动)
         * @param notifyUrl 后台通知地址,详见2.2
         * @param resultType PIC: tn为二维码图片(data:..格式)  URL : tn为支付链接
         * @param mhtOrderNo 商户订单号,如果为空则自动生成商户订单号
         * @return  respResult 应答码 responseMsg 应答消息 mhtOrderNo 商户订单号 nowPayOrderNo 现在支付订单号 responseTime 相应时间
         * tn 二维码图片, 或者支付链接
         */
        public ResultScan08 handq_scan_08(App app, OrderDetail orderDetail, String notifyUrl,ResultType resultType,String mhtOrderNo)

<h5 id='2.1.9'></h4>

- 京东主扫支付

        /**
         *京东主扫支付
         * @param app appId(应用ID)和appKey ,
         * 登录商户后台 : https://mch.ipaynow.cn ->商户中心->应用信息可以新增应用或查看appKey
         * @param orderDetail 商品名称,商品描述,商品价格(单位分),商品标记(用于营销活动)
         * @param notifyUrl 后台通知地址,详见2.2
         * @param resultType PIC: tn为二维码图片(data:..格式)  URL : tn为支付链接
         * @param mhtOrderNo 商户订单号,如果为空则自动生成商户订单号
         * @return  respResult 应答码 responseMsg 应答消息 mhtOrderNo 商户订单号 nowPayOrderNo 现在支付订单号 responseTime 相应时间
         * tn 二维码图片, 或者支付链接
         */
        public ResultScan08 jd_scan_08(App app, OrderDetail orderDetail, String notifyUrl,ResultType resultType,String mhtOrderNo)

<h5 id='2.1.10'></h4>

- 银联主扫支付

        /**
         * 银联主扫支付
         * @param app appId(应用ID)和appKey ,
         * 登录商户后台 : https://mch.ipaynow.cn ->商户中心->应用信息可以新增应用或查看appKey
         * @param orderDetail 商品名称,商品描述,商品价格(单位分),商品标记(用于营销活动)
         * @param notifyUrl 后台通知地址,详见2.2
         * @param resultType PIC: tn为二维码图片(data:..格式)  URL : tn为支付链接
         * @param mhtOrderNo 商户订单号,如果为空则自动生成商户订单号
         * @return  respResult 应答码 responseMsg 应答消息 mhtOrderNo 商户订单号 nowPayOrderNo 现在支付订单号 responseTime 相应时间
         * tn 二维码图片, 或者支付链接
         */
        public ResultScan08 union_scan_08(App app, OrderDetail orderDetail, String notifyUrl,ResultType resultType,String mhtOrderNo)

<h5 id='2.1.11'></h4>

- 微信公众号支付

        /**
         * 微信公众号支付
         * @param app appId(应用ID)和appKey ,
         * 登录商户后台 : https://mch.ipaynow.cn ->商户中心->应用信息可以新增应用或查看appKey
         * @param orderDetail 商品名称,商品描述,商品价格(单位分),商品标记(用于营销活动)
         * @param notifyUrl 后台通知地址,详见2.2
         * @param frontNotifyUrl 前台页面跳转地址
         * @param mhtOrderNo 商户订单号,如果为空则自动生成商户订单号
         * @return  重定向地址
         */
        public String wx_p_account(App app, OrderDetail orderDetail,String notifyUrl,String frontNotifyUrl,String mhtOrderNo)

<h5 id='2.1.12'></h4>

- 微信公众号支付(获取支付要素)

        /**
         * 微信公众号支付(获取支付要素)
         * @param app appId(应用ID)和appKey ,
         * 登录商户后台 : https://mch.ipaynow.cn ->商户中心->应用信息可以新增应用或查看appKey
         * @param orderDetail 商品名称,商品描述,商品价格(单位分),商品标记(用于营销活动)
         * @param mhtSubAppId 微信子号对应多个公众号的时候必填,如果只对应一个公众号则不传
         * @param consumerId 消费者ID,相当于公众号下的OPENID
         * @param mhtOrderNo 商户订单号,如果为空则自动生成商户订单号
         * @return
         * 返回支付信息，格式为:
         * timeStamp%3dtimeStamp%26nonceStr%3dnonceStr%26prepay_id%3dprepay_id%26wxAppId%3dwxAppId%26paySign%3dpaySign%26signType%3dMD5(需要URLDecode)
         */
        public String wx_p_account_getParams(App app, OrderDetail orderDetail,String mhtSubAppId,String consumerId,String mhtOrderNo)

<h5 id='2.1.13'></h4>

- 支付宝公众号支付

        /**
         * 支付宝公众号支付
         * @param app appId(应用ID)和appKey ,
         * 登录商户后台 : https://mch.ipaynow.cn ->商户中心->应用信息可以新增应用或查看appKey
         * @param orderDetail 商品名称,商品描述,商品价格(单位分),商品标记(用于营销活动)
         * @param notifyUrl 后台通知地址,详见2.2
         * @param frontNotifyUrl 前台页面跳转地址
         * @param mhtOrderNo 商户订单号,如果为空则自动生成商户订单号
         * @return  重定向地址
         */
        public  String ali_p_account(App app, OrderDetail orderDetail,String notifyUrl,String frontNotifyUrl,String mhtOrderNo)

<h5 id='2.1.14'></h4>

- 支付宝公众号支付(获取支付要素)

        /**
         * 支付宝公众号支付(获取支付要素)
         * @param app appId(应用ID)和appKey ,
         * 登录商户后台 : https://mch.ipaynow.cn ->商户中心->应用信息可以新增应用或查看appKey
         * @param orderDetail 商品名称,商品描述,商品价格(单位分),商品标记(用于营销活动)
         * @param consumerId 消费者ID,相对于支付宝账号的userid
         * @param mhtOrderNo 商户订单号,如果为空则自动生成商户订单号
         * 返回支付信息，格式为:
         * timeStamp%3dtimeStamp%26nonceStr%3dnonceStr%26prepay_id%3dprepay_id%26wxAppId%3dwxAppId%26paySign%3dpaySign%26signType%3dMD5(需要URLDecode)
         */
        public  String ali_p_account_getParams(App app, OrderDetail orderDetail,String consumerId,String mhtOrderNo)

<h5 id='2.1.15'></h4>

- 手Q公众号支付

        /**
         * 手Q公众号支付
         * @param app appId(应用ID)和appKey ,
         * 登录商户后台 : https://mch.ipaynow.cn ->商户中心->应用信息可以新增应用或查看appKey
         * @param orderDetail 商品名称,商品描述,商品价格(单位分),商品标记(用于营销活动)
         * @param notifyUrl 后台通知地址,详见2.2
         * @param frontNotifyUrl 前台页面跳转地址
         * @param mhtOrderNo 商户订单号,如果为空则自动生成商户订单号
         * @return  重定向地址
         */
        public String handq_p_account(App app, OrderDetail orderDetail,String notifyUrl,String frontNotifyUrl,String mhtOrderNo)

<h5 id='2.1.16'></h4>

- 手Q公众号支付(获取支付要素)

        /**
         * 手Q公众号支付(获取支付要素)
         * @param app appId(应用ID)和appKey ,
         * 登录商户后台 : https://mch.ipaynow.cn ->商户中心->应用信息可以新增应用或查看appKey
         * @param orderDetail 商品名称,商品描述,商品价格(单位分),商品标记(用于营销活动)
         * @param consumerId 消费者ID,相当于公众号下的OPENID
         * @param mhtOrderNo 商户订单号,如果为空则自动生成商户订单号
         * @return
         * 返回支付信息，格式为:
         * timeStamp%3dtimeStamp%26nonceStr%3dnonceStr%26prepay_id%3dprepay_id%26wxAppId%3dwxAppId%26paySign%3dpaySign%26signType%3dMD5(需要URLDecode)
         */
        public String handq_p_account_getParams(App app, OrderDetail orderDetail,String consumerId,String mhtOrderNo)

<h5 id='2.1.17'></h4>

- 微信H5

        /**
         * 微信H5
         * @param app appId(应用ID)和appKey ,
         * 登录商户后台 : https://mch.ipaynow.cn ->商户中心->应用信息可以新增应用或查看appKey
         * @param orderDetail 商品名称,商品描述,商品价格(单位分),商品标记(用于营销活动)
         * @param consumerCreateIp 用户支付IP
         * @param notifyUrl 后台通知地址,详见2.2
         * @param frontNotifyUrl 前台页面跳转地址
         * @param mhtOrderNo 商户订单号,如果为空则自动生成商户订单号
         * @return  重定向地址
         */
        public String wx_h5(App app, OrderDetail orderDetail,String consumerCreateIp,String notifyUrl,String frontNotifyUrl,String mhtOrderNo)

<h5 id='2.1.18'></h4>

- 支付宝H5

        /**
         * 支付宝H5
         * @param app appId(应用ID)和appKey ,
         * 登录商户后台 : https://mch.ipaynow.cn ->商户中心->应用信息可以新增应用或查看appKey
         * @param orderDetail 商品名称,商品描述,商品价格(单位分),商品标记(用于营销活动)
         * @param notifyUrl 后台通知地址,详见2.2
         * @param frontNotifyUrl 前台页面跳转地址
         * @param mhtOrderNo 商户订单号,如果为空则自动生成商户订单号
         * @return  重定向地址
         */
        public  String ali_h5(App app, OrderDetail orderDetail,String notifyUrl,String frontNotifyUrl,String mhtOrderNo)

<h5 id='2.1.19'></h4>

- 银联H5

        /**
         * 银联H5
         * @param app appId(应用ID)和appKey ,
         * 登录商户后台 : https://mch.ipaynow.cn ->商户中心->应用信息可以新增应用或查看appKey
         * @param orderDetail 商品名称,商品描述,商品价格(单位分),商品标记(用于营销活动)
         * @param notifyUrl 后台通知地址,详见2.2
         * @param frontNotifyUrl 前台页面跳转地址
         * @param mhtOrderNo 商户订单号,如果为空则自动生成商户订单号
         * @return  重定向地址
         */
        public  String unionpay_h5(App app, OrderDetail orderDetail,String notifyUrl,String frontNotifyUrl,String mhtOrderNo)

<h5 id='2.1.20'></h4>

- 招行一网通H5

        /**
         * 招行一网通H5
         * @param app appId(应用ID)和appKey ,
         * 登录商户后台 : https://mch.ipaynow.cn ->商户中心->应用信息可以新增应用或查看appKey
         * @param orderDetail 商品名称,商品描述,商品价格(单位分),商品标记(用于营销活动)
         * @param notifyUrl 后台通知地址,详见2.2
         * @param frontNotifyUrl 前台页面跳转地址
         * @param mhtOrderNo 商户订单号,如果为空则自动生成商户订单号
         * @return  重定向地址
         */
        public  String cmbywt_h5(App app, OrderDetail orderDetail,String notifyUrl,String frontNotifyUrl,String mhtOrderNo)

<h5 id='2.1.21'></h4>

- 手Q H5

        /**
         * 手Q H5
         * @param app appId(应用ID)和appKey ,
         * 登录商户后台 : https://mch.ipaynow.cn ->商户中心->应用信息可以新增应用或查看appKey
         * @param orderDetail 商品名称,商品描述,商品价格(单位分),商品标记(用于营销活动)
         * @param notifyUrl 后台通知地址,详见2.2
         * @param frontNotifyUrl 前台页面跳转地址
         * @param mhtOrderNo 商户订单号,如果为空则自动生成商户订单号
         * @return  重定向地址
         */
        public  String handq_h5(App app, OrderDetail orderDetail,String notifyUrl,String frontNotifyUrl,String mhtOrderNo)

<h5 id='2.1.22'></h4>

- 支付宝网页web

        /**
         * 支付宝网页web
         * @param app appId(应用ID)和appKey ,
         * 登录商户后台 : https://mch.ipaynow.cn ->商户中心->应用信息可以新增应用或查看appKey
         * @param orderDetail 商品名称,商品描述,商品价格(单位分),商品标记(用于营销活动)
         * @param notifyUrl 后台通知地址,详见2.2
         * @param mhtOrderNo 商户订单号,如果为空则自动生成商户订单号
         * @return  重定向地址
         */
        public String ali_web(App app, OrderDetail orderDetail, String notifyUrl,String mhtOrderNo)

<h5 id='2.1.23'></h4>

- 银联网页web

        /**
         * 银联网页web
         * @param app appId(应用ID)和appKey ,
         * 登录商户后台 : https://mch.ipaynow.cn ->商户中心->应用信息可以新增应用或查看appKey
         * @param orderDetail 商品名称,商品描述,商品价格(单位分),商品标记(用于营销活动)
         * @param notifyUrl 后台通知地址,详见2.2
         * @param mhtOrderNo 商户订单号,如果为空则自动生成商户订单号
         * @return  重定向地址
         */
        public String union_web(App app, OrderDetail orderDetail, String notifyUrl,String mhtOrderNo)

<h5 id='2.1.24'></h4>

- 微信小程序支付

        /**
         * 微信小程序支付
         * @param app appId(应用ID)和appKey ,
         * 登录商户后台 : https://mch.ipaynow.cn ->商户中心->应用信息可以新增应用或查看appKey
         * @param orderDetail   商品名称,商品描述,商品价格(单位分),商品标记(用于营销活动),
         * @param consumerId  用户openId
         * @param notifyUrl 后台通知地址,详见2.2
         * @param mhtOrderNo 商户订单号,如果为空则自动生成商户订单号
         * @return  支付要素
         */
        public String wx_app(App app, OrderDetail4WxApp orderDetail, String consumerId,String notifyUrl,String mhtOrderNo)


<h4 id='2.2'>2.2 支付结果通知</h4>

通知方式采用httppost方式通知,接受demo如下

        //获取通知数据需要从body中流式读取
        BufferedReader reader = req.getReader();
        StringBuilder reportBuilder = new StringBuilder();
        String tempStr = "";
        while((tempStr = reader.readLine()) != null){
               reportBuilder.append(tempStr);
        }
        //报文数据字符串
        String reportContent = reportBuilder.toString();


字段含义如下:

<table>
        <tr>
            <th>字段名称</th>
            <th>字段Key</th>
            <th>备注</th>
        </tr>
        <tr>
            <td>功能码</td>
            <td>funcode</td>
            <td>定值：N001</td>
        </tr>
        <tr>
            <td>接口版本号</td>
            <td>version</td>
            <td>定值：1.0.0</td>
         </tr>
<tr>
            <td>商户应用唯一标识</td>
            <td>appId</td>
            <td></td>
         </tr>
<tr>
            <td>商户订单号</td>
            <td>mhtOrderNo</td>
            <td></td>
         </tr>
<tr>
            <td>商户商品名称</td>
            <td>mhtOrderName</td>
            <td></td>
         </tr>
<tr>
            <td>商户交易类型</td>
            <td>mhtOrderType</td>
            <td></td>
         </tr>
<tr>
            <td>商户订单币种类型</td>
            <td>mhtCurrencyType</td>
            <td>156人民币</td>
         </tr>
<tr>
            <td>商户订单原单金额</td>
            <td>oriMhtOrderAmt</td>
            <td>单位(人民币)：分</td>
         </tr>
<tr>
            <td>商户订单实付金额</td>
            <td>mhtOrderAmt</td>
            <td>单位(人民币)：分</td>
         </tr>
<tr>
            <td>商户订单优惠金额</td>
            <td>discountAmt</td>
            <td>单位(人民币)：分</td>
         </tr>
<tr>
            <td>商户订单超时时间</td>
            <td>mhtOrderTimeOut</td>
            <td>60~3600秒，默认3600</td>
         </tr>
<tr>
            <td>商户订单开始时间</td>
            <td>mhtOrderStartTime</td>
            <td>yyyyMMddHHmmss</td>
         </tr>
<tr>
            <td>支付成功时间</td>
            <td>payTime</td>
            <td>yyyyMMddHHmmss</td>
         </tr>
<tr>
            <td>商户字符编码</td>
            <td>mhtCharset</td>
            <td>UTF-8</td>
         </tr>
<tr>
            <td>现在支付流水号</td>
            <td>nowPayOrderNo</td>
            <td></td>
         </tr>
<tr>
            <td>设备类型</td>
            <td>deviceType</td>
            <td></td>
         </tr>
<tr>
            <td>用户所选渠道类型</td>
            <td>payChannelType</td>
            <td></td>
         </tr>
<tr>
            <td>交易支付状态</td>
            <td>transStatus</td>
            <td></td>
         </tr>
<tr>
            <td>渠道订单号</td>
            <td>channelOrderNo</td>
            <td></td>
         </tr>
<tr>
            <td>付款人账号</td>
            <td>payConsumerId</td>
            <td>微信返回sub_openid,支付宝返回buyer_user_id</td>
         </tr>
<tr>
            <td>商户保留域</td>
            <td>mhtReserved</td>
            <td>给商户使用的字段，商户可以对交易进行标记，现在支付将原样返回</td>
         </tr>
<tr>
            <td>签名方法</td>
            <td>signType</td>
            <td>定值：MD5</td>
         </tr>
<tr>
            <td>数据签名</td>
            <td>signature</td>
            <td>除signature字段外，所有参数都参与MD5签名</td>
         </tr>
<tr>
            <td>银行类型</td>
            <td>bankType</td>
            <td>微信渠道返回</td>
         </tr>
<tr>
            <td>卡类型</td>
            <td>cardType</td>
            <td>CREDIT 信用卡  DEBIT  借记卡</td>
         </tr>
    </table>

<h4 id='2.3'> 2.3 订单查询API </h4>

<h5 id='2.3.1'></h4>

- 商户被扫支付订单查询

        /**
         * 商户被扫支付订单查询
         * @param mhtOrderNo    商户订单号
         * @param appId 商户的AppId,https://mch.ipaynow.cn ->商户中心->应用信息可以新增应用或查看appKey
         * @param appKey 商户的AppKey,https://mch.ipaynow.cn ->商户中心->应用信息可以新增应用或查看appKey
         * @return
         */
        public Map queryOrderScan05(String mhtOrderNo,App app)

<h5 id='2.3.2'></h4>

- 商户主扫支付订单查询

        /**
         * 商户被扫支付订单查询
         * @param mhtOrderNo    商户订单号
         * @param appId 商户的AppId,https://mch.ipaynow.cn ->商户中心->应用信息可以新增应用或查看appKey
         * @param appKey 商户的AppKey,https://mch.ipaynow.cn ->商户中心->应用信息可以新增应用或查看appKey
         * @return
         */
        public Map queryOrderScan08(String mhtOrderNo,App app)

<h5 id='2.3.3'></h4>

- 商户公众号支付订单查询

        /**
         * 商户被扫支付订单查询
         * @param mhtOrderNo    商户订单号
         * @param appId 商户的AppId,https://mch.ipaynow.cn ->商户中心->应用信息可以新增应用或查看appKey
         * @param appKey 商户的AppKey,https://mch.ipaynow.cn ->商户中心->应用信息可以新增应用或查看appKey
         * @return
         */
        public Map queryOrderPaccount(String mhtOrderNo,App app)

<h5 id='2.3.4'></h4>

- 商户H5支付订单查询

        /**
         * 商户H5支付订单查询
         * @param mhtOrderNo    商户订单号
         * @param appId 商户的AppId,https://mch.ipaynow.cn ->商户中心->应用信息可以新增应用或查看appKey
         * @param appKey 商户的AppKey,https://mch.ipaynow.cn ->商户中心->应用信息可以新增应用或查看appKey
         * @return
         */
        public Map queryOrderH5(String mhtOrderNo,App app)

<h5 id='2.3.5'></h4>

- 商户网页支付订单查询

        /**
         * 商户网页支付订单查询
         * @param mhtOrderNo    商户订单号
         * @param appId 商户的AppId,https://mch.ipaynow.cn ->商户中心->应用信息可以新增应用或查看appKey
         * @param appKey 商户的AppKey,https://mch.ipaynow.cn ->商户中心->应用信息可以新增应用或查看appKey
         * @return
         */
        public Map queryOrderWeb(String mhtOrderNo,App app)

<h5 id='2.3.6'></h4>

- 商户微信App支付订单查询

        /**
         * 商户微信App支付订单查询
         * @param mhtOrderNo    商户订单号
         * @param appId 商户的AppId,https://mch.ipaynow.cn ->商户中心->应用信息可以新增应用或查看appKey
         * @param appKey 商户的AppKey,https://mch.ipaynow.cn ->商户中心->应用信息可以新增应用或查看appKey
         * @return
         */
        public Map queryOrderWxApp(String mhtOrderNo,App app)


<h4 id='2.4'> 2.4 退款API </h4>

<h5 id='2.4.1'></h4>

- 退款

        /**
         * 退款
         * @param appId 商户的AppId,https://mch.ipaynow.cn ->商户中心->应用信息可以新增应用或查看appKey
         * @param appKey 商户的AppKey,https://mch.ipaynow.cn ->商户中心->应用信息可以新增应用或查看appKey
         * @param mhtOrderNo    商户订单号
         * @param amount    退款金额
         * @param reason    退款原因
         * @return
         */
        public Map refundOrder(String appId,String appKey,String mhtOrderNo,Integer amount,String reason)

<h5 id='2.4.2'></h4>

- 退款查询

        /**
         * 退款查询
         * @param appId 商户的AppId,https://mch.ipaynow.cn ->商户中心->应用信息可以新增应用或查看appKey
         * @param appKey 商户的AppKey,https://mch.ipaynow.cn ->商户中心->应用信息可以新增应用或查看appKey
         * @param mhtRefundNo   商户退款单号
         * @return
         */
        public Map refundQuery(String appId,String appKey,String mhtRefundNo)

<h5 id='2.4.3'></h4>

- 撤销

        /**
         * 撤销(只能撤销当天的交易,且无论成功失败(逻辑包含退款))
         * @param appId 商户的AppId,https://mch.ipaynow.cn ->商户中心->应用信息可以新增应用或查看appKey
         * @param appKey 商户的AppKey,https://mch.ipaynow.cn ->商户中心->应用信息可以新增应用或查看appKey
         * @param mhtOrderNo    商户订单号
         * @param reason    退款原因
         * @return
         */
        public Map backOrder(String appId,String appKey,String mhtOrderNo,String reason)

<h5 id='2.4.4'></h4>

- 撤销查询

        /**
         * 撤销查询
         * @param appId 商户的AppId,https://mch.ipaynow.cn ->商户中心->应用信息可以新增应用或查看appKey
         * @param appKey 商户的AppKey,https://mch.ipaynow.cn ->商户中心->应用信息可以新增应用或查看appKey
         * @param mhtRefundNo   商户退款单号
         * @return
         */
        public Map backQuery(String appId,String appKey,String mhtRefundNo)


<h2 id='3'> 3. 完整DEMO </h2>

            直接运行cn.ipaynow.ipaynow_pay_demo.Main
            访问
            http://127.0.0.1:7072/paytest/index.html