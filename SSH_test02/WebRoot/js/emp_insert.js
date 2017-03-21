$(function() {
	var validate = $("#myform").validate({
		debug: true,	//取消表单的提交操作
		submitHandler : function(form) {
			form.submit(); // 提交表单
		},
		errorPlacement : function(error, element) {	//错误显示位置
			$("#" + $(element).attr("id") + "Span").append(error);
		},
		highlight : function(element, errorClass) {	//验证失败
			$(element).fadeOut(1,function() {
				$(element).fadeIn(1, function() {
					$("#" + $(element).attr("id") + "Div").attr("class","form-group has-error");
				});
			})
		},
		unhighlight : function(element, errorClass) {	//验证成功
			$(element).fadeOut(1,function() {
				$(element).fadeIn(1, function() {
					$("#" + $(element).attr("id") + "Div").attr("class","form-group has-success");
				});
			})
		},
		errorClass : "text-danger",
		rules : {
			"id" : {
				required : true
			},
			"name" : {
				required : true
			},
			"age" : {
				required : true
				
			},
			"birthday" : {
				required : true,
				dateISO : true
			},
			"sal" : {
				required : true
			}
		},
		messages : {
			"id" : {
				required : "必填"
			},
			"name" : {
				required : "必填"
			},
			"age" : {
				required : "必填"
				
			},
			"birthday" : {
				required : "必填",
				dateISO : "日期格式不正确"
			},
			"sal" : {
				required : "必填"
			}
		}
	});
});