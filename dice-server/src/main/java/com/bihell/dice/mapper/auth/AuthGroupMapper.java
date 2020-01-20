package com.bihell.dice.mapper.auth;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bihell.dice.model.auth.AuthGroup;
import com.bihell.dice.model.params.QueryParam;

import java.util.List;

/**
 *
 * @author bihell
 * @since 2017/7/12 21:34
 */
public interface AuthGroupMapper extends BaseMapper<AuthGroup> {

    /**
     * @param param
     * @return
     */
    List<AuthGroup> queryByParam(QueryParam param);

}
