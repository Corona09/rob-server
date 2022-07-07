package com.xjtu.dbc.robserver.dynamic.post.dao;

import com.xjtu.dbc.robserver.dynamic.comment.entity.DynamicCommentDto;
import com.xjtu.dbc.robserver.dynamic.post.entity.DynamicPostDto;

public interface DynamicPostDao {

    /**
     * 新增动态
     * @param dynamicPostDto 动态的信息
     */
    void addDynamic(DynamicPostDto dynamicPostDto);

    /**
     * 获取当前最大的动态记录的编号
     * @return 最大的动态记录的编号
     */
    Integer getMaxDynamicId();

}
