package com.xjtu.dbc.robserver.question.comment.dao;

import com.xjtu.dbc.robserver.question.comment.entity.CommentDto;

import java.util.List;

public interface CommentDao {
    /**
     * 获取评论列表
     * @param articleid 评论id
     */
    List<CommentDto> getCommentList(Integer articleid);

    /**
     * 新增评论
     * @param commentDto 评论的信息
     */
    void addComment(CommentDto commentDto);

    /**
     * 获取当前最大的评论记录的编号
     * @return 最大的评论记录的编号
     */
    Integer getMaxCommentId();

    /**
     * 根据用户的编号与动态的编号来获取该动态的点赞点踩类型
     * @param authorid,aticleid  用户编编号与动态的编号
     * @return 该动态的点赞点踩类型
     */
    Integer is_in_blacklist(Integer authorid,Integer articleid);
}
