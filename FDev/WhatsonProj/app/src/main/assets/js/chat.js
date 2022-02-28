// Exernalize form variables here
var webchatUrl, callbackUrl, i18nUrl, webchatSurveyUrl, webchatSurveyUrlAgent;
var userDataString;	//{isLoggedIn: "true", chatId: ""}
var isSurvey, isRIB = false, isAgentConnected = false;
var closeChatTimer;
var globalParamNickname;
var pFirstname, pLastname, pEmail, pMobileNo, pChatSessId, pSourceChat;

function triggerChat() {	
	console.log('[triggerChat: nickname]=' + globalParamNickname);
	if (globalParamNickname != null) {
		closeChat();

		var widgetBus = window._genesys.cxwidget.bus;
		
		if (widgetBus) {
			eval('userDataParam=' + userDataString);
			var chatParams = {
				form: {
					autoSubmit: true, // prefill and submit automatically and start a chat
					nickname: "",
					firstname: globalParamNickname,
					lastname: " ",
					emailAddress: "",
					subject: ""
				},
				userData: userDataParam					
			};
			
			widgetBus.command("WebChat.open", chatParams).done(function(e) {
					// success scenario
					console.log('open success');
				}).fail(function(e) {
					// failure scenario: error, exception, improper arguments
					console.log('open failed');
				});
		}
		
		globalParamNickname = null;
	}
}

function startChatPostLogin(paramNickname, paramMobileNo) {
	globalParamNickname = paramNickname;
	pFirstname = paramNickname;
	pLastname = ' ';
	pEmail = '';
	pMobileNo = paramMobileNo;
	
	if (!window._genesys) {
		require(['chat/boot']);
	} else {
		triggerChat();
	}
	
	postStartChat();
}

// Helper functions
function startChat(paramNickname) {
	globalParamNickname = paramNickname;
	pFirstname = paramNickname;
	pLastname = ' ';
	pEmail = '';
	pMobileNo = '+60';
	
	if (!window._genesys) {
		require(['chat/boot']);
	} else {
		triggerChat();
	}
	
	postStartChat();
}

function endChatWait(waitTime) {
	try {
		var widgetBus = window._genesys.cxwidget.bus;
		
		if (waitTime > 0) {
			closeChatTimer = setTimeout(function() { closeChat(); }, waitTime);
		} else {
			closeChat();
		}
		
	} catch(e) {
		// ignore
	}	
}

function endChat() {
	// default wait time to 10s
	endChatWait(5000);
}

function closeChat() {
	try {
		var widgetBus = window._genesys.cxwidget.bus;
		
		if (widgetBus) {
			widgetBus.command("WebChat.endChat").done(function(e) {
				// success scenario
				console.log('endChat success');
			}).fail(function(e) {
				// failure scenario: error, exception, improper arguments
				console.log('endChat failed');
			});
			
			widgetBus.command("WebChat.close").done(function(e) {
				// success scenario
				console.log('close success');
			}).fail(function(e) {
				// failure scenario: error, exception, improper arguments
				console.log('close failed');
			});
		}
		
	} catch(e) {
		// ignore
	}	
}

function openRateUsDialog() {
	if (isSurvey == 'true') {
		isSurvey = 'false';
		
		try {
			postChatSurvey();
		} catch(e) {
			// ignore
		}
		
		console.log('isAgentConnected=' + isAgentConnected);
		if (isAgentConnected === true) {
			$('#idSurveyFrame').attr('src', webchatSurveyUrlAgent);
		} else {
			$('#idSurveyFrame').attr('src', webchatSurveyUrl);		
		}
		
		$('#idSurveyFrame').load(function() { this.blur(); });
		// Get the modal 
		var modal = document.getElementById('myModal');
	    modal.style.display = "block"; 
	
		// Get the <span> element that closes the modal 
		var span = document.getElementsByClassName("close-survey")[0];
		
		// When the user clicks on <span> (x), close the modal 
		span.onclick = function() {
		    modal.style.display = "none"; 
		}
	
		// When the user clicks anywhere outside of the modal, close it 
		window.onclick = function(event) {
		    if (event.target == modal) {
		        modal.style.display = "none"; 
		    }
		}
	}
}	

function postChatSurvey() {
	if (isRIB) {
	    $.ajax({
	    	type: "GET",
	    	url: contextPath + "/servlet/chat.jsp?p=SURVEY"
		});
	}
}

function postStartChat() {
	if (isRIB) {
		var chatBtn = document.getElementById('idHiddenChatBtn');		
		if (chatBtn != null) {
			chatBtn.click();
		} else {
		    $.ajax({
		    	type: "GET",
		    	url: contextPath + "/servlet/chat.jsp?p=CHAT"
			});
		}
	}
}				

function showChatButton() {
	var chatContainer = $('#idChatContainer');
	if (chatContainer) {
		chatContainer.css('display', '');
	}
}

function hideChatButton() {
	var chatContainer = $('#idChatContainer');
	if (chatContainer) {
		chatContainer.css('display', 'none');
	}
}

function attachSendEvent() {
	$(":button.send.i18n[data-message='ChatSend']").click(function() {
		var widgetWindow = document.querySelector("div.cx-webchat textarea.form-control");   //this part better use ID of text box.
		var textB = widgetWindow.value;
		var widgetBus = window._genesys.cxwidget.bus;
		
		widgetBus.command("WebChatService.sendMessage", { 
			message: textB
		}).done(function(e) {
			widgetWindow.value="";
		});
	});
}

function closeTimerDialog() {
	if (typeof wvDlgCfmLogout != 'undefined') {
		wvDlgCfmLogout.hide();
	}
}

navigator.browserSpecs = (function(){
    var ua= navigator.userAgent, tem, 
    M= ua.match(/(opera|chrome|safari|firefox|msie|trident(?=\/))\/?\s*(\d+)/i) || [];
    if(/trident/i.test(M[1])){
        tem=  /\brv[ :]+(\d+)/g.exec(ua) || [];
        return {name:'IE',version:(tem[1] || '')};
    }
    if(M[1]=== 'Chrome'){
        tem= ua.match(/\b(OPR|Edge)\/(\d+)/);
        if(tem!= null) return {name:tem[1].replace('OPR', 'Opera'),version:tem[2]};
    }
    M= M[2]? [M[1], M[2]]: [navigator.appName, navigator.appVersion, '-?'];
    if((tem= ua.match(/version\/(\d+)/i))!= null) M.splice(1, 1, tem[1]);
    return {name:M[0],version:M[1]};
})();

function checkBrowserVersion() {
    if (navigator.browserSpecs.name == 'Firefox') {
        if (navigator.browserSpecs.version < 43) {
            disableChat();
        }
    } else if (navigator.browserSpecs.name == 'Chrome') {
        if (navigator.browserSpecs.version < 47) {
            disableChat();
        }
    } else if (navigator.browserSpecs.name == 'IE' || navigator.browserSpecs.name == 'MSIE') {
        if (navigator.browserSpecs.version < 10) {
            disableChat();
        }
    } else if (navigator.browserSpecs.name == 'Safari') {
        if (navigator.browserSpecs.version < 8) {
            disableChat();
        }
    }
}

function disableChat() {
	hideChatButton();
}

// Execute script only after page is loaded
$(document).ready(function() {
    checkBrowserVersion();
});