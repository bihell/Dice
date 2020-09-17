package com.bihell.dice.blog.service.task;

import com.bihell.dice.blog.model.tool.Task;

/**
 * @author haseochen
 */
public interface TaskService {

    /**
     * @param task
     * @return
     */
    Task save(Task task);

    public Task disable(Long id);

    Task enable(Long id);

    void delete(Long id);
}
