package com.bihell.dice.service.auth.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bihell.dice.mapper.auth.AuthRelRoleApiMapper;
import com.bihell.dice.model.auth.AuthRelRoleApi;
import com.bihell.dice.service.auth.AuthRelRoleApiService;
import org.springframework.stereotype.Service;

/**
 * @author haseochen
 */
@Service
public class AuthRelRoleApiServiceImpl extends ServiceImpl<AuthRelRoleApiMapper, AuthRelRoleApi> implements AuthRelRoleApiService {
}
