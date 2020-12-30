function loadDiv1(){
    window.loadDiv = new function(){
        $(document).ready(function(){
            document.getElementById("bodyMiddleDiv").innerHTML = "";
            connect_div1_JSON();
            connect_JSON();

            let num1;
            let num5;
            const div = [];
            for(num5=div1.div1Divs.length; num5>0; num5--){
                for(num1=0; num1<divs.homePageDivs.length; num1++){
                    if(divs.homePageDivs[num1].name == div1.div1Divs[num5-1].name){
                        div[divs.homePageDivs[num1].name] = document.createElement("div");
                        div[divs.homePageDivs[num1].name].setAttribute("id", divs.homePageDivs[num1].id);
                    }
                }
            }

            let num2;
            let num3;
            let num4;
            for(num2=divs.divsPlacement.length-1; num2>0; num2--){
                for(num4=0; num4<div1.divParents.length; num4++){
                    if(divs.divsPlacement[num2].parent == div1.divParents[num4].name){
                        for(num3=0; num3<divs.divsPlacement[num2].divNumber; num3++){
                            div[divs.divsPlacement[num2].parent].appendChild(div[divs.homePageDivs[divs.divsPlacement[num2].child[num3].num-1].name]);
                        }
                    }
                }
            }

            document.getElementById("bodyMiddleDiv").appendChild(div["bodyMiddleTopDiv"]);
            document.getElementById("bodyMiddleDiv").appendChild(div["bodyMiddleBottomDiv"]);
        })
    }
}

function connect_div1_JSON(){
    const request = new XMLHttpRequest();
        request.open("GET", "./data/createdPages/createGame/pages/div1.json", 0);
        request.setRequestHeader("Content-Type", "application/json");
        request.setRequestHeader("Request-Type", "rest");
        request.send();
        div1 = JSON.parse(request.responseText);
}

function connect_JSON(){
    const request = new XMLHttpRequest();
        request.open("GET", "./data/createdPages/createGame/home_page.json", 0);
        request.setRequestHeader("Content-Type", "application/json");
        request.setRequestHeader("Request-Type", "rest");
        request.send();
        divs = JSON.parse(request.responseText);
}