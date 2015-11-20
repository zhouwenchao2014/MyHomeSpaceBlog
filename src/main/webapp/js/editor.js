$(function(){
	$("#save").click(function(){
		var ue = UE.getEditor('container');
		var text=ue.getContent();
		var doc=$(text);
		var title=doc[0].textContent;
		if(title.length>20){
			title=title.substr(0.20);
		}
		jQuery.ajax({
            url: '/addBlog.do',
            type: 'POST',
			dataType:'json',
            data: {
				title:title,
				content:text
			},
//            error: function(){
//            	jQuery.message.alert('Error Add addUser ...');
//            },
            success: function(data){
                if(data.success){
                	window.location.href="/index.do";
                }
				else{
					$.message.pop(data.msg,"warning");
				}
            }});
	});
});