function createAccountPage(){
    connect_JSON();

    window.loadCreateAccountPage = new function(){
        $(document).ready(function(){
            const mainDiv = document.createElement("div");
            const fieldSet = document.createElement("fieldset");
            const legend = document.createElement("legend");
            legend.innerHTML= "Create Account";

            let i;
            for(i=0; i<divs.create_account_divs.length; i++){
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

            const buttonDiv = document.createElement("div");
            const buttonElement = document.createElement("button");
            buttonElement.setAttribute("onclick","createAccount()");
            buttonElement.setAttribute("id","create_account");
            buttonElement.innerHTML = "Registration";
            buttonDiv.appendChild(buttonElement);

            fieldSet.appendChild(legend);
            mainDiv.appendChild(fieldSet);
            mainDiv.appendChild(buttonDiv);

            document.getElementById("createAccountPage").appendChild(mainDiv);
        })
    }
}

function createAccount(){
    const createAccountUserName = document.getElementById("createAccountUserName").value;
    const createAccountPassword = document.getElementById("createAccountPassword").value;
    const createAccountPassword2 = document.getElementById("createAccountPassword2").value;
    const createAccountEmail = document.getElementById("createAccountEmail").value;
    const createAccountFirstName = document.getElementById("createAccountFirstName").value;
    const createAccountLastName = document.getElementById("createAccountLastName").value;

    if(!createAccountUserName){
        alert("Username missing.");
        return;
    }

    if(createAccountUserName.length < 6 || createAccountUserName.length > 32){
        alert("Username length is invalid");
        return;
    }


    if(createAccountPassword.length < 6 || createAccountPassword.length > 20){
        alert("Password length is invalid.");
        return
    }

    if(createAccountPassword != createAccountPassword2){
        alert("Invalid confirm password.");
        return;
    }

    if(!isEmailValid(createAccountEmail)){
        alert("Email is invalid");
        return;
    }

    const data = {
            userName: createAccountUserName,
            password: createAccountPassword,
            email: createAccountEmail,
            firstName: createAccountFirstName,
            lastName: createAccountLastName
            }

    const request = new XMLHttpRequest();
        request.open("POST", "/create_account", 1);
        request.setRequestHeader("Content-Type", "application/json");
        request.onload = function(){
            const responseStatus = request.status;
            const responseText = request.responseText;

            if(responseStatus == 200){
                window.location.href = "/index"
            }else{
                const errorResponse = parseResponse(responseText);
                if(errorResponse.errorMessage){
                    alert(errorResponse.errorMessage);
                }else{
                    alert("Unknown error: " + responseStatus + " - " + responseText);
                }
            }

            function parseResponse(responseText){
                try{
                    return JSON.parse(responseText);
                }catch(e){
                    return {};
                }
            }
        }

        request.send(JSON.stringify(data));

    function isEmailValid(email){
        let result;
        if(email == null || email == undefined){
            result = false;
        }else if(email.indexOf("@") < 1){
            result = false;
        }else if(email.length < 4){
            result = false;
        }else if(email.indexOf(".") < 0){
            result = false;
        }else if(email.lastIndexOf(".") > email.length - 3){
            result = false;
        }else{
            result = true;
        }
        return result;
    }
}

function connect_JSON(){
    const request = new XMLHttpRequest();
        request.open("GET", "./data/createdPages/createAccount/create_account.json", 0);
        request.setRequestHeader("Content-Type", "application/json");
        request.setRequestHeader("Request-Type", "rest");
        request.send();
        divs = JSON.parse(request.responseText);
}