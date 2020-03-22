
function requestLogin() {
	var username = $("#username").val();
	var password = $("#password").val();

	axios.post('/login', {username: username, password: password})
		.then(response => {
			console.log(response['data']);
			if (response['data'] == true) {
				window.location = "/list-users";
			}
			else if (response['data'] == false) {
				$("#message-login").html("Username or Password incorrect!");
			}
		})
		.catch(error => console.log(error));
}

function requestRegister(){
	var username = $("#username").val();
	var email = $("#email").val();
	var password = $("#password").val();
	var re_type_password = $("#re-type-password").val();

	if(username=="" || email=="" || password=="" || re_type_password==""){
		$("#message-register").html("Fields cannot be blank!");
	}
	else{
		if(password != re_type_password){
			$("#message-register").html("The password and re-type password do not match!");
		}
		else{
			axios.post('/register', {username: username, password: password, email: email})
			.then(response => {
				console.log(response['data']);
				if (response['data'] == true) {
					window.location = "/list-users";
				}
				else if (response['data'] == false) {
					$("#message-register").html("Username already exits!");
				}
			})
			.catch(error => console.log(error));
		}
	}
}