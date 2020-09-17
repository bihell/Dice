package com.bihell.dice.blog.service.auth.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bihell.dice.blog.mapper.auth.AuthRelRoleApiMapper;
import com.bihell.dice.blog.model.auth.AuthRelRoleApi;
import com.bihell.dice.blog.service.auth.AuthRelRoleApiService;
import org.springframework.stereotype.Service;

/**
 * @author haseochen
 */
@Service
public class AuthRelRoleApiServiceImpl extends ServiceImpl<AuthRelRoleApiMapper, AuthRelRoleApi> implements AuthRelRoleApiService {
}
