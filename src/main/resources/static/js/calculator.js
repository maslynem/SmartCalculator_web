const calculateUrl = "http://localhost:8080/calculator/api/v1"
const historyUrl = "http://localhost:8080/calculator/api/v1/history"

let calc_sess = getCookie();

postData(historyUrl, {userUUID: calc_sess}).then((data) => {
        if (data.status === "OK") {
            for (let i = 0; i < data.history.length; i++) {
                addExpressionToTable(data.history[i]);
            }
        }
    }
);

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
                userUUID: calc_sess,
                expression: input.value
            }
            addExpressionToTable(input.value);
            postData(calculateUrl, data).then((data) => {
                    if (data.status === 'OK') {
                        input.value = data.result;
                    } else {
                        alert(data.message);
                    }
                }
            );
        });
    } else {
        btn.addEventListener("click", function () {
            input.value = input.value + btn.textContent;
        });
    }
});

document.getElementById("clearHistoryBtn").addEventListener("click", function () {
    console.log(historyUrl + "/clear");
    postData(historyUrl + "/clear", {userUUID: calc_sess}).then((data) => {
            if (data.status === "OK") {
                location.reload();
            }
        }
    );
});


function addExpressionToTable(expression) {
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

