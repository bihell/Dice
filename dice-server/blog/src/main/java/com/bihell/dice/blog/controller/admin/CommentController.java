package com.bihell.dice.blog.controller.admin;

import com.bihell.dice.blog.model.blog.Comment;
import com.bihell.dice.blog.model.dto.CommentDto;
import com.bihell.dice.blog.param.CommentPageParam;
import com.bihell.dice.framework.common.api.ApiCode;
import com.bihell.dice.framework.common.api.ApiResult;
import com.bihell.dice.blog.service.blog.CommentService;
import com.bihell.dice.framework.core.pagination.Paging;
import com.bihell.dice.framework.util.DiceUtil;
import com.bihell.dice.framework.common.api.RestResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
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
     * @return {@see Pagination<Comment>}
     */
    @PostMapping("/getPageList")
    public ApiResult<Paging<Comment>> getCommentPageList(@Validated @RequestBody CommentPageParam commentPageParam) {
        Paging<Comment> paging = commentService.getCommentPageList(commentPageParam);
        return ApiResult.ok(paging);
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
    public ApiResult<Boolean> delete(@PathVariable Integer id) {
        boolean flag = commentService.deleteComment(id);
        return ApiResult.result(flag);
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
