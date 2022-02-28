function getWindowHeight() {
	return window.innerHeight;
}

function getChatHeight() {
	return $('.cx-widget.cx-webchat').innerHeight();
}

function repositionChat() {
	$('.cx-widget.cx-webchat').css('top', getWindowHeight()-getChatHeight());
}

function onChatResize() {
	repositionChat();
}

function toggleChat() {
	$('.cx-widget.cx-webchat').toggleClass('minimized');
	onChatResize();
}

$(window).resize(function() {
	repositionChat();
});

$(window).ready(function() {
	repositionChat();
});


