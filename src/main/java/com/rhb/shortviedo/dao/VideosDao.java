package com.rhb.shortviedo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rhb.shortviedo.entity.Videos;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 视频信息表(Videos)表数据库访问层
 *
 * @author makejava
 * @since 2020-04-06 12:58:50
 */
public interface VideosDao extends BaseMapper<Videos>  {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Videos queryById(String id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Videos> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param videos 实例对象
     * @return 对象列表
     */
    List<Videos> queryAll(Videos videos);

    /**
     * 新增数据
     *
     * @param videos 实例对象
     * @return 影响行数
     */
    /*int insert(Videos videos);*/

    /**
     * 修改数据
     *
     * @param videos 实例对象
     * @return 影响行数
     */
    /*int update(Videos videos);*/

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

}
