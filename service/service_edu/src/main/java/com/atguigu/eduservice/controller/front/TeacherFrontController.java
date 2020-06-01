package com.atguigu.eduservice.controller.front;

import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.service.EduCourseService;
import com.atguigu.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/eduservice/teacherfront")
public class TeacherFrontController {

    @Autowired
    private EduTeacherService eduTeacherService;

    @Autowired
    private EduCourseService eduCourseService;

    //分页查询讲师的方法
    @GetMapping("/getTeacherFrontList/{page}/{limit}")
    public R getTeacherFrontList(@PathVariable long page, @PathVariable long limit) {
        Page<EduTeacher> pageTeacher = new Page<>(page,limit);
        Map<String, Object> map = eduTeacherService.getTeacherFrontList(pageTeacher);
        //返回分页所有数据
        return R.ok().data("map",map);
    }

    //讲师详情的功能
    @GetMapping("/getTeacherFrontInfo/{id}")
    public R getTeacherFrontInfo(@PathVariable String id) {

        //根据id查询讲师基本信息
        EduTeacher eduTeacher = eduTeacherService.getById(id);
        //根据id查询所讲课程
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        wrapper.eq("teacher_id",id);
        List<EduCourse> eduCourseList = eduCourseService.list(wrapper);
        return R.ok().data("teacher",eduTeacher).data("courseList",eduCourseList);
    }



}
