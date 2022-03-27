











[html]
    --------
    [查询余额] GET 请求 [网关?xxx=xx]
    fxid 商户号
    fxdate 年月日时分秒
    fxaction ['money'] 查询余额
    fsign 签名 =  (fxid + fxdate + fxaction + 商户秘钥) 合并后 md5
    t Math.random()
    --------
    [代付查单] GET 请求 [网关?xxx=xx]
    fxid 商户号
    fxaction ['repayquery'] 代付查单
    fxbody jsonStr '[{"fxddh":"订单号"}]'
    fsign 签名 =  (fxid + fxaction + fxbody + 商户秘钥) 合并后 md5
    t Math.random()
    --------
    [代付申请接口] POST 请求 [网关]
    fxid 商户号
    fxaction ['repay'] 代付
    fxbody jsonStr '[
        {
            "fxddh":"订单号",
            "fxdate":"年月日时分秒",
            "fxfee":"金额",
            "fxbody":"卡号",
            "fxname":"姓名",
            "fxaddress":"银行",
            "fxzhihang":"支行",
            "fxsheng":"省",
            "fxshi":"市",
            "fxlhh":"联行号"
        }
    ]'
    fsign 签名 =  (fxid + fxaction + fxbody + 商户秘钥) 合并后 md5
    fxnotifyurl 异步地址  [没有t]
    --------

[jsp]
    --------
    [支付接口Pre] POST 请求 [post.htm]
    fxdesc 商品名称
    fxfee 金额
    fxpay ['bank'] 网银支付
    t Math.random()
    --------
        [返回]
        status 1 支付, 其他输出 error
        error 错误信息
        payurl 跳转地址
    --------
    [支付接口] POST 请求 [网关]
    fxid 商户号
    "fxddh":"订单号",
    fxfee 金额
    fxpay ['bank'] 网银支付
    fxnotifyurl ["http://localhost:8084/qzf/notifyUrl.htm"] 异步url 吧
    fxbackurl ["http://localhost:8084/qzf/fxbackurl.htm"] 同步url 吧
    fxattch ['test'] 测试吧
    fxaction ['money'] 查询余额
    fxdesc 商品名称
    fxip = request.getRemoteAddr(); 远程ip
    fsign 签名 =  (fxid + fxddh + fxfee + fxnotifyurl + 商户秘钥) 合并后 md5 小写
    --------
    [回调接口back] get?post ? 
        [取出参数]
        fxstatus 1 成功
        fxid 
        fxddh
        fxfee
        fxsign 签名
        [status!=1]
        支付失败
        [校验秘钥]
        (fxstatus + fxid + fxddh + fxfee + 商户秘钥) 合并后 md5 小写
        不对提示 签名错误
        [show支付成功]
    --------
    [回调接口notify] get?post ? 
        一样, 不过使用 com.jspsmart.upload.SmartUpload 获取
    --------









