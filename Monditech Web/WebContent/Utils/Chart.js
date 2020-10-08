var colorOrder = new Array();
var highlightOrder = new Array();

colorOrder[0] = "#F7464A";
colorOrder[1] = "#46BFBD";
colorOrder[2] = "#FDB45C";
colorOrder[3] = "#949FB1";
colorOrder[4] = "#4D5360";
colorOrder[5] = "#F746E3";
colorOrder[6] = "#6846F7";
colorOrder[7] = "#46F780";
colorOrder[8] = "#D5F746";
colorOrder[9] = "#A946F7";

highlightOrder[0] = "#FF5A5E";
highlightOrder[1] = "#5AD3D1";
highlightOrder[2] = "#FFC870";
highlightOrder[3] = "#A8B3C5";
highlightOrder[4] = "#616774";
highlightOrder[5] = "#FF5AE7";
highlightOrder[6] = "#6B5AFF";
highlightOrder[7] = "#5AFF9A";
highlightOrder[8] = "#E4FF5A";
highlightOrder[9] = "#BC5AFF";

$.fn.NewGrafico = function () {

    return $(this).get(0).getContext("2d");

}

function Grafico(tipo) {

    this.barContext = null;
    this.pieContext = null;

    this.pie = null;

    this.pieOptions = {
        //Boolean - Whether we should show a stroke on each segment
        segmentShowStroke: true,

        //String - The colour of each segment stroke
        segmentStrokeColor: "#fff",

        //Number - The width of each segment stroke
        segmentStrokeWidth: 2,

        //Number - The percentage of the chart that we cut out of the middle
        percentageInnerCutout: 0, // This is 0 for Pie charts

        //Number - Amount of animation steps
        animationSteps: 100,

        //String - Animation easing effect
        animationEasing: "easeInOutQuart", //Original: easeOutBounce

        //Boolean - Whether we animate the rotation of the Doughnut
        animateRotate: true,

        //Boolean - Whether we animate scaling the Doughnut from the centre
        animateScale: false,

        //String - A legend template
        legendTemplate: "<ul class=\"<%=name.toLowerCase()%>-legend\"><% for (var i=0; i<segments.length; i++){%><li><div style=\"background-color:<%=segments[i].fillColor%>;width:15px;height:15px\"></div><div><%if(segments[i].label){%><%=segments[i].label%><%}%></div><br></li><%}%></ul>",

        tooltipTemplate: "<%= label %> : <%= value %>",

    }

    this.bar = null;

    this.barOptions = {
        //Boolean - Whether the scale should start at zero, or an order of magnitude down from the lowest value
        scaleBeginAtZero: true,

        //Boolean - Whether grid lines are shown across the chart
        scaleShowGridLines: true,

        //String - Colour of the grid lines
        scaleGridLineColor: "rgba(0,0,0,.05)",

        //Number - Width of the grid lines
        scaleGridLineWidth: 1,

        //Boolean - Whether to show horizontal lines (except X axis)
        scaleShowHorizontalLines: true,

        //Boolean - Whether to show vertical lines (except Y axis)
        scaleShowVerticalLines: true,

        //Boolean - If there is a stroke on each bar
        barShowStroke: true,

        //Number - Pixel width of the bar stroke
        barStrokeWidth: 2,

        //Number - Spacing between each of the X value sets
        barValueSpacing: 5,

        //Number - Spacing between data sets within X values
        barDatasetSpacing: 1,

        showTooltips: false,
        tooltipTemplate: "<%= label %> : <%= value %>"
        
    }

}

//Substitui a label e valor dos dados que estiverem na mesma posição do array, e por fim deleta ou acrescenta a diferença
function UpdateAndAddValues(type, chart, data, legend) {

    if (type == "pie") {

        var i;
        var totalSegments = chart.segments.length;

        for (i = 0; i < totalSegments; i++) {

            if (data[i]) {

                chart.segments[i].label = data[i].label;
                chart.segments[i].value = data[i].value;

            }
            else {

                chart.removeData();

            }

        }

        for (var j = i; j < data.length; j++) {

            chart.addData(data[j]);

        }

        chart.update();

        if (legend) {

            legend.html(GetPieLegend(data));

        }

    }
    else {

        var totalRemover = chart.datasets[0].bars.length;

        for (var i = 0; i < data.labels.length; i++) {
            
            var newData = [];

            newData.push(data.datasets[0].data[i]);
            newData.push(data.datasets[1].data[i]);
                                               
            chart.addData(newData, data.labels[i]);

        }

        for (var i = 0; i < totalRemover; i++) {

            chart.removeData();

        }

        chart.update();

    }   

}

function PieData(order) {

    this.label = null;
    this.value = null;
    this.color = colorOrder[order];
    this.highlight = highlightOrder[order];

}

function BarData() {

    this.labels = new Array();
    this.datasets = new Array();

}

function DatasetData() {

    this.label = null;
    this.fillColor = "#FFFFFF";
    this.strokeColor = "#FFFFFF";
    this.highlightFill = "#FFFFFF";
    this.highlightStroke = "#FFFFFF";
    this.data = new Array();

}

function GetPieLegend(data) {

    var retorno = [];

    retorno.push("<ul>");

    for (var i = 0; i < data.length; i++) {
        
        if (data[i].value > 0) {

            retorno.push("<li>");
            retorno.push("<div style='background-color:" + data[i].color + ";width:15px;height:15px'></div>");
            retorno.push("<div class='alinharLegenda'>" + data[i].label + "</div>");
            retorno.push("<br>");
            retorno.push("</li>");

        }

    }

    retorno.push("</ul>");

    return retorno.join("");

}