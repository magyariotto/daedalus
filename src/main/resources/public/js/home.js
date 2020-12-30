function createHomePage(){
    //scriptLoader.loadScript("/js/logout.js");
    scriptLoader.loadScript("/js/game/body.js");

    window.loadHomePage = new function(){
        $(document).ready(function(){
            loadBodyPage();
        })
    }
}

function load(value){
    scriptLoader.loadScript("/js/game/pages/div1.js");
    scriptLoader.loadScript("/js/game/pages/div2.js");
    scriptLoader.loadScript("/js/game/pages/div3.js");
    scriptLoader.loadScript("/js/game/pages/div4.js");
    scriptLoader.loadScript("/js/game/pages/div5.js");
    scriptLoader.loadScript("/js/game/pages/div6.js");
    scriptLoader.loadScript("/js/game/pages/div7.js");
    scriptLoader.loadScript("/js/game/pages/div8.js");
    scriptLoader.loadScript("/js/game/pages/div9.js");
    scriptLoader.loadScript("/js/game/pages/div10.js");
    scriptLoader.loadScript("/js/game/pages/div11.js");

    window.loadPage = new function(){
        $(document).ready(function(){
            if(value == "div1"){
                loadDiv1();
            }

            if(value == "div2"){
                loadDiv2();
            }

            if(value == "div3"){
                loadDiv3();
            }

            if(value == "div4"){
                loadDiv4();
            }

            if(value == "div5"){
                loadDiv5();
            }

            if(value == "div6"){
                loadDiv6();
            }

            if(value == "div7"){
                loadDiv7();
            }

            if(value == "div8"){
                loadDiv8();
            }

            if(value == "div9"){
                loadDiv9();
            }

            if(value == "div10"){
                loadDiv10();
            }

            if(value == "div11"){
                loadDiv11();
            }
        })
    }
}