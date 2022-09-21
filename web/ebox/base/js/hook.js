$.ajaxSetup({
    global: true,
	headers: {
		"Application-Code":APPLICATION_CODE,
	},
	beforeSend: function (XMLHttpRequest) {
    }  
});