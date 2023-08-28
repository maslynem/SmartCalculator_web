const url = "http://localhost:8080/calculator/api/v1"

let calc_sess = getCookie();

let input = document.getElementById("input");

const tbody = document.querySelector('tbody');

tbody.addEventListener('dblclick', function (e) {
    const cell = e.target.closest('td');
    if (!cell) {
        return;
    } // Quit, not clicked on a cell
    const row = cell.parentElement;
    input.value = row.innerText;
});

const btns = document.querySelectorAll(".button");

btns.forEach(btn => {

    if (btn.id === 'BtnC') {
        btn.addEventListener("click", function () {
            input.value = '';
        });
    } else if (btn.id === 'BtnMod') {
        btn.addEventListener("click", function () {
            input.value = input.value + '%';
        });
    } else if (btn.id === 'BtnSqrt') {
        btn.addEventListener("click", function () {
            input.value = input.value + btn.textContent + '(';
        });
    } else if (btn.id === 'BtnEq') {

        btn.addEventListener("click", function () {
            let data = {
                historyUUID: calc_sess,
                expression: input.value
            }
            addExpression(input.value);
            postData(data).then((data) =>
                input.value = data.status === 'OK' ? data.result : data.message
            );
        });
    } else {
        btn.addEventListener("click", function () {
            input.value = input.value + btn.textContent;
        });
    }
});

const postData = async (data = {}) => {
    console.log(JSON.stringify(data));
    const response = await fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    });
    return await response.json();
}

function addExpression(expression) {
    let tr = document.createElement("tr");
    let td = document.createElement("td");
    td.style.height = "35px";
    td.innerHTML = expression;
    tr.appendChild(td);
    let tbody = document.getElementById("tbd");
    tbody.appendChild(tr);
}

function getCookie() {
    let calc_sess = document.cookie.match(/calc_sess=(.+?)(;|$)/);
    if (calc_sess == null) {
        let uuid = generateUUID();
        document.cookie = "calc_sess=" + uuid;
        return uuid;
    }
    return calc_sess[1];
}

function generateUUID() { // Public Domain/MIT
    var d = new Date().getTime();//Timestamp
    var d2 = ((typeof performance !== 'undefined') && performance.now && (performance.now()*1000)) || 0;//Time in microseconds since page-load or 0 if unsupported
    return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
        var r = Math.random() * 16;//random number between 0 and 16
        if(d > 0){//Use timestamp until depleted
            r = (d + r)%16 | 0;
            d = Math.floor(d/16);
        } else {//Use microseconds since page-load if supported
            r = (d2 + r)%16 | 0;
            d2 = Math.floor(d2/16);
        }
        return (c === 'x' ? r : (r & 0x3 | 0x8)).toString(16);
    });
}