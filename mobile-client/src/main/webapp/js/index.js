$(function(){
	$("#jksg").next().hide();
	$("#jksg").hide();
	
	$("#yjsc").next().hide();
	$("#yjsc").hide();
	
	$("#tjsp").next().hide();
	$("#tjsp").hide();
	
	$("#ydlsp").next().hide();
	$("#ydlsp").hide();
	
	$("#hgsp").next().hide();
	$("#hgsp").hide();
	
	$("#hxsp").next().hide();
	$("#hxsp").hide();
	
	$("#dftc").next().hide();
	$("#dftc").hide();
	$.ajax({
		   type: "post",
		   async: false,
		   url: _rootUrl + "/goods/getOEMAjax.htm",
		   data:{
			   type : $("#jksgVal").val(),
			   currPage : 1,
			   num : 4
		   },
		   success: function(data){
			   var r = eval("("+data+")");
			   if(r.success){
				   var result = r.result.entry;
				   if(result.length > 0){
					   $("#jksg").next().show();
					   $("#jksg").show();
					   var jksgUl=$(".jksg");
					   for(var i=0 ; i<result.length; i++){
						   var li = $("<li>  </li>");
						   var defPhotoUrl = result[i].defPhotoUrl;
						   if(defPhotoUrl == null || defPhotoUrl == ""){
							   defPhotoUrl = result[i].photoUrl
						   }
						   var a = $("<a href='" + _rootUrl + "/goods/goodsDetail.htm?id="+result[i].id+"'><img src='"+_imgUrl+"/200X200"+defPhotoUrl+"' /></a>") ; 
						   var div =$(" <div class='flr_c_d'><a href='" + _rootUrl + "/goods/goodsDetail.htm?id="+result[i].id+"' title='"+result[i].goodsName+"'>"+result[i].goodsName+"</a></div> ");
						   var span = $(" <span class='flr_c_s'>￥"+result[i].price+"</span>");
						   li.append(a).append(div).append(span);
						   jksgUl.append(li);
					   }
				   }else{
					   $("#jksg").next().hide();
					   $("#jksg").hide();
				   }
			   }
		   }
	});
	
	$.ajax({
		   type: "post",
		   async: false,
		   url: _rootUrl + "/goods/getOEMAjax.htm",
		   data:{
			   type : $("#yjscVal").val(),
			   currPage : 1,
			   num : 4
		   },
		   success: function(data){
			   var r = eval("("+data+")");
			   if(r.success){
				   var result = r.result.entry;
				   if(result.length > 0){
					   $("#yjsc").next().show();
					   $("#yjsc").show();
					   var yjscUl=$(".yjsc");
					   for(var i=0 ; i<result.length; i++){
						   var li = $("<li class='a_c_u_l_l_ul_li'>  </li>");
						   var defPhotoUrl = result[i].defPhotoUrl;
						   if(defPhotoUrl == null || defPhotoUrl == ""){
							   defPhotoUrl = result[i].photoUrl
						   }
						   var a = $("<a href='" + _rootUrl + "/goods/goodsDetail.htm?id="+result[i].id+"'><img src='"+_imgUrl+"/200X200"+defPhotoUrl+"' /></a>") ; 
						   var div =$(" <div class='flr_c_d'><a href='" + _rootUrl + "/goods/goodsDetail.htm?id="+result[i].id+"' title='"+result[i].goodsName+"'>"+result[i].goodsName+"</a></div> ");
						   var span = $(" <span class='flr_c_s'>￥"+result[i].price+"</span>");
						   li.append(a).append(div).append(span);
						   yjscUl.append(li);
					   }
				   }else{
					   $("#yjsc").next().hide();
					   $("#yjsc").hide();
				   }
			   }
		   }
	});
	
	$.ajax({
		   type: "post",
		   async: false,
		   url: _rootUrl + "/goods/getOEMAjax.htm",
		   data:{
			   type : $("#tjspVal").val(),
			   currPage : 1,
			   num : 4
		   },
		   success: function(data){
			   var r = eval("("+data+")");
			   if(r.success){
				   var result = r.result.entry;
				   if(result.length > 0){
					   $("#tjsp").next().show();
					   $("#tjsp").show();
					   var tjspUl=$(".tjsp");
					   for(var i=0 ; i<result.length; i++){
						   var li = $("<li class='a_c_u_l_l_ul_li'>  </li>");
						   var defPhotoUrl = result[i].defPhotoUrl;
						   if(defPhotoUrl == null || defPhotoUrl == ""){
							   defPhotoUrl = result[i].photoUrl
						   }
						   var a = $("<a href='" + _rootUrl + "/goods/goodsDetail.htm?id="+result[i].id+"'><img src='"+_imgUrl+"/200X200"+defPhotoUrl+"' /></a>") ; 
						   var div =$(" <div class='flr_c_d'><a href='" + _rootUrl + "/goods/goodsDetail.htm?id="+result[i].id+"' title='"+result[i].goodsName+"'>"+result[i].goodsName+"</a></div> ");
						   var span = $(" <span class='flr_c_s'>￥"+result[i].price+"</span>");
						   li.append(a).append(div).append(span);
						   tjspUl.append(li);
					   }
				   }else{
					   $("#tjsp").next().hide();
					   $("#tjsp").hide();
				   }
			   }
		   }
	});
	
	$.ajax({
		   type: "post",
		   async: false,
		   url: _rootUrl + "/goods/getOEMAjax.htm",
		   data:{
			   type : $("#ydlspVal").val(),
			   currPage : 1,
			   num : 4
		   },
		   success: function(data){
			   var r = eval("("+data+")");
			   if(r.success){
				   var result = r.result.entry;
				   if(result.length > 0){
					   $("#ydlsp").next().show();
					   $("#ydlsp").show();
					   var ydlspUl=$(".ydlsp");
					   for(var i=0 ; i<result.length; i++){
						   var li = $("<li class='a_c_u_l_l_ul_li'>  </li>");
						   var defPhotoUrl = result[i].defPhotoUrl;
						   if(defPhotoUrl == null || defPhotoUrl == ""){
							   defPhotoUrl = result[i].photoUrl
						   }
						   var a = $("<a href='" + _rootUrl + "/goods/goodsDetail.htm?id="+result[i].id+"'><img src='"+_imgUrl+"/200X200"+defPhotoUrl+"' /></a>") ; 
						   var div =$(" <div class='flr_c_d'><a href='" + _rootUrl + "/goods/goodsDetail.htm?id="+result[i].id+"' title='"+result[i].goodsName+"'>"+result[i].goodsName+"</a></div> ");
						   var span = $(" <span class='flr_c_s'>￥"+result[i].price+"</span>");
						   li.append(a).append(div).append(span);
						   ydlspUl.append(li);
					   }
				   }else{
					   $("#ydlsp").next().hide();
					   $("#ydlsp").hide();
				   }
			   }
		   }
	});
	
	$.ajax({
		   type: "post",
		   async: false,
		   url: _rootUrl + "/goods/getOEMAjax.htm",
		   data:{
			   type : $("#hgspVal").val(),
			   currPage : 1,
			   num : 4
		   },
		   success: function(data){
			   var r = eval("("+data+")");
			   if(r.success){
				   var result = r.result.entry;
				   if(result.length > 0){
					   $("#hgsp").next().show();
					   $("#hgsp").show();
					   var hgspUl=$(".hgsp");
					   for(var i=0 ; i<result.length; i++){
						   var li = $("<li class='a_c_u_l_l_ul_li'>  </li>");
						   var defPhotoUrl = result[i].defPhotoUrl;
						   if(defPhotoUrl == null || defPhotoUrl == ""){
							   defPhotoUrl = result[i].photoUrl
						   }
						   var a = $("<a href='" + _rootUrl + "/goods/goodsDetail.htm?id="+result[i].id+"'><img src='"+_imgUrl+"/200X200"+defPhotoUrl+"' /></a>") ; 
						   var div =$(" <div class='flr_c_d'><a href='" + _rootUrl + "/goods/goodsDetail.htm?id="+result[i].id+"' title='"+result[i].goodsName+"'>"+result[i].goodsName+"</a></div> ");
						   var span = $(" <span class='flr_c_s'>￥"+result[i].price+"</span>");
						   li.append(a).append(div).append(span);
						   hgspUl.append(li);
					   }
				   }else{
					   $("#hgsp").next().hide();
					   $("#hgsp").hide();
				   }
			   }
		   }
	});
	//海鲜
	$.ajax({
		   type: "post",
		   async: false,
		   url: _rootUrl + "/goods/getOEMAjax.htm",
		   data:{
			   type : $("#hxspVal").val(),
			   currPage : 1,
			   num : 4
		   },
		   success: function(data){
			   var r = eval("("+data+")");
			   if(r.success){
				   var result = r.result.entry;
				   if(result.length > 0){
					   $("#hxsp").next().show();
					   $("#hxsp").show();
					   var hxspUl=$(".hxsp");
					   for(var i=0 ; i<result.length; i++){
						   var li = $("<li class='a_c_u_l_l_ul_li'>  </li>");
						   var defPhotoUrl = result[i].defPhotoUrl;
						   if(defPhotoUrl == null || defPhotoUrl == ""){
							   defPhotoUrl = result[i].photoUrl
						   }
						   var a = $("<a href='" + _rootUrl + "/goods/goodsDetail.htm?id="+result[i].id+"'><img src='"+_imgUrl+"/200X200"+defPhotoUrl+"' /></a>") ; 
						   var div =$(" <div class='flr_c_d'><a href='" + _rootUrl + "/goods/goodsDetail.htm?id="+result[i].id+"' title='"+result[i].goodsName+"'>"+result[i].goodsName+"</a></div> ");
						   var span = $(" <span class='flr_c_s'>￥"+result[i].price+"</span>");
						   li.append(a).append(div).append(span);
						   hxspUl.append(li);
					   }
				   }else{
					   $("#hxsp").next().hide();
					   $("#hxsp").hide();
				   }
			   }
		   }
	});
	// 地方特产
	$.ajax({
		   type: "post",
		   async: false,
		   url: _rootUrl + "/goods/getOEMAjax.htm",
		   data:{
			   type : $("#dftcVal").val(),
			   currPage : 1,
			   num : 4
		   },
		   success: function(data){
			   var r = eval("("+data+")");
			   if(r.success){
				   var result = r.result.entry;
				   if(result.length > 0){
					   $("#dftc").next().show();
					   $("#dftc").show();
					   var dftcUl=$(".dftc");
					   for(var i=0 ; i<result.length; i++){
						   var li = $("<li class='a_c_u_l_l_ul_li'>  </li>");
						   var defPhotoUrl = result[i].defPhotoUrl;
						   if(defPhotoUrl == null || defPhotoUrl == ""){
							   defPhotoUrl = result[i].photoUrl
						   }
						   var a = $("<a href='" + _rootUrl + "/goods/goodsDetail.htm?id="+result[i].id+"' ><img src='"+_imgUrl+"/200X200"+defPhotoUrl+"' /></a>") ; 
						   var div =$(" <div class='flr_c_d'><a href='" + _rootUrl + "/goods/goodsDetail.htm?id="+result[i].id+"'  title='"+result[i].goodsName+"'>"+result[i].goodsName+"</a></div> ");
						   var span = $(" <span class='flr_c_s'>￥"+result[i].price+"</span>");
						   li.append(a).append(div).append(span);
						   dftcUl.append(li);
					   }
				   }else{
					   $("#dftc").next().hide();
					   $("#dftc").hide();
				   }
			   }
		   }
	});
	
});