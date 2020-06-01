package com.atguigu.educenter.service;

import com.atguigu.educenter.entity.UcenterMember;
import com.atguigu.educenter.entity.vo.RegisterVo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-05-26
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    //登录的方法
    String login(UcenterMember ucenterMember);

    //注册
    void register(RegisterVo registerVo);

    //判断数据库表是否存在相同微信信息，根据openid判断
    UcenterMember getOpenIdMember(String openid);

    //查询某一天的注册人数
    Integer countRegisterDay(String day);
}
