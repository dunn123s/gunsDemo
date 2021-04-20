package cn.stylefeng.guns.sys.modular.system.service;

import cn.stylefeng.guns.base.pojo.node.TreeviewNode;
import cn.stylefeng.guns.base.pojo.node.ZTreeNode;
import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.sys.core.exception.enums.BizExceptionEnum;
import cn.stylefeng.guns.sys.modular.system.entity.Dept;
import cn.stylefeng.guns.sys.modular.system.entity.User;
import cn.stylefeng.guns.sys.modular.system.mapper.DeptMapper;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 部门表 服务实现类
 * </p>
 *
 * @author stylefeng
 * @since 2018-12-07
 */
@Service
public class DeptService extends ServiceImpl<DeptMapper, Dept> {

    @Resource
    private DeptMapper deptMapper;
    @Resource private UserService userService;

    /**
     * 新增部门
     *
     * @author fengshuonan
     * @Date 2018/12/23 5:00 PM
     */
    @Transactional(rollbackFor = Exception.class)
    public void addDept(Dept dept) {

        if (ToolUtil.isOneEmpty(dept, dept.getSimpleName(), dept.getFullName(), dept.getPid(), dept.getDescription())) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }

        //完善pids,根据pid拿到pid的pids
        this.deptSetPids(dept);

        this.save(dept);
    }

    /**
     * 修改部门
     *
     * @author fengshuonan
     * @Date 2018/12/23 5:00 PM
     */
    @Transactional(rollbackFor = Exception.class)
    public void editDept(Dept dept) {

        if (ToolUtil.isOneEmpty(dept, dept.getDeptId(), dept.getSimpleName(), dept.getFullName(), dept.getPid())) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }

        //完善pids,根据pid拿到pid的pids
        this.deptSetPids(dept);

        this.updateById(dept);
    }

    /**
     * 删除部门
     *
     * @author fengshuonan
     * @Date 2018/12/23 5:16 PM
     */
    @Transactional
    public void deleteDept(Long deptId) {
        Dept dept = deptMapper.selectById(deptId);

        //根据like查询删除所有级联的部门
        List<Dept> subDepts = deptMapper.likePids(dept.getDeptId());

        for (Dept temp : subDepts) {
            this.removeById(temp.getDeptId());
        }

        this.removeById(dept.getDeptId());
    }

    /**
     * 获取ztree的节点列表
     *
     * @author fengshuonan
     * @Date 2018/12/23 5:16 PM
     */
    public List<ZTreeNode> tree() {
        return this.baseMapper.tree();
    }

    /**
     * 获取ztree的节点列表
     *
     * @author fengshuonan
     * @Date 2018/12/23 5:16 PM
     */
    public List<TreeviewNode> treeviewNodes() {
        return this.baseMapper.treeviewNodes();
    }

    /**
     * 获取所有部门列表
     *
     * @author fengshuonan
     * @Date 2018/12/23 5:16 PM
     */
    public Page<Map<String, Object>> list(String condition, Long deptId) {
        Page page = LayuiPageFactory.defaultPage();
        return this.baseMapper.list(page, condition, deptId);
    }

    /**
     * 设置部门的父级ids
     *
     * @author fengshuonan
     * @Date 2018/12/23 4:58 PM
     */
    private void deptSetPids(Dept dept) {
        if (ToolUtil.isEmpty(dept.getPid()) || dept.getPid().equals(0L)) {
            dept.setPid(0L);
            dept.setPids("[0],");
        } else {
            Long pid = dept.getPid();
            Dept temp = this.getById(pid);
            String pids = temp.getPids();
            dept.setPid(pid);
            dept.setPids(pids + "[" + pid + "],");
        }
    }

    /**
     * 获取所有部门列表
     */
    public List<Map<String, Object>> allList(Long deptId) {
        return this.baseMapper.allList(deptId);
    }

    //根据部门id查找部门用户
    public List<User> getUsersByDeptId(Long deptId) {
        //根据id查找部门
        Dept dept = lambdaQuery().eq(Dept::getDeptId, deptId).one();

        String pids = dept.getPids();
        String[] pidArr = pids.replace("[0],[24],[26]"," ")
                .replace("[0],[24]"," ")
                .replace("[", "")
                .replace("]", "").trim().split(",");
        if(pidArr.length==0){
            //根据部门id查询pids
            List<Dept> list = lambdaQuery().like(Dept::getPids, "[" + dept.getDeptId() + "]").list();
            if (list.size()==0){
                Dept one = lambdaQuery().eq(Dept::getDeptId, dept.getDeptId()).one();
                list.add(one);
            }
            Object[] objects = list.stream().map(Dept::getDeptId).collect(Collectors.toList()).toArray();
            pidArr = longToString(objects);
        }
        List<Dept> list = lambdaQuery().in(Dept::getDeptId, pidArr).list();
        list.add(dept);

        Set<Long> deptIds = list.stream().map(Dept::getDeptId).collect(Collectors.toSet());

        //4， 如果我是组长，查找我所在部门的所有员工。
        return userService.lambdaQuery().in(User::getDeptId, deptIds).list();
    }

    public static String[] longToString(Object[] array){
        if(array == null || array.length == 0) return null;
        String[] strArr = new String[array.length];
        for(int i=0;i<strArr.length;i++){
            strArr[i] = String.valueOf(array[i]);
        }
        return strArr;
    }
}
