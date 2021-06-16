package com.bihell.dice.nav.convert;

import com.bihell.dice.nav.entity.NavType;
import com.bihell.dice.nav.vo.NavTypeVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 *
 * @author haseochen
 */
@Mapper
public interface NavTypeConvert {

    NavTypeConvert INSTANCE = Mappers.getMapper(NavTypeConvert.class);

    /**
     * NavType转换成NavTypeVo对象
     *
     * @param navType
     * @return
     */
    NavTypeVo entityToTreeVo(NavType navType);

    /**
     * NavType列表转换成NavTypeVo列表
     *
     * @param list
     * @return
     */
    List<NavTypeVo> listToTreeVoList(List<NavType> list);

}
