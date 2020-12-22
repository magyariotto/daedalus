function create_account(){
    //const username = document.getElementById("username").value;
    //const password = document.getElementById("password").value;
    //const password2 = document.getElementById("password2").value;
    //const email = document.getElementById("email").value;
    //const firstName = document.getElementById("firstName").value;
    //const lastName = document.getElementById("lastName").value;

    var request = new XMLHttpRequest();
        request.open("GET", "./json/create_account.json", 0);
        request.setRequestHeader("Content-Type", "application/json");
        request.setRequestHeader("Request-Type", "rest");
        request.send();
        divs = JSON.parse(request.responseText);

    window.loadCreateUserPage = new function(){
        $(document).ready(function(){

            const mainDiv = document.createElement("div");
            const fieldSet = document.createElement("fieldset");
            const legend = document.createElement("legend");
            legend.innerHTML= "Create Account";

            var i;
            for(i=0; i<6; i++){
                const div = document.createElement("div");
                const label = document.createElement("label");
                label.innerHTML = divs.create_account_divs[i].label;
                const input = document.createElement("input");
                input.setAttribute("type", divs.create_account_divs[i].type);
                input.setAttribute("id", divs.create_account_divs[i].id);
                input.setAttribute("placeholder", divs.create_account_divs[i].placeholder);
                label.appendChild(input);
                div.appendChild(label);
                fieldSet.appendChild(div)
            }
            fieldSet.appendChild(legend);
            mainDiv.appendChild(fieldSet);

            document.getElementById("create_account_html").appendChild(mainDiv);
        })
    }
}