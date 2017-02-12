<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>职位信息页</title>
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
		  @media print{ 
			.print {display:block;} 
			.notPrint {display:none;} 
		  }
		  .jobAttrVal h1,.jobAttrVal h2{
		  	font-size:16px
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
			<h3 class="muted">Job Information</h3>
		</div>
		<hr>
	</div>
	<!--startprint-->
	<table align="center" border="0" cellspacing="0" width="700" style="margin-top: 40px;">
		<tr>
			<td colspan="2" align="center"><h2><s:property value="jobInfo.title" /></h2></td>
		</tr>
		<tr>
			<td>
				<span class="jobAttr">公司名称:</span>
				<span class="jobAttrVal"><s:property value="jobInfo.company" /></span>
			</td>
			<td>
				<span class="jobAttr">职位类型:</span>
				<span class="jobAttrVal"><s:property value="jobInfo.type" /></span>
			</td>
		</tr>
		<tr>
			<td>
				<span class="jobAttr">所在城市：</span>
				<span class="jobAttrVal"><s:property value="jobInfo.city" /></span>
			</td>
			<td>
				<span class="jobAttr">薪酬等级：</span>
				<span class="jobAttrVal"><s:property value="jobInfo.salaryLevel" /></span>
			</td>
		</tr>
		<tr>
			<td colspan="2" class="jobAttrVal" style="padding-top:10px">
				<s:property value="jobInfo.content" escapeHtml="false"/>
			</td>
		</tr>
	</table>
	<!--endprint-->
	<div align="center" style="margin-top:40px;"> 
		<input class="btn btn-large btn-primary" type="button" name="print" value="预览并打印" onclick="preview()"> 
	</div>
</body>
</html>