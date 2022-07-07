package com.xjtu.dbc.robserver.dynamic.home.dao;

import com.xjtu.dbc.robserver.dynamic.home.entity.DynamicHomeDto;
import com.xjtu.dbc.robserver.dynamic.home.entity.DynamicHomeListDto;

import java.util.List;

public interface DynamicHomeDao {

    DynamicHomeDto getUserInfo(Integer userid);

    List<DynamicHomeListDto> getDynamicList(Integer userid);

    Integer getFansnumByUserid(Integer userid);

    Integer getDynamicnumByUserid(Integer userid);


    Integer getLikenumByAriticleid(Integer articleid);

    Integer getDislikenumByAriticleid(Integer articleid);
}