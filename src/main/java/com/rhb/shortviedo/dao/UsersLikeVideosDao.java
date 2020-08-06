package com.rhb.shortviedo.dao;

import com.rhb.shortviedo.entity.UsersLikeVideos;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 用户喜欢的/赞过的视频(UsersLikeVideos)表数据库访问层
 *
 * @author makejava
 * @since 2020-04-06 12:57:11
 */
public interface UsersLikeVideosDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    UsersLikeVideos queryById(String id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<UsersLikeVideos> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param usersLikeVideos 实例对象
     * @return 对象列表
     */
    List<UsersLikeVideos> queryAll(UsersLikeVideos usersLikeVideos);

    /**
     * 新增数据
     *
     * @param usersLikeVideos 实例对象
     * @return 影响行数
     */
    int insert(UsersLikeVideos usersLikeVideos);

    /**
     * 修改数据
     *
     * @param usersLikeVideos 实例对象
     * @return 影响行数
     */
    int update(UsersLikeVideos usersLikeVideos);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

}