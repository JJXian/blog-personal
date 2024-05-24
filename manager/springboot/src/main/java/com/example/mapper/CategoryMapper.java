package com.example.mapper;

import com.example.entity.Category;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 操作分类的借口
 */
public interface CategoryMapper {


    /**
     * 新增
     */
    int insert(Category category);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @Select("select * from category where id = #{id}")
    Category selectById(Integer id);

    @Delete("delete from category where id = #{id}")
    void deleteById(Integer id);


    void updateById(Category category);

    List<Category> selectAll(Category category);
}
