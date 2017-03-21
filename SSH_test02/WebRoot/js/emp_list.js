/**
 * 页面打开时应加载页面数据
 * 当点击模态窗口修改按钮时调用setUpdateEmp()函数更新页面
 */
$(function () {
	loadData();
	$("#updateBtn").on("click", function() {
		setUpdateEmp();
	});
})

function loadData() {	//数据加载函数
	$.post("EmpAction/list.action", {"cp":jsCommonCp,"ls":jsCommonLs},function (obj) {
		$("#empTable tr:gt(0)").remove();
		createSplitBar(obj.allRecorders);
		for(var x = 0; x < obj.allEmps.length; x++) {
			addRow(obj.allEmps[x].id,obj.allEmps[x].name,obj.allEmps[x].age,obj.allEmps[x].birthday,obj.allEmps[x].sal)
		}
		checkboxSelectAll($("#selall"),$("input[id='id']"));
		setDeleteButton($("#deleteBtn"),$("input[id='id']"),"EmpAction/delete.action");
	},"json");
}

function addRow(id,name,age,birthday,sal) {	//生成数据
    var str = "<tr><td class='text-center'><input type='checkbox' name='id' id='id' value='"+id+"'></td>" +
    	"<td id='id-"+id+"' class='text-center'>" + id + "</td>" +
        "<td id='name-"+id+"' class='text-center'>" + name + "</td>" +
        "<td id='age-"+id+"' class='text-center'>" + age + "</td> " +
        "<td id='birthday-"+id+"' class='text-center'>" + birthday + "</td>" +
        "<td id='sal-"+id+"' class='text-center'>" + sal + "</td>" +
        "<td id='btn-"+id+"' class='text-center'>" +
        "<button class='btn btn-xs btn-warning' data-toggle='modal' data-target='#empInfo' id='updateBtn-"+id+"'>" +
        "<span class='glyphicon glyphicon-pencil'></span>&nbsp;修改</button></td></tr>" ;
    $("#empTable").append($(str)) ;
    $("#updateBtn-"+id).on("click", function() {	//把面板的值传给模态窗口
    	$("#sid").val(id);
    	$("#name").val(name);
    	$("#age").val(age);
    	$("#birthday").val(birthday);
    	$("#sal").val(sal);
    });
}

/**
 * 数据修改函数
 * 当点击updateBtn按钮后 先把模态窗口的数据异步提交到后台 如果后台修改成功(后台向前台返回true时)则将面板的数据修改为模态窗口的数据
 */
function setUpdateEmp() {
	var id = $("#sid").val();
	var name = $("#name").val();
	var age = $("#age").val();
	var birthday = $("#birthday").val();
	var sal = $("#sal").val();
	$.post("EmpAction/update.action",{"id":id,"name":name,"age":age,"birthday":birthday,"sal":sal},function() {
		if(obj.trim() == "true") {	//修改成功
			$("#alertDiv").attr("class","alert alert-success");
			$("#alertText").text("数据修改成功！");
			$("#id-"+id).text(id);
			$("#name-"+id).text(name);
			$("#age-"+id).text(age);
			$("#birthday-"+id).text(birthday);
			$("#sal-"+id).text(sal);
		} else {
			$("#alertDiv").attr("class","alert alert-danger");
			$("#alertText").text("数据修改失败！");
		}
		$("#empInfo").modal("hide");
		$("#alertDiv").fadeIn(1000,function() {
			$("#alertDiv").fadeOut(3000);
		})
	},"text");
}