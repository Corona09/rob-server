package com.xjtu.dbc.robserver.dynamic.home.impl;


import com.xjtu.dbc.robserver.dynamic.home.DynamicHomeService;
import com.xjtu.dbc.robserver.dynamic.home.dao.DynamicHomeDao;
import com.xjtu.dbc.robserver.dynamic.home.entity.DynamicHomeDto;
import com.xjtu.dbc.robserver.dynamic.home.entity.DynamicHomeListDto;
import com.xjtu.dbc.robserver.dynamic.home.entity.DynamicMyHomeListDto;
import com.xjtu.dbc.robserver.user.register.dao.RegisterDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class DynamicHomeServiceImpl implements DynamicHomeService {

    @Resource
    private DynamicHomeDao dynamicHomeDao;

    @Override
    public DynamicHomeDto getUserInfo(Integer userid){
        return dynamicHomeDao.getUserInfo(userid);
    }

    @Override
    public List<DynamicMyHomeListDto> getDynamicList(Integer userid){
        return dynamicHomeDao.getDynamicList(userid);
    }

    @Override
    public List<DynamicMyHomeListDto> getMyDynamicList(Integer userid){
        return dynamicHomeDao.getMyDynamicList(userid);
    }


    @Override
    public List<DynamicMyHomeListDto> getFollowDynamicList(Integer userid){
        return dynamicHomeDao.getFollowDynamicList(userid);
    }


    @Override
    public DynamicMyHomeListDto getDynamic(Integer articleid){
        return dynamicHomeDao.getDynamic(articleid);
    }

    @Override
    public int getFollownumByUserid(Integer userid){
        return  dynamicHomeDao.getFollownumByUserid(userid);
    }

    @Override
    public int getFansnumByUserid(Integer userid){
        return  dynamicHomeDao.getFansnumByUserid(userid);
    }

    @Override
    public int getDynamicnumByUserid(Integer userid){
        return  dynamicHomeDao.getDynamicnumByUserid(userid);
    }




    @Override
    public int getLikenumByAriticleid(Integer articleid){
        return dynamicHomeDao.getLikenumByAriticleid(articleid);
    }

    @Override
    public int getDislikenumByAriticleid(Integer articleid){
        return dynamicHomeDao.getDislikenumByAriticleid(articleid);
    }

    @Override
    public int getCommentnumByArticleid(Integer articleid){
        return dynamicHomeDao.getCommentnumByArticleid(articleid);
    }

}
