async function showInforUsers() {
    await axios.get('/get-list-users')
        .then(response => {
            let tr = "<tr>";
            let _tr = "</tr>";
            let td = "<td>";
            let _td = "</td>";
            let innerHTML = "";
            let iconEdit = '<a class="edit" href="#" data-toggle="modal" data-target="#myModal" data-toggle="tooltip" title="Update User!"><i class="fas fa-edit"></i></a> &nbsp';
            let iconDelete = '<a class="delete" href="#" data-toggle="tooltip" title="Delete User!"><i class="fas fa-user-minus"></i></a>';
            let leng = response['data'].length;
            if (leng > 0) {
                for (let i = 0; i < leng; i++) {
                    innerHTML += tr + td + (i + 1) + _td + td + response['data'][i].username + _td + td + response['data'][i].email + _td +
                        td + response['data'][i].date_created + _td + td + response['data'][i].role + _td + td + response['data'][i].status + _td +
                        td + iconEdit + iconDelete + _td + _tr;
                }
                $("#myTable").html(innerHTML);

                for (let j = 0; j < leng; j++) {
                    $('#myTable .edit').eq(j).attr('id', response['data'][j].id);
                    $('#myTable .delete').eq(j).attr('id', response['data'][j].id);
                }
            }
            console.log(response['data']);
        })
        .catch(error => console.log(error));
    
    var id;
    await $(".edit").click(function() {
        id = $(this).attr("id");
    });

    await $(".btn-update").click(function requestUpdateUser() {
        var username = $("#username").val();
        var email = $("#email").val();
        var role = $("#role").val();

        axios.put(`/update-user/${id}`, {username: username, email: email, role: role})
            .then(response => {
                console.log(response['data']);
                if (response['data'] == true) {
                    alert("Update user successfully!");
                    window.location = "/list-users";
                }
                else{
                    alert("Update user failed!");
                }
            })
            .catch(error => console.log(error));
    });

    await $(".delete").click(function requestDeleteUser() {
        id = $(this).attr("id");

        axios.delete(`/delete-user/${id}`)
            .then(response => {
                console.log(response['data']);
                if (response['data'] == true) {
                    alert("Delete user successfully!")
                    window.location = "/list-users";
                }
                else {
                    alert("Delete user failed!");
                }
            })
            .catch(error => console.log(error));
    });
}