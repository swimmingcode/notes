$(function () {

    $("#rol_dg").datagrid({
        url:'/roleList',
        striped:true,
        rownumbers:true,
        /*只能选一行*/
        singleSelect:true,
        fitColumns:true,
        autoRowHeight:false,
        pagination:true,
        toolbar: '#tb',
        /*列填充*/
        fit:true,
        columns:[[
            {field:'rnum',title:'角色类型',width:100,align:'center'},
            {field:'rname',title:'角色名称',width:100,align:'center'},
            ]],
        toolbar: '#toolbar',
    });

    /*对话框*/
    $("#dialog").dialog({
        title: '添加角色',
        width: 550,
        height: 500,
        closed: true,
        cache: false,

        buttons:[{
            text:'保存',
            handler:function(){
                /*提交表单*/
                /*判读是添加还是修改*/
                var url;
                var id = $("[name='rid']").val();
                if (id){
                   /* alert(id);*/
                    url = "updateRole";
                }else{
                    url = "saveRole";
                }
                $('#myform').form('submit', {
                    url:url,
                    /*添加权限信息参数*/
                    onSubmit:function(param){
                        /*获取所有添加的权限*/
                        var rows = $("#role_data2").datagrid("getRows");
                        for (var i=0 ;i<rows.length;i++){
                            /*取出每一行数据*/
                            var row = rows[i];
                            param["permissions["+i+"].pid"] = row.pid;
                        }
                    },
                    success:function(data){
                        /*解析成json*/
                        data = $.parseJSON(data);
                        console.log(data);
                        if (data.success){
                            $.messager.alert("温馨提示",data.message);
                            /* 更新表格数据*/
                            $("#rol_dg").datagrid("reload");
                            /*清除表单数据*/
                            $("#myform").form("clear");
                            /*关闭对话框*/
                            $("#dialog").dialog("close");
                        }else {
                            $.messager.alert("温馨提示",data.message);
                        }
                    }
                });
            }
        },{
            text:'关闭',
            handler:function(){
                $("#dialog").dialog("close");
            }
        }]
    });

    /*所有权限*/
    $("#role_data1").datagrid({
        url:'/permissionList',
        width: 250,
        height: 285,
        /*填充*/
        fitColumns:true,
        singleSelect:true,
        columns:[[
            {field:'pname',title:'权限名称',width:100,align:'center'},
        ]],
        onClickRow:function (rowIndex,rowData) {
            console.log(rowData);
            /*判断该权限是否在已有权限中*/
            var rows = $("#role_data2").datagrid("getRows");
            for (var i=0;i<rows.length;i++){
                var row = rows[i];
                if (row.pid == rowData.pid){
                    return;
                }
            }
            /*添加到已有权限*/
            $("#role_data2").datagrid("appendRow",rowData);
        }
    })

    /*已拥有权限*/
    $("#role_data2").datagrid({
        width: 250,
        height: 285,
        /*填充*/
        fitColumns:true,
        singleSelect:true,
        columns:[[
            {field:'pname',title:'权限名称',width:100,align:'center'},
        ]],
        onClickRow:function(rowIndex,rowData){
            /*删除选中的行*/
            $("#role_data2").datagrid("deleteRow",rowIndex);
        }
    });

    /*添加角色*/
    $("#add").click(function () {

        /*清空表单*/
        $("#myform").form("clear");

        /*清空表格数据*/
        $('#role_data2').datagrid('loadData',{total:0,rows:[]});

        $("#dialog").dialog("setTitle","添加角色");

        /*打开对话框*/
        $("#dialog").dialog("open");
    });

    /*编辑角色*/
    $("#edit").click(function () {
        /*判断是否选中了数据*/
        let rowData = $("#rol_dg").datagrid("getSelected");
        if (!rowData) {
            $.messager.alert("温馨提示","请选择一条数据");
            return;
        }
        $("#dialog").dialog("setTitle","编辑员工");
        console.log(rowData);

        /*清空表单*/
        $("#myform").form("clear");

        /*回显表单*/
        $("#myform").form("load",rowData);

        $("#dialog").dialog("setTitle","编辑角色");

        /*显示该角色的所有权限*/
        var options = $("#role_data2").datagrid("options");
        options.url="/getRolePermissionByRid?rid="+rowData.rid;

        /*重新加载数据*/
        $("#role_data2").datagrid("load");

        $("#dialog").dialog("open");

    });

    /*删除角色*/
    $("#remove").click(function () {
        /*判断是否选中了数据*/
        let rowData = $("#rol_dg").datagrid("getSelected");
        if (!rowData) {
            $.messager.alert("温馨提示","请选择一条数据");
            return;
        }
        /*弹出提示框是否要真正删除*/
        $.messager.confirm("确定","是否删除角色",function (res) {
            /*删除角色*/
            if (res){
                $.get("/deleteRoleByRid?rid="+rowData.rid,function (data) {
                    if (!data.success) {
                        $.messager.alert("温馨提示", data.message);
                    }else{
                        $.messager.alert("温馨提示", data.message);
                        /*更新表格数据*/
                        $("#rol_dg").datagrid("reload");
                    }

                });
            }
        })
    });

    /*刷新*/
    $("#reload").click(function () {
        /*重新加载数据*/
        $("#rol_dg").datagrid("load",{});
    });
});