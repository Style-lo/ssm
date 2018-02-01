//================================
// 公共js
// 作者：Hearken
// 时间：2014-08-07 17:05
//================================
var commonJs = {};
//空函数，统一使用void(0)，例如href="javascript:void(0)"
commonJs.buttonType = '0';// 1-（放弃在左边，确定在右边），0-（确定在左边，放弃在右边）

// 是否手机浏览器宽度
commonJs.getMobileWidth = function() {
	if ($(window).width() < 981) return true; else return false;
};
// commonJs.isMobileWidth = commonJs.getMobileWidth();

//Md5加密
commonJs.getMd5 = function(sStr) {
	return hex_md5(sStr);
}

commonJs.getBtnCancel = {
	label: '返回' + (commonJs.getMobileWidth() ? '' : '(ESC)'),
	hotkey: 27,
	cssClass: 'btn-default',
	action: function(dialogRef) {
		dialogRef.close();
	}
}

commonJs.getBtnClose = {
	label: '关闭窗口' + (commonJs.getMobileWidth() ? '' : '(ESC)'),
	hotkey: 27,
	cssClass: 'btn-default',
	action: function(dialogRef) {
		dialogRef.close();
	}
}

//清除空值，防止表格没有边框
commonJs.clearNull = function(sValue, sDefault) {
	if (sValue == null || sValue == undefined || sValue == '' || sValue == 'undefined' || sValue.length < 1) {
		if (sDefault == null || sDefault == undefined || sValue == 'undefined') {
			return '';
		} else {
			return sDefault;
		}
	}
	return sValue;
}

// 打开加载对话框
var modComLoadDialog = null;
commonJs.openLoadDialog = function() {
	var dialog = new BootstrapDialog({
		message: function(dialogRef){
			var $message = $('<i class="fa fa-spinner fa-spin loading" style="font-size:24px;"></i><span style="font-size:16px;">&ensp;处理中，请稍候...</span>');	
			return $message;
		},
		cssClass: 'load-dialog',
		closable: false
	});
	dialog.realize();
	dialog.getModalHeader().hide();
	dialog.getModalFooter().hide();
	dialog.open();
	modComLoadDialog = dialog;
}

// 关闭加载对话框
commonJs.closeLoadDialog = function() {
	if (modComLoadDialog != null) {
		modComLoadDialog.close();
		modComLoadDialog = null;
	}
}

// 打开对话框(type=info,warning,error,success)，2秒后自动关闭
commonJs.openDialog = function(type, message, callback) {
	if (callback == null) {
		callback = function() { };	
	}
	var sIHtml = '';
	if (type == 'info') {
		sIHtml = '<i class="fa fa-info" style="font-size:24px; color:#5bc0de;"></i>';
	} else if (type == 'warning') {
		sIHtml = '<i class="fa fa-warning" style="font-size:24px; color:#f0ad4e;"></i>';
	} else if (type == 'error') {
		sIHtml = '<i class="fa fa-times" style="font-size:24px; color:#d9534f;"></i>';
	} else if (type == 'success') {
		sIHtml = '<i class="fa fa-check" style="font-size:24px; color:#5cb85c;"></i>';
	}
	var dialog = new BootstrapDialog({
		title: '提示信息',
		message: function(dialogRef){
			var $message = $(sIHtml + '<span style="font-size:16px;">&ensp;' + message + '</span>');	
			return $message;
		},
		closable: false,
		cssClass: 'message-dialog',
		onhidden: function(dialogRef) {
			callback();// 回调函数
		}
	});
	dialog.realize();
	dialog.getModalHeader().hide();
	dialog.getModalFooter().hide();
	dialog.open();
	setTimeout(function() {
		dialog.close();
	}, 2000);
}

// 打开询问对话框
commonJs.openConfirmDialog = function(message, callback) {
	var btnCancel = {
		label: '放弃' + (commonJs.getMobileWidth() ? '' : '(ESC)'),
		hotkey: 27,
		action: function(dialog) {
			typeof dialog.getData('callback') === 'function' && dialog.getData('callback')(false);
			dialog.close();
		}
	};
	var btnConfirm = {
		label: '确定' + (commonJs.getMobileWidth() ? '' : '(Enter)'),
		hotkey: 13,
		cssClass: 'btn btn-outline ol-pink',
		action: function(dialog) {
			typeof dialog.getData('callback') === 'function' && dialog.getData('callback')(true);
			dialog.close();
		}
	};
	var buttons = [btnCancel, btnConfirm];
	if (commonJs.buttonType != '1') {
		buttons = [btnConfirm, btnCancel];
	}
	var dialog = new BootstrapDialog({
		title: '询问信息',
		message: function(dialogRef){
			var $message = $('<span style="font-size:16px;">' + message + '</span>');	
			return $message;
		},
		closable: false,
		cssClass: 'message-dialog',
		data: {
			'callback': callback
		},
		buttons: buttons
	});
	dialog.open();
}

commonJs.openModelDialog = function(message, arrButtons){
	if(!arrButtons || arrButtons.length == 0){
		arrButtons = [{
			label: '关闭' + (commonJs.getMobileWidth() ? '' : '(ESC)'),
			hotkey: 27,
			action: function(dialog){
				dialog.close();
			}
		}]
	}

	var dialog = new BootstrapDialog({
		title: "询问信息",
		message: function(dialogRef){
			var $message = $('<span style="font-size:16px;">' + message + '</span>');
			return $message;
		},
		closable: false,
		cssClass: "message-dialog",
		buttons: arrButtons
	});
	dialog.open();
}

commonJs.loadPageDialog = function(par){
	if (par != null && par.closable == null) { par.closable = false; }
	if (par != null && par.lg != null && par.lg == true) {
		par.lg = BootstrapDialog.SIZE_WIDE;
	} else {
		par.lg = BootstrapDialog.SIZE_NORMAL;
	}
	if (par != null && par.enableMobile == null) { par.enableMobile = true; }
	if (commonJs.getMobileWidth() && par.enableMobile) {// 支持手机浏览器
		if(!commonJs.loadPageDialog.inited){
			window.onpopstate = function(event){
				var state = event.state;
				if(state == null) return;
				$("#" + state.show).show();
				$("#" + state.hide).hide();
			};
			commonJs.loadPageDialog.inited = true;
		}

		$("#" + par.pageContainer).hide();
		$("#" + par.dialogContainer).html(commonJs.getLoadingHtml()).show();
		window.history.replaceState({ show: par.pageContainer, hide: par.dialogContainer }, "", window.location.href);
		par.data["page_width"] = $(window).width();// 传入页宽才会触发模板
		$.ajax({
			url:		par.url,
			data:		{"jsonParams" : JSON.stringify(par.data)},//par.data,
			type:		'POST',
			dataType:	'html',// xml,html,script,json,jsonp
			timeout:	30 * 60 * 1000,
			success:	function(data) {
				window.history.pushState({ show: par.dialogContainer, hide: par.pageContainer }, "", par.url);
				$("#" + par.dialogContainer).html(data);
			},
			error:		function() {}
		});
		return null;
	}
	return commonJs.doOpenPageDialog(par);
};

// 打开页面对话框
commonJs.openPageDialog = function(par) {
	if (par != null && par.closable == null) { par.closable = false; }
	if (par != null && par.lg != null && par.lg == true) {
		par.lg = BootstrapDialog.SIZE_WIDE;
	} else {
		par.lg = BootstrapDialog.SIZE_NORMAL;
	}
	if (par != null && par.enableMobile == null) { par.enableMobile = true; }
	if (commonJs.getMobileWidth() && par.enableMobile) {// 支持手机浏览器
		if(par.ajaxOpen){
			$(par.pageContainer).hide();
			$(par.dialogContainer).html(commonJs.getLoadingHtml());
			var hash = window.location.href + "#dialog-" + par.dialogContainer;
			window.history.pushState({ url: hash }, "", hash);
			$.ajax({
				url:		par.url,
				data:		{"jsonParams" : JSON.stringify(par.data)},//par.data,
				type:		'POST',
				dataType:	'html',// xml,html,script,json,jsonp
				timeout:	30 * 60 * 1000,
				success:	function(data) {
					$(par.dialogContainer).html(data);
				},
				error:		function() {}
			});
		}else{
			var sUrl = par.url;
			if (par.data != null) {
				var i = 0;
				for (var key in par.data) {
					if (commonJs.clearNull(par.data[key]) == "") continue;
					if (i == 0) sUrl += "?"; else sUrl += "&";
					sUrl += key + "=" + commonJs.clearNull(par.data[key]);
					i++;
				}
			}
			if (sUrl.indexOf("?") < 0) {
				sUrl += "?page_width=" + $(window).width();
			} else {
				sUrl += "&page_width=" + $(window).width();
			}
			window.location.href = sUrl;
		}
		return null;
	}
	return commonJs.doOpenPageDialog(par);
}

commonJs.doOpenPageDialog = function(par){
	return new BootstrapDialog({
		id:		par.id,// 窗口id
		title:	par.title,// 标题
		size:	par.lg,// 是否大窗口
		closable: par.closable,// 是否可关闭（默认为不关闭）
		closeByKeyboard: par.closable,// 是否可用Esc关闭窗口（与closable一致）
		message: function(dialog) {
			var $message = $('<div><i class="fa fa-spinner fa-spin loading" style="font-size:24px;"></i><span style="font-size:16px;">&ensp;加载中，请稍候...</span></div>');
			setTimeout(function() {
				if (par.url == null || par.url == '') return;
				if (par.url.indexOf('?') > -1) {
					// par.url += '&ran=' + Math.random();
					par.url += "&page_width=" + $(window).width();
				} else {
					// par.url += '?ran=' + Math.random();
					par.url += "?page_width=" + $(window).width();
				}
				$message.load(par.url, {"jsonParams" : JSON.stringify(par.data)}, function() { });
			}, 500);
			return $message;
		},
		buttons: par.buttons
	}).open();
};

// 关闭所有对话框
commonJs.closeAllDialog = function() {
	$.each(BootstrapDialog.dialogs, function(id, dialog){
		dialog.close();
	});	
}

//打开ajax请求(请求地址, 参数, 成功后回调的函数, 失败后回调的函数)
commonJs.openAjax = function(sUrl, vParams, funSuccess, funError,hasLoading) {
	// 默认显示加载层，如果不需要，请设置vParams.hasLoading为false
	if (hasLoading) { commonJs.openLoadDialog(); }
	$.ajax({
		url:		sUrl,
		data:		{"jsonParams" : JSON.stringify(vParams)},
		type:		'POST',
		dataType:	'json',// xml,html,script,json,jsonp
		timeout:	30 * 60 * 1000,
		success:	function(data) {
			if (hasLoading) { commonJs.closeLoadDialog(); }
			funSuccess(data);
		},
		error:		funError
	});
};

//公共错误函数
commonJs.funError = function(data) {
	commonJs.closeLoadDialog();
	commonJs.openDialog("error","网络繁忙，请稍候再尝试！",null);
};

// 获取选中的对象值
commonJs.getSelectValue = function(sSelectsName, sSelectType) {
	var arrComNums = new Array();
	$('input[name="' + sSelectsName + '"]:' + sSelectType).each(function() {
		var bChecked = document.getElementById(this.id).checked;
		if (bChecked != null && (bChecked == true || bChecked == 'checked')) {
			arrComNums.push(this.value);
		}
	});
	return arrComNums;
}

var validateRegExp = {
	decmal:"^([+-]?)\\d*\\.\\d+$", //浮点数
	decmal1: "^[1-9]\\d*.\\d*|0.\\d*[1-9]\\d*$", //正浮点数
	decmal2: "^-([1-9]\\d*.\\d*|0.\\d*[1-9]\\d*)$", //负浮点数
	decmal3: "^-?([1-9]\\d*.\\d*|0.\\d*[1-9]\\d*|0?.0+|0)$", //浮点数
	decmal4: "^[1-9]\\d*.\\d*|0.\\d*[1-9]\\d*|0?.0+|0$", //非负浮点数（正浮点数 + 0）
	decmal5: "^(-([1-9]\\d*.\\d*|0.\\d*[1-9]\\d*))|0?.0+|0$", //非正浮点数（负浮点数 + 0）
	intege: "^-?[1-9]\\d*$", //整数
	intege1: "^[1-9]\\d*$", //正整数
	intege2: "^-[1-9]\\d*$", //负整数
	num: "^([+-]?)\\d*\\.?\\d+$", //数字
	num1: "^[1-9]\\d*|0$", //正数（正整数 + 0）
	num2: "^-[1-9]\\d*|0$", //负数（负整数 + 0）
	num3: "^[0-9]*$",
	ascii: "^[\\x00-\\xFF]+$", //仅ACSII字符
	chinese: "^[\\u4e00-\\u9fa5]+$", //仅中文
	color: "^[a-fA-F0-9]{6}$", //颜色
	date: "^\\d{4}(\\-|\\/|\.)\\d{1,2}\\1\\d{1,2}$", //日期
	email: "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$", //邮件
	idcard: "^[1-9]([0-9]{14}|[0-9]{17})$", //身份证
	ip4: "^(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)$", //ip地址
	letter: "^[A-Za-z]+$", //字母
	letter_l: "^[a-z]+$", //小写字母
	letter_u: "^[A-Z]+$", //大写字母
	mobile: "^0?(13|15|18)[0-9]{9}$", //手机
	notempty: "^\\S+$", //非空
	account: "^[A-Za-z0-9_-]+$", //账号
	password: "^[A-Za-z0-9_-]+$", //密码
	picture: "(.*)\\.(jpg|bmp|gif|ico|pcx|jpeg|tif|png|raw|tga)$", //图片
	qq: "^[1-9]*[1-9][0-9]*$", //QQ号码
	rar: "(.*)\\.(rar|zip|7zip|tgz)$", //压缩文件
	tel: "^[0-9\-()（）]{7,18}$", //电话号码的函数(包括验证国内区号,国际区号,分机号)
	url: "^http[s]?:\\/\\/([\\w-]+\\.)+[\\w-]+([\\w-./?%&=]*)?$", //url
	username: "^[A-Za-z0-9_\\-\\u4e00-\\u9fa5]+$", //用户名
	deptname: "^[A-Za-z0-9_()（）\\-\\u4e00-\\u9fa5]+$", //单位名
	zipcode: "^\\d{6}$", //邮编
	realname:"^[A-Za-z0-9\\u4e00-\\u9fa5]+$", // 真实姓名
	companyname:"^[A-Za-z0-9_()（）\\-\\u4e00-\\u9fa5]+$",
	companyaddr:"^[A-Za-z0-9_()（）\\#\\-\\u4e00-\\u9fa5]+$",
	companysite:"^http[s]?:\\/\\/([\\w-]+\\.)+[\\w-]+([\\w-./?%&#=]*)?$"
};

//校验电子邮箱
commonJs.validateEmail = function(sEmail) {
	if (sEmail.search(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/) != -1){
		return true;
	}else{
		return false;
	}
}

//保存2位小数
commonJs.renderMoney = function(sValue) {
	var dTempValue = 0.00;
	if (sValue == null || sValue == '') {
		return dTempValue.toFixed(2);
	} if (sValue == '&ensp;') {
		return sValue;
	}
	// 保留2位
	try { dTempValue = parseFloat(sValue); dTempValue = dTempValue.toFixed(2); } catch(e) { dTempValue = 0.00; }
	return dTempValue; 
};

/*校验只能输入整数或小数:
sType: 1-只能录入整数、2-只能录入两位小数、整数
*/
commonJs.validateDigital = function(sDigital,sType){
	if(sType=='1'){
		exp=/^[1-9][0-9]*$/;
		return exp.test(sDigital);
	}else if(sType=='2'){
		exp=/^[1-9][0-9]+(.[0-9]{1,2})?$/;
		return exp.test(sDigital);
	}
}

commonJs.getLoadingHtml = function() {
	return '<div class="div-loading"><i class="fa fa-spinner fa-spin loading" style="font-size:24px;"></i><span style="font-size:16px;">&ensp;加载中，请稍候...</span></div>';	
}

// 找开分页Ajax请求(请求地址, 参数, 将内容渲染到层的ID)
commonJs.openPageAjax = function(sUrl, vParams, sDivId) {
	if (vParams != null && vParams.hasLoading != null && vParams.hasLoading == false) {
		// 不需要显示加载效果
	} else {
		$('#' + sDivId).html(commonJs.getLoadingHtml());
	}
	$.ajax({
		url:		sUrl,
		data:		{"jsonParams" : JSON.stringify(vParams)},//vParams,
		type:		'POST',
		dataType:	'html',// xml,html,script,json,jsonp
		timeout:	30 * 60 * 1000,
		success:	function(data) {
			$('#' + sDivId).html(data);
		},
		error:		function() {}
	});
};

// 全选/反选
commonJs.selectOrUnSelect = function(sSelectAllId, sSelectsName) {
	var objSelectAll = $('#' + sSelectAllId);	
	if (objSelectAll ==  null) { return false; }
	objSelectAll.click(function() {
		if (this.checked) {
			$("input[name='" + sSelectsName + "']").each(function() {
				if (this.disabled) { return; }
				$(this).attr("checked", true);
			});
		} else {
			$("input[name='" + sSelectsName + "']").each(function() {
				if (this.disabled) { return; }
				$(this).attr("checked", false);
			});
		}
	});
};

//验证空值
commonJs.verifyNullValue = function(arrValues) {
	var sResult = 'true';
	if (arrValues == null || arrValues.length < 1) { return sResult; }
	for (var i = 0; i < arrValues.length; i+=1) {
		var item = arrValues[i];
		if (item[1] == null || item[1] == '') {
			if (item[0].indexOf('sel;') > -1) {
				return '“' + item[0].replace('sel;', '') + '”不能为空，请重新选择！';
			} else if (item[0].indexOf('ckb;') > -1) {
				return '“' + item[0].replace('ckb;', '') + '”不能为空，请重新勾选！';
				break;
			} else {
				return '“' + item[0] + '”不能为空，请重新输入！';
			}
		}
	}
	return sResult;
};

// 获取值长度控件
commonJs.getValueLength = function(sContent) {
	if (sContent == null || sContent == '' || sContent == 'undefine') return 0;
	var iCount = sContent.replace(/[^\x00-\xff]/g, "**").length;
	return iCount;
}

//校验手机格式
commonJs.validateMobilePhone = function(sMobileNumber) {
	var exp =/(^[1][3][0-9]{9}$)|(^[1][4][0-9]{9}$)|(^[1][5][^4]{1}\d{8}$)|(^[1][8][^4]{1}\d{8}$)/;
  	return exp.test(sMobileNumber);  // 手机验证 13x 14x 15x 18x 以此类推
}

// 公共下拉框值(下拉框公共类型)
commonJs.getComboValues = function(type) {
	var values = [];
	if ('openState' == type) {// 开放状态
		values = [
			['','--请选择--']
			,['1','渠道（大客户）']
			,['2','个人']
		];
	} else if ('dictionaryType' == type) {//字典类型
		values = [
			['', '--请选择--']
			,['1', '风险类型分类']
			,['2', '干预分类']
			,['3', '渠道分类']
			,['4', '预约分类']
			,['5', '动态数据标识']
		];
	} else if ('relationSetting' == type) {//关联设置
		values = [
			['', '--请选择--']
			,['1', '按风评题目']
			,['2', '按风评类型']
			,['3', '按风评概率']
			,['4', '按病种']
			,['5', '固定值']
		];
	} else if ('gender' == type) {// 性别
		values = [
			['','--请选择--']
			,['1','男']
			,['2','女']
		];
	} else if ('idType' == type) {// 证件类型
		values = [
			['', '--请选择--']
			,['1', '二代身份证']
			,['2', '港澳通行证']
			,['3', '台湾通行证']
			,['4', '护照']
		];
	} else if ('transactionType' == type) {// 交易类型
		values = [
			['', '--请选择--']
			,['10', '注册奖励']
			,['11', '充值']
			,['12', '提现']
			,['13', '邀请注册奖励']
			,['14', '邀请加入团队奖励']
			,['15', '实名认证费用']
			,['16', '医生工作室认证费用']
			,['17', '医生称谓认证费用']
			,['18', '医生工作室组合认证费用']
			,['19', '第三方检测开单提成']
			,['20', '第三方检测采集提成']
			,['21', '第三方检测运营提成']
			,['22', '检测报告解读提成']
			,['23', '诊室租用提成']
			,['24', '第三方检测申请提成']
			,['25', '第三方服务申请提成']
			,['26', '基因解读资格考核申请']
			,['27', '购买医疗系统服务']
			,['28', '医生工作室认证提成']
			,['29', '第三方检测服务代付款']
			,['30', '提现失败退款']
			,['31', '爱心捐赠']
		];
	} else if ('teamMemberStatus' == type) {// 团队成员状态
		values = [
			['', '--请选择--']
			,['1', '待加入']
			,['2', '已加入']
			,['3', '已退出']
		];
	} else if ('currencyType' == type) {// 账户类型
		values = [
			['', '--请选择--']
			,['1', '现金账户']
			,['2', '壹康币账户']
			,['3', '第三方网银']
		];
	} else if ('reserveTimeType' == type) {
		values = [
			['', '']
			,['1', '上午']
			,['2', '下午']
			,['3', '晚上']
			,['4', '全天']
		];
	} else if ('consultingRoomOrderStatus' == type) {
		values = [
			['', '--请选择--']
			,['1', '待确认']
			,['2', '已确认']
			,['3', '已完成']
			,['4', '已取消']
		];
	} else if ('consultingRoomOrderSource' == type) {
		values = [
			['', '--请选择--']
			,['1', '医疗系统']
			,['2', '工作室网站']
			,['3', '工作室手机应用']
		];
	} else if ('commissionType' == type) {
		values = [
			['', '--请选择--']
			,['1', '诊室租用提成']
			,['2', '第三方服务劳务费']
			,['3', '第三方服务提成']
			,['4', '基因检测开单提成']
			,['5', '基因检测解读单提成']
			,['6', '健康评估提成']
		];
	} else if ('commissionStatus' == type) {
		values = [
			['', '--请选择--']
			,['1', '待核对']
			,['2', '数据正确']
			,['3', '数据有误']
		];
	} else if ('serviceOrderStatus' == type) {
		values = [
			['', '--请选择--']
			,['1', '待确认']
			,['2', '待服务']
			,['3', '已服务']
			,['4', '已取消']
		];
	} else if ('serviceItemStatus' == type) {
		values = [
			['', '--请选择--']
			,['1', '已下架']
			,['2', '已上架']
			,['3', '已锁定']
		];
	} else if ('userRole' == type) {
		values = [
			['', '--请选择--']
			,['1', '医生工作室']
			,['2', '渠道工作室']
			,['3', '第三方检测工作室']
			,['4', '第三方服务工作室']
			,['5', '采集定点工作室']
		];
	} else if ('useState' == type) {
		values = [
			['', '--请选择--']
			,['0', '已停用']
			,['1', '已启用']
		];
	} else if ('accountType' == type) {
		values = [
			['', '--请选择--']
			,['1', '总账号']
			,['2', '子账号']
		];
	} else if ('detectionOrderStatus' == type) {
		values = [
			['', '--请选择--']
			,['1', '待收费']
			,['2', '待采集']
			,['3', '待送检']
			,['4', '待上传']
			,['5', '待发送']
			,['6', '待解读']
			,['7', '已解读']
			,['8', '已取消']
		];
	} else if ('paymentMode' == type) {// 付款方式
		values = [
			['', '--请选择--']
			,['1', '现金账户支付']
			,['2', '壹康币支付']
			,['3', '微信支付']
			,['4', '支付宝支付']
			,['5', '门店支付']
		];
	} else if ('spType' == type) {// 机构性质
		values = [
			['', '--请选择--']
			,['3', '检测机构']
			,['4', '服务机构']
		];
	} else if ('spBindingStatus' == type) {// 服务机构绑定状态
		values = [
			['', '--请选择--']
			,['0', '未绑定']
			,['1', '已绑定']
		];
	} else if ('detectionOrderTimeType' == type) {
		values = [
			['', '--请选择--']
			,['1', '上午']
			,['2', '下午']
			,['3', '晚上']
		];
	} else if ('examOrderType' == type) {
		values = [
			['', '--请选择--']
			,['1', '模拟考核']
			,['2', '真实考核']
		];
	} else if ('examOrderStatus' == type) {
		values = [
			['', '--请选择--']
			,['1', '待付款']
			,['2', '考核中']
			,['3', '未通过']
			,['4', '已通过']
			,['5', '已结束']
		];
	} else if ('hisServicePaymentType' == type) {
		values = [
			['', '--请选择--']
			,['1', '按年付费']
			,['2', '按月付费']
		];
	} else if ('hisServiceStatus' == type) {
		values = [
			['', '--请选择--']
			,['1', '待生效']
			,['2', '执行中']
			,['3', '已完成']
		];
	} else if ('authType' == type) {
		values = [
			['', '--请选择--']
			,['1', '实名认证']
			,['2', '医生工作室认证']
			,['3', '医生称谓认证']
		];
	} else if ('studioBindingType' == type) {// 工作室绑定类型
		values = [
			['', '--请选择--']
			,['3', '第三方服务']
			,['4', '第三方服务']
			,['5', '采集定点']
		];
	} else if ('withdrawStatus' == type) {// 提现申请状态
		values = [
			['', '--请选择--']
			,['1', '待提现']
			,['2', '已提现']
			,['3', '不能提现']
			,['4', '已取消']
		];
	} else if ('withdrawPaymentMode' == type) {// 提现方式
		values = [
			['', '--请选择--']
			,['1', '转账银行卡']
			,['2', '到门店提现']
		];
	} else if ('serviceType' == type) {// 第三方服务类型
		values = [
			['', '--请选择--']
			,['1', '第三方检测项目管理']
			,['2', '第三方服务项目管理']
		];
	} else if ('studioType' == type) {// 工作室分类
		values = [
			['', '--请选择--']
			,['1', '一类工作室']
			,['2', '二类工作室']
		];
	} else if ('recipientType' == type) {// 寄送方式
		values = [
					['', '--请选择--']
					,['1', '快递']
					,['2', '自提']
				];
	} else if ('isNeedReceipt' == type) {// 寄送方式
		values = [
					['', '--请选择--']
					,['0', '不需要']
					,['1', '需要']
				];
	} else if ('doctorType' == type) {// 寄送方式
		values = [
					['', '--请选择--']
					,['1', '站内']
					,['2', '站外']
				];
	}	
	return values;
};

//设置下拉框的option(控件的ID, 下拉框公共类型, 是否不显示空项, 选中哪个值，不显示的项目)
commonJs.setOption = function(sSelectId, sType, bShowNotNullItem, sSelectValue, sNoShowItem) {
	var objSelect = $('#' + sSelectId);
	var arrData = commonJs.getComboValues(sType);
	// 删除数组的第一项
	if (bShowNotNullItem == true) { arrData.shift(); }
	$.each(arrData, function(i, item) {
		if (item == null) { return true; }
		if (sNoShowItem == null || (sNoShowItem != null && (',' + sNoShowItem + ',').indexOf(',' + item[0] + ',') < 0)) {
			if (item[0] == sSelectValue) {
				$("<option value='" + item[0] + "' selected>" + item[1] + "</option>").appendTo(objSelect);
			} else {
				$("<option value='" + item[0] + "'>" + item[1] + "</option>").appendTo(objSelect);
			}
		}
	});
};

//根据身份证获取出生日期
commonJs.getBirthdayByIdCard = function(sIdCard) {
	var sBirthdayValue = ''; 
	if (15 == sIdCard.length) {// 15位身份证号码 
		sBirthdayValue = sIdCard.charAt(6) + sIdCard.charAt(7); 
		if (parseInt(sBirthdayValue) < 10) {
			sBirthdayValue = '20' + sBirthdayValue; 
		} else { 
			sBirthdayValue = '19' + sBirthdayValue; 
		} 
		sBirthdayValue=sBirthdayValue+'-'+sIdCard.charAt(8)+sIdCard.charAt(9)+'-'+sIdCard.charAt(10)+sIdCard.charAt(11); 
	} else if(18 == sIdCard.length) {// 18位身份证号码 
		sBirthdayValue= sIdCard.charAt(6) + sIdCard.charAt(7) + sIdCard.charAt(8) + sIdCard.charAt(9)
		+ '-' + sIdCard.charAt(10) + sIdCard.charAt(11) + '-' + sIdCard.charAt(12) + sIdCard.charAt(13);
	}
	return sBirthdayValue;
} 

// 根据身份证获取性别
commonJs.getGenderByIdCard = function (sIdCard) { 
	var sGenderValue; 
	if (15 == sIdCard.length) {// 15位身份证号码 
		if (parseInt(sIdCard.charAt(14)/2)*2 != sIdCard.charAt(14)) {
			sGenderValue = '1'; 
		} else {
			sGenderValue = '2';
		}
	} else if(18 == sIdCard.length) {// 18位身份证号码 
		if (parseInt(sIdCard.charAt(16)/2)*2 != sIdCard.charAt(16)) {
			sGenderValue = '1';
		} else {
			sGenderValue = '2';
		}
	}
	return sGenderValue;
}

//校验身份证
commonJs.validateIdCard = function(sIdCard) {
	if (sIdCard == null || sIdCard.length != 15 && sIdCard.length != 18) {
		return false;
	}
	if (sIdCard.length == 15) {
		var exp = /^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])(\d{3}|\d{2}[xX])$/;
		return exp.test(sIdCard);
	} else {
		var exp = /^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])(\d{4}|\d{3}[xX])$/;
		return exp.test(sIdCard);
	}
}

//秒倒计时(sId:控件名，sSecond:开始秒数，sContent：显示的内容)
commonJs.funDaoJiShi = function (sId,sSecond,sContent) {
	var sSpn = sId.replace('btn','spn');
	if (sSecond > 0) {
		$('#'+sId).attr("disabled", true);
		$('#'+sId).addClass("disabled");
		// $('#'+sId).add("class", "daojishi-on");
		$('#'+sId).attr("value", sContent + "(" + sSecond + "秒)");
		$('#'+sSpn).html(sContent + "(" + sSecond + "秒)");
	} 
	sSecond = sSecond - 1;
	if (sSecond >= 0) {
		timeOut = setTimeout(function(){commonJs.funDaoJiShi(sId,sSecond,sContent);},1000);
	} else {
		// 启用方法
		$('#'+sId).attr("disabled", false);
		$('#'+sId).removeClass("disabled");
		// $('#'+sId).attr("class", "daojishi-off");
		$('#'+sId).attr("value",sContent);
		$('#'+sSpn).html(sContent);
	}
}

//加密路径
commonJs.enReturnUrl = function(sCustomUrl) {
	var sUrl = '';
	if (sCustomUrl == null) {
		sUrl = window.location.href;
	} else {
		sUrl = sCustomUrl;
	}
	if (sUrl == null || sUrl == '') { return ''; }
	do {
		sUrl = sUrl.replace('?', '@');
		sUrl = sUrl.replace('&', '$');
		sUrl = sUrl.replace('%', '^');
	} while(sUrl.indexOf('?') > -1 || sUrl.indexOf('&') > -1 || sUrl.indexOf('%') > -1);
	return sUrl;
}

// 解密路径
commonJs.deReturnUrl = function(sReturnUrl) {		
	var sUrl = sReturnUrl;
	if (sUrl == null || sUrl == '') { return ''; }
	do {
		sUrl = sUrl.replace('@', '?');
		sUrl = sUrl.replace('$', '&');
		sUrl = sUrl.replace('^', '%');
	} while(sUrl.indexOf('@') > -1 || sUrl.indexOf('$') > -1 || sUrl.indexOf('^') > -1);
	return sUrl;
}

commonJs.toJson = function(sJsonString) {
	if (sJsonString != null && sJsonString.indexOf('{') > -1) {
		return eval('(' + sJsonString + ')');
	} else if (sJsonString != null && sJsonString !='' ) {
		return eval('({' + sJsonString + '})');
	}
	return sJsonString;
};

// 判断金额，具体可查看 /po/common/customer.js中的validateRegExp
commonJs.testNumber = function(number,sType) {
	var sExp;
	if (sType == 'decmal1') {// 正浮点数
		sExp = validateRegExp.decmal1;
	} else if (sType == 'num1') {// 正数（正整数 + 0）
		sExp = validateRegExp.num1;
	} else if (sType == 'intege') {// 整数
		sExp = validateRegExp.intege;
	} else if (sType == 'decmal4') {//非负浮点数（正浮点数 + 0）
		sExp = validateRegExp.decmal4;
	}
	var bSuccess = false;
	if (sExp != null && sExp != '') {
		bSuccess = new RegExp(sExp).test(number)
	}
	return bSuccess;
}

// 刷新验证码
commonJs.resetValidateImage = function(objImgValidate) {
	objImgValidate.setAttribute('src', 'validateCodeAction?ran=' + Math.random());
}

commonJs.reloadPage = function() {
	window.location.href = window.location.href;
}

// 渲染公共值(含有清除空值)
commonJs.renderValue = function(sValue, sType) {
	var arrData = commonJs.getComboValues(sType);
	arrData.shift();
	$.each(arrData, function(i, item) {
		if (item[0] == sValue) {
			sValue = item[1];
			return;
		}
	});
	return commonJs.clearNull(sValue);
};

// 获取select选中的text值
commonJs.getSelectText = function(sSelectId) {
	if (sSelectId == null) { return ''; }
	var sText = $('#' + sSelectId).find('option:selected').text();
	return sText;
}

// 按回车下一个控件
commonJs.enterNext = function(sFormId, funDo) {
	if (sFormId == null || sFormId == '') { return false; }
	$("form[id='" + sFormId + "'] input:not(:hidden),select").keypress(function(e) {
		if (e.which == 13) {// 判断所按是否回车键
			var inputs = $("form[id='" + sFormId + "']").find("input:not(:hidden),select"); // 获取表单中的所有输入框
			var idx = inputs.index(this); // 获取当前焦点输入框所处的位置
			// 跳过控件
			if (inputs[idx+1] != null && $(inputs[idx+1]).attr('class') != null && $(inputs[idx+1]).attr('class').indexOf('select2') > -1) {
				idx++;
			}
			if (idx == inputs.length - 1) {// 判断是否是最后一个输入框
				if (funDo != null) {
					try{ funDo(); }catch(e){ } // 执行传入的函数
				} else {
					inputs[0].focus(); // 设置焦点
					try{ inputs[0].select(); }catch(e){ } // 选中文字
				}
			} else {
				inputs[idx + 1].focus(); // 设置焦点
				try{ inputs[idx + 1].select(); }catch(e){ } // 选中文字
			}
			return false;// 取消默认的提交行为
		}
	});
}

commonJs.getRelImagePath = function(sAbsIamgePath) {
	if (sAbsIamgePath == null || sAbsIamgePath == '') return "";
	if (sAbsIamgePath.indexOf("http") == 0 || sAbsIamgePath.indexOf("https") == 0) {
		return sAbsIamgePath;
	}
	return FILE_PATH + sAbsIamgePath;
}

commonJs.getRelMediaPath = function(sAbsIamgePath) {
	if (sAbsIamgePath == null || sAbsIamgePath == '') return "";
	if (sAbsIamgePath.indexOf("http") == 0 || sAbsIamgePath.indexOf("https") == 0) {
		return sAbsIamgePath;
	}
	return FILE_PATH + sAbsIamgePath;
}

// 初始化iCheck
commonJs.initICheck = function() {
	$('.icheck').each(function() {
		if($(this).data("inited")){
			return;
		}

		var checkboxClass = $(this).attr('data-checkbox') ? $(this).attr('data-checkbox') : 'icheckbox_square-blue';
		var radioClass = $(this).attr('data-radio') ? $(this).attr('data-radio') : 'iradio_square-blue';
		
		if (checkboxClass.indexOf('_line') > -1 || radioClass.indexOf('_line') > -1) {
			$(this).iCheck({
				checkboxClass: checkboxClass,
				radioClass: radioClass,
				insert: '<div class="icheck_line-icon"></div>' + $(this).attr("data-label")
			});
		} else {
			$(this).iCheck({
				checkboxClass: checkboxClass,
				radioClass: radioClass
			});
		}
		$(this).data("inited", true);
	});	
}

// 初始化指定容器内的iCheck
commonJs.initICheckInContainer = function(container){
	container = container || "";
	$(container + " .icheck").iCheck({
		checkboxClass:	'icheckbox_square-blue',
		radioClass:		'iradio_square-blue',
		increaseArea:	'20%'
	});
}

// 全选/反选iCheck（含初始化）
commonJs.checkOrUnCheck = function(sCheckAllId, sCheckName) {
	commonJs.initICheck();
	$('#' + sCheckAllId).on('ifChecked ifUnchecked', function(event) {
		var sState = event.type.replace('if', '').toLowerCase();
		if (sState == 'checked') {
			$('input[name="' + sCheckName + '"]').iCheck('check');
		} else if (sState == 'unchecked') {
			$('input[name="' + sCheckName + '"]').iCheck('uncheck');
		}
	}).data("inited", true);
	$('input[name="' + sCheckName + '"]').on('ifChecked ifUnchecked', function(event) {
		var sState = event.type.replace('if', '').toLowerCase();
		if (sState == 'checked') {
			$('#' + this.id).attr('checked', true);
		} else {
			$('#' + this.id).attr('checked', false);
		}
	}).data("inited", true);	
}

//全选/反选容器内的iCheck（含初始化）
commonJs.checkOrUnCheckInContainer = function(sCheckAllId, sCheckName, sContainer) {
	sContainer = sContainer || "";
	commonJs.initICheckInContainer(sContainer);
	$(sContainer + ' #' + sCheckAllId).on('ifChecked ifUnchecked', function(event) {
		var sState = event.type.replace('if', '').toLowerCase();
		if (sState == 'checked') {
			$(sContainer + ' input[name="' + sCheckName + '"]').iCheck('check');
		} else if (sState == 'unchecked') {
			$(sContainer + ' input[name="' + sCheckName + '"]').iCheck('uncheck');
		}
	});
	$(sContainer + ' input[name="' + sCheckName + '"]').on('ifChecked ifUnchecked', function(event) {
		var sState = event.type.replace('if', '').toLowerCase();
		if (sState == 'checked') {
			$('#' + this.id).attr('checked', true);
		} else {
			$('#' + this.id).attr('checked', false);
		}
	});	
}

//获取表格中已选定行的Id
commonJs.getSelectedRowIds = function(selector){
	var $checked = $(selector + " tbody tr input[type=checkbox]:checked");
	var ids = [];
	$checked.each(function(){
		var id = $(this).parents("tr").data("id");
		if(id){
			ids.push(id);
		}
	});
	return ids;
}

//确认提交AJAX请求
commonJs.confirmSubmit = function(text, url, params, callback, ignoreSuccessMsg){
	commonJs.openConfirmDialog(text, function(btn){
		if(!btn) return;
		commonJs.openAjax(url, params, function(data){
			if(data.success){
				if(ignoreSuccessMsg){
					try { callback(data); } catch(e) {}
				}else{
					commonJs.openDialog("success", data.message, function(){
						try { callback(data); } catch(e) {}
					});
				}
			}else{
				commonJs.openDialog("error", data.message);
			}
		}, commonJs.funError);
	});
}

//提交AJAX请求
commonJs.submitAjax = function(url, params, callback, ignoreSuccessMsg){
	commonJs.openAjax(url, params, function(data){
		if(data.success){
			if(ignoreSuccessMsg){
				try { callback(data); } catch(e) {}
			}else{
				commonJs.openDialog("success", data.message, function(){
					try { callback(data); } catch(e) {}
				});
			}
		}else{
			commonJs.openDialog("error", data.message);
		}
	}, commonJs.funError);
}

commonJs.bindTableEvent = function(selector, events){
	$(selector).click(function(e){
		var $target = $(e.target);
		var event = $target.data("event");
		if(!event){
			event = $target.parent().data("event");
		}
		if(!event){
			return;
		}
		
		var rowId = $target.parents("tr").data("id");
		var result = undefined;
		$.each(events, function(index, item){
			if(item.event === event && item.handler){
				result = item.handler(rowId, $target.parents("tr"));
			}
		});
		return result;
	});
}

commonJs.bindListTableEvent = function(selector, events){
	$(selector).click(function(e){
		var $target = $(e.target);
		var event = $target.data("event");
		if(!event){
			return false;
		}
		
		var rowId = $target.parents("li.lt-row").data("id");
		var result = undefined;
		$.each(events, function(index, item){
			if(item.event === event && item.handler){
				result = item.handler(rowId, $target.parents("li.lt-row"));
			}
		});
		return result;
	});
}

//显示表单验证后的错误信息
commonJs.showInvalidFieldError = function(errorMap, errorList){
	if(errorList.length == 0){
		return;
	}
	
	commonJs.openDialog("warning", errorList[0].message, function(){
		var $element = $(errorList[0].element);
		if($element.is("input[type=text],input[type=password]")){
			$element.focus();
		}
	});
};

//获取表单数据
commonJs.getFormData = function(sSelector){
	var objFormData = $(sSelector).serializeArray();
	var objJsonData = {};
	for(var i in objFormData){
		objJsonData[objFormData[i].name] = objFormData[i].value;
	}
	return objJsonData;
};

//获取某月份的天数
commonJs.getDaysOfMonth = function(iYear, iMonth){
	iYear = (typeof iYear == "number") ? iYear : parseInt(iYear);
	iMonth = (typeof iMonth == "number") ? iMonth : parseInt(iMonth);
	var iDays = 0;
	switch(iMonth){
	case 1:
	case 3:
	case 5:
	case 7:
	case 8:
	case 10:
	case 12:
		iDays = 31; break;
	case 4:
	case 6:
	case 9:
	case 11:
		iDays = 30; break;
	case 2:
		if((iYear%4 == 0 && iYear%100 != 0) || (iYear%400 == 0)){
			iDays = 29;
		}else{
			iDays = 28;
		}
		break;
	}
	return iDays;
}

//获取字符串所占的长度（中文占2个，英文占1个）
commonJs.getTextSize = function(sText){
	if(sText === null || sText === undefined || sText === ""){
		return 0;
	}

	var arrChinese = sText.match(/[^\x00-\x80]/g);
	return sText.length + (arrChinese ? arrChinese.length : 0);
};

//关闭弹出窗口（需传入窗口中的子元素ID）
commonJs.closeDialog = function(children){
	var $dialogs = $("#" + children).parents(".bootstrap-dialog");
	if($dialogs.size() == 0) return;

	var sId = $dialogs.eq(0).attr("id");
	BootstrapDialog.dialogs[sId].close();
};

// 初始化下拉框
commonJs.initSelect = function(sSelectId, sValue){	
	var objSelect = $('.bs-select');
	if (sSelectId != null && sSelectId != '') {
		objSelect = $('#' + sSelectId);
	}
	objSelect.selectpicker({
		iconBase: 'fa',
		tickIcon: 'fa-check',
		noneSelectedText:' ',
		noneResultsText:' ',
		val:sValue
	});
}

//设置下拉框的option新样式(控件的ID, 下拉框公共类型, 是否不显示空项, 选中哪个值)
// 请注意控件命名方式
commonJs.setOption4New = function(sSelectId, sType, bShowNotNullItem, sSelectValue) {
	var divOption = sSelectId.replace('sel','div');
	var selOption = $('#' + divOption);
	var selName = sSelectId;
	var txtName = sSelectId.replace('sel','txt');
	selOption.html('');
	var sHtml = '';
	var arrData = commonJs.getComboValues(sType);
	// 删除数组的第一项
	if (bShowNotNullItem == true) { arrData.shift(); }
	sHtml += '<select class="bs-select form-control" id="'+selName+'">';
	$.each(arrData, function(i, item) {
		if (item == null) { return true; }
		if (item[0] == sSelectValue) {
			sHtml += "<option value='" + item[0] + "' selected>" + item[1] + "</option>";
		} else {
			sHtml += "<option value='" + item[0] + "'>" + item[1] + "</option>";
		}
	});
	sHtml += '</select>';
	selOption.html(sHtml);
	commonJs.initSelect(selName, sSelectValue);
	$('#'+selName).bind('change', function(e) {
		$('#'+txtName).val($('#'+selName).val());
	}).trigger("change");
};

//获取验证失败时的提示信息
//sName: 字段名称
//sType: 字段类型(text, select, max, min, date)
//可选参数：用指定max、min的值
commonJs.getValidateMessage = function(sName, sType){
	sName = "“" + sName + "”";
	switch(sType){
		case "text":
			return sName + "不能为空，请重新输入！";
		case "textarea":
			return sName + "不能为空，请重新输入！";
		case "select":
			return sName + "不能为空，请重新选择！";
		case "max":
			if(arguments.length > 2){
				var max = parseInt(arguments[2]);
				return sName + "不能超过" + max + "个字符或" + (max / 2) + "个汉字，请重新输入！";
			}else{
				return sName + "过长，请重新输入！";
			}
		case "min":
			if(arguments.length > 2){
				var max = parseInt(arguments[2]);
				return sName + "不能少于" + max + "个字符或" + (max / 2) + "个汉字，请重新输入！";
			}else{
				return sName + "过短，请重新输入！";
			}
		case "date":
			return sName + "不是有效的日期，请重新输入！";
		case "number":
			return sName + "不是有效的数字，请重新输入！";
		case "mobilephone":
			return sName + "不是有效的手机号码，请重新输入！";
		case "telephone":
			return sName + "不是有效的电话号码，请重新输入！";
		case "email":
			return sName + "不是有效的邮箱地址，请重新输入！";
		case "idcard":
			return sName + "不是有效的身份证号码，请重新输入！";
		case "minnum":
			return sName + "不能为0或小于0，请重新输入！";
		default:
			return sName + "有误，请重新输入！";
	}
};

commonJs.getMoneyUppercase = function(n){  
	var fraction = ['角', '分'];
	var digit = ['零', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖'];
	var unit = [ ['元', '万', '亿'], ['', '拾', '佰', '仟']  ];
	var head = n < 0? '欠': '';
	n = Math.abs(n);
          
	var s = '';
          
	for (var i = 0; i < fraction.length; i++){
		s += (digit[Math.floor(n * 10 * Math.pow(10, i)) % 10] + fraction[i]).replace(/零./, '');
	}
	s = s || '整';
	n = Math.floor(n);

	for (var i = 0; i < unit[0].length && n > 0; i++){
		var p = '';
		for (var j = 0; j < unit[1].length && n > 0; j++){
			p = digit[n % 10] + unit[1][j] + p;
			n = Math.floor(n / 10);
		}
		s = p.replace(/(零.)*零$/, '').replace(/^$/, '零')  + unit[0][i] + s;
	}
	return head + s.replace(/(零.)*零元/, '元').replace(/(零.)+/g, '零').replace(/^整$/, '零元整');
}

// 浮点型或整形（当数值的小数位不是0就显示小数位，否则就不显示）
commonJs.doubleOrLongValue = function(dValue) {
	dValue = dValue + '';
	try {
		if (dValue != null && dValue.indexOf(".") > -1) {
			var sTemp = dValue.substring(dValue.indexOf(".") + 1, dValue.length);
			if (sTemp == "00") {
				dValue = dValue.substring(0, dValue.length - 3);
			} else if (sTemp == "0") {
				dValue = dValue.substring(0, dValue.length - 2);
			} else if (sTemp.length == 2) {
				sTemp = sTemp.substring(1, 2);
				if (sTemp == "0") {
					dValue = dValue.substring(0, dValue.length - 1);
				}
			}
		}
	} catch(e) {}
	return dValue;
}

// 获取年龄  birthday 出生日期，type 1：实龄 2：虚龄
commonJs.getAgeByBirthday = function(birthday, type) {
	var sAge = "";
	if (birthday == null || birthday == '') return "";
	try {
		var age = 0;
		var sYear = "", sMonth = "", sDay = "";
		if (birthday.length < 4) {
			return sAge;
		} else {
			sYear = birthday.substring(0, 4);
		}
		if (birthday.length >= 7) {
			sMonth = birthday.substring(5, 7);
		}
		if (birthday.length >= 10) {
			sDay = birthday.substring(8, 10);
		}
		var iYear = 0, iMonth = 0, iDay = 0;
		if (sYear != '') {
			iYear = parseInt(sYear);
		}
		if (sMonth != '') {
			iMonth = parseInt(sMonth);
		}
		if (sDay != '') {
			iDay = parseInt(sDay);
		}
		var myDate = new Date();
		var nowYear = myDate.getFullYear();    //获取完整的年份(4位,1970-????)
		var nowMonth = myDate.getMonth()+1;       //获取当前月份(0-11,0代表1月)
		var nowDay = myDate.getDate();        //获取当前日(1-31)
		if (iYear != 0) {
			age = nowYear - iYear;
		}
		if (1 == type) {// 1：实龄
			if (iMonth > nowMonth || (iMonth == nowMonth && iDay > nowDay)) {
				age -= 1;
			}
		}
		sAge = age;
	} catch (e) {
		// System.out.println("获取年龄异常=" + e.getMessage());
	}
	return sAge;
}

commonJs.getUrlParam = function(sName){
	var reg = new RegExp("(^|&)"+ sName +"=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if(r!=null){
		return unescape(r[2]);
	}else{
		return null;
	}
};

commonJs.initTableEnterEvent = function(sContainer, funAddRow, iSkip, arrCopy, sCondition){
	$(sContainer).keypress(function(e){
		var code = e.keyCode ? e.keyCode : e.which;
		if(code != 13){
			return;
		}

		var $target = $(e.target);
		if(!$target.is("input:text") && !$target.is("select")){
			return;
		}

		var $td = $target.parents("td:first");
		var $next = $td.next("td");
		while($next.size() != 0){
			var $input = $next.find("input:text,select");
			if($input.size() != 0){
				$input.focus().select();
				return false;
			}
			$next = $next.next("td");
		}
		
		var $tr = $td.parent("tr");
		var $nextTr = $tr.next("tr");
		if($nextTr.size() == 0){
			funAddRow(e);
			$nextTr = $tr.next("tr");
			arrCopy = arrCopy || [];
			$.each(arrCopy, function(index, item){
				var sSource = $tr.find(item).val();
				$nextTr.find(item).val(sSource);
			});
		}
		if(sCondition && $nextTr.is(sCondition)){
			$nextTr.find("input:first").focus().select();
		}else{
			var iIndex = (iSkip == undefined || iSkip == null) ? 0 : iSkip;
			$nextTr.find("td:eq(" + iIndex + ")>*").focus().select();
		}
		return false;
	});
};

/*
 * 初始化下拉框
 * selector: 元素选择器
 * url: 请求地址
 * data: 请求参数(JSON对象)
 * headerText: 第一个选项的文本（默认：--请选择--）
 * headerValue: 第一个选项的值（默认：[空字符]）
 * textField: 用作显示文本的字段
 * valueField: 用作值的字段
 * defaultValue: 默认值
 * showHeader: 是否显示第一个选项（默认：true）
 * isClear: 是否清空元素（默认：true）
 * hasLoading: 是否显示加载动画（默认：false）
 */
commonJs.initCombobox = function(opts){
	var defaults = {
		data: {},
		headerText: "--请选择--",
		headerValue: "",
		showHeader: true,
		isClear: true,
		hasLoading: false,
		complete: function(){}
	};
	opts = $.extend(defaults, opts);
	opts.data["hasLoading"] = opts.hasLoading;
	commonJs.openAjax(opts.url, opts.data, function(data){
		if(!data.success){
			commonJs.openDialog("error", data.message);
			return;
		}
		var lstData = data.lstResult || [];
		var $list = $(opts.selector);
		opts.defaultValue = opts.defaultValue || $list.data("value");
		$list.data("value", null);

		if(opts.isClear) $list.empty();
		if(opts.showHeader) $list.append('<option value="' + opts.headerValue + '">' + opts.headerText + '</option>');

		var trigger = false;
		$.each(lstData, function(index, item){
			var $option = $("<option></option>").attr("value", item[opts.valueField]).text(item[opts.textField]);
			$option.data("raw", item);
			if(opts.defaultValue == item[opts.valueField]){
				$option.prop("selected", true);
				trigger = true;
			}
			$list.append($option);
		});
		if(trigger) $list.trigger("change");
		opts.complete();
	}, commonJs.funError);
};

commonJs.initCascadingCombobox = function(opts){
	if(!opts.selectors || opts.selectors.length == 0) return;
	var selectors = opts.selectors;
	delete opts.selectors;

	var first = { selector: selectors[0] };
	first = $.extend(opts, first);
	commonJs.initCombobox(first);

	for(var i=0; i<selectors.length - 1; i++){
		var opt = { selector: selectors[i + 1] };
		opt = $.extend(opts, opt);
		$(selectors[i]).data(opt).change(function(){
			var value = $(this).val();
			var cascadeOpt = $(this).data();
			$(cascadeOpt.selector).empty().trigger("change");
			if(value){
				var data = cascadeOpt.data || {};
				data[cascadeOpt.cascade] = value;
				cascadeOpt.data = data;
				commonJs.initCombobox(cascadeOpt);
			}
		});
		
	}
};

// 将字符串转换成json对象(要配合<u:common jsonValue标签)
commonJs.toJson = function(sStr){
	if (sStr != null && sStr != '') {
		sStr = sStr.replace(/`/g, "'");// 替换字符串中的特殊字符
		sStr = sStr.replace(/＼/g, "\\");// 替换字符串中的特殊字符
		return eval('(' + sStr + ')');
	}
	return null;
};

commonJs.getPageSizeOfPrinter = function(LODOP, sPrinter, sType){
	if(!sPrinter || !sType) return "";
	
	var sPageSize = LODOP.GET_PAGESIZES_LIST(sPrinter, "\n");
	var arrPageSize = sPageSize.split("\n");
	var sResult = "";
	$.each(arrPageSize, function(i, item){
		if(item.indexOf(sType) >= 0){
			sResult = item;
			return false;
		}
	});
	return sResult;
};

// 打开支付窗口alipay-支付宝,wxpay-微信支付
// par.jumpType:1-门户,2-手机
commonJs.openPaymentWindow = function(par) {
	if (par.orderNumber == null || par.orderNumber == '') {
		commonJs.openDialog('error', '提交付款请求失败，请传入足够的参数！');
		return false;
	}
	var sUrl = BASE_PATH;
	var isMobile = false;
	try {
		var u = navigator.userAgent;
		isMobile = !!u.match(/AppleWebKit.*Mobile.*/) || !!u.match(/AppleWebKit/);
	} catch(e) { isMobile = false; }
	if (isMobile) isMobile = commonJs.getMobileWidth();
	if (par.isMobile != null && (par.isMobile == true || par.isMobile == false)) isMobile = par.isMobile;
	if (par.isCustomerPay == null || par.isCustomerPay == '') isCustomerPay = false;
	if (par.payType == 'alipay') {
		if (isMobile) {
			sUrl += '/payment/alipay/alipaywap.jsp?order_no=' + par.orderNumber;
			if (par.orderType != null && par.orderType != '') sUrl += '&order_type=' + par.orderType;
			if (par.jumpType != null && par.jumpType != '') sUrl += '&jump_type=' + par.jumpType;
			window.location.href = sUrl;
		} else {
			sUrl += '/payment/alipay/alipayapi.jsp?out_trade_no=' + par.orderNumber;
			if (par.orderType != null && par.orderType != '') sUrl += '&order_type=' + par.orderType;
			window.open(sUrl);
		}
	} else if (par.payType == 'wxpay') {
		if (isMobile && !par.isCustomerPay) {
			/*sUrl += '/payment/wxpay/wxpaywap.jsp?showwxpaytitle=1&order_no=' + par.orderNumber;
			if (par.orderType != null && par.orderType != '') sUrl += '&order_type=' + par.orderType;
			if (par.jumpType != null && par.jumpType != '') sUrl += '&jump_type=' + par.jumpType;*/
			sUrl = 'https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx5b549fe34f84c2b6&redirect_uri=http%3A%2F%2Frichqxl.imwork.net%2Fcommon%2Foauth2.jsp&response_type=code&scope=snsapi_base&state=0@@2@@gh_498ec102bb87@@'+par.orderNumber;
			sUrl += '@@' + commonJs.clearNull(par.orderType, "-999");
			sUrl += '@@' + commonJs.clearNull(par.jumpType, "-999");
			sUrl += '#wechat_redirect';
			window.location.href = sUrl;
		} else {
			sUrl += '/payment/wxpay/wxpayapi.jsp?out_trade_no=' + par.orderNumber;
			if (par.orderType != null && par.orderType != '') sUrl += '&order_type=' + par.orderType;
			window.open(sUrl);
		}
	} else {
		commonJs.openDialog('info', '该付款方式即将开放，敬请期待！');
		return false;
	}
	// 弹出付款后续窗口
	if (par.payType == 'alipay' || par.payType == 'wxpay') {
		commonJs.openPageDialog({
			id:			"diaPaymentConfirm"
			,title: 	"网上付款"
			,lg: 		false
			,ajaxOpen:	true
			,url: 		BASE_PATH + "/payment/confirm_dialog.jsp"
			,buttons: 	[]
		});
	}
};

// 将textarea的内容转换为html
commonJs.renderTextarea = function(sContent) {
	if (sContent != '') {
		sContent = sContent.replace(/ /g, "&ensp;");
		sContent = sContent.replace(/\r\n/g, "<br/>");
		sContent = sContent.replace(/\n/g, "<br/>");
	} else {
		sContent = '';
	}
	return sContent;
};

//初始化html文本编辑器 (id - teaxarea的id, height - 定义编辑器的高度)
commonJs.initCKEDITOR = function(id, height){
	var sHeight = 100;
	if(height) sHeight = height;
	CKEDITOR.replace(id,{
		toolbarCanCollapse: true
		,toolbarStartupExpanded: false
		,height: sHeight +'px'
	});
};

// 获取html文本编辑器的数据 (id - teaxarea的id)
commonJs.getCKEDITORData = function(id){
	if(!id) return false;
	if(CKEDITOR.instances[id]){
		return CKEDITOR.instances[id].getData();
	}else{
		return $("#"+id).val();
	}
};
