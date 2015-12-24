function allinfo() {
	var info = new Object();
	var userLanguage = navigator.language; // 用户在自己的操作系统上设置的语言（火狐没有）
	var userAgent = navigator.userAgent; //包含以下属性中所有或一部分的字符串：appCodeName,appName,appVersion,language,platform
	var systemLanguage = navigator.systemLanguage; // 用户操作系统支持的默认语言（火狐没有）
	var systemInfo = getOs();
	info["浏览器属性信息： "] = userAgent;
	info["用户设置的操作系统语言： "] = userLanguage;
	info["操作系统支持的默认语言： "] = systemLanguage;
	info["操作系统： "] = systemInfo;
	return info;
}

function netType(){
	var sUserAgent = navigator.userAgent;
	var sUserAgents=sUserAgent.split(" ");
	for(var i=0;i<sUserAgents.length;i++){
		if(sUserAgents[i].match(/NetType/i)){
			return sUserAgents[i].split(/\//i)[1];
		}
	}
	return "other";
}

function getOs() {
	var sUserAgent = navigator.userAgent;

	var isWin = (navigator.platform == "Win32") || (navigator.platform == "Windows");
	var isMac = (navigator.platform == "Mac68K") || (navigator.platform == "MacPPC") || (navigator.platform == "Macintosh") || (navigator.platform == "MacIntel");
	if (isMac) return "Mac";
	var isUnix = (navigator.platform == "X11") && !isWin && !isMac;
	if (isUnix) return "Unix";
	var isLinux = (String(navigator.platform).indexOf("Linux") > -1);

	var bIsIpad = sUserAgent.match(/ipad/i) == "ipad";
	if (bIsIpad) {
		return "IOS";
	}
	var bIsIphoneOs = sUserAgent.match(/iPhone OS/i) == "iPhone OS";
	if (bIsIphoneOs) {
		return "IOS";
	}

	var bIsAndroid = sUserAgent.toLowerCase().match(/android/i) == "android";

	if (isLinux) {
		if (bIsAndroid) return "Android";
		else return "Linux";
	}
	if (isWin) {
		var isWin2K = sUserAgent.indexOf("Windows NT 5.0") > -1 || sUserAgent.indexOf("Windows 2000") > -1;
		if (isWin2K) return "Win2000";
		var isWinXP = sUserAgent.indexOf("Windows NT 5.1") > -1 ||
			sUserAgent.indexOf("Windows XP") > -1;
		if (isWinXP) return "WinXP";
		var isWin2003 = sUserAgent.indexOf("Windows NT 5.2") > -1 || sUserAgent.indexOf("Windows 2003") > -1;
		if (isWin2003) return "Win2003";
		var isWinVista = sUserAgent.indexOf("Windows NT 6.0") > -1 || sUserAgent.indexOf("Windows Vista") > -1;
		if (isWinVista) return "WinVista";
		var isWin7 = sUserAgent.indexOf("Windows NT 6.1") > -1 || sUserAgent.indexOf("Windows 7") > -1;
		if (isWin7) return "Win7";
		var isWin10 = sUserAgent.indexOf("Windows NT 10.0") > -1 || sUserAgent.indexOf("Windows 10") > -1;
		if (isWin10) return "Win10";
	}
	return "other";
}
/**
 * 获取浏览器信息
 * [bower description]
 * @return {[type]} [description]
 */
function bower() {
	var sUserAgent = navigator.userAgent;

	var IsMac = sUserAgent.match(/Mac/i) == "Mac";

	var IsChrome = sUserAgent.match(/Chrome/i) == "Chrome";

	var IsSafari = sUserAgent.match(/Safari/i) == "Safari";

	var IsUCBrowser = sUserAgent.match(/UCBrowser/i) == "UCBrowser";

	var IsFirefox = sUserAgent.match(/Firefox/i) == "Firefox";

	var IsQQBrowser = sUserAgent.match(/QQBrowser/i) == "QQBrowser";

	var IsMobile = sUserAgent.match(/Mobile/i) == "Mobile";

	var IsMiuiBrowser = sUserAgent.match(/MiuiBrowser/i) == "MiuiBrowser";


	if (!IsChrome && IsSafari && IsMac) {
		return "Safari";
	}
	if (!IsChrome && IsSafari && IsMac && IsMobile) {
		return "SafariMobile";
	}
	if (IsChrome && IsSafari && !IsQQBrowser && !IsMiuiBrowser) {
		return "Chrome";
	}
	if (IsUCBrowser && IsSafari) {
		return "UCBrowser";
	}
	if (IsFirefox) {
		return "Firefox";
	}
	if (IsChrome && IsSafari && IsQQBrowser) {
		return "QQBrowser";
	}
	if (IsChrome && IsSafari && IsMiuiBrowser) {
		return "MiuiBrowser";
	}
	return "other";

}

/**
 * 获取移动设备型号
 * [mobileType description]
 * @return {[type]} [description]
 */
function mobileType() {
	var systemInfo = getOs();

	if (systemInfo == "IOS" || systemInfo == "Android") {

		var sUserAgent = navigator.userAgent;
		var bIsIpad = sUserAgent.match(/ipad/i) == "ipad";
		if (bIsIpad) {
			return "Ipad";
		}
		var bIsIphoneOs = sUserAgent.match(/iPhone OS/i) == "iPhone OS";
		if (bIsIphoneOs) {
			return "iPhone";
		}
		var bIsXIAOMIOs = sUserAgent.match(/XiaoMi/i) == "XiaoMi";
		if (bIsIphoneOs) {
			if (sUserAgent.match(/Mi-Pad/i) == "Mi-Pad") {
				return "Mi-Pad";
			}
			return "XiaoMi";
		}
		var bIsHW = sUserAgent.match(/HONORPLK-AL10/i) == "HONORPLK-AL10";
		if (bIsHW) {
			return "HONORPLK-AL10(华为荣耀7全网通)";
		}
		if (sUserAgent.match(/R7t/i) == "R7t") {
			return "OPPO R7t";
		}
	}else{
		return "other"
	}
}

function getCpu() {
	// var locator = new ActiveXObject("WbemScripting.SWbemLocator");
	// var service = locator.ConnectServer(".");
	// var properties = service.ExecQuery("SELECT * FROM Win32_Processor");
	// var e = new Enumerator(properties);
	// var info = "";
	// info += "CPU 信息";
	// for (; !e.atEnd(); e.moveNext()) {
	// 	var p = e.item();
	// 	info += "CPU序列号:" + p.ProcessorID;
	// 	info += "," + p.Caption;
	// 	info += "CPU编号：" + p.DeviceID;
	// 	info += "CPU型号：" + p.Name;
	// 	info += "CPU状态：" + p.CpuStatus;
	// 	info += "CPU可用性：" + p.Availability;
	// 	info += "CUP Level：" + p.Level;
	// 	info += "主机名称：" + p.SystemName;
	// 	info += "Processor Type：" + p.ProcessorType;
	// 	return info;
	// }
}