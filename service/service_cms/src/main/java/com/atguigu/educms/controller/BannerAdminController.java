package com.atguigu.educms.controller;


import com.atguigu.commonutils.R;
import com.atguigu.educms.entity.CrmBanner;
import com.atguigu.educms.service.CrmBannerService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 首页banner表 前端控制器
 * 后台Banner管理接口
 * </p>
 *
 * @author testjava
 * @since 2020-05-25
 */
@RestController
@RequestMapping("/educms/crm-banneradmin")
@CrossOrigin
public class BannerAdminController {

    @Autowired
    private CrmBannerService crmBannerService;

    //1.分页查询
    @GetMapping("/pageBanner/{page}/{limit}")
    public R pageBanner(@PathVariable long page, @PathVariable long limit) {
        Page<CrmBanner> pageBanner = new Page<>(page,limit);
        crmBannerService.page(pageBanner,null);
        return R.ok().data("items",pageBanner.getRecords()).data("total",pageBanner.getTotal());
    }

    //2.添加Banner
    @PostMapping("/addBanner")
    public R addBanner(@RequestBody CrmBanner crmBanner) {
        crmBannerService.save(crmBanner);
        return R.ok();
    }

    //3.修改Banner
    @PutMapping("/updateBanner")
    public R updateBanner(@RequestBody CrmBanner crmBanner) {
        crmBannerService.updateById(crmBanner);
        return R.ok();
    }

    //4.删除Banner
    @DeleteMapping("/deleteBanner/{id}")
    public R deleteBanner(@PathVariable String id) {
        crmBannerService.removeById(id);
        return R.ok();
    }

    //5.根据id查询Banner
    @GetMapping("/selectBannerById/{id}")
    public R selectBannerById(@PathVariable String id) {
        CrmBanner crmBanner = crmBannerService.getById(id);
        return R.ok().data("item",crmBanner);
    }

}

