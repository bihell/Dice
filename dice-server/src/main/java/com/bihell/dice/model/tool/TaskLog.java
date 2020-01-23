package com.bihell.dice.model.tool;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;


/**
 * @author haseochen
 */

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class TaskLog extends Model<TaskLog> {

    @TableId
    private Long id;
    private Long idTask;
    private String name;
    private Date execAt;
    private int execSuccess;
    private String jobException;
}
