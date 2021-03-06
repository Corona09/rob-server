package com.xjtu.dbc.robserver.dynamic.home.dao;

import com.xjtu.dbc.robserver.dynamic.home.entity.DynamicHomeDto;
import com.xjtu.dbc.robserver.dynamic.home.entity.DynamicHomeListDto;
import com.xjtu.dbc.robserver.dynamic.home.entity.DynamicMyHomeListDto;


import java.util.List;

public interface DynamicHomeDao {

    DynamicHomeDto getUserInfo(Integer userid);

    List<DynamicMyHomeListDto> getDynamicList(Integer userid);

    List<DynamicMyHomeListDto> getMyDynamicList(Integer userid);


    DynamicMyHomeListDto getDynamic(Integer dynamicid);

    Integer deleteDynamic(Integer articleid);

    Integer getFollownumByUserid(Integer userid);



    Integer getFansnumByUserid(Integer userid);

    Integer getDynamicnumByUserid(Integer userid);

    Integer getLikenumByAriticleid(Integer articleid);

    Integer getDislikenumByAriticleid(Integer articleid);

    Integer getCommentnumByArticleid(Integer articleid);


    Integer is_in_blacklist(Integer userid,Integer loginid);

    Integer getVoteTypeByU_A_id(Integer userid,Integer articleid);

}
