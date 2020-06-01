package com.atguigu.eduorder.service.impl;

import com.atguigu.commonutils.ordervo.CourseWebVoOrder;
import com.atguigu.commonutils.ordervo.UcenterMemberOrder;
import com.atguigu.eduorder.client.EduClient;
import com.atguigu.eduorder.client.UcenterClient;
import com.atguigu.eduorder.entity.TOrder;
import com.atguigu.eduorder.mapper.TOrderMapper;
import com.atguigu.eduorder.service.TOrderService;
import com.atguigu.eduorder.utils.OrderNoUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-05-28
 */
@Service
public class TOrderServiceImpl extends ServiceImpl<TOrderMapper, TOrder> implements TOrderService {

    @Autowired
    private EduClient eduClient;

    @Autowired
    private UcenterClient ucenterClient;

    //创建订单，返回订单号
    @Override
    public String createOrders(String courseId, String memberIdByJwtToken) {
        //通过远程调根据用户id获取用户信息
        UcenterMemberOrder userInfoOrder = ucenterClient.getUserInfoOrder(memberIdByJwtToken);
        //通过远程调用根据课程id获取课程信息
        CourseWebVoOrder courseInfoOrder = eduClient.getCourseInfoOrder(courseId);

        //创建Order对象，向order对象里面设置需要数据
        TOrder tOrder = new TOrder();
        tOrder.setOrderNo(OrderNoUtil.getOrderNo());//订单号
        tOrder.setCourseId(courseId);//课程id
        tOrder.setCourseTitle(courseInfoOrder.getTitle());//标题
        tOrder.setCourseCover(courseInfoOrder.getCover());//封面
        tOrder.setTeacherName(courseInfoOrder.getTeacherName());//讲师名称
        tOrder.setTotalFee(courseInfoOrder.getPrice());//价格
        tOrder.setMemberId(memberIdByJwtToken);//用户id
        tOrder.setMobile(userInfoOrder.getMobile());//用户手机
        tOrder.setNickname(userInfoOrder.getNickname());//用户昵称
        tOrder.setStatus(0);
        tOrder.setPayType(1);
        baseMapper.insert(tOrder);
        return tOrder.getOrderNo();
    }
}
