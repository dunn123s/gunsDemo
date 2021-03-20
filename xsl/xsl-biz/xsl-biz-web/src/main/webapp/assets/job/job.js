layui.use(['table', 'admin', 'ax', 'func',"dict",'xform','form'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;
    var dict = layui.dict;
    var xform = layui.xform;
    var form = layui.form;

    /**
     * 定时任务调度表管理
     */
    var Job = {
        tableId: "jobTable"
    };

    /**
     * 初始化表格的列
     */
    Job.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'jobId', hide: true, title: '任务ID'},
            {field: 'jobName', sort: true, title: '任务名称'},
            {field: 'jobGroup', sort: true, title: '任务组名'},
            {field: 'invokeTarget', sort: true, title: '调用目标字符串'},
            {field: 'cronExpression', sort: true, title: 'cron执行表达式'},
            {field: 'misfirePolicy', sort: true, title: '计划执行错误策略',templet: dict.format('misfirePolicy', 'JOB_MISFIRE_POLICY')},
            {field: 'concurrent', sort: true, title: '是否并发执行',templet: dict.format('concurrent', 'JOB_CONCURRENT')},
            {field: 'status', sort: true, title: '状态',templet: '#statusTpl'},
            {field: 'createBy', sort: true, title: '创建者'},
            {field: 'createTime', sort: true, title: '创建时间'},
            {field: 'updateBy', sort: true, title: '更新者'},
            {field: 'updateTime', sort: true, title: '更新时间'},
            {field: 'remark', sort: true, title: '备注信息'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    Job.search = function () {
        var queryData = {};
        queryData['jobName'] = $("#jobName").val();
        queryData['jobGroup'] = $("#jobGroup").val();
        queryData['status'] = $("#status").val();
        table.reload(Job.tableId, {
            where: queryData, page: {curr: 1}
        });
    };
    /**
     * 点击重置按钮
     */

    Job.reset = function (){
        $("#jobName").val("");
        $("#jobGroup").val("");
        $("#status").val("");
        form.render();
        Job.search();
    }

    /**
     * 点击重执行一次
     */
    Job.oneTime = function (){
        //获取每一行数据
        var checkRows = table.checkStatus(Job.tableId);
        if (checkRows.data.length === 0) {
            layer.msg('请选择一条数据的数据');
            return;
        }
        if (checkRows.data.length > 1) {
            layer.msg('您选择了多条数据，只能选择一条查看详细');
            return;
        }
        var jobId = checkRows.data[0].jobId;
            var operation = function () {
                var ajax = new $ax(Feng.ctxPath + "/job/run", function (data) {
                    Feng.success("执行成功!");
                    table.reload(Job.tableId);
                }, function (data) {
                    Feng.error("执行失败!" + data.responseJSON.message + "!");
                });
                ajax.set("jobId", jobId);
                ajax.start();
            };
        Feng.confirm("您确认要执行一次任务吗?", operation);
    };
    /**
     * 弹出添加对话框
     */
    Job.openAddDlg = function () {
        func.open({
            title: '添加定时任务调度表',
            content: Feng.ctxPath + '/job/add',
            tableId: Job.tableId
        });
    };

    /**
    * 点击编辑
    *
    * @param data 点击按钮时候的行数据
    */
    Job.openEditDlg = function (data) {
        func.open({
            title: '修改定时任务调度表',
            content: Feng.ctxPath + '/job/edit?jobId=' + data.jobId,
            tableId: Job.tableId
        });
    };

    /**
     * 导出excel按钮
     */
    Job.exportExcel = function () {
        var checkRows = table.checkStatus(Job.tableId);
        if (checkRows.data.length === 0) {
            Feng.error("请选择要导出的数据");
        } else {
            table.exportFile(tableResult.config.id, checkRows.data, 'xls');
        }
    };

    /**
     * 点击删除
     *
     * @param data 点击按钮时候的行数据
     */
    Job.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/job/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(Job.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("jobId", data.jobId);
            ajax.start();
        };
        Feng.confirm("是否删除?", operation);
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + Job.tableId,
        url: Feng.ctxPath + '/job/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: Job.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        Job.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        Job.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        Job.exportExcel();o
    });

    // 重置按钮点击事件
    $('#btnReset').click(function (){
       Job.reset();
    });

    // 执行一次按钮点击事件
    $('#btnOneTime').click(function (){
        Job.oneTime();
    });

    // 修改user状态
    form.on('switch(status)', function (obj) {

        var jobId = obj.elem.value;
        var checked = obj.elem.checked ? true : false;

        Job.changeUserStatus(jobId, checked);
    });

    /**
     * 修改job表态
     *
     * @param jobId jobId
     * @param checked 是否选中（true,false），选中就是解锁用户，未选中就是锁定用户
     */
    Job.changeUserStatus = function (jobId, checked) {
        if (checked) {
            var ajax = new $ax(Feng.ctxPath + "/job/unfreeze", function (data) {
                Feng.success("设置为正常状态!");
            }, function (data) {
                Feng.error("设置为正常状态失败!");

            });
            ajax.set("jobId", jobId);
            ajax.start();
        } else {
            var ajax = new $ax(Feng.ctxPath + "/job/freeze", function (data) {
                Feng.success("设置为暂停状态!");
            }, function (data) {
                Feng.error("设置为暂停状态失败!" + data.responseJSON.message + "!");

            });
            ajax.set("jobId", jobId);
            ajax.start();
        }
        table.reload(Job.tableId);
    };

    // 工具条点击事件
    table.on('tool(' + Job.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            Job.openEditDlg(data);
        } else if (layEvent === 'delete') {
            Job.onDeleteItem(data);
        }
    });
});
