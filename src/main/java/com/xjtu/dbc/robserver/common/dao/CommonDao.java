package com.xjtu.dbc.robserver.common.dao;

import com.xjtu.dbc.robserver.common.model.tag.Tag;
import com.xjtu.dbc.robserver.common.model.user.User;

public interface CommonDao {

    /**
     * 根据 ID 获取用户数据
     * @param userId 用户 ID
     * @return 用户数据
     */
    User getUserById(Integer userId);

    /**
     * 添加历史记录
     * @param userId 用户 ID
     * @param historyType 历史类型
     * @param articleId 文本 ID (可以为  null)
     */
    void addHistory(Integer userId, Integer historyType, Integer articleId);

    /**
     * 添加Tag
     * @param tag (tagid tag名称 tag所属)
     */
    void addTag(Tag tag);

    /**
     * 删除用户Tag
     * @param tagid 标签id
     */
    void deleteTag(int tagid);

}
