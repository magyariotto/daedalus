(function ScriptLoader(){
    const date = getDate();
    const loadedScripts = [];

    window.scriptLoader = new function(){
        this.loadScript = loadScript;
    }

    function loadScript(src){
        if(src == undefined || src == null){
            throwException("IllegalArgument", "src must not be null or undefined.");
        }

        //console.log("Loading script " + src);
        if(loadedScripts.indexOf(src) > -1){
            //console.log(src + " is already loaded.");
            return;
        }

        if($ == undefined){
            throwException("IllegalState", "jQuery cannot be resolved.");
        }
        $.ajax({
            async: false,
            url: src + "?" + date,
            dataType: "script",
            cache: true
        });
        loadedScripts.push(src);
    }

    function getDate(){
        const date = new Date();
        return date.getFullYear() + "-" + date.getMonth() + "-" + date.getDate() + "/" + date.getHours() + ":" + date.getMinutes();
    }
})();