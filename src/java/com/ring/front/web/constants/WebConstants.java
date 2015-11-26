package com.ring.front.web.constants;

/**
 * 
 * @description常量类
 * @author GANCHUNGEN512
 * @date 2014-8-8
 */
public interface WebConstants {

    /** 返回给前端编码定义 */
    public static final String RETURN_SUCCESS_CODE = "000";
    public static final String RETURN_SUCCESS_MSG = "SUCCESS";
    public static final String RETURN_FAIL_CODE = "001";
    public static final String RETURN_FAIL_MSG = "FAIL";
    /** session常量定义 */
    public static final String SESSION_USER_INFO = "SESSION-USER-INFO";

    
    /**用户类型： P--个人客户，C--企业用户*/
    public static final String USER_TYPE_PRIVATE = "P";
    public static final String USER_TYPE_COMPANY = "C";
    
    /**app名称*/
    public static final String APP_NAME = "Lighting";
    
    
    
    
    
    
    /**环境开关常量定义     TEST--测试环境，PRD--生产环境*/
    public static final String ring_ENV_SWITCH_TEST = "TEST";
    /**PRD--生产环境*/
    public static final String ring_ENV_SWITCH_PRD = "PRD";
    /**
     * 子账户类型 --- 产品类 P
     */
    public static final String SUB_ACCOUNT_TYPE_PRODUCT = "P";

    public static final String SUB_ACCOUNT_TYPE_PRODUCT_NUM = "001";

    /**
     * 子账户类型 --- 基金类 F
     */
    public static final String SUB_ACCOUNT_TYPE_FOUND = "F";

    public static final String SUB_ACCOUNT_TYPE_FOUND_NUM = "002";

    /** 功能类型 */
    public static final String FUNCTION_TYPE_LOGIN = "LOGIN";

    public static final String FUNCTION_TYPE_REGISTER = "REGIST";

    /**
     * 定义密码类型. LOGIN: 登录密码; TRADE: 交易密码
     */
    public static final String PASSWORD_TYPE_LOGIN = "LOGIN";

    public static final String PASSWORD_TYPE_TRADE = "TRADE";

    /**
     * 实名认证标志 Y-已验证，N-未认证 1-已认证过一次，2-已认证过2次，3-已认证过3次
     */
    public static final String IS_VALIDATE_YES = "Y";

    public static final String IS_VALIDATE_NO = "N";

    public static final String IS_VALIDATE_ONE = "1";

    public static final String IS_VALIDATE_TWO = "2";

    public static final String IS_VALIDATE_THREE = "3";

    /**
     * 定义对接银联接口的交易类型 '0001': 消费交易; '0002': 退款交易
     */
    public static final String UP_TRADE_TYPE_PAYMENT = "0001";

    public static final String UP_TRADE_TYPE_REFUND = "0002";

    /*------------------- Session参数设置 -------------------*/
    // 短信验证码
    static public final String SESSION_MOBILE_CODE = "sendMobileCode";

    // 邮箱验证码
    static public final String SESSION_EMAIL_CODE = "sendEmailCode";

    /*------------------ 国政通常变量设置(后期会移至配置文件中) ------------------*/
    // 服务地址
    public static final String GZT_ENDPOINT_ADDRESS = "http://gboss.id5.cn/services/QueryValidatorServices";

    // 密钥
    public static final String GZT_ENCODE_KEY = "12345678";

    // Webservice用户名
    public static final String GZT_WEBSERVICE_NAME = "HMJJService";

    // webService密码
    public static final String GZT_WEBSERVICE_PASSWORD = "HMJJService_BxB5j(mN";

    // DATASOURCE
    public static final String GZT_DATASOURCE = "1A020201";

    /**
     * 产品状态 筹备中
     */
    public static final String PRODUCT_STATE_READY = "0";

    /**
     * 产品状态 抢购中
     */
    public static final String PRODUCT_STATE_SELLING = "1";

    /**
     * 产品状态 已售罄
     */
    public static final String PRODUCT_STATE_SALE_OVER = "2";

    /**
     * 产品状态 结算中
     */
    public static final String PRODUCT_STATE_REFUNDING = "3";

    /**
     * 产品状态 已结算
     */
    public static final String PRODUCT_STATE_REFUND_OVER = "4";
    /**
     * 状态  Y
     */
    public static final String USER_PARAMS_STATE_YES = "Y";
    /**
     * 状态  N 
     */
    public static final String USER_PARAMS_STATE_NO = "N";
    /**
     * 产品状态 是否到期  Y
     */
    public static final String PRODUCT_STATE_END_YES = "Y";
    /**
     * 产品状态 是否到期  N
     */
    public static final String PRODUCT_STATE_END_NO = "N";
    
    /**
     * 支付方式  0-平台 1-银联
     */
    public static final String PAY_TYPE_PLATFORM = "0";
    /**
     * 支付方式  0-平台 1-银联
     */
    public static final String PAY_TYPE_BANK = "1";
    
    /**
     * 分割字符串
     */
    public static final String SPLIT_CHAR ="&&";
    
}