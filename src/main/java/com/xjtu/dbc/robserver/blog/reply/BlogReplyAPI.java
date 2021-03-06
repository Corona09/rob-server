package com.xjtu.dbc.robserver.blog.reply;

import com.xjtu.dbc.robserver.blog.publish.BlogPublishService;
import com.xjtu.dbc.robserver.common.CommonService;
import com.xjtu.dbc.robserver.common.Result;
import com.xjtu.dbc.robserver.common.TokenUtils;
import com.xjtu.dbc.robserver.common.Utils;
import com.xjtu.dbc.robserver.common.model.reply.Reply;
import com.xjtu.dbc.robserver.level.Level;
import com.xjtu.dbc.robserver.level.LevelService;
import com.xjtu.dbc.robserver.manage.sensitive.SensitiveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController//本注解说明本类的对象将作为受spring容器管理的对象，并且说明这是一个控制器组件(Bean)
@RequestMapping("/blog")//本注解说明本控制器内的所有路径都以之开头
public class BlogReplyAPI {
    @Resource
    private BlogReplyService replyService;

    @Resource
    private BlogPublishService blogPublishService;

    @Resource
    private LevelService levelService;

    @Resource
    private CommonService commonService;

    @Resource
    private SensitiveService sensitiveService;

    @PostMapping("/reply")
    public Result reply(@RequestBody Reply dto, @RequestHeader("Token") String token){
        Integer myid = TokenUtils.getUserInfo(token,commonService).getUserid();//当前用户id;
        if (blogPublishService.getUserStatus(myid) != 200) {
            return Result.fail(Result.ERR_CODE_BUSINESS, "您当前无法发言！");
        }
        if (replyService.ifPullBlack(myid,dto.getRootid()) > 0) {
            return Result.fail(Result.ERR_CODE_BUSINESS, "您已被博主拉黑！");
        }
        if (dto.getRootid()!=dto.getReplyto() && replyService.ifPullBlack(myid,dto.getReplyto()) > 0) {
            return Result.fail(Result.ERR_CODE_BUSINESS, "您已被当前用户拉黑！");
        }
        int blogStatus = blogPublishService.getArticleStatus(dto.getRootid());
        if (blogStatus != 402) {
            return Result.fail(Result.ERR_CODE_BUSINESS, "博客未发布，无法评论！");
        }
        if (dto.getContent()==null || "".equals(dto.getContent())) {
            return Result.fail(Result.ERR_CODE_BUSINESS, "内容不能为空！");
        }
        dto.setAuthorid(myid);
        dto.setCreatetime(Utils.getNow());
        replyService.reply(dto);
        return Result.success("成功回复！",dto);
    }

    @GetMapping("/replylist")
    public Result getReplyList(@RequestParam("articleid") int articleid){
        List<ReplyDto> replyList = replyService.getReplyList(articleid);
        int value;
        for (ReplyDto reply:replyList) {
            value = levelService.getLevel(reply.getAuthorid());
            Level level = new Level(value);
            reply.setLevelname(level.getName());
            reply.setContent(sensitiveService.filter(reply.getContent(),'*'));
        }
        return Result.success("获取回复列表成功！",replyList);
    }

    @PostMapping("/delReply")
    public Result delReply(@RequestBody Reply dto, @RequestHeader("Token") String token){
        Integer myid = TokenUtils.getUserInfo(token,commonService).getUserid();//当前用户id;
        dto.setAuthorid(myid);
        if (replyService.cannotDelReply(dto)) {
            return Result.fail(Result.ERR_CODE_BUSINESS, "您没有删除该评论的权限！");
        }
        replyService.delReply(dto.getReplyid());
        return Result.success("删除成功！",dto);
    }
}
