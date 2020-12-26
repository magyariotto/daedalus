function createIndexPage(){
    scriptLoader.loadScript("/js/login_account.js");
    scriptLoader.loadScript("/js/create_account.js");

    window.loadIndexPage = new function(){
        $(document).ready(function(){
            loginAccountPage();
            createAccountPage();
        })
    }
}

