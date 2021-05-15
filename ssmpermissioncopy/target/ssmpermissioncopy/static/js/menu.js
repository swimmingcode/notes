$(function () {


    $("#menu_datagrid").datagrid({
        url:"/menuList",
        columns:[[
            {field:'text',title:'名称',width:1,align:'center'},
            {field:'url',title:'跳转地址',width:1,align:'center'},
            {field:'parent',title:'父菜单',width:1,align:'center',formatter:function(value,row,index){
                    return value?value.text:'';
                }
            }
        ]],
        fit:true,
        rownumbers:true,
        singleSelect:true,
        striped:true,
        pagination:true,
        fitColumns:true,
        toolbar:'#menu_toolbar'
    });


    /*
     * 初始化新增/编辑对话框
     */
    $("#menu_dialog").dialog({
        width:270,
        height:220,
        closed:true,
        /*buttons:'#menu_dialog_bt',*/
        buttons:[{
            text:'保存',
            handler:function(){
                /*提交表单*/
                /*判读是添加还是修改*/
                var url;
                var id = $("[name='id']").val();
                var parent_id = $("[name = 'parent.id']").val();

              /*  alert(id);*/
                if (id){
                    if (id == parent_id ){
                        $.messager.alert("温馨提示","不能设置自己为父菜单");
                        return;
                    }
                    url = "updateMenu";
                }else{
                    url = "saveMenu";
                }
                $('#menu_form').form('submit', {
                    url:url,
                    success:function(data){
                        /*解析成json*/
                        data = $.parseJSON(data);
                        console.log(data);
                        if (data.success){
                            $.messager.alert("温馨提示",data.message);
                            /* 更新表格数据*/
                            $("#menu_datagrid").datagrid("reload");
                            /*清除表单数据*/
                            $("#menu_form").form("clear");
                            /*关闭对话框*/
                            $("#menu_dialog").dialog("close");
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

    /*增加菜单*/
    $("#add").click(function () {
        $("#menu_dialog").dialog("open");
    });

    /*编辑菜单*/
    $("#edit").click(function () {
        /*判断是否选中了数据*/
        let rowData = $("#menu_datagrid").datagrid("getSelected");
        if (!rowData) {
            $.messager.alert("温馨提示","请选择一条数据");
            return;
        }
        $("#dialog").dialog("setTitle","编辑菜单");
       /* console.log(rowData);*/

        /*回显父菜单列表*/
        if (rowData.parent){
            rowData["parent.id"] = rowData.parent.id;
        }
        /* rowData["parent.Id"] = rowData.parent.id;*/
        /*console.log(rowData);*/
        $("#menu_form").form("load",rowData);
        $("#menu_dialog").dialog("open");
    });

    /*删除菜单*/
    $("#del").click(function () {
        /*判断是否选中了数据*/
        let rowData = $("#menu_datagrid").datagrid("getSelected");
        if (!rowData) {
            $.messager.alert("温馨提示","请选择一条数据");
            return;
        }

        /*弹出提示框是否要真正删除*/
        $.messager.confirm("确定","是否删除菜单",function (res) {
            /*修改员工状态*/
            if (res){
                $.get("/deleteMenu?id="+rowData.id,function (data) {
                    if (!data.success) {
                        $.messager.alert("温馨提示", data.message);
                    }else{
                        $.messager.alert("温馨提示", data.message);
                        /*更新表格数据*/
                        $("#menu_datagrid").datagrid("reload");
                    }

                });
            }
        })
    });
    /*父菜单下拉框*/
    $("#parentSelect").combobox({
        url:'/menuList',
        valueField:'id',
        textField:'text',
        panelWidth:'150',
        panelHeight:'auto',
    })

});