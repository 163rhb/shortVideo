package com.rhb.shortviedo.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rhb.shortviedo.common.utilcommon.idworker.Sid;
import com.rhb.shortviedo.dao.UsersDao;
import com.rhb.shortviedo.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




/*import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;*/

/**
 * (Users)表服务接口
 *
 * @author makejava
 * @since 2020-04-06 12:56:33
 */
@Service
public class UsersService {
    @Autowired
    UsersDao usersDao;
    @Autowired
    private Sid sid;


    public Integer queryUsernameIsExist(String username) {
        QueryWrapper queryWrapper = new QueryWrapper();
       queryWrapper.eq("username",username);
        return usersDao.selectCount(queryWrapper);
    }
    public Integer saveUser(Users user) {
        /*mabtis数据库自动生成id是long类型的，所以这里要加工*/
        String userId = sid.nextShort();
        user.setId(userId);
        return usersDao.insert(user);
    }

    public Users queryUserForLogin(String username, String md5Str) {
        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username).eq("password", md5Str);

        return usersDao.selectOne(queryWrapper);
    }

    public void updateUserInfo(Users user) {
        usersDao.updateById(user);
    }

    public Users queryUserInfo(String userId) {
        return usersDao.selectById(userId);

    }
}