package com.example.controller;

import com.example.common.Result;
import com.example.entity.Comment;
import com.example.service.CollectService;
import com.example.service.CommentService;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 评论相关操作
 */
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    CommentService commentService;

    /**
     *
     * @param comment
     * @return
     */
    @PostMapping("/add")
    public Result add(@RequestBody Comment comment){
        commentService.add(comment);
        return Result.success();
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id){
        commentService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids){
        commentService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 更新
     * @param comment
     * @return
     */
    @PutMapping("/update")
    public Result update(@RequestBody Comment comment){
        commentService.update(comment);
        return  Result.success();
    }
    /**
     * 根据ID查询
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Comment comment = commentService.selectById(id);
        return Result.success(comment);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(Comment comment) {
        List<Comment> list = commentService.selectAll(comment);
        return Result.success(list);
    }


    /**
     * 分页查询
     * @param comment
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/selectPage")
    public Result selectPage(Comment comment,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize){
        PageInfo<Comment> page = commentService.selectPage(comment,pageNum,pageSize);
        return Result.success(page);
    }

    @GetMapping("/selectCount")
    public Result selectCount(@RequestParam Integer fid, @RequestParam String module) {
        Integer count = commentService.selectCount(fid, module);
        return Result.success(count);
    }


    @GetMapping("/selectForUser")
    public Result selectForUser(Comment comment) {
        List<Comment> list = commentService.selectForUser(comment);
        return Result.success(list);
    }


}
