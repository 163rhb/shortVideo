package com.rhb.shortviedo.controller.open;

import com.rhb.shortviedo.common.utils1.MD5Utils;
import com.rhb.shortviedo.entity.Users;
import com.rhb.shortviedo.entity.redisVo.UsersVO;
import io.swagger.annotations.ApiImplicitParam;
import org.apache.commons.lang3.StringUtils;


import com.rhb.shortviedo.common.utils1.JSONResult;
import com.rhb.shortviedo.service.UsersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@Api(value="用户注册登录的接口", tags= {"注册和登录的controller"})
public class RegistLoginController extends BasicController {

	@Autowired
	private UsersService userService;

	@ApiOperation(value="用户注册", notes="用户注册的接口")
	@PostMapping("/regist")
	public JSONResult regist(@RequestBody Users user) throws Exception {

		// 1. 判断用户名和密码必须不为空
		if (StringUtils.isBlank(user.getUsername()) || StringUtils.isBlank(user.getPassword())) {
			return JSONResult.errorMsg("用户名和密码不能为空");
		}

		// 2. 判断用户名是否存在
		Integer usernameIsExist = userService.queryUsernameIsExist(user.getUsername());
		Integer res=0;
		// 3. 保存用户，注册信息
		if (usernameIsExist==0) {
			user.setNickname(user.getUsername());
			user.setPassword(MD5Utils.getMD5Str(user.getPassword()));
			user.setFansCounts(0);
			user.setReceiveLikeCounts(0);
			user.setFollowCounts(0);
			res=userService.saveUser(user);
		} else {
			return JSONResult.errorMsg("用户名已经存在，请换一个再试");
		}

		if(res==1)
        {
            user.setPassword("");
			UsersVO usersVO=setUserRedisSessionToken(user);
			return JSONResult.ok("注册成功！",usersVO);
        }
		return JSONResult.errorMsg("注册失败");
	}


	public UsersVO setUserRedisSessionToken(Users user) {
		String uniqueToken = UUID.randomUUID().toString();
		redis.set(USER_REDIS_SESSION + ":" + user.getId(), uniqueToken, 1000 * 60 * 30);
		UsersVO usersVO = new UsersVO();
		BeanUtils.copyProperties(user, usersVO);
		usersVO.setUserToken(uniqueToken);
		return usersVO;
	}

    @ApiOperation(value="用户登录", notes="用户登录的接口")
    @PostMapping("/login")
    public JSONResult login(@RequestBody Users user) throws Exception {
        String username = user.getUsername();
        String password = user.getPassword();

//		Thread.sleep(3000);

        // 1. 判断用户名和密码必须不为空
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            return JSONResult.ok("用户名或密码不能为空...");
        }

        // 2. 判断用户是否存在
        Users userResult = userService.queryUserForLogin(username,
                MD5Utils.getMD5Str(user.getPassword()));

        // 3. 返回
        if (userResult != null) {
            userResult.setPassword("");
			UsersVO userVO = setUserRedisSessionToken(userResult);
            return JSONResult.ok(userVO);
        } else {
            return JSONResult.errorMsg("用户名或密码不正确, 请重试...");
        }
    }

	@ApiOperation(value="用户注销", notes="用户注销的接口")
	@ApiImplicitParam(name="userId", value="用户id", required=true,
			dataType="String", paramType="query")
	@PostMapping("/logout")
	public JSONResult logout(String userId) throws Exception {
		redis.del(USER_REDIS_SESSION + ":" + userId);
		return JSONResult.ok();
	}

}
