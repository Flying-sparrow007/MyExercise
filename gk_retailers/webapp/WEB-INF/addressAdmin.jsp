<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>我的订单 - 益康特学子商城</title>
    <link href="../css/orders.css" rel="stylesheet"/>
    <link href="../css/header.css" rel="stylesheet"/>
    <link href="../css/footer.css" rel="stylesheet"/>
    <link href="../css/personage.css" rel="stylesheet" />
    <link href="../css/footTxt.css" rel="Stylesheet"/>
</head>
<body>
<!-- 页面顶部-->
<%@ include file="header.jsp" %>
<!-- nav主导航-->
<nav id="nav">
    <ul>
        <li><a href="index.html" class="acti">首页</a></li>
        <li><a href="index.html#computer" >电脑办公</a></li>
        <li><a href="index.html#stationery" >办公文具</a></li>
    </ul>
</nav>
<!-- 我的订单导航栏-->
<div id="nav_order">
    <ul>
        <li><a href="">首页<span>&gt;</span>个人中心</a></li>
    </ul>
</div>
<!--我的订单内容区域 #container-->
<div id="container" class="clearfix">
    <!-- 左边栏-->
    <div id="leftsidebar_box" class="lf">
        <div class="line"></div>
        <dl class="my_order">
            <dt >我的订单
                <img src="../images/myOrder/myOrder2.png">
            </dt>
            <dd class="first_dd"><a href="orders.html">全部订单</a></dd>
            <dd>
                <a href="#">
                    待付款
                    <span><!--待付款数量--></span>
                </a>
            </dd>
            <dd>
                <a href="#">
                    待收货
                    <span><!--待收货数量-->1</span>
                </a>
            </dd>
            <dd>
                <a href="#">
                    待评价<span><!--待评价数量--></span>
                </a>
            </dd>
            <dd>
                <a href="#">退货退款</a>
            </dd>
        </dl>

        <dl class="footMark">
            <dt >我的优惠卷<img src="../images/myOrder/myOrder1.png"></dt>
        </dl>
        <dl class="address">
                <dt>收货地址<img src="../images/myOrder/myOrder1.png"></dt>
				<dd><a href="addressAdmin.html">地址管理</a></dd>
        </dl>
       <dl class="count_managment">
            <dt >帐号管理<img src="../images/myOrder/myOrder1.png"></dt>
            <dd class="first_dd"><a href="personage.html">我的信息</a></dd>
            <dd><a href="personal_icon.html">个人头像</a></dd>
            <dd><a href="personal_password.html">安全管理</a></dd>
        </dl>
    </div>
   <!-- 右边栏-->
    <div class="rightsidebar_box rt">	
        <!--标题栏-->
        <div class="rs_header">
            <span class="address_title">收获地址管理</span>
        </div>
        <!--收货人信息填写栏-->
        <div class="rs_content">
        	<form id="address_form" method="post" action="">
	            <!--收货人姓名-->
	            <div class="recipients">
	                <span class="red">*</span><span class="kuan">收货人：</span><input type="text" name="recvUsername" id="recvUsername"/>
	            </div>
	            <!--收货人所在城市等信息-->
	            <div data-toggle="distpickers" class="address_content">
					 <span class="red">*</span><span class="kuan">省&nbsp;&nbsp;份：</span><select name="recvProvincecode" id="recvProvincecode" onchange="getCities(this.value, -1, -1)"></select>
					  城市：<select name="recvCitycode" id="recvCitycode" onchange="getArea(this.value, -1)"></select>
					  区/县：<select name="recvAreacode" id="recvAreacode"></select>
				</div> 
	            
	            <!-- 存储id的隐藏标签 -->
	            <input type="hidden" name="id" id="aid" />
	            
	            <!--收货人详细地址-->
	            <div class="address_particular">
	                <span class="red">*</span><span class="kuan">详细地址：</span><textarea name="recvAddress" id="receiverAddress" cols="60" rows="3" placeholder="建议您如实填写详细收货地址"></textarea>
	            </div>
	            <!--收货人地址-->
	            <div class="address_tel">
	                <span class="red">*</span><span class="kuan">手机号码：</span><input type="tel" id="recvPhone" name="recvPhone"/>固定电话：<input type="text" name="recvTel" id="recvTel"/>
	            </div>
	            <!--邮政编码-->
	            <div class="address_postcode">
	                <span class="red">&nbsp;</span class="kuan"><span>邮政编码：</span>&nbsp;<input type="text" id="recvZip" name="recvZip"/>
	            </div>
	            <!--地址名称-->
	            <div class="address_name">
	                <span class="red">&nbsp;</span class="kuan"><span>地址名称：</span>&nbsp;<input type="text" id="recvTag" name="recvTag"/>如：<span class="sp">家</span><span class="sp">公司</span><span class="sp">宿舍</span>
	            </div>
	            <!--保存收货人信息-->
	            <div class="save_recipient">
	                保存收货人信息
	            </div>
	
    		</form>
            <!--已有地址栏-->
            <div class="aim_title">
                <span class="dzmc dzmc_title">地址名称</span><span class="dzxm dzxm_title">姓名</span><span class="dzxq dzxq_title">地址详情</span><span class="lxdh lxdh_title">联系电话</span><span class="operation operation_title">操作</span>
            </div>
            <div class="address_information_manage">
				<!-- <div class="aim_content_one aim_active">
                    <span class="dzmc dzmc_active">办公室</span>
                    <span class="dzxm dzxm_normal">杨洋</span>
                    <span class="dzxq dzxq_normal">西安市曲江旺座H座307</span>
                    <span class="lxdh lxdh_normal">18435110514</span>
                    <span class="operation operation_normal">
                    	<span class="aco_change">修改</span>|<span class="aco_delete">删除</span>
                    </span>
                    <span class="swmr swmr_normal"></span>
                </div>
                <div class="aim_content_two">
                    <span class="dzmc dzmc_normal">家里</span>
                    <span class="dzxm dzxm_normal">杨洋</span>
                    <span class="dzxq dzxq_normal">曲江旺座</span>
                    <span class="lxdh lxdh_normal">13788882346</span>
                    <span class="operation operation_normal">
                    	<span class="aco_change">修改</span>|<span class="aco_delete">删除</span>
                    </span>
                    <span class="swmr swmr_normal">设为默认</span>
                </div>
                <div class="aim_content_three">
                    <span class="dzmc dzmc_normal">宿舍</span>
                    <span class="dzxm dzxm_normal">杨洋</span>
                    <span class="dzxq dzxq_normal">山西省小店区山西大学商务学院</span>
                    <span class="lxdh lxdh_normal">13799992347</span>
                    <span class="operation operation_normal">
                    	<span class="aco_change">修改</span>|<span class="aco_delete">删除</span>
                    </span>
                    <span class="swmr swmr_normal">设为默认</span>
                </div> -->
                
            </div>
        </div>
    </div>
</div>

<!-- 品质保障，私人定制等-->
<div id="foot_box">
    <div class="icon1 lf">
        <img src="../images/footer/icon1.png" alt=""/>

        <h3>品质保障</h3>
    </div>
    <div class="icon2 lf">
        <img src="../images/footer/icon2.png" alt=""/>

        <h3>私人定制</h3>
    </div>
    <div class="icon3 lf">
        <img src="../images/footer/icon3.png" alt=""/>

        <h3>学员特供</h3>
    </div>
    <div class="icon4 lf">
        <img src="../images/footer/icon4.png" alt=""/>

        <h3>专属特权</h3>
    </div>
</div>
<!-- 页面底部-->
<%@ include file="footer.jsp" %>
</body>
<script type="text/javascript" src="../js/jquery-3.1.1.min.js"></script>
<script src="../js/jquery.page.js"></script>
<script type="text/javascript" src="../js/orders.js"></script>
<script type="text/javascript" src="../js/distpicker.data.js"></script>
<script type="text/javascript" src="../js/distpicker.js"></script>
<script type="text/javascript" src="../js/personal.js"></script>
<script src="../js/footTxt.js"></script>
<script type="text/javascript">
	$(".lxdh_normal").each(function(i,e) {
		var phone = $(e).html();
		$(e).html(changePhone(phone));
	});
</script>
<script type="text/javascript">

	var aid;//地址id

	//当页面加载完毕执行
	$(function(){
		//获取省份	陕西省610000,西安市610100,蓝田县610122
		getProvinceList("-1", "-1", "-1");
		//加载地址栏所有信息
		getAllAddress();
	});
	
	//获取省份
	function getProvinceList(provinceCode, cityCode, areaCode){
		var url = "${basePath}/address/getProvinceList.do";
		$("#recvProvincecode").html("<option>-请选择-</option>");
		$.get(url, function(result){
			if(result.state == 1){
				var p = result.data;
				//回显所有省份再下拉选中
				for(var i in p){
					var name = p[i].provinceName;
					var code = p[i].provinceCode;
					var option = new Option(name, code);
					$("#recvProvincecode").append(option);
				}
				if(provinceCode != -1){
					$("#recvProvincecode").val(provinceCode);
				}
				getCities(provinceCode, cityCode, areaCode);
			}else{
				alert("数据异常!");
			}
		});
	}
	
	//根据省的Code获取市
	function getCities(provinceCode, cityCode, areaCode){
		//清空市标签的内容,同时添加"-请选择-"
		$("#recvCitycode").html("<option>-请选择-</option>");
		
		var url = "${basePath}/address/getCitiesList.do";
		var params = {"provinceCode": provinceCode};
		$.post(url, params, function(result){
			if(result.state == 1){
				//获取所有城市
				var data = result.data;
				//渲染数据
				for(var i in data){
					var name = data[i].cityName;
					var code = data[i].cityCode;
					var option = new Option(name, code);
					$("#recvCitycode").append(option);
				}
				if(cityCode != -1){
					$("#recvCitycode").val(cityCode);
				}
				getArea(cityCode, areaCode);
			}else{
				alert("查询数据失败!");
			}
		});
	}
	
	//根据市的Code获取区/县
	function getArea(cityCode, areaCode){
		//清空区域标签的内容
		$("#recvAreacode").html("<option>-请选择-</option>");
		
		var url = "${basePath}/address/getAreasList.do";
		var params = {"cityCode": cityCode};
		$.post(url, params, function(result){
			if(result.state == 1){
				//获取所有区/县
				var data = result.data;
				//渲染数据
				for(var i in data){
					var name = data[i].areaName;
					var code = data[i].areaCode;
					var option = new Option(name, code);
					$("#recvAreacode").append(option);
				}
				if(areaCode != -1){
					$("#recvAreacode").val(areaCode);
				}
			}else{
				alert("数据异常");
			}
			
		});
	}
	
	//保存/修改收货人信息
	$(".save_recipient").click(function(){
		var receiverName = $("#recvUsername").val();// 收件人
		var receiverState = $("#recvProvincecode").val();// 省
		var receiverCity = $("#recvCitycode").val();// 市
		var receiverDistrict = $("#recvAreacode").val();// 区/县
		var receiverAddress = $("#receiverAddress").val();// 详细地址
		var receiverMobile = $("#recvPhone").val();// 手机
		if(receiverName && receiverState && receiverCity && receiverDistrict && receiverAddress && receiverMobile){
			var data = $("#address_form").serialize();
			if(aid){
				//data = data + "&id=" + aid;//方法一,方法二通过隐藏的input标签提交id
				var url = "${basePath}/address/updateAddressById.do";
			}else{
				var url = "${basePath}/address/addAddress.do";
			}
			$.post(url, data, function(result){
				if(result.state == 1){
					alert("保存成功!");
					//显示在地址栏上
					getAllAddress();
					//清空表单
					//clearForm();
					location.href = "${basePath}/address/toAddressHtml.do";//清空表单也可以是重新访问一下该页面
				}else{
					alert(result.message);
				}
			});
		}else{
			alert("请将必填信息填写完整");
		}
	});
	
	//加载地址栏所有信息
	function getAllAddress(){
		//清空class名为address_information_manage的div
		$(".address_information_manage").empty();
		var url = "${basePath}/address/getAllAddress.do";
		$.post(url, function(result){
			if(result.state == 1){
				//如果地址的isDefault为1,则是已设置的默认地址,否则为未设置的默认地址
				var addressArr = result.data;//获取所查询的用户数据
				for(var i in addressArr){
					
					//隐藏电话号码中间的四位  例183****6854
					var phone = addressArr[i].recvPhone;
					var hiddinPhone = phone.substr(0, 3) + "*****" + phone.substr(7, 11);
					
					if(addressArr[i].isDefault == 1){//设置	已设置默认
						$(".address_information_manage").append(
								addressTemp1.replace("[recvTag]", addressArr[i].recvTag)
											.replace("[recvUserName]", addressArr[i].recvUsername)
											.replace("[recvDistrict]", addressArr[i].recvDistrict)
											.replace("[recvPhone]", hiddinPhone)
											.replace("[goUpdate()]", "goUpdate( "+ addressArr[i].id +" )")
											.replace("[goDelete()]", "goDelete(" + addressArr[i].id + ")")
								);
					}else{//设置	设为默认
						$(".address_information_manage").append(
								addressTemp2.replace("[recvTag]", addressArr[i].recvTag)
											.replace("[recvUserName]", addressArr[i].recvUsername)
											.replace("[recvDistrict]", addressArr[i].recvDistrict)
											.replace("[recvPhone]", hiddinPhone)
											.replace("[goUpdate()]", "goUpdate( "+ addressArr[i].id +" )")
											.replace("[goDelete()]", "goDelete(" + addressArr[i].id + ")")
											.replace("[isDefault()]", "isDefault(" + addressArr[i].id + ")")
								);
					}
				}
			}else{
				alert(result.message);
			}
		});
	}
	
	//根据id查询地址信息
	function goUpdate(id){
		aid = id;
		if(aid){
			$(".save_recipient").html("修改收货人信息");
		}
		var url = "${basePath}/address/goUpdateUserAddress.do";
		var params = {"id": id};
		$.post(url, params, function(result){
			if(result.state == 1){
				//把查询的数据回显在form表中
				$("#recvUsername").val(result.data.recvUsername);
				$("#receiverAddress").val(result.data.recvAddress);
				$("#recvPhone").val(result.data.recvPhone);
				$("#recvTel").val(result.data.recvTel);
				$("#recvZip").val(result.data.recvZip);
				$("#recvTag").val(result.data.recvTag);
				$("#aid").val(result.data.id);
				//获取省的编号
				var provinceCode = result.data.recvProvincecode;
				//获取市的编号
				var cityCode = result.data.recvCitycode;
				//获取县/区的编号
				var areaCode = result.data.recvAreacode;
				//设置省份,城市,区/县
				getProvinceList(provinceCode, cityCode, areaCode);
			}else{
				alert(result.message);
			}
		});
	}
	
	//设置地址的默认地址
	function isDefault(id){
		var url = "${basePath}/address/updateIsDefaultById.do";
		var params = {"id": id};
		$.post(url, params, function(result){
			if(result.state == 1){
				//刷新地址栏信息
				getAllAddress();
			}else{
				alert(result.message);
			}
		});
	}
	
	//清空form表单
	function clearForm(){
		$("#recvUsername").val("");//用户名
		getProvinceList("-1", "-1", "-1");//省市区
		$("#aid").val("");//地址id
		$("#receiverAddress").val("");//详细地址
		$("#recvPhone").val("");//手机
		$("#recvTel").val("");//电话
		$("#recvZip").val("");//邮政编码
		$("#recvTag").val("");//地址名称
		
		aid = null;//删除地址id
	}
	
	//address模板(已设置默认)
	var addressTemp1 = '<div class="aim_content_one aim_active">'
	        			+ '<span class="dzmc dzmc_active">[recvTag]</span>'
	        			+ '<span class="dzxm dzxm_normal">[recvUserName]</span>'
	        			+ '<span class="dzxq dzxq_normal">[recvDistrict]</span>'
	        			+ '<span class="lxdh lxdh_normal">[recvPhone]</span>'
	        			+ '<span class="operation operation_normal">'
	        				+ '<span class="aco_change" onclick="[goUpdate()]">修改</span>|<span class="aco_delete" onclick="[goDelete()]">删除</span>'
	        			+ '</span>'
	        			+ '<span class="swmr swmr_normal" style="background: #2ea8ee;">已设置默认</span>'
    				+ '</div>';
    //address模板(未设置默认)
    var addressTemp2 = '<div class="aim_content_two">'
	    				+ '<span class="dzmc dzmc_normal">[recvTag]</span>'
	    				+ '<span class="dzxm dzxm_normal">[recvUserName]</span>'
	    				+ '<span class="dzxq dzxq_normal">[recvDistrict]</span>'
	    				+ '<span class="lxdh lxdh_normal">[recvPhone]</span>'
	    				+ '<span class="operation operation_normal">'
	    					+ '<span class="aco_change" onclick="[goUpdate()]">修改</span>|<span class="aco_delete"onclick="[goDelete()]">删除</span>'
	    				+ '</span>'
	    				+ '<span class="swmr swmr_normal" onclick="[isDefault()]" style="cursor: pointer;">设为默认</span>'
					+ '</div>';
</script>
</html>