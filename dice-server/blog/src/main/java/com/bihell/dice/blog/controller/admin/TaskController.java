package com.bihell.dice.blog.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bihell.dice.blog.mapper.tool.TaskLogMapper;
import com.bihell.dice.blog.mapper.tool.TaskMapper;
import com.bihell.dice.framework.core.pagination.Pagination;
import com.bihell.dice.blog.model.tool.Task;
import com.bihell.dice.blog.model.tool.TaskLog;
import com.bihell.dice.blog.service.task.TaskService;
import com.bihell.dice.framework.common.api.RestResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author haseochen
 */
@RestController
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RequestMapping("/v1/api/admin/task")
public class TaskController {
    private final TaskMapper taskMapper;
    private final TaskService taskService;
    private final TaskLogMapper taskLogMapper;

    /**
     * 获取定时任务管理列表
     */
    @RequestMapping(value = "/list")
    public Object list(String name) {
        // todo 这里返回分页稍后实现
        if (StringUtils.isEmpty(name)) {
            return RestResponse.ok(new Task().selectAll());
        } else {
            return RestResponse.ok(taskMapper.selectList(new QueryWrapper<Task>().lambda().like(Task::getName, name)));
        }
    }

    /**
     * 新增定时任务管理
     */
    @RequestMapping(method = RequestMethod.POST)
    public Object add(@ModelAttribute @Valid Task task) {
        if (task.getId() == null) {
            taskService.save(task);
        } else {
            Task old = taskMapper.selectById(task.getId());
            old.setName(task.getName());
            old.setCron(task.getCron());
            old.setJobClass(task.getJobClass());
            old.setNote(task.getNote());
            old.setData(task.getData());
            old.updateById();
        }
        return RestResponse.ok();
    }

    /**
     * 删除定时任务管理
     */
    @RequestMapping(method = RequestMethod.DELETE)

    public Object delete(@RequestParam Long id) {
        taskService.delete(id);
        return RestResponse.ok();
    }

    @RequestMapping(value = "/disable", method = RequestMethod.POST)
    public Object disable(@RequestParam Long taskId) {
        taskService.disable(taskId);
        return RestResponse.ok();
    }

    @RequestMapping(value = "/enable", method = RequestMethod.POST)
    public Object enable(@RequestParam Long taskId) {
        taskService.enable(taskId);
        return RestResponse.ok();
    }

    @RequestMapping(value = "/logList")
    public Object logList(@RequestParam Long taskId) {
        // todo 稍后完善分页
        Page<TaskLog> page = new Page<>(1, 20);
        LambdaQueryWrapper<TaskLog> wrapper = new QueryWrapper<TaskLog>().lambda()
                .eq(TaskLog::getIdTask, taskId);
        return RestResponse.ok(new Pagination<TaskLog>(taskLogMapper.selectPage(page, wrapper)));
    }
}
