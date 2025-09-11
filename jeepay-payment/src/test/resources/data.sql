

-- 初始化支付方式
INSERT INTO t_pay_way (way_code, way_name) VALUES ('ALI_BAR', '支付宝条码');
INSERT INTO t_pay_way (way_code, way_name) VALUES ('ALI_JSAPI', '支付宝生活号');
INSERT INTO t_pay_way (way_code, way_name) VALUES ('ALI_APP', '支付宝APP');
INSERT INTO t_pay_way (way_code, way_name) VALUES ('ALI_WAP', '支付宝WAP');
INSERT INTO t_pay_way (way_code, way_name) VALUES ('ALI_PC', '支付宝PC网站');
INSERT INTO t_pay_way (way_code, way_name) VALUES ('ALI_QR', '支付宝二维码');
INSERT INTO t_pay_way (way_code, way_name) VALUES ('ALI_LITE', '支付宝小程序');
INSERT INTO t_pay_way (way_code, way_name) VALUES ('ALI_OC', '支付宝订单码');

INSERT INTO t_pay_way (way_code, way_name) VALUES ('WX_BAR', '微信条码');
INSERT INTO t_pay_way (way_code, way_name) VALUES ('WX_JSAPI', '微信公众号');
INSERT INTO t_pay_way (way_code, way_name) VALUES ('WX_APP', '微信APP');
INSERT INTO t_pay_way (way_code, way_name) VALUES ('WX_H5', '微信H5');
INSERT INTO t_pay_way (way_code, way_name) VALUES ('WX_NATIVE', '微信扫码');
INSERT INTO t_pay_way (way_code, way_name) VALUES ('WX_LITE', '微信小程序');

INSERT INTO t_pay_way (way_code, way_name) VALUES ('YSF_BAR', '云闪付条码');
INSERT INTO t_pay_way (way_code, way_name) VALUES ('YSF_JSAPI', '云闪付jsapi');

INSERT INTO t_pay_way (way_code, way_name) VALUES ('PP_PC', 'PayPal支付');

INSERT INTO t_pay_way (way_code, way_name) VALUES ('UP_APP', '银联App支付');
INSERT INTO t_pay_way (way_code, way_name) VALUES ('UP_WAP', '银联手机网站支付');
INSERT INTO t_pay_way (way_code, way_name) VALUES ('UP_QR', '银联二维码(主扫)');
INSERT INTO t_pay_way (way_code, way_name) VALUES ('UP_BAR', '银联二维码(被扫)');
INSERT INTO t_pay_way (way_code, way_name) VALUES ('UP_B2B', '银联企业网银支付');
INSERT INTO t_pay_way (way_code, way_name) VALUES ('UP_PC', '银联网关支付');
INSERT INTO t_pay_way (way_code, way_name) VALUES ('UP_JSAPI', '银联Js支付');
INSERT INTO t_pay_way (way_code, way_name) VALUES ('EMS_DIRECT', 'EMS 信用卡支付');


-- 初始化支付接口定义
INSERT INTO t_pay_interface_define (if_code, if_name, is_mch_mode, is_isv_mode, config_page_type, isv_params, isvsub_mch_params, normal_mch_params, way_codes, icon, bg_color, state, remark)
VALUES ('alipay', '支付宝官方', 1, 1, 2,
        '[{"name":"sandbox","desc":"环境配置","type":"radio","verify":"","values":"1,0","titles":"沙箱环境,生产环境","verify":"required"},{"name":"pid","desc":"合作伙伴身份（PID）","type":"text","verify":"required"},{"name":"appId","desc":"应用App ID","type":"text","verify":"required"},{"name":"privateKey", "desc":"应用私钥", "type": "textarea","verify":"required","star":"1"},{"name":"alipayPublicKey", "desc":"支付宝公钥(不使用证书时必填)", "type": "textarea","star":"1"},{"name":"signType","desc":"接口签名方式(推荐使用RSA2)","type":"radio","verify":"","values":"RSA,RSA2","titles":"RSA,RSA2","verify":"required"},{"name":"useCert","desc":"公钥证书","type":"radio","verify":"","values":"1,0","titles":"使用证书（请使用RSA2私钥）,不使用证书"},{"name":"appPublicCert","desc":"应用公钥证书（.crt格式）","type":"file","verify":""},{"name":"alipayPublicCert","desc":"支付宝公钥证书（.crt格式）","type":"file","verify":""},{"name":"alipayRootCert","desc":"支付宝根证书（.crt格式）","type":"file","verify":""}]',
        '[{"name":"appAuthToken", "desc":"子商户app_auth_token", "type": "text","readonly":"readonly"},{"name":"refreshToken", "desc":"子商户刷新token", "type": "hidden","readonly":"readonly"},{"name":"expireTimestamp", "desc":"authToken有效期（13位时间戳）", "type": "hidden","readonly":"readonly"}]',
        '[{"name":"sandbox","desc":"环境配置","type":"radio","verify":"","values":"1,0","titles":"沙箱环境,生产环境","verify":"required"},{"name":"appId","desc":"应用App ID","type":"text","verify":"required"},{"name":"privateKey", "desc":"应用私钥", "type": "textarea","verify":"required","star":"1"},{"name":"alipayPublicKey", "desc":"支付宝公钥(不使用证书时必填)", "type": "textarea","star":"1"},{"name":"signType","desc":"接口签名方式(推荐使用RSA2)","type":"radio","verify":"","values":"RSA,RSA2","titles":"RSA,RSA2","verify":"required"},{"name":"useCert","desc":"公钥证书","type":"radio","verify":"","values":"1,0","titles":"使用证书（请使用RSA2私钥）,不使用证书"},{"name":"appPublicCert","desc":"应用公钥证书（.crt格式）","type":"file","verify":""},{"name":"alipayPublicCert","desc":"支付宝公钥证书（.crt格式）","type":"file","verify":""},{"name":"alipayRootCert","desc":"支付宝根证书（.crt格式）","type":"file","verify":""}]',
        '[{"wayCode": "ALI_JSAPI"}, {"wayCode": "ALI_WAP"}, {"wayCode": "ALI_BAR"}, {"wayCode": "ALI_APP"}, {"wayCode": "ALI_PC"}, {"wayCode": "ALI_QR"}, {"wayCode": "ALI_OC"}]',
        'http://jeequan.oss-cn-beijing.aliyuncs.com/jeepay/img/alipay.png', '#1779FF', 1, '支付宝官方通道');

INSERT INTO t_pay_interface_define (if_code, if_name, is_mch_mode, is_isv_mode, config_page_type, isv_params, isvsub_mch_params, normal_mch_params, way_codes, icon, bg_color, state, remark)
VALUES ('wxpay', '微信支付官方', 1, 1, 2,
        '[{"name":"mchId", "desc":"微信支付商户号", "type": "text","verify":"required"},{"name":"appId","desc":"应用App ID","type":"text","verify":"required"},{"name":"appSecret","desc":"应用AppSecret","type":"text","verify":"required","star":"1"},{"name":"oauth2Url", "desc":"oauth2地址（置空将使用官方）", "type": "text"},{"name":"apiVersion", "desc":"微信支付API版本", "type": "radio","values":"V2,V3","titles":"V2,V3","verify":"required"},{"name":"key", "desc":"APIv2密钥", "type": "textarea","verify":"required","star":"1"},{"name":"apiV3Key", "desc":"APIv3密钥（V3接口必填）", "type": "textarea","verify":"","star":"1"},{"name":"serialNo", "desc":"序列号（V3接口必填）", "type": "textarea","verify":"","star":"1"},{"name":"cert", "desc":"API证书(apiclient_cert.p12)", "type": "file","verify":""},{"name":"apiClientCert", "desc":"证书文件(apiclient_cert.pem) ", "type": "file","verify":""},{"name":"apiClientKey", "desc":"私钥文件(apiclient_key.pem)", "type": "file","verify":""}]',
        '[{"name":"subMchId","desc":"子商户ID","type":"text","verify":"required"},{"name":"subMchAppId","desc":"子账户appID(线上支付必填)","type":"text","verify":""}]',
        '[{"name":"mchId", "desc":"微信支付商户号", "type": "text","verify":"required"},{"name":"appId","desc":"应用App ID","type":"text","verify":"required"},{"name":"appSecret","desc":"应用AppSecret","type":"text","verify":"required","star":"1"},{"name":"oauth2Url", "desc":"oauth2地址（置空将使用官方）", "type": "text"},{"name":"apiVersion", "desc":"微信支付API版本", "type": "radio","values":"V2,V3","titles":"V2,V3","verify":"required"},{"name":"key", "desc":"APIv2密钥", "type": "textarea","verify":"required","star":"1"},{"name":"apiV3Key", "desc":"APIv3密钥（V3接口必填）", "type": "textarea","verify":"","star":"1"},{"name":"serialNo", "desc":"序列号（V3接口必填）", "type": "textarea","verify":"","star":"1" },{"name":"cert", "desc":"API证书(apiclient_cert.p12)", "type": "file","verify":""},{"name":"apiClientCert", "desc":"证书文件(apiclient_cert.pem) ", "type": "file","verify":""},{"name":"apiClientKey", "desc":"私钥文件(apiclient_key.pem)", "type": "file","verify":""}]',
        '[{"wayCode": "WX_APP"}, {"wayCode": "WX_H5"}, {"wayCode": "WX_NATIVE"}, {"wayCode": "WX_JSAPI"}, {"wayCode": "WX_BAR"}, {"wayCode": "WX_LITE"}]',
        'http://jeequan.oss-cn-beijing.aliyuncs.com/jeepay/img/wxpay.png', '#04BE02', 1, '微信官方通道');

INSERT INTO t_pay_interface_define (if_code, if_name, is_mch_mode, is_isv_mode, config_page_type, isv_params, isvsub_mch_params, normal_mch_params, way_codes, icon, bg_color, state, remark)
VALUES ('ysfpay', '云闪付官方', 0, 1, 1,
        '[{"name":"sandbox","desc":"环境配置","type":"radio","verify":"","values":"1,0","titles":"沙箱环境,生产环境","verify":"required"},{"name":"serProvId","desc":"服务商开发ID[serProvId]","type":"text","verify":"required"},{"name":"isvPrivateCertFile","desc":"服务商私钥文件（.pfx格式）","type":"file","verify":"required"},{"name":"isvPrivateCertPwd","desc":"服务商私钥文件密码","type":"text","verify":"required","star":"1"},{"name":"ysfpayPublicKey","desc":"云闪付开发公钥（证书管理页面可查询）","type":"textarea","verify":"required","star":"1"},{"name":"acqOrgCode","desc":"可用支付机构编号","type":"text","verify":"required"}]',
        '[{"name":"merId","desc":"商户编号","type":"text","verify":"required"}]',
        NULL,
        '[{"wayCode": "YSF_BAR"}, {"wayCode": "ALI_JSAPI"}, {"wayCode": "WX_JSAPI"}, {"wayCode": "ALI_BAR"}, {"wayCode": "WX_BAR"}]',
        'http://jeequan.oss-cn-beijing.aliyuncs.com/jeepay/img/ysfpay.png', 'red', 1, '云闪付官方通道');

INSERT INTO t_pay_interface_define (if_code, if_name, is_mch_mode, is_isv_mode, config_page_type, isv_params, isvsub_mch_params, normal_mch_params, way_codes, icon, bg_color, state, remark)
VALUES ('pppay', 'PayPal支付', 1, 0, 1,
        NULL,
        NULL,
        '[{"name":"sandbox","desc":"环境配置","type":"radio","verify":"required","values":"1,0","titles":"沙箱环境, 生产环境"},{"name":"clientId","desc":"Client ID（客户端ID）","type":"text","verify":"required"},{"name":"secret","desc":"Secret（密钥）","type":"text","verify":"required","star":"1"},{"name":"refundWebhook","desc":"退款 Webhook id","type":"text","verify":"required"},{"name":"notifyWebhook","desc":"支付 Webhook id","type":"text","verify":"required"}]',
        '[{"wayCode": "PP_PC"}]',
        'http://jeequan.oss-cn-beijing.aliyuncs.com/jeepay/img/paypal.png', '#005ea6', 1, 'PayPal官方通道');

-- 计全付支付通道
INSERT INTO t_pay_interface_define (if_code, if_name, is_mch_mode, is_isv_mode, config_page_type, isv_params, isvsub_mch_params, normal_mch_params, way_codes, icon, bg_color, state, remark)
VALUES ('plspay', '计全付', 1, 0, 1,
        NULL,
        NULL,
        '[{"name":"signType","desc":"签名方式","type":"radio","verify":"required","values":"MD5,RSA2","titles":"MD5,RSA2"},{"name":"merchantNo","desc":"计全付商户号","type":"text","verify":"required"},{"name":"appId","desc":"应用ID","type":"text","verify":"required"},{"name":"appSecret","desc":"md5秘钥","type":"textarea","verify":"required","star":"1"},{"name":"rsa2AppPrivateKey","desc":"RSA2: 应用私钥","type":"textarea","verify":"required","star":"1"},{"name":"rsa2PayPublicKey","desc":"RSA2: 支付网关公钥","type":"textarea","verify":"required","star":"1"}]',
        '[{"wayCode": "ALI_APP"}, {"wayCode": "ALI_BAR"}, {"wayCode": "ALI_JSAPI"}, {"wayCode": "ALI_LITE"}, {"wayCode": "ALI_PC"}, {"wayCode": "ALI_QR"}, {"wayCode": "ALI_WAP"}, {"wayCode": "WX_APP"}, {"wayCode": "WX_BAR"}, {"wayCode": "WX_H5"}, {"wayCode": "WX_JSAPI"}, {"wayCode": "WX_LITE"}, {"wayCode": "WX_NATIVE"}]',
        'http://jeequan.oss-cn-beijing.aliyuncs.com/jeepay/img/plspay.svg', '#0CACFF', 1, '计全付');

INSERT INTO t_pay_interface_define (if_code, if_name, is_mch_mode, is_isv_mode, config_page_type, isv_params, isvsub_mch_params, normal_mch_params, way_codes, icon, bg_color, state, remark)
VALUES ('emspay', 'EMS支付', 1, 0, 1,
        NULL,
        NULL,
        '[{"name":"sandbox","desc":"环境配置","type":"radio","verify":"required","values":"1,0","titles":"沙箱环境, 生产环境"},{"name":"secret","desc":"Secret（密钥）","type":"text","verify":"required","star":"1"},{"name":"bearer","desc":"Bearer Token (认证)","type":"text","verify":"required"}]',
        '[{"wayCode": "EMS_DIRECT"}]',
        NULL, '#005ea6', 1, 'EMS');

INSERT INTO t_mch_info (mch_no, mch_name, mch_short_name, type, isv_no, contact_name, contact_tel, contact_email, state, remark, init_User_id, created_uid, created_at, updated_at)
values (
  'merch no', 'merch name', 'merch', 1, NULL, 'merch contact', 'merch tel', 'merch@email.com', 1,
  NULL, 100001, 801, 'Admin', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
);

insert into t_mch_app (app_id, app_name, mch_no, state, app_secret, remark, created_uid, created_at, updated_at)
values (
  'app id', 'app name', 'merch no', 1, '12345', null, 801, 'Admin',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
);

insert into t_pay_interface_config (id, info_type, info_id, if_code, if_params, if_rate, state, remark, created_uid, created_by, created_at, updated_at)
values (
  100345, 3, '9999999', 'emspay',
  '{"signType":"MD5","merchantNo":"merch no","appId":"app id","appSecret":"12345"}',
  3.5,
  1, null, 801, 'Admin', CURRENT_TIMESTAMP,CURRENT_TIMESTAMP
);
