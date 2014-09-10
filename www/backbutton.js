var calendarPlugin = {
    createEvent: function(successCallback, errorCallback) {
        console.log('to Cal exec');
        cordova.exec(
            successCallback, // success callback function
            errorCallback, // error callback function
            'BackPlugin', // mapped to our native Java class called "CalendarPlugin"
            'addCalendarEntry', // with this action name
            []
        ); 
     }
}
module.exports = calendarPlugin;