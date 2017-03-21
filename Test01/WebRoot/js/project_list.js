$(function () {
	loadData();
	$("#updateBtn").on("click", function() {
		setUpdateProject();
	});
})
function loadData() {	//数据加载函数
	$.post("pages/project/ProjectServlet/list", {"cp":jsCommonCp,"ls":jsCommonLs},function (obj) {
		$("#projectTable tr:gt(0)").remove();
		createSplitBar(obj.allRecorders);
		for(var x = 0; x < obj.allProjects.length; x++) {
			addRow(obj.allProjects[x].id,obj.allProjects[x].name,obj.allProjects[x].frName,obj.allProjects[x].tel,obj.allProjects[x].address)
		}
		checkboxSelectAll($("#selall"),$("input[id='id']")) ;
		setDeleteButton($("#deleteBtn"),$("input[id='id']"),"pages/project/ProjectServlet/delete");
	},"json");
} 

function addRow(id,name,frName,tel,address) {	//生成数据
    var str = "<tr><td class='text-center'><input type='checkbox' name='id' id='id' value='"+id+"'></td>" +
        "<td id='name-"+id+"' class='text-center'>" + name + "</td>" +
        "<td id='frName-"+id+"' class='text-center'>" + frName + "</td> " +
        "<td id='tel-"+id+"' class='text-center'>" + tel + "</td>" +
        "<td id='address-"+id+"' class='text-center'>" + address + "</td>" +
        "<td id='btn-"+id+"' class='text-center'>" +
        "<button class='btn btn-xs btn-warning' data-toggle='modal' data-target='#projectInfo' id='updateBtn-"+id+"'>" +
        "<span class='glyphicon glyphicon-pencil'></span>&nbsp;修改</button></td></tr>" ;
    $("#projectTable").append($(str)) ;
    $("#updateBtn-"+id).on("click", function() {
    	$("#sid").text(id);
    	$("#name").val(name);
    	$("#frName").val(frName);
    	$("#tel").val(tel);
    	$("#address").val(address);
    });
}
 
function setUpdateProject() {
	var id = $("#sid").text();
	var name = $("#name").val();
	var frName = $("#frName").val();
	var tel = $("#tel").val();
	var address = $("#address").val();
	$.post("pages/project/ProjectServlet/update",{"id":id,"name":name,"frName":frName,"tel":tel,"address":address},function(obj) {
		if(obj.trim() == "true") {	//修改成功
			$("#alertDiv").attr("class","alert alert-success");
			$("#alertText").text("数据修改成功！");
			$("#name-"+id).text(name);
			$("#frName-"+id).text(frName);
			$("#tel-"+id).text(tel);
			$("#address-"+id).text(address);
		} else {	//修改失败
			$("#alertDiv").attr("class","alert alert-danger");
			$("#alertText").text("数据修改失败！");
		}
		$("#projectInfo").modal("hide");
		$("#alertDiv").fadeIn(1000,function() {
			$("#alertDiv").fadeOut(3000);
		})
	},"text");
}
