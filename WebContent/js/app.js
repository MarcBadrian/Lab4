$("#createButton").click(function(){
	console.log('add new todo');
	//var data = new Object();
	//data.id = $("#idField").val();
	//data.msg = $("#msgField").val();
	$.ajax({
		type: 'POST',
        contentType: 'application/json',
        url: '/Lab4',
        dataType: "json",
        data: formToJSON(),
		success: function(data,status,xhr){
			var res=JSON.parse(AJAX.responseText)
			$("#todoHeader").html(data);
			alert(data);
			alert(res);
		},
		error: function(data,status,xhr){
			$("#todoHeader").html("Error occurred. I have no idea why this won't work.");
		},
	});
	
});

//If making a simple Get call to a server then there is a shorthand for the above ajax
$("#getTodoButton").click(function(){
	var id = $("#idField").val();
	$.ajax({
		url:"/Lab4/Todo",
		method:"GET",
		dataType: "json",
		success: function(data,status,xhr){
			$("#todoHeader").html(data);
		},
		error: function(data,status,xhr){
			$("#todoHeader").html("Error. Seriously, these functions should work.");
		},
		data: id
	});
	
});

//You can get pretty much anything even other html.
$("#getAllTodosButton").click(function(){
	$.get("/Lab4/Todo", function(data){
		$("#todoHeader").html(data);
	});
	
});

//You can even grab other javascript files using getScript
$("#deleteTodoButton").click(function(){
	var id = $("#idField").val();
	$.ajax({
		url:"/Lab4/Todo",
		method:"DELETE",
		dataType: "json",
		success: function(data,status,xhr){
			$("#todoHeader").html(data);
		},
		error: function(data,status,xhr){
			$("#todoHeader").html("Error. Why? Whhyyyyy??");
		},
		data: id
	});

});

//Helper function to serialize all the form fields into a JSON string
function formToJSON() {
    return JSON.stringify({
        "id": $('#id').val(),
        "msg": $('#msg').val(),
        });
}