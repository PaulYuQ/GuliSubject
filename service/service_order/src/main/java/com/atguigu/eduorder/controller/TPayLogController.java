package com.atguigu.eduorder.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduorder.service.TPayLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 支付日志表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-05-28
 */
@RestController
@RequestMapping("/eduorder/t-pay-log")
@CrossOrigin
public class TPayLogController {

    @Autowired
    private TPayLogService tPayLogService;

    //生成微信支付二维码接口
    //参数是订单号
    @GetMapping("/createNative/{orderNo}")
    public R createNative(@PathVariable String orderNo) {
        //返回信息，包括二维码地址，还有其他需要的信息
        Map map = tPayLogService.createNative(orderNo);
        return R.ok().data(map);
    }

    //查询订单支付状态
    @GetMapping("/queryPayStatus/{orderNo}")
    public R queryPayStatus(@PathVariable String orderNo) {
        Map<String,String> map = tPayLogService.queryPayStatus(orderNo);
        if(map == null) {
            return R.error().message("支付出错了");
        }
        if(map.get("trade_state").equals("SUCCESS")) {
            //添加记录到支付表，更新订单表状态
            tPayLogService.updateOrderStatus(map);
            return R.ok().message("支付成功");
        }
        return R.ok().code(25000).message("支付中。。");
    }

}

