package com.bihell.dice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bihell.dice.model.domain.AuthItem;
import com.bihell.dice.model.params.Param;

import java.util.List;

/**
 *
 * @author bihell
 * @since 2017/7/12 21:34
 */
public interface AuthItemMapper extends BaseMapper<AuthItem> {

    /**
     * @param param
     * @return
     */
    List<AuthItem> queryByProjectType(Param param);

}
