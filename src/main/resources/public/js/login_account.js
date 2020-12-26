function loginAccountPage(){
    window.loadLoginPage = new function(){
        $(document).ready(function(){
            const mainDiv = document.createElement("div");
            const fieldSet = document.createElement("fieldset");
            const legend = document.createElement("legend");
            legend.innerHTML = "Login";
            fieldSet.appendChild(legend);

            const userNameOrEmailDiv = document.createElement("div");
            const userNameOrEmailLabel = document.createElement("label");
            userNameOrEmailLabel.innerHTML = "Username or E-mail";
            const userNameOrEmailInput = document.createElement("input");
            userNameOrEmailInput.setAttribute("type", "text");
            userNameOrEmailInput.setAttribute("id", "loginAccountUserName");
            userNameOrEmailInput.setAttribute("placeholder", "Username or E-mail");
            userNameOrEmailLabel.appendChild(userNameOrEmailInput);
            userNameOrEmailDiv.appendChild(userNameOrEmailLabel);
            fieldSet.appendChild(userNameOrEmailDiv);

            const passwordDiv = document.createElement("div");
            const passwordLabel = document.createElement("label");
            passwordLabel.innerHTML = "Password";
            const passwordInput = document.createElement("input");
            passwordInput.setAttribute("type", "password");
            passwordInput.setAttribute("id", "loginAccountPassword")
            passwordInput.setAttribute("placeholder", "Password");
            passwordLabel.appendChild(passwordInput);
            passwordDiv.appendChild(passwordLabel);
            fieldSet.appendChild(passwordDiv);

            const rememberDiv = document.createElement("div");
            const rememberLabel = document.createElement("label");
            rememberLabel.innerHTML = "Remember me.";
            const rememberInput = document.createElement("input");
            rememberInput.setAttribute("type", "checkbox");
            rememberInput.setAttribute("id", "loginAccountRemember");
            rememberLabel.appendChild(rememberInput);
            rememberDiv.appendChild(rememberLabel);

            const buttonDiv = document.createElement("div");
            const buttonElement = document.createElement("button");
            buttonElement.innerHTML = "Login";
            buttonElement.setAttribute("onclick", "login()");
            buttonElement.setAttribute("id", "login");
            buttonDiv.appendChild(buttonElement);

            mainDiv.appendChild(fieldSet);
            mainDiv.appendChild(rememberDiv);
            mainDiv.appendChild(buttonDiv);

            document.getElementById("loginAccountPage").appendChild(mainDiv);
        })
    }
}

function login(){
    const loginAccountUserNameOrEmail = document.getElementById("loginAccountUserName").value;
    const loginAccountPassword = document.getElementById("loginAccountPassword").value;
    const loginAccountRemember = document.getElementById("loginAccountRemember").checked;

    const data = {
        userNameOrEmail: loginAccountUserNameOrEmail,
        password: loginAccountPassword,
        remember: loginAccountRemember
    }

    const request = new XMLHttpRequest();
    request.open("POST", "/login", 1);
    request.setRequestHeader("Content-Type", "application/json");
    request.onload = function(){
        const responseStatus = request.status;
        const responseText = request.responseText;

        if(responseStatus == 200){
            window.location.href = "/home";
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
            }catch(error){
                return{};
            }
        }
    }
    request.send(JSON.stringify(data));
}