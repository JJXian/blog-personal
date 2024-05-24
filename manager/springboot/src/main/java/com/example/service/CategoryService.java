package com.example.service;

import com.example.entity.Category;
import com.example.mapper.CategoryMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 博客分类业务处理
 */
@Service
public class CategoryService {

    @Resource
    private CategoryMapper categoryMapper;

    /**
     * 新增
     * @param category
     */
    public void add(Category category) {
        categoryMapper.insert(category);
    }

    /**
     * 查询
     * @param id
     * @return
     */
    public Category selectById(Integer id) {
        return categoryMapper.selectById(id);

    }

    /**
     * 删除
     * @param id
     */
    public void deleteById(Integer id) {
        categoryMapper.deleteById(id);
    }

    /**
     * 批量删除
     * @param ids
     */
    public void deleteBatch(List<Integer> ids) {
        for(Integer id : ids){
            categoryMapper.deleteById(id);
        }
    }

    /**
     * 修改
     * @param category
     */
    public void updateById(Category category) {
        categoryMapper.updateById(category);
    }


    /**
     * 分页查询
     */
    public PageInfo<Category> selectPage(Category category, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Category> list = categoryMapper.selectAll(category);
        return PageInfo.of(list);
    }

    /**
     * 查询所有
     */
    public List<Category> selectAll(Category category) {
        return categoryMapper.selectAll(category);
    }
}
