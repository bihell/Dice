package com.bihell.dice.service.auth.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bihell.dice.mapper.auth.AuthRelItemApiMapper;
import com.bihell.dice.model.auth.AuthRelItemApi;
import com.bihell.dice.service.auth.AuthRelItemApiService;
import org.springframework.stereotype.Service;

/**
 * @author haseochen
 */
@Service
public class AuthRelItemApiServiceImpl extends ServiceImpl<AuthRelItemApiMapper, AuthRelItemApi> implements AuthRelItemApiService {
}
