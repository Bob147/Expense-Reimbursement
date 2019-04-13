document.addEventListener("DOMContentLoaded", function (e) {
  createOnStartUp();
})

let url = "http://localhost:9006/Revature/employeeallReqs";

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

		newRow.innerHTML = 
			`<td>${requestArr[i].id}</td>
			<td>${requestArr[i].type}</td>
			<td>${requestArr[i].amount}</td> 
			<td>${requestArr[i].status}</td> 
			<td>${requestArr[i].decision}</td> 
			`
		newBody.appendChild(newRow);
	}
}

const ShowAll = () => {
	url = "http://localhost:9006/Revature/employeeallReqs";
	sendAjaxGet(url, display);
}

const ShowPending = () => {
	url = "http://localhost:9006/Revature/employeependingReqs";
	sendAjaxGet(url, display);
}

const ShowResolved = () => {
	url = "http://localhost:9006/Revature/employeeresolvedReqs";
	sendAjaxGet(url, display);
}
