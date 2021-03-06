package com.xjtu.dbc.robserver.user.login;

import com.xjtu.dbc.robserver.common.*;
import com.xjtu.dbc.robserver.user.login.entity.LoginDto;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

@RestController
@RequestMapping("/user/login")
public class LoginAPI {
    @Resource
    private CommonService commonService;

    @Resource
    private LoginService loginService;

    @PostMapping("")
    public Result login(@RequestBody LoginDto loginDto) {
        Integer cnt = null;
        Integer userId = null;
        if (loginDto.getUserid() != null) {
            userId = loginDto.getUserid();
        } else if (loginDto.getUseremail() != null) {
            userId = loginService.getUserIdByEmail(loginDto.getUseremail());
        } else if (loginDto.getUsername() != null) {
            userId = loginService.getUserIdByName(loginDto.getUsername());
        } else {
            return Result.fail(Result.ERR_CODE_BUSINESS, "请指定用户ID/用户名/邮箱");
        }

        cnt = loginService.verifyById(userId, loginDto.getUserpwd());
        if (cnt == 1) {
            System.out.println("Client Token = " + userId);
            String token = TokenUtils.loginSign(Integer.toString(userId), loginDto.getUserpwd());
            if (loginService.isFirstLoginToday(userId)) {
                // 为用户增加经验
                loginService.addExp(userId, 10);
                // 为用户增加代币
                loginService.addCans(userId, 1);
            }
            // 添加历史记录
            commonService.addHistory(userId, Constants.HISTORY_LOGIN, null);
            return Result.success("登录成功", token);
        } else {
            return Result.fail(Result.ERR_CODE_BUSINESS, "帐号或密码错误");
        }
    }
}
