package com.example.xixin.util;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.alipay.api.response.AlipayTradeWapPayResponse;


public class AlipayTradePagePay {

    public static String alipay (String orderId, String orderName, String totalAmount) throws AlipayApiException {

        /** 引用初始化方法，Config配置链接：https://opensupport.alipay.com/support/FAQ/08d46c24-15a8-4f1c-85a0-68fdf587f813  **/
        AlipayClient alipayClient = new DefaultAlipayClient(Config.gatewayUrl, Config.app_id, Config.merchant_private_key, Config.format, Config.charset, Config.alipay_public_key, Config.sign_type);

        /** 实例化具体API对应的request类，类名称和接口名称对应,当前调用接口名称 alipay.trade.page.pay **/
        AlipayTradeWapPayRequest request = new AlipayTradeWapPayRequest();

        JSONObject Content = new JSONObject() ;

        /******必传参数******/
        //    	商户订单号,商户自定义，需保证在商户端不重复，如：20200612000001
        Content.put("out_trade_no", orderId);

        //    	订单标题
        Content.put("subject", "健康守护360°智慧体检平台:"+orderName);

        //    	订单金额，精确到小数点后两位
        Content.put("total_amount", totalAmount);

        //    	销售产品码,App支付固定值：FAST_INSTANT_TRADE_PAY
        Content.put("product_code", "FAST_INSTANT_TRADE_PAY");

        /******可选参数******/
        //      扩展信息
        /*		JSONObject extendParams = new JSONObject();
//		花呗分期参数：hb_fq_num 代表花呗分期数，仅支持传入 3、6、12
		extendParams.put("hb_fq_num", "3");
//		花呗分期参数：hb_fq_seller_percent 代表卖家承担收费比例，商家承担手续费传入 100，用户承担手续费传入 0
		extendParams.put("hb_fq_seller_percent", "100");
		Content.put("extend_params", extendParams);*/

        //封装请求参数到biz_content
        request.setBizContent(Content.toString());

        /**注：支付结果以异步通知为准，不能以同步返回为准，因为如果实际支付成功，但因为外力因素，如断网、断电等导致页面没有跳转，则无法接收到同步通知；**/
        /** 支付完成的跳转地址,用于用户视觉感知支付是否完成，传值外网可以访问的地址 **/
        request.setReturnUrl(Config.return_url);

        /** 异步通知地址，以http或者https开头的，商户外网可以post访问的异步地址，用于接收支付宝返回的支付结果 **/
        request.setNotifyUrl(Config.notify_url);

        /**第三方调用（服务商模式），传值app_auth_token后，会收款至授权token对应商家账号 **/
        request.putOtherTextParam("app_auth_token", Config.app_auth_token);

        AlipayTradeWapPayResponse response = alipayClient.pageExecute(request);//生成form表单

//    	AlipayTradePagePayResponse response = alipayClient.pageExecute(request,"GET");//生成url链接


        /** 获取接口调用结果 **/
        System.out.println(response.getBody());
        return response.getBody();
    }

}