package com.rhb.shortviedo.dao;

import com.rhb.shortviedo.entity.UsersFans;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 用户粉丝关联关系表(UsersFans)表数据库访问层
 *
 * @author makejava
 * @since 2020-04-06 12:56:49
 */
public interface UsersFansDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    UsersFans queryById(String id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<UsersFans> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param usersFans 实例对象
     * @return 对象列表
     */
    List<UsersFans> queryAll(UsersFans usersFans);

    /**
     * 新增数据
     *
     * @param usersFans 实例对象
     * @return 影响行数
     */
    int insert(UsersFans usersFans);

    /**
     * 修改数据
     *
     * @param usersFans 实例对象
     * @return 影响行数
     */
    int update(UsersFans usersFans);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

}