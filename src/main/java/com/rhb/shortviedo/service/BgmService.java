package com.rhb.shortviedo.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rhb.shortviedo.dao.BgmDao;
import com.rhb.shortviedo.entity.Bgm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (Bgm)表服务接口
 *
 * @author makejava
 * @since 2020-04-06 12:55:37
 */
@Service
public class BgmService {
    @Autowired
    BgmDao bgmDao;


    public List<Bgm> queryBgmList() {
        return bgmDao.selectList(null) ;
    }

    public Bgm queryBgmById(String bgmId) {
        return bgmDao.selectById(bgmId);
    }
}