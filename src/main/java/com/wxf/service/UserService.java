package com.wxf.service;

import com.wxf.pojo.SysRoles;
import com.wxf.pojo.SysUser;

public interface UserService {
    /** 
     * 查找所有用户 
     * @return 
     * @throws Exception 
     */  
    SysUser findUser()throws Exception;

    SysUser getUserByUserName(String userName);

    SysRoles getRoles(SysUser sysUser);
} 