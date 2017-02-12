<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta name="author" content="">

	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"/>
	<title>就业信息详情</title>
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
		  .jobAttr{
		  	font-family: '微软雅黑';
		  	font-size: 15px;
		  	color: #666666;
		  	margin-right: 5px;
		  }
		  .jobAttrVal{
		  	font-family: "微软雅黑";
		  	font-size: 15px;
		  	margin-right: 12px;
		  	color:#000;
		  }
		  tr{
		  	height:30px;
		  }
		  .content{
		  	width:800px;
		  	font-family: '微软雅黑';
		  	margin: 0 auto;
		  }
		   @media print{ 
			.print {display:block;} 
			.notPrint {display:none;} 
		  } 
	</style>
	<script type="text/javascript">
		function preview() 
		{ 
			bdhtml=window.document.body.innerHTML; 
			sprnstr="<!--startprint-->"; 
			eprnstr="<!--endprint-->"; 
			prnhtml=bdhtml.substr(bdhtml.indexOf(sprnstr)+17); 
			prnhtml=prnhtml.substring(0,prnhtml.indexOf(eprnstr)); 
			window.document.body.innerHTML=prnhtml; 
			window.print(); 
		} 
	</script>
</head>
<body>
	<div class="container-narrow">
		<div class="masthead">
			<ul class="nav nav-pills pull-right">
				<li style="padding-top: 25px;">The reason is searching for success</li>
			</ul>
			<h3 class="muted">Employment Information</h3>
		</div>
		<hr>
	</div>
	<!--startprint-->
	<div>
		<h2 style="text-align: center;"><s:property value="employmentInfo.title" /></h2>
		<small><s:property value="employmentInfo.date" /></small>
		<div class="content" escapeHtml="false" class="text-center"><s:property value="employmentInfo.content" /></div>
	</div>
	<!--endprint-->
	<div align="center" style="margin-top:40px;"> 
		<input class="btn btn-large btn-primary" type="button" name="print" value="预览并打印" onclick="preview()"> 
	</div>
</body>
</html>