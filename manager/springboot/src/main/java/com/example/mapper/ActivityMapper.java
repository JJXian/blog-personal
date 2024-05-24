package com.example.mapper;

import com.example.entity.Activity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ActivityMapper {

    /**
     *新增
     * @param activity
     */
    void add(Activity activity);


    @Delete("delete from activity where id = #{id}")
    void deleteById(Integer id);

    void updateById(Activity activity);

    @Select("select * from activity where id = #{id}")
    Activity selectById(Integer id);

    List<Activity> selectAll(Activity activity);

    @Update("update activity set read_count = read_count + 1 where id = #{activityId}")
    void updateReadCount(Integer activityId);

    List<Activity> selectUser(Activity activity);

    List<Activity> selectLike(Activity activity);

    List<Activity> selectCollect(Activity activity);

    List<Activity> selectComment(Activity activity);
}
