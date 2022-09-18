package com.example.swagger_01.controller;

import com.example.swagger_01.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
@Api(tags = "Hello控制类")
@RestController
public class HelloController {

    @GetMapping("/get")
    @ApiOperation(value = "获取分页信息方法",notes = "get请求，返回值为String类型")
    public String get(){
        return "get";
    }

    @PostMapping("/post")
    @ApiOperation(value = "获取用户信息方法",notes = "post请求，返回值为User类型")
    public User post(@ApiParam(value = "用户id") Integer id){
        return new User("xiu",27,"江苏省宿迁市");
    }
}
