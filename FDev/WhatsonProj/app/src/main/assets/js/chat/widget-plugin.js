/***********************************************************************
 * Copyright Genesys Laboratories. All Rights Reserved
 ************************************************************************/

define([], function() {
    var log_prefix = "HLBB widget-plugin: ";
    var _voice_agents;
    var _parentInteractionId;
    var _watsonConversationId;
    var _contactId;
    var _ixnKVP;
    var _callbackScheduled;
    var _botAgentName;
    var _origPhoneNumber, _replacedPhoneNumber, _inputPhoneNumber;
    var _phoneNumberChanged = false;
    var _lastAgentMsg;
    var _cbOpened = false

    /**
     * Initialize this module
     */
    function initialize() {
        console.log(log_prefix + "initialize");
        if (!window._genesys.widgets.extensions) window._genesys.widgets.extensions = {};

        window._genesys.widgets.extensions.HLBB = function ($, CXBus, CXCommon) {
            var oPlugin = CXBus.registerPlugin("HLBB");

            // Chat ready
            /*
            oPlugin.subscribe('WebChatService.ready', function (e) {
            	console.log(log_prefix + 'Chat ready');
            	triggerChat();
            });*/

            // Chat Window is closed
            oPlugin.subscribe('WebChat.closed', function (e) {
                console.log(log_prefix + 'Chat closed');
                try {
                	clearTimeout(closeChatTimer);
                	showChatButton();
                	openRateUsDialog();
                } catch(e) {                	
                };
            });
            
            // Client disconnected
            oPlugin.subscribe('WebChatService.clientDisconnected', function (e) {
                console.log(log_prefix + 'Client disconnected');
                endChat();
            }); 

            // Chat is started
            oPlugin.subscribe('WebChatService.started', function (e) {
                console.log(log_prefix + 'Chat started');
                hideChatButton();
                attachSendEvent();
            }); 

            // Chat is restored
            oPlugin.subscribe('WebChatService.restored', function (e) {
                console.log(log_prefix + 'Chat restored');
                hideChatButton();
                attachSendEvent();
            });

            // initialize a few things when we're opened
            oPlugin.subscribe('WebChat.opened', function (e) {
                console.log(log_prefix + 'WebChat opened');
                _voice_agents = null;
                _parentInteractionId = null;
                _watsonConversationId = null;
                _contactId = null;
                _ixnKVP = null;
                _callbackScheduled = false;
                _botAgentName = null;
            });

            // hide some elements if an immediate voice callback isn't supported
            oPlugin.subscribe('Callback.opened', function (e) {
                console.log(log_prefix + 'Callback opened');
                if (_voice_agents === 0) {
                    $('.cx-right-away').hide();
                    $('.cx-callback-schedule').css('float', 'left');
                    $('.cx-callback-later').hide();
                }
                
                //custom function of startsWith in case not compatible by browser
                if (!String.prototype.startsWith) {
                    String.prototype.startsWith = function(searchString, position) {
                        position = position || 0;
                        return this.indexOf(searchString, position) === position;
                    };
                }
                
            	var c = $('div.intl-tel-input.allow-dropdown').parent('td');

            	$('button.i18n.btn.cx-callback-confirm.btn-primary').click(function(event) {
            		// validate first name
            		var firstNameVal = $('#cx_form_callback_firstname').val();
            		_inputPhoneNumber = $('input#cx_form_callback_phone_number.form-control.i18n.cx-callback-phone-number').val();
            		
            		if (firstNameVal && _inputPhoneNumber && _inputPhoneNumber.length >= 10) {
	                	var finalNo = "";
	                	var index;
	                    if (!_phoneNumberChanged) {
	                        console.log(log_prefix + 'Put original phone number back');
	                    	finalNo = _origPhoneNumber.replace('+', '');
	                        if (finalNo.startsWith('60')) {
	                        	index = finalNo.indexOf('60');
	                        	finalNo = finalNo.substring(index + 1);
	                        } else if (!finalNo.startsWith('0')) {
	                        	finalNo = '00' + finalNo;
	                        } else if (finalNo.startsWith('02')) {
	                        	finalNo = '0065' + finalNo.substring(2);
	                        }
	                        $('input#cx_form_callback_phone_number.form-control.i18n.cx-callback-phone-number').val(finalNo);
	                    }
	                    else {
	                        console.log(log_prefix + 'Phone number was changed by user');
	                        var newNumber = $('input#cx_form_callback_phone_number.form-control.i18n.cx-callback-phone-number').val();
	
	                        if(newNumber.indexOf('x') >= 0){
	                            c.find('input#cx_form_callback_phone_number').addClass('cx-border-error').addClass('cx-error');
	                            c.addClass('left-inner-addon');
	                            c.find('.icon-alert-circle').show();
	                            event.stopImmediatePropagation(); 
	                        } else {
	                        	finalNo = newNumber.replace('+', '');
	                            if (finalNo.startsWith('60')) {
	                            	index = finalNo.indexOf('60');
	                            	finalNo = finalNo.substring(index + 1);
	                            } else if (!finalNo.startsWith('0')) {
	                            	finalNo = '00' + finalNo;
	                            } else if (finalNo.startsWith('02')) {
	                            	finalNo = '0065' + finalNo.substring(2);
	                            }
	                            $('input#cx_form_callback_phone_number.form-control.i18n.cx-callback-phone-number').val(finalNo);
	                        }
	                    }
	
	    				var fn, ln, pn, em, previewData;
						fn = $("input#cx_form_callback_firstname").val();
						ln = $("input#cx_form_callback_lastname").val();
						pn = finalNo;
						em = $("input#cx_form_callback_email").val();
						previewData = fn + "," + ln + "," + pn + "," + em;
						var callbackUserDataParams = {
							userData: {
								_agent_preview_data: previewData,
								ParentInteractionId: _parentInteractionId,
								ConversationID: _watsonConversationId,
								ContactId: _contactId,								
								ChatSessionID: pChatSessId,
								SourceChat: pSourceChat,
								IsLoggedInFlag: _ixnKVP,
								FirstName: fn,
								LastName: ln,
								_customer_number: pn
							}
						};
						
						oPlugin.command('Callback.configure', callbackUserDataParams).done(function() {
							console.log(log_prefix + 'and reconfigured');
						});
            		}
                });
            	
				//Done button clicked in confirmation window
                $('div.cx-button-group.cx-callback-done > button.i18n.btn.btn-primary').click(function(event) {
                    console.log(log_prefix + 'Confirm button clicked');
                    oPlugin.command("WebChat.close").done(function(){
                        console.log("Chat is closed");
                    });
                }); 
                                
                // swap the widget click handler so that our custom version is first
                var handlers = $._data($('button.i18n.btn.cx-callback-confirm.btn-primary')[0], "events").click;
                var handler = handlers.pop();
                handlers.splice(0, 0, handler);
                
                $('input#cx_form_callback_phone_number.form-control.i18n.cx-callback-phone-number').keyup(function() {
                    //remove error messages
                    c.find('.icon-alert-circle').hide();
                    c.removeClass('left-inner-addon');
                    c.find('input#cx_form_callback_phone_number').removeClass('cx-border-error').removeClass('cx-error ');
                            
                    if ($(this).val() !== _replacedPhoneNumber) {
                        _phoneNumberChanged = true;
                        if($(this).val().indexOf('x') < 0){
                            console.log(log_prefix + 'Phone number changed');
                            $('button.i18n.btn.cx-callback-confirm.btn-primary').prop('disabled',false);
                        }else{
                            console.log(log_prefix + 'X needs to be cleared');
                            $('button.i18n.btn.cx-callback-confirm.btn-primary').prop('disabled',true);
                        }
                    }
                });
                
                var pdpa = "<p class='pdpaSentence'>We will protect your information under PDPA 2010.</p>";
                var footerButtonContainer = $('DIV.cx-button-container');
                footerButtonContainer.prepend(pdpa);

                /*
				$("div.cx-callback-container").find("div.cx-callback-details").mouseleave(function() {
					fn = $("input#cx_form_callback_firstname").val();
					ln = $("input#cx_form_callback_lastname").val();
					if (!_phoneNumberChanged) {
						pn = _origPhoneNumber.replace('+', '');
					} else {
						pn = $("input#cx_form_callback_phone_number").val().replace('+', '');
					}
                    if (pn.startsWith('60')) {
                    	index = pn.indexOf('60');
                    	pn = pn.substring(index + 1);
                    } else if (!pn.startsWith('0')) {
                    	pn = '00' + pn;
                    }					
					em = $("input#cx_form_callback_email").val();
					previewData = fn + "," + ln + "," + pn + "," + em;

					var callbackUserDataParams = {
						userData: {
							_agent_preview_data: previewData,
							ParentInteractionId: _parentInteractionId,
							ChatSessionID: pChatSessId,
							SourceChat: pSourceChat,
							IsLoggedInFlag: _ixnKVP,
							FirstName: fn,
							LastName: ln,
							_customer_number: pn
						}
					};
					
					oPlugin.command('Callback.configure', callbackUserDataParams).done(function() {
						console.log(log_prefix + 'and reconfigured');
					});
				});
				*/
            });

        	// Schedule error, restore original input phone number
        	oPlugin.subscribe("CallbackService.scheduleError", function(e) {
                console.log("An error happend when submitting callback request");
               //add back country code, for below it should reset to the original number after the masking
                $('input#cx_form_callback_phone_number.form-control.i18n.cx-callback-phone-number').val(_inputPhoneNumber);   
        	});
            
            // detect if a callback has been done
            oPlugin.subscribe("CallbackService.scheduled", function(e) {
                console.log(log_prefix + "callback is scheduled");
                _callbackScheduled = true;

                $(".control-label[name='phonenumber']").html(_phoneNumberChanged ?
                        $('input#cx_form_callback_phone_number.form-control.i18n.cx-callback-phone-number').val() :
                        _replacedPhoneNumber);
                
                var conf_intro = '<p class="confirmation-intro">Great! We will give you a call from <strong><font color="white">03-76268899</font></strong> at your preferred phone number soon.</p>';
                var conf_outro = '<p class="confirmation-outro">We look forward to speaking to you!</p>';
                var confirmationWrapperDiv = $('.confirmation-wrapper');
                confirmationWrapperDiv.prepend(conf_intro);
                confirmationWrapperDiv.append(conf_outro);                    
                
                // send a dummy message so that Watson can do statistics
                e = {
                    message: "CALL_ME_BACK"
                };

                oPlugin.command('WebChatService.sendCustomNotice', e).done(function() {
                	console.log(log_prefix + "callback message is sent");
                    // end the chat window
                    oPlugin.command('WebChatService.endChat');
                });
                
                var desiredtime = document.getElementsByName("desiredtime");
                var currval = $(desiredtime).text();
                var pos = currval.lastIndexOf('-');
                $(desiredtime).html(currval.substring(0, pos) + '<br/>' + currval.substring(pos + 1));
                                
            });

            // change the avatar on the "agent typing started" event.
            oPlugin.subscribe("WebChatService.agentTypingStarted", function(e){
                console.log(log_prefix +  "agent is typing, id is " + e.data.from.id);
                $('.them[data-id=' + e.data.from.id + ']').find('.avatar').addClass('cx-img-bot');
            });

            // process a new message that is going to the Widget
            oPlugin.subscribe('WebChat.messageAdded', function(e) {
            	try {
            		closeTimerDialog();
            		startSessionTimer();
            		keepSessionAlive();
            	} catch(e) {            		
            	};            	            	
            	
                var html = e.data.html;

                // custom avatar
                if ($(html).find('.isBot').length) {
                    console.log('BLAH: ' + $(html).html());
                    $(html).find('.avatar').addClass('cx-img-bot').removeClass('cx-img-map avatar-agent').children().hide();
                }
                
                $('.HLBB.ChatNow').off();
                $('.HLBB.ChatNow').click(function(event) {
                    event.preventDefault();

                    // send a dummy message so that Watson can do statistics
                    var e = {
                        message: "CHAT_NOW"
                    };

                    oPlugin.command('WebChatService.sendCustomNotice', e).done(function() {
                        console.log(log_prefix + 'CHAT_NOW custom notice has been injected');

                        setTimeout(function() {
                            // bit of mucking about to ensure the message coming back to the Widget is from "Watson"
                            var msgToSend = {
                                transferToQueue: 1,
                                text: "Transferring now"
                            };

                            e = {
                                message: JSON.stringify(msgToSend)
                            };

                            oPlugin.command('WebChatService.sendCustomNotice', e).done(function(){
                                console.log(log_prefix + 'Message has been injected');
                            });
                        }, 100);
                    });
                    
                    console.log('HLBB.ChatNow.click');
                    isAgentConnected = true;
                });

                $('.HLBB.CallMeBack').off();
                $('.HLBB.CallMeBack').click(function() {
            	   if (_cbOpened) {
            	       console.warn(log_prefix + 'callback window has already opened');
            	       return;
            	   }
                	
                    // don't let customer do multiple callbacks. Not required any more as requirement is to close
                    // chat session after the callback has been done.
                    if (_callbackScheduled) {
                        console.warn(log_prefix + 'callback has already been done');

                        var msg = {
                            type: 'text',
                            name: _botAgentName,
                            text: 'Callback has already been done'
                        };

                        oPlugin.command('WebChat.injectMessage', msg);
                        return;
                    }

                    // code snippet to get a session cookie value
                    function get_cookie(Name, defVal) {
                        var search = Name + "=";
                        var returnvalue = defVal;

                        if (document.cookie.length > 0) {
                            offset = document.cookie.indexOf(search);
                            // if cookie exists
                            if (offset != -1) {
                                offset += search.length;
                                // set index of beginning of value
                                end = document.cookie.indexOf(";", offset);
                                // set index of end of cookie value
                                if (end == -1) end = document.cookie.length;
                                returnvalue=decodeURI(document.cookie.substring(offset, end));
                            }
                        }
                        return returnvalue;
                    }

                    _origPhoneNumber = get_cookie("phone", pMobileNo);
                    if (_origPhoneNumber.length > 7) {
                    	_replacedPhoneNumber = _origPhoneNumber.substring(0, 5) + "xxx" + _origPhoneNumber.substring(8);
                    } else {
                    	_replacedPhoneNumber = _origPhoneNumber;
                    }

                    var callbackParams = {
                        // TODO: will have to be modified in portal
                        form: {
                            autoSubmit: false,
                            firstname: get_cookie("firstName", (pFirstname === 'Anonymous') ? '' : pFirstname),
                            lastname: get_cookie("lastName", pLastname),
                            email: get_cookie("email", pEmail),
                            phonenumber: _replacedPhoneNumber,
                            subject: "" ,
                            userData: userDataParam
                        }
                    };

                    oPlugin.command('Callback.open', callbackParams).done(function(response){
                        console.log(log_prefix + 'Callback has been opened');
                    });
                });
            });

            oPlugin.subscribe('Callback.closed', function (e) {
    		   console.log(log_prefix + 'Callback has been closed');
    		   _cbOpened = false;
    		});
            
            oPlugin.subscribe("WebChat.ready", function(){
                // Register a preprocessor that lets you intercept incoming messages and modify them
                oPlugin.command("WebChatService.registerPreProcessor", {preprocessor: function(oMessage){
                    console.log("msg: " + JSON.stringify(oMessage));

                    try {
                        if (oMessage.from.type == "Agent" && oMessage.text) {
                            // Custom notice message? (comes from VAA)
                            if (oMessage.type === 'CustomNotice') {
                                console.log(log_prefix + oMessage.text);

                                var VAA = JSON.parse(oMessage.text);

                                if (VAA.action === 'DisplayText') {
                                    oMessage.text = '<span class="isBot">' + CXCommon.linkify(VAA.text) + '</span>';
                                    oMessage.type = 'Message';
                                    oMessage.html = true;
                                }
                                else if (VAA.action === 'AgentAvailability') {
                                    _voice_agents = VAA.voice_agents;
                                    var chat_agents = VAA.chat_agents;
                                    var text = '<span class="isBot">' + CXCommon.linkify(VAA.text) + "<br /><br />";
                                    _parentInteractionId = VAA.ixnId;
                                    _watsonConversationId = VAA.watsonConversationId;
                                    _contactId = VAA.contactId;
                                    _ixnKVP = VAA.ixnKVP;
                                    _botAgentName = oMessage.from.name;

                                    if (chat_agents > 0) {
                                        text += '<a class="HLBB ChatNow" style="cursor:pointer;">Chat Now</a> or ';
                                    }

                                    text += '<a class = "HLBB CallMeBack" style="cursor:pointer;">Call Me Back</a></span>';
                                    oMessage.text = text;
                                    oMessage.type = 'Message';
                                    oMessage.html = true;
                                }
                                _lastAgentMsg = VAA.text;
                            }
                            else if (oMessage.type === 'Message') {
                                if (_lastAgentMsg === oMessage.text) {
                                    // hide identical messages due to VAA sending a custom notice and a regular text msg
                                    console.log(log_prefix + "agent msg ignored");
                                    oMessage.type = '';
                                }

                                _lastAgentMsg = null;
                            }
                        }
                    }
                    catch (ex) {
                        console.warn("Could not parse " + oMessage.text);
                    }

                    return oMessage;
                }});
                
                triggerChat();
            });
            
            oPlugin.publish("ready");  // Tell the bus that we've initialised successfully (but not opened yet)
            console.log(log_prefix + "HLBB widget-plugin started");
        };
    }

    return {
        initialize: initialize
    };
});

