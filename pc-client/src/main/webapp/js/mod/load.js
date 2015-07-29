require.config({
	/*
	path:{
		"jquery": "http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.3.min" 
		"msg"	:	'js/mod'
	}*/
	baseUrl:'/js/mod',
	
	shim : {
		'colne':{
			exports:'clone'
		},
		'msg':{
			deps:['clone'],
			exports:'msg'
		}
	}
});


require(['jquery','msg'] , function($,msg){

});