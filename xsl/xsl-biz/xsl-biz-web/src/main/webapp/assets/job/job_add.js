/**
 * 添加或者修改页面
 */
var JobInfoDlg = {
    data: {
        jobName: "",
        jobGroup: "",
        invokeTarget: "",
        cronExpression: "",
        misfirePolicy: "",
        concurrent: "",
        status: "",
        createBy: "",
        createTime: "",
        updateBy: "",
        updateTime: "",
        remark: ""
    }
};

layui.use(['form', 'admin', 'ax','dict','xform'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;
    var dict = layui.dict;
    var xform = layui.xform;

    //让当前iframe弹层高度适应
    admin.iframeAuto();

    //表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        var ajax = new $ax(Feng.ctxPath + "/job/addItem", function (data) {
            Feng.success("添加成功！");

            //传给上个页面，刷新table用
            admin.putTempData('formOk', true);

            //关掉对话框
            admin.closeThisDialog();

        }, function (data) {
            Feng.error("添加失败！" + data.responseJSON.message)
        });
        ajax.set(data.field);
        ajax.start();

        return false;
    });

});
