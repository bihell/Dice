package com.bihell.dice.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bihell.dice.framework.common.service.impl.BaseServiceImpl;
import com.bihell.dice.framework.core.pagination.PageInfo;
import com.bihell.dice.framework.core.pagination.Paging;
import com.bihell.dice.system.convert.SysDepartmentConvert;
import com.bihell.dice.system.entity.SysDept;
import com.bihell.dice.system.enums.StateEnum;
import com.bihell.dice.system.mapper.SysDeptMapper;
import com.bihell.dice.system.param.SysDepartmentPageParam;
import com.bihell.dice.system.service.SysDeptService;
import com.bihell.dice.system.vo.SysDepartmentVo;
import com.bihell.dice.system.vo.SysDepartmentTreeVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * <pre>
 * 部门 服务实现类
 * </pre>
 *
 * @author geekidea
 * @since 2019-10-24
 */
@Slf4j
@Service
public class SysDeptServiceImpl extends BaseServiceImpl<SysDeptMapper, SysDept> implements SysDeptService {

    @Autowired
    private SysDeptMapper sysDeptMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveSysDepartment(SysDept sysDept) throws Exception {
        sysDept.setId(null);
        return super.save(sysDept);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateSysDepartment(SysDept sysDept) throws Exception {
        return super.updateById(sysDept);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteSysDepartment(Long id) throws Exception {
        return super.removeById(id);
    }

    @Override
    public SysDepartmentVo getSysDepartmentById(Serializable id) throws Exception {
        return sysDeptMapper.getSysDepartmentById(id);
    }

    @Override
    public Paging<SysDepartmentVo> getSysDepartmentPageList(SysDepartmentPageParam sysDepartmentPageParam) throws Exception {
        Page<SysDepartmentVo> page = new PageInfo<>(sysDepartmentPageParam, OrderItem.desc("create_time"));
        IPage<SysDepartmentVo> iPage = sysDeptMapper.getSysDepartmentPageList(page, sysDepartmentPageParam);
        return new Paging(iPage);
    }

    @Override
    public boolean isEnableSysDepartment(Long id) throws Exception {
        SysDept sysDept = new SysDept()
                .setId(id)
                .setStatus(StateEnum.ENABLE.getCode());
        Long count = sysDeptMapper.selectCount(new QueryWrapper<>(sysDept));
        return count > 0;
    }

    @Override
    public List<SysDept> getAllDepartmentList() {
        SysDept sysDept = new SysDept().setStatus(StateEnum.ENABLE.getCode());
        // 获取所有已启用的部门列表
        return sysDeptMapper.selectList(new QueryWrapper(sysDept));
    }

    @Override
    public List<SysDepartmentTreeVo> getDepartmentTree() {
        List<SysDept> sysDeptList = getAllDepartmentList();
        if (CollectionUtils.isEmpty(sysDeptList)) {
            return null;
        }
        List<SysDepartmentTreeVo> list = SysDepartmentConvert.INSTANCE.listToTreeVoList(sysDeptList);
        List<SysDepartmentTreeVo> treeVos = new ArrayList<>();
        for (SysDepartmentTreeVo treeVo : list) {
            if (treeVo.getParentId() == null) {
                treeVos.add(findChildren(treeVo, list));
            }
        }
        return treeVos;
    }

    /**
     * 递归获取树形结果列表
     *
     * @param tree
     * @param list
     * @return
     */
    public SysDepartmentTreeVo findChildren(SysDepartmentTreeVo tree, List<SysDepartmentTreeVo> list) {
        for (SysDepartmentTreeVo vo : list) {
            if (tree.getId().equals(vo.getParentId())) {
                if (tree.getChildren() == null) {
                    tree.setChildren(new ArrayList<>());
                }
                tree.getChildren().add(findChildren(vo, list));
            }
        }
        return tree;
    }


}
