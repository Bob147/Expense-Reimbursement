let url = "http://localhost:9006/Revature/getemployee";

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

sendAjaxGet(url, display);

function display(xhr) {
	employeeArr = JSON.parse(xhr.response).requests;
	let table = document.getElementById("employeeTable");
	let newBody = document.createElement("tbody");
	newBody.setAttribute("id", "employeehome");
	table.appendChild(newBody);
	for (i in employeeArr) {
		let newRow = document.createElement("tr");

		newRow.innerHTML = 
			`<td>${employeeArr[i].id}</td>
			<td>${employeeArr[i].firstname}</td> 
			<td>${employeeArr[i].lastname}</td>
			<td>${employeeArr[i].address}</td>
			`; 
		newBody.appendChild(newRow);
	}
}

