package com.example.xixin.util;

/**
 * 初始化方法参数&公共参数配置
 */
public class Config {
 	
	/** 初始化代码配置信息  **/
	//（必填）支付宝网关
	//正式环境网关：https://openapi.alipay.com/gateway.do
	//沙箱环境网关：https://openapi-sandbox.dl.alipaydev.com/gateway.do	
	public static final  String gatewayUrl = "https://openapi-sandbox.dl.alipaydev.com/gateway.do";
	
    //（必填）应用ID
    //请填写您的APPID:https://opendocs.alipay.com/common/02nebp
	public static final  String app_id = "9021000138656875";

    //（必填）应用私钥:https://opendocs.alipay.com/common/02kipk?pathHash=0d20b438
	//请填写您的应用私钥，例如：MIIEvQIBADANB ...
	public static final  String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCwN20HrduPvUrS/bjWDXPgevHqAHXqXp9Dp0svuRAbYtWdwt/t4N6fWghCoECy6KLmqj8moQDjhR/qEg0qgoqFQcCNgfLTfxIxOHtjMd76ZSnIHIDrRhffgIwlQ0qrO9YMK5N3CI4l7TdEyUattaKxEZgDmx9vC3IzLYsw7EBQozb+jB+hFiuAItfeN9HFOR1+UNY/MFphDIruc3fercKec40zVrFSVFRnONd4IJ+/LXW103Yzt13UQdI+dsoyjo1C6CpV2XrWCbwMTpYGpsbgE+1v0/bVx9JzAHb+2nkJ6cQqtUhmdL4h9Wl1f730mHKJ1Esrw59OyydXVFOaY1ttAgMBAAECggEAchoxmztUZGqpvJ1VxiTH159EbcUGlU/XyLLW2Hv4lgMVVyYMYbet73kYyCuEOVSxxrKOWbD/JlPZKnOt2szUbRz6VabDWXd6jCyKmNx+CZzfxRfP0oh8hNhz5tz/yIjiPNcqkXUUtjI4C25ZObnXMti1mgAkBn9e3wyqiG8x5gP2GCjHnmINZ+kUtyK8yw/EGp/IEHJJRo2J/64cvHu/xRmGvnWxlstTpvvosVoDKFqxV3EEdRA8XpqQtXStqAR7vc3daTy/N4aj9rijEQbtW3Ur5vASHdSOToTcBIb6soheTQfpL7sJvnwncgMmLt1LNf3GeFZU69PtFMROfYx9cQKBgQDdZLMOF0lXI5Zg1imPbYUXNHxcG51JVDxGfItFQqTAbHN5wNeDQvZP7NSuzRd4UxYbKL2OfxpqDQ84lA8rXcfaNnWR8MhogXFUfb166FR9txkQHdBXK5INxOv0LTd5RMubUO+x0BuuaJaPL3mkH4zsbdtM/iZGSm6fdHWYQCo44wKBgQDLwuy8dZfEl61sY31Cef09zFQDtI9pmpAgErAE/L9DriTHgkBYC0ifhsXXRLr+DGDw/dtp2kbMQmoINQafEXm5hqJzYlOxa7/WNgEiAnyrKeGzSNIT13BCekbtqQHP9Vw308YtmQyVRuiHhGKqBg6fydOcrauNj2VhlkWINXRbbwKBgASM5E0+10gOODIHVNVY1+pxLrkp7CKss4xzVN14kDBQSlNPTl6pSqy73mc1We0kMAkg3jyGikFxwUVVbbZjeA8ZHXOBcm0LdAvbhcB38/ydfctiAX05LQhRatQa9c/tRQ4NLNAHyfnctnN881AXQes+GsrLUngRYZKvbxdRAk0nAoGBALGlGuJfn1atamwx/PXlBw1GxASK+fF26KqdwEpI1LO78qLiAxj/lWxHgtrgOCrfPfE9UXri65CPYoQPPtuVz8sY/y6bjh4Ds7edU7WD947+r6CwHz9fN6ctlgMDsunMn36oqoH8ikTnEjl8CMz1BPD4c/6IRyXe2+ONsMdE21IPAoGAepK+7W8sm4mn0Gb9yqj2HTI+MDkBxcY+BbKF/bqTR/5AauBrpTAiiBFwyt9aNzJqlyfNNbpo3BVLhWXVwZxVXI8klK55FgMWCSiyk0MO1SWqlWd7MpXkZyDBdj+ZPrZ6vepoPsHyg+TvkENiBGh+XhTkLLvJfTrMBSsopCzMeDE=";
	
    /********** RSA2公钥模式签名必用，公钥证书签名不传 ************/
    /**注：如果采用非证书模式，则无需赋值三个证书路径，改为赋值如下的支付宝公钥字符串即可**/
	//设置RSA2公钥方式：hhttps://opendocs.alipay.com/common/02kdnc?pathHash=fb0c752a
	
	//支付宝公钥
	//请填写您的支付宝公钥，例如：MIIBIjANBg...
	public static final  String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAu0T0vmDoP8dsh6Y85T64wXyZV125KG9S1i/jHpa2aJFQrSWZg/gd4hba0qEqEGVpEGL5pK9CC51QrD1WEymSX5XBlZ6XEXgUgYwJOZ6aCaVN3sNKe4Swq8cP0IGigmJMZfte38iOMIGvBNtmJp8Mtqf3ZePusMK8AF8LFCH1Qu7rEeALptYXA9z61er5HDYMJ8A0S8cYdrXYyQZsjwIkTTnJ9Puei6CaBs2nGEcBxkdqCps2KL4HTX3Zsynb6/gvcdirpPc2pM0YgEoBoi+1Y3c+zzfTbva39fdjKO8CkQlEjL+/XtZFZLSa+xVyyp1etenAuXsgoDn/1rXHkZGMLQIDAQAB";
    /********** 公钥证书模式签名必用，RSA2公钥签名不传 ************/
    /** 注：证书文件路径支持设置为文件系统中的路径或CLASS_PATH中的路径，同时配置公钥证书和RSA2公钥优先取公钥证书**/
	//设置证书方式：https://opendocs.alipay.com/common/056zub?pathHash=91c49771


	// 应用公钥证书路径
	//请填写您的应用公钥证书文件路径，例如：/foo/appCertPublicKey_2019051064521003.crt
	public static String app_cert_path ="";

	//支付宝公钥证书路径
	//请填写您的支付宝公钥证书文件路径，例如：/foo/alipayCertPublicKey_RSA2.crt
	public static String alipay_cert_path ="";
	
	//支付宝根证书路径
	//请填写您的支付宝根证书文件路径，例如：/foo/alipayRootCert.crt
	public static String alipay_root_cert_path ="";
	
	
	//（必填）签名类型
	public static String sign_type = "RSA2";
	
	//（必填）编码格式
	public static String charset = "UTF-8";

	//请求格式，固定值json
	public static String format = "JSON";
	
	//调用的接口版本，固定为：1.0
	public static String version = "1.0";

    //AES密钥，配合encrypt_type=AES加解密相关接口
    public static String encryptKey = "";

    //请求格式，固定值AES，（设置EncryptKey时必选） 
    public static String encryptType = "AES";
	

	/** 代码中其他配置信息，根据各产品API公共参数选择性引用  **/
	//第三方调用（服务商模式），传值app_auth_token后相当于以授权商户角色调用接口，app_auth_token获取流程：https://opendocs.alipay.com/isv/10467/xldcyq?pathHash=abce531a
	 public static String app_auth_token = "";
	 	 
	//通过公共参数notify_url配置上传异步地址  
	//异步通知地址需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，商户外网可以post访问的异步地址（不支持本地测试），用于接收支付宝返回的支付结果 
	 public static String notify_url ="";

	//通过接口公共参数return_url配置上传同步地址 
	//同步通知地址需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，get访问，用于支付完成后前端页面同步跳转
	public static String return_url ="http://localhost:5173/payConfirm";
		
	//日志记录目录
	public static String log_path = "D:/log.txt";


}