/***********************************************************************
 * Copyright Genesys Laboratories. All Rights Reserved
 *
 * Custom HLBB Widget test code harness
 ************************************************************************/

define(['chat/config', 'text!../../css/chat/widgets.min.css'],
        function(config, widgetCSS) {
    var log_prefix = "HLBB Widget: ";

    /**
     * Initialize this module
     */
    function initialize() {
        console.log(log_prefix + "initialize");
        $('head').append($('<style>' + widgetCSS + '</style>'));

        if (!window._genesys) window._genesys = {};
        if (!window._gt) window._gt = [];

        window._genesys.widgets = {
            main: {
                debug: false,
                theme: "dark",
                lang: "en",
                i18n: config.I18N_URL, //add this line to include localization file
                customStylesheetID: "genesys_widgets_HLBB_custom",
                plugins: [
                    "cx-webchat",
                    "cx-webchat-service",
                    "cx-calendar",
                    "cx-callback",
                    "cx-callback-service"
                ]
            },
            webchat: {
                dataURL: config.WEBCHAT_URL,
                autoInvite: {
                    enabled: false,
                    timeToInviteSeconds: 5,
                    inviteTimeoutSeconds: 30
                },
                chatButton: {
                    enabled: true,
                    openDelay: 1000,
                    effectDuration: 300,
                    hideDuringInvite: true
                }
            },
            callback: {
                dataURL: config.CALLBACK_URL,
                userData: {},
                countryCodes: true,
                formValidation: true,
                callDirection: 'USERTERMINATED'
            }
        };

        // defer the load until we have configuration
        require(['chat/widgets.min', 'chat/widget-plugin'], function(widget, widgetPlugin) {
            widgetPlugin.initialize();
            console.log(log_prefix + "initialize completed");
        });
    }

    return {
        initialize: initialize
    };
});
