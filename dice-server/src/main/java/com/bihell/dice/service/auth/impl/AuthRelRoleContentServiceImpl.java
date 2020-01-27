package com.bihell.dice.service.auth.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bihell.dice.mapper.auth.AuthRelRoleContentMapper;
import com.bihell.dice.model.auth.AuthRelRoleContent;
import com.bihell.dice.service.auth.AuthRelRoleContentService;
import org.springframework.stereotype.Service;

/**
 * @author haseochen
 */
@Service
public class AuthRelRoleContentServiceImpl extends ServiceImpl<AuthRelRoleContentMapper, AuthRelRoleContent> implements AuthRelRoleContentService {
}
