document.addEventListener("DOMContentLoaded", function (e) {
  createOnStartUp();
})

let url = "http://localhost:9006/Revature/managerallReqs";

const createOnStartUp = () => {
	sendAjaxGet(url, display);
}

const sendAjaxGet = (url, func) => {
	let xhr = new XMLHttpRequest() || new ActiveXObject("Microsoft.HTTPRequest");
	xhr.onreadystatechange = function() {
		if (this.readyState==4 && this.status==200) {
			func(this);
		}
	}
	xhr.open("GET", url);
	xhr.send();
}

const display = (xhr) => {
	requestArr = JSON.parse(xhr.responseText).requests;
	let table = document.getElementById("requestTable");
	table.removeChild(document.getElementById("requestTableBody"));
	let newBody = document.createElement("tbody");
	newBody.setAttribute("id", "requestTableBody");
	table.appendChild(newBody);
	for (i in requestArr) {
		let newRow = document.createElement("tr");
		if (requestArr[i].manager) {
			man = `${requestArr[i].manager.firstname} ${requestArr[i].manager.lastname}`;
		} else {
			man = "TBA";
		}
		newRow.innerHTML = 
			`<td>${requestArr[i].id}</td>
			<td>${requestArr[i].employee.firstname + " " + requestArr[i].employee.lastname}</td>
			<td>${requestArr[i].type}</td> 
			<td>${requestArr[i].amount}</td>
			<td>${requestArr[i].status}</td> 
			<td>${requestArr[i].decision}</td>
			<td>${man}</td>`;
		newBody.appendChild(newRow);
	}
}

const myFunction = () => {
    let input, filter, tbody, tr, td, i, txtValue;
    input = document.getElementById("empName");
    filter = input.value.toUpperCase();
    ul = document.getElementById("requestTableBody");
    li = ul.getElementsByTagName("tr");
    for (i = 0; i < li.length; i++) {
        a = li[i].getElementsByTagName("td")[1];
        txtValue = a.textContent || a.innerText;
        if (txtValue.toUpperCase().indexOf(filter) > -1) {
            li[i].style.display = "";
        } else {
            li[i].style.display = "none";
        }
    }
}

const ShowAll = () => {
	url = "http://localhost:9006/Revature/managerallReqs";
	sendAjaxGet(url, display);
}

const ShowPending = () => {
	url = "http://localhost:9006/Revature/managerpendingReqs";
	sendAjaxGet(url, display);
}

const ShowResolved = () => {
	url = "http://localhost:9006/Revature/managerresolvedReqs";
	sendAjaxGet(url, display);
}
