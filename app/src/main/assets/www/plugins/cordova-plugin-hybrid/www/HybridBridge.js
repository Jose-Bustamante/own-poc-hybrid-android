cordova.define("cordova-plugin-hybrid.HybridBridge", function(require, exports, module) {
    var exec = require('cordova/exec'),
        cordova = require('cordova');

    function HybridBridge() {
    }

    HybridBridge.prototype.showAlert = function(item, successCallback, errorCallback) {
        exec(successCallback, errorCallback, "HybridBridge", "showAlert", [item]);
    };

    module.exports = new HybridBridge();
});