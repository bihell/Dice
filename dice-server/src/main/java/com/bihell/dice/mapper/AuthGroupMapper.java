package com.bihell.dice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bihell.dice.model.domain.AuthGroup;
import com.bihell.dice.model.params.Param;

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
    List<AuthGroup> queryByProjectType(Param param);

}
