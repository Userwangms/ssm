package com.wxf.shiro;


import com.wxf.pojo.SysRoles;
import com.wxf.pojo.SysUser;
import com.wxf.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class CustomRealm extends AuthorizingRealm {
    // 设置realm的名称  
    @Override  
    public void setName(String name) {  
        super.setName("customRealm");  
    }

    @Autowired
    private UserService adminUserService;

    /** 
     * 认证 
     */  
    @Override  
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        // token中包含用户输入的用户名和密码  
        // 第一步从token中取出用户名  
        String userName = (String) token.getPrincipal();  
        // 第二步：根据用户输入的userCode从数据库查询  
        SysUser sysUser = adminUserService.getUserByUserName(userName);
        // 如果查询不到返回null  
        if (sysUser == null) {//
            return null;  
        }  
        // 获取数据库中的密码  
        String password = sysUser.getPassword();
        /** 
         * 认证的用户,正确的密码 
         */  
        AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(sysUser, password, this.getName());
               //MD5 加密+加盐+多次加密  
//<span style="color:#ff0000;">SimpleAuthenticationInfo authcInfo = new SimpleAuthenticationInfo(adminUser, password,ByteSource.Util.bytes(salt), this.getName());</span>  
        return authcInfo;  
    }  

    /** 
     * 授权,只有成功通过<span style="font-family: Arial, Helvetica, sans-serif;">doGetAuthenticationInfo方法的认证后才会执行。</span> 
     */  
    @Override  
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // 从 principals获取主身份信息  
        // 将getPrimaryPrincipal方法返回值转为真实身份类型（在上边的doGetAuthenticationInfo认证通过填充到SimpleAuthenticationInfo中身份类型），  
        SysUser sysUser = (SysUser) principals.getPrimaryPrincipal();
        // 根据身份信息获取权限信息  
        // 从数据库获取到权限数据  
        SysRoles sysRoles = adminUserService.getRoles(sysUser);
        // 单独定一个集合对象  
        List<String> permissions = new ArrayList<String>();
        if (sysUser != null) {
            permissions.add(sysRoles.getName());
        }  
        // 查到权限数据，返回授权信息(要包括 上边的permissions)  
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        // 将上边查询到授权信息填充到simpleAuthorizationInfo对象中  
        simpleAuthorizationInfo.addStringPermissions(permissions);  
        return simpleAuthorizationInfo;  
    }  

    // 清除缓存  
    public void clearCached() {  
        PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
        super.clearCache(principals);  
    }  
}  