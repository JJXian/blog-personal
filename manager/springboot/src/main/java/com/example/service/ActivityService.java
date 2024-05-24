package com.example.service;

import cn.hutool.core.date.DateUtil;
import com.example.common.enums.LikesModuleEnum;
import com.example.common.enums.RoleEnum;
import com.example.entity.*;
import com.example.mapper.ActivityMapper;
import com.example.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActivityService {

    @Resource
    private ActivityMapper activityMapper;
    @Resource
    ActivitySignService activitySignService;

    @Resource
    LikesService likesService;

    @Resource
    CollectService collectService;


    /**
     * 新增
     * @param activity
     */
    public void add(Activity activity) {
        activityMapper.add(activity);
    }

    public void deleteById(Integer id) {
        activityMapper.deleteById(id);
    }

    public void deleteBatch(List<Integer> ids) {
        for(Integer  id : ids){
            activityMapper.deleteById(id);
        }
    }

    public void updateById(Activity activity) {
        activityMapper.updateById(activity);
    }

    /**
     * 根据ID查询
     */
    public Activity selectById(Integer id) {
        Activity activity = activityMapper.selectById(id);
        this.setAct(activity, TokenUtils.getCurrentUser());

        int likesCount = likesService.selectByFidAndModule(id, LikesModuleEnum.ACTIVITY.getValue());
        int collectCount = collectService.selectByFidAndModule(id, LikesModuleEnum.ACTIVITY.getValue());
        activity.setLikesCount(likesCount);
        activity.setCollectCount(collectCount);

        Likes likes = likesService.selectUserLikes(id, LikesModuleEnum.ACTIVITY.getValue());
        activity.setIsLike(likes != null);

        Collect collect = collectService.selectUserCollect(id, LikesModuleEnum.ACTIVITY.getValue());
        activity.setIsCollect(collect != null);
        return activity;
    }

    public List<Activity> selectAll(Activity activity) {
        return activityMapper.selectAll(activity);
    }

    /**
     * 分页查询
     * @param activity
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<Activity> selectPage(Activity activity, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Activity> list = activityMapper.selectAll(activity);
        PageInfo<Activity> pageInfo = PageInfo.of(list);
        List<Activity> activityList = pageInfo.getList();
        for (Activity act : activityList) {
            act.setIsEnd(DateUtil.parseDate(act.getEnd()).isBefore(new Date()));  // 活动的结束时间在当前时间之前  就表示活动结束了
        }
        return pageInfo;
//        return PageInfo.of(list);
    }

    /**
     * 热门活动
     */
    public List<Activity> selectTop() {
        List<Activity> activityList = this.selectAll(null);
        activityList = activityList.stream().sorted((b1, b2) -> b2.getReadCount().compareTo(b1.getReadCount()))
                .limit(2)
                .collect(Collectors.toList());
        return activityList;
    }


    /**
     * 更新阅读量
     * @param activityId
     */
    public void updateReadCount(Integer activityId) {
        activityMapper.updateReadCount(activityId);
    }

    // 设置活动额外信息
    private void setAct(Activity act, Account currentUser) {
        act.setIsEnd(DateUtil.parseDate(act.getEnd()).isBefore(new Date()));  // 活动的结束时间在当前时间之前  就表示活动结束了
        ActivitySign activitySign = activitySignService.selectByActivityIdAndUserId(act.getId(), currentUser.getId());
        act.setIsSign(activitySign != null);
    }

    /**
     * 查询用户报名的列表
     * @param activity
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<Activity> selectUser(Activity activity, Integer pageNum, Integer pageSize) {
        Account currentUser = TokenUtils.getCurrentUser();
        if (RoleEnum.USER.name().equals(currentUser.getRole())) {
            activity.setUserId(currentUser.getId());
        }
        PageHelper.startPage(pageNum, pageSize);
        List<Activity> list = activityMapper.selectUser(activity);
        PageInfo<Activity> pageInfo = PageInfo.of(list);
        List<Activity> activityList = pageInfo.getList();
        for (Activity act : activityList) {
            this.setAct(act, currentUser);
        }
        return pageInfo;
    }

    /**
     * 用户点赞的博客
     * @param activity
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<Activity> selectLike(Activity activity, Integer pageNum, Integer pageSize) {
        Account currentUser = TokenUtils.getCurrentUser();
        if (RoleEnum.USER.name().equals(currentUser.getRole())) {
            activity.setUserId(currentUser.getId());
        }
        PageHelper.startPage(pageNum, pageSize);
        List<Activity> list = activityMapper.selectLike(activity);
        PageInfo<Activity> pageInfo = PageInfo.of(list);
        List<Activity> activityList = pageInfo.getList();
        for (Activity act : activityList) {
            this.setAct(act, currentUser);
        }
        return pageInfo;
    }

    /**
     * 用户收藏的博客
     * @param activity
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<Activity> selectCollect(Activity activity, Integer pageNum, Integer pageSize) {
        Account currentUser = TokenUtils.getCurrentUser();
        if (RoleEnum.USER.name().equals(currentUser.getRole())) {
            activity.setUserId(currentUser.getId());
        }
        PageHelper.startPage(pageNum, pageSize);
        List<Activity> list = activityMapper.selectCollect(activity);
        PageInfo<Activity> pageInfo = PageInfo.of(list);
        List<Activity> activityList = pageInfo.getList();
        for (Activity act : activityList) {
            this.setAct(act, currentUser);
        }
        return pageInfo;
    }

    /**
     * 用户评论的博客
     * @param activity
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<Activity> selectComment(Activity activity, Integer pageNum, Integer pageSize) {
        Account currentUser = TokenUtils.getCurrentUser();
        if (RoleEnum.USER.name().equals(currentUser.getRole())) {
            activity.setUserId(currentUser.getId());
        }
        PageHelper.startPage(pageNum, pageSize);
        List<Activity> list = activityMapper.selectComment(activity);
        PageInfo<Activity> pageInfo = PageInfo.of(list);
        List<Activity> activityList = pageInfo.getList();
        for (Activity act : activityList) {
            this.setAct(act, currentUser);
        }
        return pageInfo;
    }




}
