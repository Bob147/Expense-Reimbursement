const sideBar = document.getElementById("sidebar");

sideBar.innerHTML = 
	`
	<li class="nav-item active">
		<a class="nav-link" href="managerhome"> 
			<i class="fas fa-fw fa-tachometer-alt"></i> 
			<span>Dashboard</span>
		</a>
	</li>
	<li class="nav-item">
		<a class="nav-link" href="managerminion">
			<i class="fa fa-users"></i> 
			<span>Employees</span>
		</a>
	</li>
	<li class="nav-item">
		<a class="nav-link" href="logout"> 
			<i class="fas fa-sign-out-alt"></i> 
			<span>Logout</span>
		</a>
	</li>
`