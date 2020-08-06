package com.rhb.shortviedo.controller;

import com.rhb.shortviedo.common.utils1.JSONResult;
import com.rhb.shortviedo.entity.Bgm;
import com.rhb.shortviedo.service.BgmService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Bgm)表控制层
 *
 * @author makejava
 * @since 2020-04-06 12:55:39
 */
@RestController
@Api(value="背景音乐业务的接口", tags= {"背景音乐业务的controller"})
@RequestMapping("/bgm")
public class BgmController {

    @Autowired
    private BgmService bgmService;

    @ApiOperation(value="获取背景音乐列表", notes="获取背景音乐列表的接口")
    @GetMapping("/list")
    public JSONResult list() {
        return JSONResult.ok(bgmService.queryBgmList());
    }

}