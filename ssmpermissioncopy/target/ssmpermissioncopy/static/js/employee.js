$(function () {

    $('#employee_dg').datagrid({
        url:'employeeList',
        striped:true,
        /*只能选一行*/
        singleSelect:true,
        fitColumns:true,
        autoRowHeight:false,
        pagination:true,
        rownumbers:true,
        toolbar: '#tb',
        /*列填充*/
        fit:true,
        columns:[[
            {field:'empUsername',title:'姓名',width:100,align:'center'},
            {field:'empInputtime',title:'入职时间',width:100,align:'center'},
            {field:'empTel',title:'电话号码',width:100,align:'center'},
            {field:'empEmail',title:'邮箱',width:100,align:'center'},

            {field:'empDepId',title:'部门',width:100,align:'center',formatter: function (value,row,index) {
                    if (row.department) {
                        return row.department.depName;
                    }
                }
            },
            {field:'empState',title:'状态',width:100,align:'center',formatter: function(value,row,index){
                if(row.empState){
                        return "在职";
                    }else {
                        return "<font color='#ff2828'>离职</font>";
                    }
                }
            },
            {field:'empAdmin',title:'管理员',width:100,align:'center', formatter: function(value,row,index) {
                if (row.empAdmin) {
                        return '是';
                    } else {
                        return '否';
                    }
                }
            },
        ]],

        /*员工离职时，按钮不可用*/
        onClickRow:function(rowIndex,rowDte){
            if (!rowDte.empState){
                $("#delete").linkbutton("disable");
            }else {
                $("#delete").linkbutton("enable");
            }
        }
    });

    /*对话框*/
    $("#dialog").dialog({
        title: '添加员工',
        width: 320,
        height: 400,
        closed: true,
        buttons:[{
            text:'保存',
                handler:function(){
                /*提交表单*/
                /*判读是添加还是修改*/
                var url;
                var id = $("[name='empId']").val();
                /*alert(id);*/
                if (id){id
                    url = "updateEmployee";
                }else{
                    url = "saveEmployee";
                }
                $('#myform').form('submit', {
                    url: " "+url,
                    /*添加角色信息参数*/
                    onSubmit:function(param){
                        var rows = $("#role").combobox("getValues");
                        for (var i=0 ;i<rows.length;i++){
                            /*取出每一行数据*/
                            param["roles["+i+"].rid"] = rows[i];
                        }
                    },
                    success:function(data){
                        /*解析成json*/
                        data = $.parseJSON(data);
                        console.log(data);
                        if (data.success){
                            $.messager.alert("温馨提示",data.message);
                           /* 更新表格数据*/
                            $("#employee_dg").datagrid("reload");
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
    })

    /*添加员工*/
    $("#add").click(function () {

        /*清除表单数据*/
        $("#myform").form("clear");

        /*添加密码验证*/
        $("[name='empPassword']").validatebox({
            required:true
        });

        $("#dialog").dialog("setTitle","添加员工");
        /*打开密码框*/
        $("#password").show();

        /*打开对话框*/
        $("#dialog").dialog("open");

    });

    /*员工部门下拉框*/
    $("#department").combobox({
        url:'/department',
        valueField:'depId',
        textField:'depName',
        panelWidth:'150',
        panelHeight:'auto',
    });

    /*是否为管理员下拉框*/
    $("#admin").combobox({
        panelWidth:'150',
        panelHeight:'auto',
        valueField:'value',
        textField:'label',
        data: [{
            label: '是',
            value: 'true',
        },{
            label: '否',
            value: 'false',
        }]
    })

    /*角色回显*/
    $("#role").combobox({
        url:'roleListCombox',
        panelWidth:'150',
        panelHeight:'auto',
        valueField:'rid',
        textField:'rname',
        multiple:true
    })

    /*编辑员工*/
    $("#edit").click(function () {

        /*判断是否选中了数据*/
        let rowData = $("#employee_dg").datagrid("getSelected");
        if (!rowData) {
            $.messager.alert("温馨提示","请选择一条数据");
            return;
        }
        $("#dialog").dialog("setTitle","编辑员工");

        /*回显表单*/
        /*部门回显*/
        if (rowData.department) {
            rowData["department.depId"] = rowData.department.depId;
        }
        /*显示是否为管理员*/
        rowData["empAdmin"] = rowData.empAdmin+"";

        /*角色回显*/
        $.get("getRoleByEmployeeEid?empId="+rowData.empId,function (data) {
            $("#role").combobox("setValues",data);
        })
        /*隐藏密码框*/
        $("#password").hide();
        console.log(rowData)
        $("#myform").form("load",rowData);

        $("#dialog").dialog("open");
    });

    /*设置员工离职*/
    $("#delete").click(function () {
        /*判断是否选中了数据*/
        let rowData = $("#employee_dg").datagrid("getSelected");
        if (!rowData) {
            $.messager.alert("温馨提示","请选择一条数据");
            return;
        }
        /*弹出提示框是否要真正删除*/
        $.messager.confirm("确定","是否离职",function (res) {
            /*修改员工状态*/
            if (res){
                $.get("updateEmployeeState?empId="+rowData.empId,function (data) {
                    if (!data.success) {
                        $.messager.alert("温馨提示", data.message);
                    }else{
                        $.messager.alert("温馨提示", data.message);
                        /*更新表格数据*/
                        $("#employee_dg").datagrid("reload");
                    }

                });
            }
        })

    });

    /*搜索*/
    $("#searchbtn").click(function () {
        /*获取表单的内容*/
        var keyword = $("[name='keyword']").val();

        /*重新获取数据，并传递搜索字段的参数*/
        $("#employee_dg").datagrid("load",{keyword:keyword});
    });

    /*刷新*/
    $("#reload").click(function () {
        /*清空搜索内容*/
        $("[name=keyword]").val('');
        /*重新加载数据*/
        $("#employee_dg").datagrid("load",{});
    });

    /*下载Excel模板*/
    $("#downloadTml").click(function () {
        window.open("downloadExcelTpl")
    });

    /*excel导出*/
    $("#excelOut").click(function () {
        window.open("downloadExcel");
    });

    /*excel导入*/
    $("#excelIn").click(function () {
        $("#excelUpload").dialog("open");
    });

    $("#excelUpload").dialog({
        width:260,
        height:180,
        title:"导入Excel",
        closed:true,
        buttons:[{
            text:'保存',
            handler:function(){
                $('#uploadForm').form('submit', {
                    url:' uploadExcelFile',
                    /*添加角色信息参数*/
                    success:function(data){
                        /*解析成json*/
                        data = $.parseJSON(data);
                        if (data.success){
                            $.messager.alert("温馨提示",data.message);
                            /* 更新表格数据*/
                            $("#employee_dg").datagrid("reload");
                            /*清除表单数据*/
                            $("#uploadForm").form("clear");
                            /*关闭对话框*/
                            $("#excelUpload").dialog("close");
                        }else {
                            $.messager.alert("温馨提示",data.message);
                        }
                    }
                });
            }
        },{
            text:'关闭',
            handler:function(){
                $("#excelUpload").dialog("close");
            }
        }],

    })
});