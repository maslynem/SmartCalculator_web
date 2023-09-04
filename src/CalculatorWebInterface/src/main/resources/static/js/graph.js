const graphUrl = "http://localhost:9090/calculator/api/v1/graph"

let expression = document.getElementById("expression");
const ctx = document.getElementById('myChart');
let myLineChart = new Chart(ctx, null);

document.getElementById("calculateGraph").addEventListener("click", function () {
    let minX = parseInt(document.getElementById("minX").value);
    let maxX = parseInt(document.getElementById("maxX").value);
    let minY = parseInt(document.getElementById("minY").value);
    let maxY = parseInt(document.getElementById("maxY").value);

    if (checkInput(minX,maxX,minY,maxY)) {
        let data = {
            minX: minX,
            maxX: maxX,
            expression: expression.value
        }
        postData(graphUrl, data).then((data) => {
                if (data.status === "OK") {
                    printGraph(minX, maxX, minY, maxY, data.xvalues, data.yvalues);
                } else {
                    alert(data.message);
                }
            }
        );
    }
});

printGraph(0,10,0,20, [],[]);

function checkInput(minX, maxX, minY, maxY) {
    let flag = false;
    if (isNaN(minX)) alert('Enter min X');
    else if (isNaN(maxX)) alert('Enter max X');
    else if (isNaN(minY)) alert('Enter min Y');
    else if (isNaN(maxY)) alert('Enter max Y');
    else if (minX < -1000000) alert("min X must be greater -1_000_000")
    else if (minY < -1000000) alert("min Y must be greater -1_000_000")
    else if (maxX > 1000000) alert("min X must be lesser 1_000_000")
    else if (minY > 1000000) alert("min Y must be lesser 1_000_000")
    else if (minX > maxX) alert('max X must be greater than min X');
    else if (minY > maxY) alert('max Y must be greater than min Y');
    else flag = true;
    return flag;
}

function printGraph(minX, maxX, minY, maxY, xvalues, yvalues) {
    myLineChart.destroy();
    let data = getData(xvalues, yvalues);
    myLineChart = new Chart(ctx, {
        type: 'scatter',
        data: {
            datasets: [{
                label: expression.value,
                pointRadius: 1,
                data: data,
            }]
        },
        options: {
            legend: {
                display: false
            },
            plugins: {
                tooltip: {
                    enabled: false
                }
            },
            scales: {
                y: {
                    min: minY,
                    max: maxY
                },
                x: {
                    min: minX,
                    max: maxX
                }
            }
        }
    });
}

function getData(xvalues, yvalues) {
    let arr = [];
    for (let i = 0; i < xvalues.length; i++) {
        arr.push({x:xvalues[i], y: yvalues[i]})
    }
    return arr;
}