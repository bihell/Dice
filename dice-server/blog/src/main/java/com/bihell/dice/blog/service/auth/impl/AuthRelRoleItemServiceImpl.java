package com.bihell.dice.blog.service.auth.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bihell.dice.blog.mapper.auth.AuthRelRoleItemMapper;
import com.bihell.dice.blog.model.auth.AuthRelRoleItem;
import com.bihell.dice.blog.service.auth.AuthRelRoleItemService;
import org.springframework.stereotype.Service;

/**
 * @author haseochen
 */
@Service
public class AuthRelRoleItemServiceImpl extends ServiceImpl<AuthRelRoleItemMapper, AuthRelRoleItem> implements AuthRelRoleItemService {
}
