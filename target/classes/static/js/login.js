function requestLogin() {
	var username = $("#username").val();
    var password = $("#password").val();
    
    $(':input[type="button"]').prop('disabled', true);
    $("#message-login").html("");

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
        
    $(':input[type="button"]').prop('disabled', false);
}