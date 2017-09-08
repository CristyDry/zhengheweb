<%@ page contentType="text/html;charset=UTF-8" 
	import="java.util.List,com.fullteem.modules.zhenghe.entity.BigDataZhenghe" %>

<!doctype html>
<html>
	<head>
		<title>Bar Chart</title>
		<script src="../../../static/chart/Chart.js"></script>
	</head>
	<body>
		<h6>下单即算</h6>
		<div style="width: 100%">
			<canvas id="canvas" height="40%" width="100%"></canvas>
		</div>
		
	<%
		List<BigDataZhenghe> list = (List<BigDataZhenghe>)request.getAttribute("bigDataZhenghe");
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
	
	<%-- 动态生成柱状图--%>
	<script>
		var barChartData = {
			labels : [<%=labels%>],
			datasets : [
				{
					fillColor : "rgba(220,220,220,0.5)",		//柱状颜色
					strokeColor : "rgba(220,220,220,0.8)",		//外边框颜色
					highlightFill: "rgba(220,220,220,0.75)",	//鼠标停留时柱状颜色
					highlightStroke: "rgba(220,220,220,1)",		//鼠标停留时外边框颜色
					data : [<%=data%>]
				}
			]
		}
		window.onload = function(){
			var ctx = document.getElementById("canvas").getContext("2d");
			window.myBar = new Chart(ctx).Bar(barChartData, {
				responsive : true
			});
		}

	</script>
	</body>
</html>
