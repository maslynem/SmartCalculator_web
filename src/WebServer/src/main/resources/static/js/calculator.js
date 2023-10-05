const calculateUrl = "http://localhost:8081/api/v1/calculator/"
const historyUrl = "http://localhost:8081/api/v1/calculator/history/"

let calc_sess = getCookie();

getRequest(historyUrl + calc_sess).then((data) => {
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
            postRequest(calculateUrl, data).then((data) => {
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
    deleteRequest(historyUrl + calc_sess).then(() => location.reload());
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

