$(function(){
	$(".bloglist").click(function(){
		var uuid=$("#uuid",this).val();
		window.location.href="/blogShowPage.do?uuid="+uuid;
	});
});