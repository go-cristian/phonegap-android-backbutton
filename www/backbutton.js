var backPlugin = {
    createCallback: function(successCallback, errorCallback) {
        cordova.exec(
            successCallback, // success callback function
            errorCallback, // error callback function
            'BackPlugin', // mapped to our native Java class called "CalendarPlugin"
            'addCallback', // with this action name
            []
        ); 
     },
     removeCallback: function(successCallback, errorCallback) {
        cordova.exec(
            successCallback, // success callback function
            errorCallback, // error callback function
            'BackPlugin', // mapped to our native Java class called "CalendarPlugin"
            'removeCallback', // with this action name
            []
        ); 
     }
}
module.exports = backPlugin;