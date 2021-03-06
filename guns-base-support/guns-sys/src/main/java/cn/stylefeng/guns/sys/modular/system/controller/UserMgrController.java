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
import cn.stylefeng.guns.base.log.BussinessLog;
import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.shiro.annotion.Permission;
import cn.stylefeng.guns.sys.core.constant.Const;
import cn.stylefeng.guns.sys.core.constant.dictmap.UserDict;
import cn.stylefeng.guns.sys.core.constant.state.ManagerStatus;
import cn.stylefeng.guns.sys.core.exception.enums.BizExceptionEnum;
import cn.stylefeng.guns.sys.core.log.LogObjectHolder;
import cn.stylefeng.guns.sys.core.properties.GunsProperties;
import cn.stylefeng.guns.sys.core.shiro.ShiroKit;
import cn.stylefeng.guns.sys.core.util.GoogleAuthenticator;
import cn.stylefeng.guns.sys.modular.system.entity.User;
import cn.stylefeng.guns.sys.modular.system.model.UserDto;
import cn.stylefeng.guns.sys.modular.system.service.UserService;
import cn.stylefeng.guns.sys.modular.system.warpper.UserWrapper;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.datascope.DataScope;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import cn.stylefeng.roses.core.reqres.response.SuccessResponseData;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.RequestEmptyException;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.util.Map;
import java.util.UUID;

/**
 * ????????????????????????
 *
 * @author fengshuonan
 * @Date 2017???1???11??? ??????1:08:17
 */
@Controller
@RequestMapping("/mgr")
public class UserMgrController extends BaseController {

    private static String PREFIX = "/modular/system/user/";

    @Autowired
    private GunsProperties gunsProperties;

    @Autowired
    private UserService userService;

    /**
     * ???????????????????????????????????????
     *
     * @author fengshuonan
     * @Date 2018/12/24 22:43
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "user.html";
    }

    /**
     * ???????????????????????????????????????
     *
     * @author fengshuonan
     * @Date 2018/12/24 22:43
     */
    @RequestMapping("/user_add")
    public String addView() {
        return PREFIX + "user_add.html";
    }

    /**
     * ???????????????????????????
     *
     * @author fengshuonan
     * @Date 2018/12/24 22:43
     */
    @Permission
    @RequestMapping("/role_assign")
    public String roleAssign(@RequestParam Long userId, Model model) {
        if (ToolUtil.isEmpty(userId)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        model.addAttribute("userId", userId);
        return PREFIX + "user_roleassign.html";
    }

    /**
     * ??????????????????????????????
     *
     * @author fengshuonan
     * @Date 2018/12/24 22:43
     */
    @Permission
    @RequestMapping("/user_edit")
    public String userEdit(@RequestParam Long userId) {
        if (ToolUtil.isEmpty(userId)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        User user = this.userService.getById(userId);
        LogObjectHolder.me().set(user);
        return PREFIX + "user_edit.html";
    }

    /**
     * ??????????????????
     *
     * @author fengshuonan
     * @Date 2018/12/24 22:43
     */
    @RequestMapping("/getUserInfo")
    @ResponseBody
    public SuccessResponseData getUserInfo(@RequestParam Long userId) {
        if (ToolUtil.isEmpty(userId)) {
            throw new RequestEmptyException();
        }

        this.userService.assertAuth(userId);
        return new SuccessResponseData(userService.getUserInfo(userId));
    }


    /**
     * ???????????????????????????
     *
     * @author fengshuonan
     * @Date 2018/12/24 22:43
     */
    @RequestMapping("/changePwd")
    @ResponseBody
    public Object changePwd(@RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String newPassword) {
        if (ToolUtil.isOneEmpty(oldPassword, newPassword)) {
            throw new RequestEmptyException();
        }
        this.userService.changePwd(oldPassword, newPassword);
        return SUCCESS_TIP;
    }

    /**
     * ?????????????????????
     *
     * @author fengshuonan
     * @Date 2018/12/24 22:43
     */
    @RequestMapping("/list")
    @Permission
    @ResponseBody
    public Object list(@RequestParam(required = false) String name,
                       @RequestParam(required = false) String timeLimit,
                       @RequestParam(required = false) Long deptId) {

        //??????????????????
        String beginTime = "";
        String endTime = "";

        if (ToolUtil.isNotEmpty(timeLimit)) {
            String[] split = timeLimit.split(" - ");
            beginTime = split[0];
            endTime = split[1];
        }

        if (ShiroKit.isAdmin() ||"Gordon".equals(ShiroKit.getUserNotNull().getAccount())) {
            Page<Map<String, Object>> users = userService.selectUsers(null, name, beginTime, endTime, deptId);
            Page wrapped = new UserWrapper(users).wrap();
            return LayuiPageFactory.createPageInfo(wrapped);
        } else {
            DataScope dataScope = new DataScope(ShiroKit.getDeptDataScope());
            Page<Map<String, Object>> users = userService.selectUsers(dataScope, name, beginTime, endTime, deptId);
            Page wrapped = new UserWrapper(users).wrap();
            return LayuiPageFactory.createPageInfo(wrapped);
        }
    }

    /**
     * ???????????????
     *
     * @author fengshuonan
     * @Date 2018/12/24 22:44
     */
    @RequestMapping("/add")
    @BussinessLog(value = "???????????????", key = "account", dict = UserDict.class)
    @Permission({Const.ADMIN_NAME,Const.PERSONNEL_SPECIALIST})
    @ResponseBody
    public ResponseData add(@Valid UserDto user, BindingResult result) {
        if (result.hasErrors()) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        if(user.getAccount().replaceAll("\t", " ")
                .replaceAll("???", " ")
                .contains(" ")) {
            return ResponseData.error("??????????????????????????????");
        }
        user.setGogSecret(GoogleAuthenticator.getSecret());
        this.userService.addUser(user);
        SUCCESS_TIP.setData(user.getGogSecret());
        return SUCCESS_TIP;
    }

    /**
     * ???????????????
     *
     * @author fengshuonan
     * @Date 2018/12/24 22:44
     */
    @RequestMapping("/edit")
    @BussinessLog(value = "???????????????", key = "account", dict = UserDict.class)
    @ResponseBody
    public ResponseData edit(@Valid UserDto user, BindingResult result) {
        if (result.hasErrors()) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        this.userService.editUser(user);
        return SUCCESS_TIP;
    }

    /**
     * ?????????????????????????????????
     *
     * @author fengshuonan
     * @Date 2018/12/24 22:44
     */
    @RequestMapping("/delete")
    @BussinessLog(value = "???????????????", key = "userId", dict = UserDict.class)
    @Permission
    @ResponseBody
    public ResponseData delete(@RequestParam Long userId) {
        if (ToolUtil.isEmpty(userId)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        this.userService.deleteUser(userId);
        return SUCCESS_TIP;
    }

    /**
     * ?????????????????????
     *
     * @author fengshuonan
     * @Date 2018/12/24 22:44
     */
    @RequestMapping("/view/{userId}")
    @ResponseBody
    public User view(@PathVariable Long userId) {
        if (ToolUtil.isEmpty(userId)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        this.userService.assertAuth(userId);
        return this.userService.getById(userId);
    }

    /**
     * ????????????????????????
     *
     * @author fengshuonan
     * @Date 2018/12/24 22:44
     */
    @RequestMapping("/reset")
    @BussinessLog(value = "?????????????????????", key = "userId", dict = UserDict.class)
    @Permission({Const.ADMIN_NAME,Const.PERSONNEL_SPECIALIST})
    @ResponseBody
    public ResponseData reset(@RequestParam Long userId) {
        if (ToolUtil.isEmpty(userId)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        this.userService.assertAuth(userId);
        User user = this.userService.getById(userId);
        user.setSalt(ShiroKit.getRandomSalt(5));
        user.setPassword(ShiroKit.md5(ConstantsContext.getDefaultPassword(), user.getSalt()));
        user.setGogSecret(GoogleAuthenticator.getSecret());
        this.userService.updateById(user);
        SUCCESS_TIP.setData(user.getGogSecret());
        return SUCCESS_TIP;
    }

    /**
     * ????????????
     *
     * @author fengshuonan
     * @Date 2018/12/24 22:44
     */
    @RequestMapping("/freeze")
    @BussinessLog(value = "????????????", key = "userId", dict = UserDict.class)
    @Permission({Const.ADMIN_NAME,Const.PERSONNEL_SPECIALIST})
    @ResponseBody
    public ResponseData freeze(@RequestParam Long userId) {
        if (ToolUtil.isEmpty(userId)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        //???????????????????????????
        if (userId.equals(Const.ADMIN_ID)) {
            throw new ServiceException(BizExceptionEnum.CANT_FREEZE_ADMIN);
        }
        this.userService.assertAuth(userId);
        this.userService.setStatus(userId, ManagerStatus.FREEZED.getCode());
        return SUCCESS_TIP;
    }

    /**
     * ??????????????????
     *
     * @author fengshuonan
     * @Date 2018/12/24 22:44
     */
    @RequestMapping("/unfreeze")
    @BussinessLog(value = "??????????????????", key = "userId", dict = UserDict.class)
    @Permission({Const.ADMIN_NAME,Const.PERSONNEL_SPECIALIST})
    @ResponseBody
    public ResponseData unfreeze(@RequestParam Long userId) {
        if (ToolUtil.isEmpty(userId)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        this.userService.assertAuth(userId);
        this.userService.setStatus(userId, ManagerStatus.OK.getCode());
        return SUCCESS_TIP;
    }

    /**
     * ????????????
     *
     * @author fengshuonan
     * @Date 2018/12/24 22:44
     */
    @RequestMapping("/setRole")
    @BussinessLog(value = "????????????", key = "userId,roleIds", dict = UserDict.class)
    @Permission({Const.ADMIN_NAME,Const.PERSONNEL_SPECIALIST})
    @ResponseBody
    public ResponseData setRole(@RequestParam("userId") Long userId, @RequestParam("roleIds") String roleIds) {
        if (ToolUtil.isOneEmpty(userId, roleIds)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        //???????????????????????????
        if (userId.equals(Const.ADMIN_ID)) {
            throw new ServiceException(BizExceptionEnum.CANT_CHANGE_ADMIN);
        }
        String[] split = roleIds.split(",");
        if(!ShiroKit.isAdmin()){
            for (int i = 0; i < split.length; i++) {
                String s = split[i];
                if ((!s.equals("1") || s.length() > 1) &&!s.equals("1263681344161046530")) {
                    this.userService.assertAuth(userId);
                    this.userService.setRoles(userId, roleIds);
                    return SUCCESS_TIP;
                } else {
                    throw new ServiceException(401, "????????????????????????????????????????????????????????????????????????????????????");
                }
            }
        }else {
            this.userService.assertAuth(userId);
            this.userService.setRoles(userId, roleIds);
            return SUCCESS_TIP;
        }
        return SUCCESS_TIP;
    }

    /**
     * ????????????
     *
     * @author fengshuonan
     * @Date 2018/12/24 22:44
     */
    @RequestMapping(method = RequestMethod.POST, path = "/upload")
    @ResponseBody
    public String upload(@RequestPart("file") MultipartFile picture) {

        String pictureName = UUID.randomUUID().toString() + "." + ToolUtil.getFileSuffix(picture.getOriginalFilename());
        try {
            String fileSavePath = gunsProperties.getFileUploadPath();
            picture.transferTo(new File(fileSavePath + pictureName));
        } catch (Exception e) {
            throw new ServiceException(BizExceptionEnum.UPLOAD_ERROR);
        }
        return pictureName;
    }
}
