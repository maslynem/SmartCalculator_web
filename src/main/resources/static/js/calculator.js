const url = "http://localhost:8080/calculator/api/v1"
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
            addExpression(input.value);
            postData(input.value).then((data) =>
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
