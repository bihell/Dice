package com.bihell.dice.model.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

/**
 * 用户 Model
 *
 * @author bihell
 * @since 2017/7/9 22:09
 */
@Entity
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Data
public class User extends BaseEntity {

    /**
     * 账号
     */
    @Column(name = "username", columnDefinition = "VARCHAR(45) NOT NULL UNIQUE")
    private String username;

    /**
     * 用户密码 md5存储
     */
    @Column(name = "password_md5", columnDefinition = "VARCHAR(45) NOT NULL")
    private String passwordMd5;

    /**
     * 用户邮箱
     */
    @Column(name = "email", columnDefinition = "VARCHAR(45)")
    private String email;

    /**
     * 用户显示名称
     */
    @Column(name = "screen_name", columnDefinition = "VARCHAR(45)")
    private String screenName;

    /**
     * 用户创建时间
     */
    @Column(name = "created", columnDefinition = "TIMESTAMP NOT NULL DEFAULT current_timestamp")
    private Date created;

    /**
     * 最后登陆时间
     */
    @Column(name = "logged", columnDefinition = "TIMESTAMP NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp")
    private Date logged;

}
