/** pie Chart
* use on index.html
**/

		//var real_dataVeh = /*[[${chartDataVeh}]]*/'noValue';
	        
        //var real_dataCus = /*[[${chartDataCus}]]*/'noValue';
 
		// alert(real_dataVeh); // for debugging
	
        $(document).ready(function() {
            google.charts.load('current', {
                packages : [ 'corechart', 'bar' ]
            });
            
            google.charts.setOnLoadCallback(drawPieChartCus);
            google.charts.setOnLoadCallback(drawPieChartVeh);
			google.charts.setOnLoadCallback(drawColumnChart);
        });
        
        function drawPieChartCus() {
            var data = new google.visualization.DataTable();
            data.addColumn('string', 'Year');
            data.addColumn('number', 'Views');
            Object.keys(real_dataCus).forEach(function(key) {
                data.addRow([ key, real_dataCus[key] ]);
            });
            var options = {
                title : 'Customer usage'
            };
            var chart = new google.visualization.PieChart(document
                    .getElementById('piechartCus'));
            chart.draw(data, options);
        }
        
        function drawPieChartVeh() {
            var data = new google.visualization.DataTable();
            data.addColumn('string', 'Year');
            data.addColumn('number', 'Views');
            Object.keys(real_dataVeh).forEach(function(key) {
                data.addRow([ key, real_dataVeh[key] ]);
            });
            var options = {
                title : 'Vehicle usage'
            };
            var chart = new google.visualization.PieChart(document
                    .getElementById('piechartVeh'));
            chart.draw(data, options);
        }

        function drawColumnChart() {
            var data = new google.visualization.DataTable();
            data.addColumn('string', 'Year');
            data.addColumn('number', 'Views');
            Object.keys(real_dataBar).forEach(function(key) {
                data.addRow([ key, real_dataBar[key] ]);
            });
            var options = {
                title : 'stats',
                hAxis : {
                    title : 'Month',
                },
                vAxis : {
                    title : 'Amount $'
                }
            };
            var chart = new google.visualization.ColumnChart(document
                    .getElementById('chart_div'));
            chart.draw(data, options);
        }