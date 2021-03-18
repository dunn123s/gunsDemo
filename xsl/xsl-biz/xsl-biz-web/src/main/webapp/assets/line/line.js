layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * 客户线路表管理
     */
    var Line = {
        tableId: "lineTable"
    };

    /**
     * 初始化表格的列
     */
    Line.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: 'ID'},
            {field: 'line', sort: true, title: '线路'},
            {field: 'sysPlatform', sort: true, title: '系统平台'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    Line.search = function () {
        var queryData = {};
        queryData['condition'] = $("#condition").val();
        table.reload(Line.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    Line.openAddDlg = function () {
        func.open({
            title: '添加客户线路表',
            content: Feng.ctxPath + '/line/add',
            tableId: Line.tableId
        });
    };

    /**
    * 点击编辑
    *
    * @param data 点击按钮时候的行数据
    */
    Line.openEditDlg = function (data) {
        func.open({
            title: '修改客户线路表',
            content: Feng.ctxPath + '/line/edit?id=' + data.id,
            tableId: Line.tableId
        });
    };

    /**
     * 导出excel按钮
     */
    Line.exportExcel = function () {
        var checkRows = table.checkStatus(Line.tableId);
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
    Line.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/line/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(Line.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("id", data.id);
            ajax.start();
        };
        Feng.confirm("是否删除?", operation);
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + Line.tableId,
        url: Feng.ctxPath + '/line/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: Line.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        Line.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        Line.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        Line.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + Line.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            Line.openEditDlg(data);
        } else if (layEvent === 'delete') {
            Line.onDeleteItem(data);
        }
    });
});
