function login_page(){
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
            userNameOrEmailInput.setAttribute("id", "userName");
            userNameOrEmailInput.setAttribute("placeholder", "Username or E-mail");
            userNameOrEmailLabel.appendChild(userNameOrEmailInput);
            userNameOrEmailDiv.appendChild(userNameOrEmailLabel);
            fieldSet.appendChild(userNameOrEmailDiv);

            const passwordDiv = document.createElement("div");
            const passwordLabel = document.createElement("label");
            passwordLabel.innerHTML = "Password";
            const passwordInput = document.createElement("input");
            passwordInput.setAttribute("type", "password");
            passwordInput.setAttribute("id", "password")
            passwordInput.setAttribute("placeholder", "Password");
            passwordLabel.appendChild(passwordInput);
            passwordDiv.appendChild(passwordLabel);
            fieldSet.appendChild(passwordDiv);

            const rememberDiv = document.createElement("div");
            const rememberLabel = document.createElement("label");
            rememberLabel.innerHTML = "Remember me.";
            const rememberInput = document.createElement("input");
            rememberInput.setAttribute("type", "checkbox");
            rememberInput.setAttribute("id", "remember");
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

            document.getElementById("login_html").appendChild(mainDiv);
        })
    }
}

function login(){
    const userNameOrEmail = document.getElementById("userName").value;
    const password = document.getElementById("password").value;
    const remember = document.getElementById("remember").checked;

    const data = {
        userNameOrEmail: userNameOrEmail,
        password: password,
        remember: remember
    }

    const request = new XMLHttpRequest();
    request.open("POST", "/login", 1);
    request.setRequestHeader("Content-Type", "application/json");
    request.onload = function(){
        const responseStatus = request.status;
        const responseText = request.responseText;

        if(responseStatus == 200){
            window.location.href = "/index";
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