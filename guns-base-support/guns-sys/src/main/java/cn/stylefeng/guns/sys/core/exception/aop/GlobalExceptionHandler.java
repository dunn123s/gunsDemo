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
package cn.stylefeng.guns.sys.core.exception.aop;

import cn.stylefeng.guns.sys.core.exception.InvalidGoogleAuthException;
import cn.stylefeng.guns.sys.core.exception.InvalidKaptchaException;
import cn.stylefeng.guns.sys.core.exception.InvalidLoginErrorLimitException;
import cn.stylefeng.guns.sys.core.exception.PageException;
import cn.stylefeng.guns.sys.core.exception.enums.BizExceptionEnum;
import cn.stylefeng.guns.sys.core.log.LogManager;
import cn.stylefeng.guns.sys.core.log.factory.LogTaskFactory;
import cn.stylefeng.guns.sys.core.shiro.ShiroKit;
import cn.stylefeng.roses.core.reqres.response.ErrorResponseData;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.CredentialsException;
import org.apache.shiro.authc.DisabledAccountException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.lang.reflect.UndeclaredThrowableException;

import static cn.stylefeng.roses.core.util.HttpContext.getIp;
import static cn.stylefeng.roses.core.util.HttpContext.getRequest;

/**
 * ??????????????????????????????????????????????????????????????????@RequestMapping?????????????????????????????????
 *
 * @author fengshuonan
 * @date 2016???11???12??? ??????3:19:56
 */
@ControllerAdvice
@Order(-1)
public class GlobalExceptionHandler {

    private Logger log = LoggerFactory.getLogger(this.getClass());


    /**
     * ????????????
     */
    @ExceptionHandler(PageException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String accountLocked(PageException e, Model model) {
        model.addAttribute("error", e.getMessage());
        return "/500.html";
    }

    /**
     * ??????????????????
     */
    @ExceptionHandler(ServiceException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResponseData bussiness(ServiceException e) {
        if (ShiroKit.isUser()) {
            LogManager.me().executeLog(LogTaskFactory.exceptionLog(ShiroKit.getUserNotNull().getId(), e));
        }
        getRequest().setAttribute("tip", e.getMessage());
        log.error("????????????:", e);
        return new ErrorResponseData(e.getCode(), e.getMessage());
    }

    /**
     * ?????????????????????
     */
    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String unAuth(AuthenticationException e) {
        log.error("??????????????????", e);
        return "/login.html";
    }

    /**
     * ?????????????????????
     */
    @ExceptionHandler(DisabledAccountException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String accountLocked(DisabledAccountException e, Model model) {
        String username = getRequest().getParameter("username");
        LogManager.me().executeLog(LogTaskFactory.loginLog(username, "???????????????", getIp()));
        model.addAttribute("tips", "???????????????");
        return "/login.html";
    }

    /**
     * ????????????????????????
     */
    @ExceptionHandler(CredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String credentials(CredentialsException e, Model model) {
        String username = getRequest().getParameter("username");
        LogManager.me().executeLog(LogTaskFactory.loginLog(username, "??????????????????", getIp()));
        model.addAttribute("tips", "??????????????????");
        return "/login.html";
    }

    /**
     * ?????????????????????
     */
    @ExceptionHandler(InvalidKaptchaException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String credentials(InvalidKaptchaException e, Model model) {
        String username = getRequest().getParameter("username");
        LogManager.me().executeLog(LogTaskFactory.loginLog(username, "???????????????", getIp()));
        model.addAttribute("tips", "???????????????");
        return "/login.html";
    }

    /**
     * ????????????????????????
     */
    @ExceptionHandler(InvalidGoogleAuthException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String credentials(InvalidGoogleAuthException e, Model model) {
        String username = getRequest().getParameter("username");
        LogManager.me().executeLog(LogTaskFactory.loginLog(username, "????????????????????????", getIp()));
        model.addAttribute("tips", "????????????????????????");
        return "/login.html";
    }

    /**
     * ????????????????????????
     */
    @ExceptionHandler(InvalidLoginErrorLimitException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String credentials(InvalidLoginErrorLimitException e, Model model) {
        String username = getRequest().getParameter("username");
        LogManager.me().executeLog(LogTaskFactory.loginLog(username, "??????????????????????????????????????????", getIp()));
        model.addAttribute("tips", "??????????????????????????????????????????");
        return "/login.html";
    }

    /**
     * ???????????????????????????
     */
    @ExceptionHandler(UndeclaredThrowableException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ErrorResponseData credentials(UndeclaredThrowableException e) {
        getRequest().setAttribute("tip", "????????????");
        log.error("????????????!", e);
        return new ErrorResponseData(BizExceptionEnum.NO_PERMITION.getCode(), BizExceptionEnum.NO_PERMITION.getMessage());
    }

    /**
     * ??????????????????????????????
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResponseData notFount(RuntimeException e) {
        if (ShiroKit.isUser()) {
            LogManager.me().executeLog(LogTaskFactory.exceptionLog(ShiroKit.getUserNotNull().getId(), e));
        }
        getRequest().setAttribute("tip", "??????????????????????????????");
        log.error("???????????????:", e);
        return new ErrorResponseData(BizExceptionEnum.SERVER_ERROR.getCode(), BizExceptionEnum.SERVER_ERROR.getMessage());
    }
}
