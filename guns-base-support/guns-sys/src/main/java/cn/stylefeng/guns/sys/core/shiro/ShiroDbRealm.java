/**
 * Copyright 2018-2020 stylefeng & fengshuonan (https://gitee.com/stylefeng)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.stylefeng.guns.sys.core.shiro;

import cn.stylefeng.guns.base.shiro.ShiroUser;
import cn.stylefeng.guns.sys.core.shiro.service.UserAuthService;
import cn.stylefeng.guns.sys.core.shiro.service.impl.UserAuthServiceServiceImpl;
import cn.stylefeng.guns.sys.modular.system.entity.User;
import cn.stylefeng.roses.core.util.ToolUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
public class ShiroDbRealm extends AuthorizingRealm {

    /**
     * 登录认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
            throws AuthenticationException {
        UserAuthService shiroFactory = UserAuthServiceServiceImpl.me();
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        User user = shiroFactory.user(token.getUsername());
        ShiroUser shiroUser = shiroFactory.shiroUser(user);
        return shiroFactory.info(shiroUser, user, super.getName());
    }

    /**
     * 权限认证
     */ 
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        try {
            UserAuthService shiroFactory = UserAuthServiceServiceImpl.me();
            ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();
            List<Long> roleList = shiroUser.getRoleList();

            Set<String> permissionSet = new HashSet<>();
            Set<String> roleNameSet = new HashSet<>();

            for (Long roleId : roleList) {
                List<String> permissions = shiroFactory.findPermissionsByRoleId(roleId);
                if (permissions != null) {
                    for (String permission : permissions) {
                        if (ToolUtil.isNotEmpty(permission)) {
                            permissionSet.add(permission);
                        }
                    }
                }
                String roleName = shiroFactory.findRoleNameByRoleId(roleId);
                roleNameSet.add(roleName);
            }

            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            info.addStringPermissions(permissionSet);
            info.addRoles(roleNameSet);
            return info;
        } catch (Exception e) {
            log.error("获取info异常", e);
            throw new AuthenticationException(e);
        }
    }

}
