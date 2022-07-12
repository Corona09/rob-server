package com.xjtu.dbc.robserver.user.personal.dao;

import com.xjtu.dbc.robserver.common.model.article.Article;
import com.xjtu.dbc.robserver.common.model.user.User;
import com.xjtu.dbc.robserver.user.personal.entity.ArticleDto;
import com.xjtu.dbc.robserver.user.personal.entity.FollowDto;

import java.util.List;

public interface PersonalDao {

    void getMyAvatar(Integer uid, String url);

    void changeInformation(User user);

    User checkPassword(User user);

    void changePassword(User user);

    List<Article> getArtical(ArticleDto articleDto);

    Integer getRelationship(Integer myid, Integer userid);

    Integer getListid(Integer myid, int constant);

    void follow(Integer userlistid,Integer userid);

    void disfollow(Integer userlistid,Integer userid);

    void deleteBlog(Integer articleid);

    List<FollowDto> getFollow(Integer userlistid);

    List<FollowDto> getFans(Integer userid,int type);

    Integer getFollowNum(Integer userlistid);

    Integer getFansNum(Integer userid,int type);

    Integer getFavoritesid(Integer userid);

    Integer getFavoritesNum(Integer bookmarkid);

    Integer getHistoryNum(Integer userid,int type);

    Integer getHuntNum(Integer userid,int type);
}
