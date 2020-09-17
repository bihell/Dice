package com.bihell.dice.blog.service.auth.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bihell.dice.blog.mapper.auth.AuthRelRoleContentMapper;
import com.bihell.dice.blog.model.auth.AuthRelRoleContent;
import com.bihell.dice.blog.service.auth.AuthRelRoleContentService;
import org.springframework.stereotype.Service;

/**
 * @author haseochen
 */
@Service
public class AuthRelRoleContentServiceImpl extends ServiceImpl<AuthRelRoleContentMapper, AuthRelRoleContent> implements AuthRelRoleContentService {
}
