package com.bihell.dice.blog.service.auth.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bihell.dice.blog.mapper.auth.AuthRelRoleUserMapper;
import com.bihell.dice.blog.model.auth.AuthRelRoleUser;
import com.bihell.dice.blog.service.auth.AuthRelRoleUserService;
import org.springframework.stereotype.Service;

/**
 * @author haseochen
 */
@Service
public class AuthRelRoleUserServiceImpl extends ServiceImpl<AuthRelRoleUserMapper, AuthRelRoleUser> implements AuthRelRoleUserService {
}
