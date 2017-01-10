var exec = require('cordova/exec');

exports.checkIntConn = function(arg0, success, error) {
    exec(success, error, "CheckInternet", "checkIntConn", [arg0]);
};
