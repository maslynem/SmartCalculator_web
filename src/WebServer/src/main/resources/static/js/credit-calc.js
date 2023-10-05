const creditCalcUrl = "http://localhost:8081/api/v1/calculator/credit/"

let sum = document.getElementById("creditSum");
let term = document.getElementById("creditTerm");
let rate = document.getElementById("interestRate");

document.getElementById("calculateCredit").addEventListener("click", function () {
   console.log(sum.value);
   console.log(term.value);
   console.log(rate.value);
   console.log(getCreditType());
   let creditType = getCreditType();

   if (checkCreditSum() && checkCreditTerm() && checkInterestRate() && creditType) {

      let data = {
         creditSum: sum.value,
         creditTerm: term.value,
         termCoefficient: $("#termCoefficient option:selected").text() === 'Лет' ? 12 : 1,
         interestRate: rate.value
      }
      postRequest(creditCalcUrl + creditType, data).then((data) =>{
         console.log(data);
         if (creditType === 'ann') {
            document.getElementById("monthPay").innerText = data.monthPay;
            document.getElementById("debt").innerText = data.debt;
            document.getElementById("allSum").innerText = data.allSum;
            document.getElementById("annuityCreditResult").style.display = "block";
            document.getElementById("differentiatedCreditResult").style.display = "none";
         } else {
            document.getElementById("annuityCreditResult").style.display = "none";
            document.getElementById("diffMonthPayFirst").innerText = data.monthPay[0];
            document.getElementById("diffMonthPayLast").innerText = data.monthPay[data.monthPay.length-1];
            document.getElementById("diffDebt").innerText = data.debt;
            document.getElementById("diffAllSum").innerText = data.allSum;
            remakeTable(data.monthPay);
            document.getElementById("differentiatedCreditResult").style.display = "block";
         }
      });
   }
});

function remakeTable(monthPayList) {
   let tbody = document.getElementById("tbd");
   tbody.innerHTML = '';
   for (let i = 0; i < monthPayList.length; i++) {
      let td1 = document.createElement("td");
      td1.innerHTML = i+1;
      let td2 = document.createElement("td");
      td2.innerHTML = monthPayList[i];
      let tr = document.createElement("tr");
      tr.appendChild(td1);
      tr.appendChild(td2);
      tbody.appendChild(tr);
   }
}

function getCreditType() {
   return $("input[type='radio'][name='creditType']:checked").val();
}

function checkCreditSum() {
   let flag = false;
   let val= sum.value;
   if (Number.isNaN(val)) alert('Введите сумму кредита');
   else if (val <= 0) alert('Cумма кредита не может быть меньше 0');
   else flag = true;
   return flag;
}

function checkCreditTerm() {
   let flag = false;
   let val= term.value;
   if (Number.isNaN(val)) alert('Введите cрок кредита');
   else if (val <= 0) alert('Срок кредита не может быть меньше 0');
   else if (val >=200) alert('Срок кредита не может быть больше 200');
   else flag = true;
   return flag;
}

function checkInterestRate() {
   let flag = false;
   let val= rate.value;
   if (Number.isNaN(val)) alert('Введите процентную ставку');
   else if (val <= 0) alert('Процентная ставка не может быть меньше 0');
   else flag = true;
   return flag;
}
