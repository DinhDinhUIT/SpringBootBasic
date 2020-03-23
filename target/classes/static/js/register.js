function requestRegister(){
	var username = $("#username").val();
	var email = $("#email").val();
	var password = $("#password").val();
    var re_type_password = $("#re-type-password").val();
    
    $(':input[type="button"]').prop('disabled', true);
    $("#message-register").html("");

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
					window.location = "/login";
				}
				else if (response['data'] == false) {
					$("#message-register").html("Username already exits!");
				}
			})
			.catch(error => console.log(error));
		}
    }

    $(':input[type="button"]').prop('disabled', false);
}