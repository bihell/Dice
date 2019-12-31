package com.bihell.dice.model.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;
import java.util.Date;
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
    private Integer id;

    /**
     * 账号
     */
    private String username;

    /**
     * 用户密码 md5存储
     */
    @JsonIgnore
    private String passwordMd5;

    /**
     * 用户邮箱
     */
    @JsonIgnore
    private String email;

    /**
     * 用户显示名称
     */
    private String screenName;

    /**
     * 用户创建时间
     */
    @JsonIgnore
    private Date created;

    /**
     * 最后登陆时间
     */
    @JsonIgnore
    private Date logged;

    @TableField(exist = false)
    @JsonIgnore
    private List<Integer> roleIds;

}
