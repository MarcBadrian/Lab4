
$("#createTodo").click(function(){
	var id = $("#todoId").val();
	var message = $("#todoMessage").val();
	var data = {
			id:id,
			message:message
			};
	$.ajax({
		url:"/LAB4/create",
		method:"POST",
		contentType: "application/x-www-form-urlencoded; charset=UTF-8",
		dataType: "json",
		success: function(data,status,xhr){
			alert("Success!");

		},
		error: function(data,status,xhr){
			alert(JSON.stringify(data));
		},
		data: data
	});
});

$("#getJSON").click(
			function() {
				var id = $("#todoId_get").val();
				$.ajax({
					type : 'GET',
					url : "/LAB4/json/" + id,
					dataType: "json",
					success : function(data,status,xhr) {
						alert("Success!");

					},
					error : function(data,status,xhr) {
						alert(JSON.stringify(data));
					}
				});
			});	

$("#getXML").click(
		function() {
			var id = $("#todoId_get").val();
			$.ajax({
				type : 'GET',
				url : "/LAB4/xml/" + id,
				dataType: "xml",
				success : function(data,status,xhr) {
					alert("Success!");

				},
				error : function(data,status,xhr) {
					alert(JSON.stringify(data));
				}
			});
		});	

$("#getAllJSON").click(
		function() {		
			$.ajax({
				type : 'GET',
				url : "/LAB4/all/json",
				success : function(data,status,xhr) {
					alert(JSON.stringify(data));
				},
				error : function(data,status,xhr) {
					alert(JSON.stringify(data));
				}
			});
		});		
$("#getAllXML").click(
		function() {		
			$.ajax({
				type : 'GET',
				url : "/LAB4/all/xml",
				success : function(data,status,xhr) {
					alert("Success!");

				},
				error : function(data,status,xhr) {
					alert(data);
				}
			});
		});		
$("#deleteTodo").click(
	function() {
		var id = $("#todoId_delete").val();
		$.ajax({
			url : "/LAB4/delete/" + id,
			method : "DELETE",
			contentType : 'application/octet-stream; charset=utf-8',
			success: function(data,status,xhr){
				if(data.code == "Success"){
					alert("Success!");
				}else{
					alert("Error! Message is " + data.message);
				}
			},
			error: function(data,status,xhr){
				alert(JSON.stringify(data));
			},
		});
	});