package com.xkyz.xinke.enums;

import lombok.Getter;

@Getter
public enum ExceptionEnums {
    PRICE_CANNOT_BE_NULL(400, "价格不能为空!"),
    BRAND_NOT_FOUND(404, "品牌不存在!"),
    CATEGORY_NOT_FOUND(404, "商品分类不存在!"),
    BRAND_SAVE_ERROR(500, "新增品牌失败!"),
    UPLOAD_ERROR(500, "文件上传失败!"),
    INVALID_FILE_TYPE(400, "无效的文件类型!"),
    SPEC_GROUP_NOT_FOUND(404, "商品规格组不存在!"),
    SPEC_PARAM_NOT_FOUND(404, "商品规格参数不存在!"),
    GOODS_NOT_FOUND(404, "商品不存在!"),
    GOODS_DETAIL_NOT_FOUND(404, "商品详情不存在!"),
    GOODS_SKU_NOT_FOUND(404, "商品SKU不存在!"),
    GOODS_STOCK_NOT_FOUND(404, "商品库存不存在!"),
    //    GOODS_SAVE_ERROR(500, "新增商品失败!"),
//    GOODS_UPDATE_ERROR(500, "更新商品失败!"),
    GOODS_ID_CANNOT_BE_NULL(400, "商品不能为空!"),



    //新客
    SIGN_CHECK_FAILURE(500, "签名校验失败"),
    USER_ORDER_NOT_EXIST(501,"用户订单不存在"),
    DELIVER_TASK_NOT_EXIST(502,"揽收任务不存在"),
    RUNTIME_EXCEPTION(503,"运行异常"),
    IMAGE_URL_NOT_EXIST(504,"图片URL不存在！"),
    UNIFIED_ORDER_FAILED(505,"微信支付统一下单失败"),
    INVALID_USER_TOKEN(506,"无效的用户token"),

    TRANSFER_NOT_EXIST(507,"转账记录不存在"),
    POINTS_ID_NOT_EXIST(508,"网点ID不存在"),
    INVALID_USER_ADDRESS_ID(509,"无效的用户地址id"),
    USER_TOKEN_CANNOT_BE_EMPTY(506,"用户token不能为空"),

    USER_DATA_TYPE_ERROR(400, "用户数据类型无效!"),
    UNAUTHORIZED(403, "未授权!"),


    ;
    private int code;
    private String msg;

    ExceptionEnums() {
    }

    ExceptionEnums(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
