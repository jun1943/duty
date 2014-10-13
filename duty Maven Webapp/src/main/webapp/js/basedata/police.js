$(function(){
	$('#my-search').click(function(){
		$('#my-search-box').toggle();	
	});	
	$('#my-check-all').click(function(){
		$(".my-check").each(function(){
			$(this).attr('checked','checked');
		});
	});
	$('#addnew').click(function(){
		$('#myModal').modal('show');	
	});
	$('#edit').click(function(){
		$('#myModal').modal('show');	
	});
	$('#delete').click(function(){
		$('#confirmModal').modal('show');	
	});
});


function savepoliceAction(){  
	$('#myModal').modal('hide');	 
};
function confirmAction(){
	$('#confirmModal').modal('hide');	
};