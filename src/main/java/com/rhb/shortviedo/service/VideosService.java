package com.rhb.shortviedo.service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.rhb.shortviedo.common.utilcommon.idworker.Sid;
import com.rhb.shortviedo.dao.VideosDao;
import com.rhb.shortviedo.entity.Videos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 视频信息表(Videos)表服务接口
 *
 * @author makejava
 * @since 2020-04-06 12:58:50
 */
@Service
public class VideosService {

    @Autowired
    VideosDao videosDao;
    @Autowired
    private Sid sid;

    public String saveVideo(Videos videos) {
        String id = sid.nextShort();
        videos.setId(id);
        videosDao.insert(videos);
        return id;
    }
    public void updateVideo(String videoId, String coverPath){
        /*Videos video = new Videos();
        video.setId(videoId);
        video.setCoverPath(coverPath);*/
        //MyBatis-plus 条件更新构造器
        UpdateWrapper<Videos> updateWrapper = new UpdateWrapper();
        updateWrapper.eq("id",videoId).set("cover_path",coverPath);
        videosDao.update(null,updateWrapper);

    };
}
