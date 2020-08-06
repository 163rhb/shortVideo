package com.rhb.shortviedo.dao;

import com.rhb.shortviedo.entity.UsersReport;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 举报用户表(UsersReport)表数据库访问层
 *
 * @author makejava
 * @since 2020-04-06 12:57:24
 */
public interface UsersReportDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    UsersReport queryById(String id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<UsersReport> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param usersReport 实例对象
     * @return 对象列表
     */
    List<UsersReport> queryAll(UsersReport usersReport);

    /**
     * 新增数据
     *
     * @param usersReport 实例对象
     * @return 影响行数
     */
    int insert(UsersReport usersReport);

    /**
     * 修改数据
     *
     * @param usersReport 实例对象
     * @return 影响行数
     */
    int update(UsersReport usersReport);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

}