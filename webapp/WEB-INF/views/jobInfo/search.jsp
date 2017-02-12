<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>职位信息搜索</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta name="author" content="">

	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"/>
	<style type="text/css">
		body {
		    padding-top: 20px;
		    padding-bottom: 40px;
		  }
		
		  .container-narrow {
		    margin: 0 auto;
		    max-width: 900px;
		  }
		  .container-narrow > hr {
		    margin: 30px 0;
		  }
		
		  .jumbotron {
		    margin: 60px 0;
		    text-align: center;
		  }
		  .jumbotron h1 {
		    font-size: 72px;
		    line-height: 1;
		  }
		  .jumbotron .btn {
		    font-size: 21px;
		    padding: 14px 24px;
		  }
		
		  .marketing {
		    margin: 60px 0;
		  }
		  .marketing p + h4 {
		    margin-top: 28px;
		  }
		  #searchDiv{
		  	text-align: center;
		  }
		 
		  ul#jobUl li{
		  	padding: 30px;
		  }
		  ul#jobUl li div{
		  	margin-bottom: 10px;
		  }
		  ul#jobUl li:nth-child(odd)
	      {
		    background:rgb(248,248,248);
		  }
		  .jobHeader{
		  	font-family: '微软雅黑';
		  	font-size: 20px;
		  }
		  .jobAttr{
		  	font-family: '微软雅黑';
		  	font-size: 15px;
		  	color: #888888;
		  	margin-right: 5px;
		  }
		  .jobAttrVal{
		  	font-family: "微软雅黑";
		  	font-size: 15px;
		  	margin-right: 12px;
		  }
		  #jobList{
		  	padding-left: 30px;
		  }
		  form{
		  	font-family: "微软雅黑";
		  	font-size: 18px;
		  	color: #777777;
		  }
		  form div{
		  	margin: 10px;
		  }
	</style>
	<script type="text/javascript">
		function goPage(page) {
			document.getElementById("currentPage").value = page;
			document.forms[0].submit();
		}
		function selectChanged() {
			document.getElementById("currentPage").value = 1;
		}
		function goNPage(totalPage){
			console.debug(totalPage);
			var toPage=document.getElementById("appendedInputButton").value;
			if(isNaN(toPage)){
				alert("请输入数值!");
				return;
			}
			document.getElementById("currentPage").value=Math.min(toPage,totalPage);
			document.forms[0].submit();
		}
	</script>
</head>
<body>
	<div class="container-narrow">
		<div class="masthead">
			<ul class="nav nav-pills pull-right">
				<li style="padding-top: 25px;"><span style="font-weight:bold;"><a href="index.html">首页</a></span>——The reason is searching for success</li>
			</ul>
			<h3 class="muted">Job Search</h3>
		</div>
		<hr>
		<div id="searchDiv">
			<form class="form-search" action="jobInfo.action">
				<s:hidden name="qo.currentPage" value="%{qo.currentPage}"
					id="currentPage" />
				<div>
					关键字：<s:textfield name="qo.keyWords" value="%{qo.keyWords}" cssClass="input-xxlarge" placeholder="请输入职位关键字"/>
				</div>
				<div style="margin-top: 18px;">
					<button type="submit" class="btn">Search</button>
				</div>
			</form>
		</div>
		<hr>
		<div id="jobList">
			<ul id="jobUl" class="unstyled">
			<s:iterator value="pager.listData">
				<li>
					<div>
						<a href="jobInfo_view?id=<s:property value="id"/>" class="jobHeader">
							<s:property value="title" escapeHtml="false" />
						</a>
							<span id="" style="margin-left: 8px;color: #888888;">
								[<s:property value="city" escapeHtml="false" />]
							</span>
						</div>
					<div>
						<span class="jobAttr">公司名称：</span><span class="jobAttrVal"><s:property value="company" escapeHtml="false" /></span>
						<span class="jobAttr">职位类型：</span><span class="jobAttrVal"><s:property value="type" escapeHtml="false" /></span>
						<span class="jobAttr">时间：</span><span class="jobAttrVal"><s:property value="date" escapeHtml="false" /></span>
						<span id="" class="jobAttr">工资水平：</span><span id="" class="jobAttrVal"><s:property value="salaryLevel" escapeHtml="false" /></span>
					</div>
				</li>
				</s:iterator>
			</ul>
			<div style="text-align: center;">
				<div>
					一共<s:property value="pager.totalCount" />条
					,第<s:property value="pager.currentPage" />/<s:property value="pager.totalPage" />页
				</div>
				<ul class="pager">
				  <li><a href="javascript:goPage(<s:property value="pager.prePage"/>)">上页</a></li>
				  <li><a href="javascript:goPage(<s:property value="pager.nextPage"/>)">下页</a></li>
				</ul>
				
				<!-- <div class="input-append"> -->
					<!--  <input id="appendedInputButton" type="number" style="width: 50px;" type="text" placeholder="N Page"> -->
					<!--  <button class="btn" type="button" onclick="goNPage(<s:property value="pager.totalPage"/>)">Go</button>  -->
				<!-- </div> -->
			</div>
		</div>
		<hr>
		<div class="footer">
			<p>&copy; He Rengui 2015</p>
		</div>
	</div>
	<!-- /container -->
	<script src="/js/jquery.min.js"></script>
	<script src="/js/bootstrap.min.js"></script>
</body>
</html>