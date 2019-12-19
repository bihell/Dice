package com.bihell.dice.service.impl;

import com.bihell.dice.mapper.AuthClassesMapper;
import com.bihell.dice.mapper.AuthGroupMapper;
import com.bihell.dice.model.domain.AuthClasses;
import com.bihell.dice.model.domain.AuthGroup;
import com.bihell.dice.model.params.Param;
import com.bihell.dice.service.AuthGroupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author haseochen
 */
@Slf4j
@Service
@Transactional(rollbackFor = Throwable.class)
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class AuthGroupServiceImpl implements AuthGroupService {

    private final AuthGroupMapper authGroupMapper;
    private final AuthClassesMapper authClassesMapper;

    /**
     * @param param 查询参数
     * @return List<AuthGroup>
     */
    @Override
    public List<AuthGroup> getGroupList(Param param) {
        List<AuthGroup> groupList = authGroupMapper.queryByProjectType(param);

        if (CollectionUtils.isEmpty(groupList)) {
            return groupList;
        }


        for (AuthGroup group : groupList) {
            if (group == null || group.getGroupId() == null) {
                continue;
            }

            List<AuthClasses> classesList = authClassesMapper.queryByGroupId(group.getGroupId());
            group.setChildren(classesList);
        }

        return groupList;

    }
}
