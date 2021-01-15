<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<meta charset="UTF-8">
<title>商品搜索页面</title>
<link rel="stylesheet" href="../css/header.css" />
<link rel="stylesheet" href="../css/search.css" />
<link rel="stylesheet" href="../css/footer.css" />
<link href="../css/footTxt.css" rel="Stylesheet"/>
</head>
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

<body>
	<div class="big">
		<form name="" action="" method="post">
			<section id="section">
				<p class="header">全部结果>笔记本</p>
				<div id="wrap">
					<c:forEach items="${map.goods}" var="g">
						<div class="lf box" id="d1">
							<div class="info">
								<div class="img pic">
									<img src="${basePath}${g.image}" alt="" title="${g.title}" onclick="toItemInfo(${g.id})" />
								</div>			
								<div class="describe">
									<p onclick="toItemInfo(${g.id})">${g.title}</p>
									<span class="price"><b>￥</b><span class="priceContent">${g.price}.00</span></span>
									<span class="addCart"><img id="collect" src="../images/search/care.png" alt="" /><a href="javascript:void(0);" class="add_cart">加入购物车</a></span>
									<!--<span class="succee" style="display: none"> 
										<img src="/images/search/product_true.png" alt="" /> 
										<span>已移入购物车</span>
									</span>-->
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
			</section>
		</form>
	</div>
	<!-- 分页 -->
	<div style="text-align: center;">
		<a href="${basePath}/goods/topPage.do?pageCurrent=${map.pageCurrent}">上一页</a>
		<c:forEach begin="1" end="${map.pageCount}" var="p">
			<c:if test="${p == map.pageCurrent}">
				<a href="${basePath}/goods/getPage.do?pageCurrent=${p}" style="color: blue; text-decoration: none;">${p}</a>
			</c:if>
			<c:if test="${p != map.pageCurrent}">
				<a href="${basePath}/goods/getPage.do?pageCurrent=${p}" style="color: #ccc;">${p}</a>
			</c:if>
		</c:forEach>
		<a href="${basePath}/goods/nextPage.do?pageCurrent=${map.pageCurrent}">下一页</a>
		<span>总页数:${map.pageCount}</span>
	</div>
	<!-- 尾部-->
<!-- 页面底部-->
<%@ include file="footer.jsp" %>
<div class="modal" style="display:none">
    <div class="modal_dialog">
        <div class="modal_header">
            操作提醒
        </div>
        <div class="modal_information">
            <img src="../images/model/model_img2.png" alt=""/>
            <span>将您的宝贝加入购物车？</span>

        </div>
        <div class="yes"><span>确定</span></div>
        <div class="no"><span>取消</span></div>
    </div>
</div>
	<script src="../js/jquery-3.1.1.min.js"></script>
	<script src="../js/index.js"></script>
	<script src="../js/jquery.page.js"></script>
	<script>
	$(".add_cart").click(function(){
		$(".modal").show();
		$(".modal .modal_information span").html("将您的宝贝加入购物车?");
	})
	$(".yes").click(function(){
	    $(".modal").hide();
	})
	$('.no').click(function(){
    	$('.modal').hide();
    	
    })
</script>
    <!--<script type="text/javascript">
	// var status = ${status};
	var pages = ${pageBean.totalPages};
	var index = ${pageBean.pageIndex};
	$(".tcdPageCode").createPage({
		// 总页数
	    pageCount:pages,
	 	// 起始页
	    current:index,
	    backFn:function(p){
	    	// 执行代码
	    	window.location.href="http://localhost:18888/search.html?q=${q}&page="+p;
	    }
	});
</script>-->
<script type="text/javascript">
    /* 商品详情页  */
	function toItemInfo(id) {
		if (id) {
			window.location.href="${basePath}/goods/goodsDetail.do?id=" + id;
		}else {
			alert("商品id不存在");
		}
	} 
</script>
<script type="text/javascript">
	/**添加到收藏**/
    $("#collect").click(function(e){
    	$(".modal").show();
		$(".modal .modal_information span").html("将您的宝贝加入收藏夹");
    })
    $(".yes").click(function(){
	    $(".modal").hide();
	    $('#collect').attr("src","../images/search/care1.png");
    })
</script>
<script type="text/javascript">
	/* 分页查询 */
	function upPage(){
		
	}
</script>
<script src="../js/footTxt.js"></script>
</body>
</html>