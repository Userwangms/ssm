package com.wxf.dao;

import com.wxf.pojo.SysRoles;
import com.wxf.pojo.SysUser;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysUserMapper {

    int deleteByPrimaryKey(Integer userid);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Integer userid);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    SysUser getUserByUserName(String userName);

    SysRoles getRoles(SysUser sysUser);
}