package com.bihell.dice.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;


/**
 * 用户 Model
 *
 * @author bihell
 * @since 2017/7/9 22:09
 */

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class User extends Model<User> {


    @TableId
    private Long id;

    /**
     * 账号
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 用户显示名称
     */
    private String screenName;

    /**
     * 用户创建时间
     */
    private LocalDateTime created;

    /**
     * 最后登陆时间
     */
    private LocalDateTime logged;

    @TableField(exist = false)
    private List<Integer> roleIds;

    @ApiModelProperty("盐值")
    private String salt;

}
