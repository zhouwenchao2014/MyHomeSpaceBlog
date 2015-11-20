$(function() {
	$("#loadContent").css("z-index","0");
	var data = new Date();
	var day = data.getDay() == 0 ? 7 : data.getDay();
	var num = ['一', '二', '三', '四', '五', '六', '日'];

	var left = 245 * (day - 1) + "px 0px";
	$(".leftShow").css("background-image", "url('../img/1538841_1420525583915_1920x1200.jpg')");
	$(".leftShow").css("background-position", "-" + left);

	$(".bloglist").click(function(){
		var uuid=$("#uuid",this).val();
		window.location.href="/blogShowPage.do?uuid="+uuid;
	});
	
	$(".tip").click(function(){
		var value=this.id;
		$("#loadContent").empty();
		$("#loadContent").append('<div id="loadBar"></div>');
		$("#loadContent").css("z-index","100");
		jQuery.ajax({
            url: '/search/elasticSearch.do',
            type: 'POST',
			dataType:'json',
            data: {
				operationType:value,
				content:value
			},
//            error: function(){
//            	jQuery.message.alert('Error Add addUser ...');
//            },
            success: function(data){
                if(data.success){
                	$(".contentCenter ul").empty();
                	var html="";
                	for(var i=0;i<data.attr.length;i++){
                		html=html+'<li><div class="title"><h3>'+data.attr[i].title+'</h3></div><div class="rightTime">'+data.attr[i].writeTime+'</div><div class="bar">喜欢:1 评论:10</div><input type="hidden" value='+data.attr[i].uuid+'></li>';
                	}
                	$(".contentCenter ul").prepend(html);
                	$("#loadContent").css("z-index","0");
                }
				else{
					$("#loadContent").css("z-index","0");
					$.message.pop(data.msg,"warning");
				}
            }});
	});

	function getTime() {
		var date = new Date();
		var year = date.getFullYear();
		var month = date.getMonth();
		var day = date.getDate();
		var hours = date.getHours();
		var seconds = date.getSeconds();
		var minutes = date.getMinutes();
		var day = date.getDay() == 0 ? 7 : date.getDay();

		var tims = year + "年" + getTwolength(month) + "月" + getTwolength(day) + "日" + "       " + getTwolength(hours) + ":" + getTwolength(minutes) + ":" + getTwolength(seconds) + "       星期" + num[day - 1];
		$(".time span").remove();
		$(".time").append("<span>" + tims + "</span>");
	}
	setInterval(getTime, 10);

	function getTwolength(value) {
		if (value < 10) {
			return "0" + value;
		} else {
			return value;
		}
	}

	$(".bloglist").click(function(){
		var uuid=$("#uuid",this).val();

	});




});




