<%@ page contentType="text/html;charset=UTF-8" 
	import="java.util.List,com.fullteem.modules.zhenghe.entity.BigDataZhenghe" %>

<!doctype html>
<html>
	<head>
		<title>Line Chart</title>
		<script src="../../../static/chart/Chart.js"></script>
	</head>
	<body>
		<h6>支付即算</h6>
		<div style="width:100%">
			<div>
				<canvas id="canvas" height="40%" width="100%"></canvas>
			</div>
		</div>
		
	<%
		List<BigDataZhenghe> list = (List<BigDataZhenghe>)request.getAttribute("bigDataZhenghe2");
		StringBuilder sX = new StringBuilder();
		StringBuilder sY = new StringBuilder();
		for(BigDataZhenghe bigData:list){
			sX.append('"').append(bigData.getX()).append('"').append(',');
			sY.append(bigData.getY()).append(',');
		}
		
		String labels = sX.toString();
		//out.println(labels);
		String data = sY.toString();
		//out.println(data);
	%>
	
	<%-- 动态生成曲线图--%>
	<script>
		var lineChartData = {
			labels : [<%=labels%>],
			datasets : [
				{
					label: "daily sales",
					fillColor : "rgba(220,220,220,0.2)",		 //曲线内面积颜色
					strokeColor : "rgba(220,220,220,1)",		 //曲线颜色
					pointColor : "rgba(220,220,220,1)",			 //圆点颜色
					pointStrokeColor : "#fff",					 //圆点外边框颜色
					pointHighlightFill : "#fff",				 //鼠标停留时圆点颜色
					pointHighlightStroke : "rgba(220,220,220,1)",//鼠标停留时圆点外边框颜色
					data : [<%=data%>]
				}
			]
		}
		window.onload = function(){
			var ctx = document.getElementById("canvas").getContext("2d");
			window.myLine = new Chart(ctx).Line(lineChartData, {
				responsive: true
			});
		}

	</script>
	</body>
</html>
