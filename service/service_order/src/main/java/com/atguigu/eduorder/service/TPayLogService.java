package com.atguigu.eduorder.service;

import com.atguigu.eduorder.entity.TPayLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 支付日志表 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-05-28
 */
public interface TPayLogService extends IService<TPayLog> {

    //生成微信支付二维码接口
    Map createNative(String orderNo);

    //根据订单号查询订单状态
    Map<String, String> queryPayStatus(String orderNo);

    //添加记录到支付表，更新订单表状态
    void updateOrderStatus(Map<String, String> map);
}
