package com.bihell.dice.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bihell.dice.system.entity.AuthGroup;
import com.bihell.dice.system.param.QueryParam;

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
