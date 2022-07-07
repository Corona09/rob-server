package com.xjtu.dbc.robserver.dynamic.home;


import com.xjtu.dbc.robserver.common.CommonService;
import com.xjtu.dbc.robserver.common.Result;

import com.xjtu.dbc.robserver.dynamic.home.entity.DynamicHomeDto;
import com.xjtu.dbc.robserver.dynamic.home.entity.DynamicHomeListDto;
import com.xjtu.dbc.robserver.user.register.RegisterService;
import com.xjtu.dbc.robserver.user.register.entity.RegisterDto;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/dynamic/home")
public class DynamicHomeAPI {
    @Resource
    private CommonService commonService;
    @Resource
    private DynamicHomeService dynamicHomeService;

    /*
     * 个人动态主页左边的个人信息栏的数据
     * */
    @GetMapping("uInfo")
    public Result getUInfo( Integer userid) {

        DynamicHomeDto homeDto = dynamicHomeService.getUserInfo(userid);
        homeDto.setFans_num(dynamicHomeService.getFansnumByUserid(userid));
        homeDto.setDynamic_num(dynamicHomeService.getDynamicnumByUserid(userid));
        //还需要得到用户的粉丝数 动态数
        return Result.success("获取动态主页的个人信息成功!", homeDto);
    }


    /*
     * 个人动态主页左边的个人信息栏的数据
     * */
    @GetMapping("dList")
    public Result getDList( Integer userid) {

        List<DynamicHomeListDto> listDto = dynamicHomeService.getDynamicList(userid);

        //获取每条评论的点赞数与点踩数
        for(int i=0; i<listDto.size();i++){
            listDto.get(i).setLike_num(dynamicHomeService.getLikenumByAriticleid(listDto.get(i).getArticleid()));
            listDto.get(i).setDislike_num(dynamicHomeService.getDislikenumByAriticleid(listDto.get(i).getArticleid()));
        }

        //还需要得到用户的粉丝数 动态数
        return Result.success("获取动态主页的动态列表成功!", listDto);
    }





}