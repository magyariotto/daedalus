function createHomePage(){
    //scriptLoader.loadScript("/js/logout.js");
    scriptLoader.loadScript("/js/game/body.js");

    window.loadHomePage = new function(){
        $(document).ready(function(){
            loadBodyPage();
        })
    }
}