/***********************************************************************
* Copyright Genesys Laboratories. All Rights Reserved
************************************************************************/

require.config({
    paths: {
    	'text': 'chat/text'
    }
});

require(['chat/Widget'], function(app) {
    app.initialize();
});