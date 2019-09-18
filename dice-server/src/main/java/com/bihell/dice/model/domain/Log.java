package com.bihell.dice.model.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.bihell.dice.model.enums.LogType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 日志 Model
 *
 * @author bihell
 * @since 2017/10/11 9:57
 */
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class Log extends Model<Log> {

    @TableId
    private Integer id;

    /**
     * 操作动作
     */
    private String action;

    /**
     * 操作数据
     */
    private String data;

    /**
     * 操作信息
     */
    private String message;

    /**
     * 操作类型
     */
    private LogType type;

    /**
     * ip地址
     */
    private String ip;

    /**
     * 操作人
     */
    private Integer userId;

    /**
     * 操作时间
     */
    private Date created;
}
