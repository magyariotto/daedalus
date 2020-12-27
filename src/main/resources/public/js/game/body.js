function loadBodyPage(){
    connect_JSON();

    window.loadBody = new function(){
        $(document).ready(function(){
            let num1;
            const div = [];
            for(num1=0; num1<divs.homePageDivs.length; num1++){
                div[divs.homePageDivs[num1].name] = document.createElement("div");
                div[divs.homePageDivs[num1].name].setAttribute("id", divs.homePageDivs[num1].id);
            }

            let num2;
            let num3;
            for(num2=divs.divsPlacement.length-1; num2>0; num2--){
                for(num3=0; num3<divs.divsPlacement[num2].divNumber; num3++){
                    div[divs.divsPlacement[num2].parent].appendChild(div[divs.homePageDivs[divs.divsPlacement[num2].child[num3].num-1].name]);
                }
            }
            document.getElementById("homePage").appendChild(div["mainDiv"]);
        })
    }
}

function connect_JSON(){
    const request = new XMLHttpRequest();
        request.open("GET", "./data/createdPages/createGame/home_page.json", 0);
        request.setRequestHeader("Content-Type", "application/json");
        request.setRequestHeader("Request-Type", "rest");
        request.send();
        divs = JSON.parse(request.responseText);
}