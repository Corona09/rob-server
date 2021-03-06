package com.xjtu.dbc.robserver.manage.module;

import com.xjtu.dbc.robserver.common.Constants;
import com.xjtu.dbc.robserver.common.Result;
import com.xjtu.dbc.robserver.manage.module.entity.ModuleDto;
import com.xjtu.dbc.robserver.manage.module.entity.ModuleVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/manage/module")
public class ManageModuleAPI {
    @Resource
    private ManageModuleService manageModuleService;

    /**
     * 获取模块列表
     * @param token 用户令牌
     * @return Result {data: 模块列表}
     */
    @GetMapping("/get")
    public Result getModules(@RequestHeader("Token") String token) {
        List<ModuleVO> moduleVOTree = manageModuleService.getModulesTree();
        return Result.successData(moduleVOTree);
    }

    /**
     * 启用某一模块及其子模块
     * @param moduleDto 模块参数 { 模块  ID }
     * @param token 用户令牌
     * @return Result { Message }
     */
    @PostMapping("/open")
    public Result setModuleAvailable(@RequestBody ModuleDto moduleDto, @RequestHeader("Token") String token) {
        manageModuleService.setModuleAvailable(moduleDto.getModuleid());
        return Result.success();
    }

    /**
     * 禁用某一模块及其子模块
     * @param moduleDto 模块参数 { 模块  ID }
     * @param token 用户令牌
     * @return Result { Message }
     */
    @PostMapping("/ban")
    public Result setModuleUnavailable(@RequestBody ModuleDto moduleDto, @RequestHeader("Token") String token) {
        if (moduleDto.getModuleid() == null) {
            return Result.fail(Result.ERR_CODE_BUSINESS, "未指定模块");
        }

        Integer moduleStatus = manageModuleService.getModuleStatus(moduleDto.getModuleid());

        if (moduleStatus == Constants.MODULE_STATUS_PROTECT) {
            return Result.fail(Result.ERR_CODE_BUSINESS, "该模块处于保护状态");
        }

        log.info("Module Status :" + moduleStatus);
        manageModuleService.setModuleUnavailable(moduleDto.getModuleid());
        return Result.success();
    }

    /**
     * 将模块设置为保护模块
     * @param moduleDto 模块参数
     * @param token 用户令牌
     * @return Result
     */
    @PostMapping("/protect")
    public Result setModuleProtected(@RequestBody ModuleDto moduleDto, @RequestHeader("Token") String token) {
//        Integer moduleStatus = manageModuleService.getModuleStatus(moduleDto.getParentid());

        manageModuleService.setModuleProtected(moduleDto.getModuleid());

        return Result.success();
    }

    /**
     * 新增模块
     * @param moduleDto 模块参数
     * @param token 用户令牌
     * @return Result
     */
    @PostMapping("/add")
    public Result addModule(@RequestBody ModuleDto moduleDto, @RequestHeader("Token") String token) {
        moduleDto.setModulestatus(Constants.MODULE_STATUS_NORMAL);
        manageModuleService.addModule(moduleDto);
        return Result.successMsg("添加成功");
    }

    /**
     * 修改模块
     * @param moduleDto
     * @param token
     * @return
     */
    @PostMapping("/update")
    public Result updateModule(@RequestBody ModuleDto moduleDto, @RequestHeader("Token") String token) {
        manageModuleService.updateModule(moduleDto);
        return  Result.success();
    }

    @PostMapping("/delete")
    public Result deleteModule(@RequestBody ModuleDto moduleDto, @RequestHeader("Token") String token) {
        Integer moduleStatus = manageModuleService.getModuleStatus(moduleDto.getModuleid());
        if (moduleStatus == Constants.MODULE_STATUS_PROTECT) {
            return Result.fail(Result.ERR_CODE_BUSINESS, "模块处于保护状态, 无法删除!");
        }
        manageModuleService.deleteModule(moduleDto.getModuleid());
        return Result.success();
    }

}
