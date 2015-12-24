window.onload = function() {
	console.log(returnCitySN["cip"]);
	//var username=document.cookie.split(";")[0].split("=")[1];
	var country = "";
	var province = "";
	var city = "";
	$.getScript('http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=js', function() {
		var country = remote_ip_info.country;
		var province = remote_ip_info.province;
		var city = remote_ip_info.city;
		console.log(remote_ip_info.country);
		console.log(remote_ip_info.province);
		console.log(remote_ip_info.city);
		console.log(remote_ip_info);
		var userInfo = allinfo();
		var cupInfo = getCpu() == undefined ? "" : getCpu();

		
		var str = formatDate((new Date()), "yyyy-MM-dd hh:mm:ss");
		var url = window.location.href;
		var bowerInfo=bower();
		var netTypeInfo=netType();
		var mobileTypeInfo=mobileType();
		var cookie = document.cookie.split(";");
		var esUuid = "";
		for (var i = 0; i < cookie.length; i++) {
			if (cookie[i].match("esUuid")) {
				esUuid = cookie[i].split("=")[1];
			}
		}
		if (esUuid == "") {
			esUuid = Math.round(Math.random() * 1000000000000);
			
			jQuery.ajax({
			    url: '/elastic_search/add.do',
			    data:{
			    	index:"blog",
			    	type:"ip",
			    	uuid: esUuid,
					ip: returnCitySN["cip"],
					date: str,
					brower:bowerInfo,
					mobileType:mobileTypeInfo,
					netType:netTypeInfo,
					navigator:JSON.stringify(navigator),
					country: country,
					province: province,
					city: city,
					fromurl: url,
					userInfo: JSON.stringify(userInfo),
					cupInfo: cupInfo
			    },
			    type: 'post',
			    success: function(data) {
			      var ab = data;
			    }
			  });
			document.cookie = "esUuid=" + esUuid;
		}

		// client.search({
		// 	index: 'blog',
		// 	size: 50,
		// 	body: {
		// 		query: {
		// 			match: {
		// 				ip: returnCitySN["cip"],
		// 			}
		// 		}
		// 	}
		// }).then(function(resp) {
		// 	var hits = null;
		// 	try {
		// 		hits = resp.hits.hits;
		// 		if (hits.length == 0) {
		// 			client.index({
		// 				index: 'blog',
		// 				type: 'ip',
		// 				body: {
		// 					uuid: Math.round(Math.random() * 1000000000000),
		// 					ip: returnCitySN["cip"],
		// 					country: country,
		// 					province: province,
		// 					city: city,
		// 					fromurl: url,
		// 					date: str
		// 				}
		// 			}, function(err, resp) {
		// 				// ...

		// 			});
		// 		}

		// 	} catch(e) {
		// 		client.index({
		// 			index: 'blog',
		// 			type: 'ip',
		// 			body: {
		// 				uuid: Math.round(Math.random() * 1000000000000),
		// 				ip: returnCitySN["cip"],
		// 				country: country,
		// 				province: province,
		// 				city: city,
		// 				fromurl: url,
		// 				date: str
		// 			}
		// 		}, function(err, resp) {
		// 			// ...

		// 		});
		// 	}

		// });

	});



}

function getIframe() {
	var iframe = $("#iframe");
	var a = $("pre", iframe).val();
}
//格式化日期,
function formatDate(date, format) {
	var paddNum = function(num) {
			num += "";
			return num.replace(/^(\d)$/, "0$1");
		}
		//指定格式字符
	var cfg = {
		yyyy: date.getFullYear() //年 : 4位
			,
		yy: date.getFullYear().toString().substring(2) //年 : 2位
			,
		M: date.getMonth() + 1 //月 : 如果1位的时候不补0
			,
		MM: paddNum(date.getMonth() + 1) //月 : 如果1位的时候补0
			,
		d: date.getDate() //日 : 如果1位的时候不补0
			,
		dd: paddNum(date.getDate()) //日 : 如果1位的时候补0
			,
		hh: date.getHours() //时
			,
		mm: date.getMinutes() //分
			,
		ss: date.getSeconds() //秒
	}
	format || (format = "yyyy-MM-dd hh:mm:ss");
	return format.replace(/([a-z])(\1)*/ig, function(m) {
		return cfg[m];
	});
}