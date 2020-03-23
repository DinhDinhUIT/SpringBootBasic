webix.ui({
    rows: [
        {},
        {
            cols: [
                {},
                {   css:{"border-radius": "10px"}, 
                    view: "form",
                    id: "form_login",
                    width: 400,
                    elements: [
                        {
                            cols: [
                                {},
                                { view: "label", label: "Login information", align:"center" },
                                {}
                            ]
                        },
                        { view: "text", name: "username", label: "Username:" },
                        { view: "text", name: "password", label: "Password:" },
                        {view: "button", id: "button_login", value: "Login", css: "webix_primary",
                            click:requestLogin
                        }
                    ]
                },
                {}
            ]
        },
        {}
    ]

});

function requestLogin(){
    var data = $$("form_login").getValues();
    console.log(data);
}