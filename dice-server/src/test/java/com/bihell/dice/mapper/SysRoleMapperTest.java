package com.bihell.dice.mapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bihell.dice.system.entity.SysRole;
import com.bihell.dice.system.mapper.SysRoleMapper;
// 使用junit5的org.junit.jupiter.api.Test可以比junit4的org.junit.test少引入注释@RunWith(SpringRunner.class)
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class SysRoleMapperTest {
    @Autowired
    private SysRoleMapper sysRoleMapper;

    //7 条件删除
    @Test
    public void testDelete() {
        QueryWrapper<SysRole> wrapper = new QueryWrapper<>();
        wrapper.eq("role_name","用户管理员");
        sysRoleMapper.delete(wrapper);
    }

    //6 条件查询
    @Test
    public void testSelect() {
        //创建条件构造器对象
        QueryWrapper<SysRole> wrapper = new QueryWrapper<>();
        //设置条件
        //wrapper.eq("role_name","用户管理员");
        wrapper.like("role_name","管理员");
        //调用方法查询
        List<SysRole> list = sysRoleMapper.selectList(wrapper);
        System.out.println(list);
    }

    //5 批量删除
    @Test
    public void testBatchDelete() {
        sysRoleMapper.deleteBatchIds(Arrays.asList(1, 2));
    }

    //4 id删除
    @Test
    public void deleteId() {
        int rows = sysRoleMapper.deleteById(5);
    }

    //3 修改操作
    @Test
    public void update() {
        //根据id查询
        SysRole sysRole = sysRoleMapper.selectById(1);

        //设置修改值
        sysRole.setDescription("系统管理员尚硅谷");

        //调用方法实现修改
        sysRoleMapper.updateById(sysRole);
    }

    //2 添加操作
    @Test
    public void add() {
        SysRole sysRole = new SysRole();
        sysRole.setRoleName("测试角色2");
        sysRole.setRoleCode("testManager2");
        sysRole.setDescription("测试角色2");
        int rows = sysRoleMapper.insert(sysRole);
        System.out.println(rows);
    }

    //1 查询表所有记录
    @Test
    public void findAll() {
        List<SysRole> list = sysRoleMapper.selectList(null);
        for (SysRole sysRole:list) {
            System.out.println(sysRole);
        }
    }
}
