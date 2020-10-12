package com.bihell.dice.blog.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bihell.dice.blog.model.blog.Comment;
import com.bihell.dice.blog.model.dto.CommentDto;
import com.bihell.dice.framework.common.api.ApiCode;
import com.bihell.dice.framework.common.api.ApiResult;
import com.bihell.dice.framework.core.pagination.Pagination;
import com.bihell.dice.blog.service.blog.CommentService;
import com.bihell.dice.config.constant.DiceConsts;
import com.bihell.dice.framework.util.DiceUtil;
import com.bihell.dice.framework.common.api.RestResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 后台评论管理 Controller
 *
 * @author bihell
 * @since 2018/1/21 10:47
 */
@RestController
@RequestMapping("/v1/api/admin/comment")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class CommentController {

    private final CommentService commentService;

    /**
     * 获取所有评论
     *
     * @param pageNum  第几页
     * @param pageSize 每页数量
     * @return {@see Pagination<Comment>}
     */
    @GetMapping
    public RestResponse index(@RequestParam(required = false, defaultValue = "1") Integer pageNum,
                              @RequestParam(required = false, defaultValue = DiceConsts.PAGE_SIZE) Integer pageSize) {
        IPage<Comment> comments = commentService.getAdminComments(pageNum, pageSize);
        return RestResponse.ok(new Pagination<Comment>(comments));
    }

    /**
     * 获取评论详情
     *
     * @param id 评论id
     * @return {@see CommentDto}
     */
    @GetMapping("{id}")
    public ApiResult<CommentDto> detail(@PathVariable Integer id) {
        CommentDto comment = commentService.getCommentDetail(id);
        if (null == comment) {
            return ApiResult.fail(ApiCode.NOT_FOUND, null);
        }
        if (null != comment.getPComment()) {
            comment.getPComment().setContent(DiceUtil.mdToHtml(comment.getPComment().getContent()));
        }
        comment.setContent(DiceUtil.mdToHtml(comment.getContent()));
        return ApiResult.ok(comment);
    }

    /**
     * 删除评论
     *
     * @param id 评论id
     * @return {@see RestResponse.ok()}
     */
    @DeleteMapping("{id}")
    public RestResponse delete(@PathVariable Integer id) {
        if (commentService.deleteComment(id)) {

            return RestResponse.ok();
        } else {
            return RestResponse.fail("删除评论失败");
        }
    }

    /**
     * 获取评论数量
     *
     * @return {@see Integer}
     */
    @GetMapping("count")
    public RestResponse count() {
        return RestResponse.ok(commentService.count());
    }

}
