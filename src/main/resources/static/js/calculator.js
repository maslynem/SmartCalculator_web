const url = "http://localhost:8080/calculator/api/v1"

const btns = document.querySelectorAll(".button");

btns.forEach(btn => {
    let input = document.getElementById("input");
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
            postData(input.value).then((data) =>
                input.value = data.status === 'OK' ? data.result : data.message
            )
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