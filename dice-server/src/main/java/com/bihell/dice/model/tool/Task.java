package com.bihell.dice.model.tool;


import com.baomidou.mybatisplus.annotation.TableId;
import com.bihell.dice.model.BaseEntity;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.Date;


/**
 * @author haseochen
 */

@Setter
@Getter
@Accessors(chain = true)
public class Task extends BaseEntity<Task> {

    @TableId
    private Long id;

    private String name;

    private String jobGroup;

    private String jobClass;

    private String note;

    private String cron;

    private boolean concurrent;

    private String data;

    private Date execAt;

    private String execResult;

    private Integer status;
}
