var list_data;
var title_data = [
  { id: "username", header: "Username", fillspace: true, sort: "string", },
  { id: "password", header: "Password", fillspace: true, sort: "string" },
  { id: "email", header: "Email", fillspace: true, sort: "string" },
  { id: "date_created", header: "Date Created", sort: "string" },
  { id: "role", header: "Role", sort: "string" },
  { id: "status", header: "Status", sort: "string" },
  { id: "delete", header: "", sort: "string" }
];

async function showInforUsers() {
  await axios.get('/get-list-users')
    .then(response => {
      list_data = response['data'];
    })
    .catch(error => console.log(error));

  await webix.ready(function () {
    console.log(this.list_data);
    webix.ui({
      view: "layout",
      rows: [
        { view: "template", type: "header", template: "User" },
        {
          view: "form",
          id: "film_form",
          gravity: 0.5,
          minWidth: 200,

          cols: [
            { view: "text", name: "Username", id: "inp_title", placeholder: "Username" },
            { label: "Search", view: "button", click: searchUserByUsername },
            {}
          ]
        },
        {
          cols: [
            {
              id: "list_data",
              columns: title_data,
              view: "datatable",
              data: list_data
            }
          ]
        }
      ]
    });
  });
}

async function searchUserByUsername() {
  await $$("list_data").clearAll();
  var username = $$('inp_title').getValue();
  username = username.replace(/^\s+|\s+$|\s+(?=\s)/g, "");;

  await axios.get('/get-list-users-by-username', {
    params: {
      username: username
    }
  })
    .then(response => {
      list_data = response['data'];
      $$("list_data").define("data", list_data);
      $$("list_data").refresh();
    })
    .catch(error => console.log(error));
}