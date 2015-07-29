$(function(){
	var floor_vid=$("#floor_vid");
	for(var i=0; i<window.index_mobile_floor.length;i++){
		var mobile_floor_obj = window.index_mobile_floor[i];
		$.ajax({
			   type: "post",
			   async: false,
			   url: _rootUrl + "/goods/getOEMAjax.htm",
			   data:{
				   type : mobile_floor_obj.type,
				   currPage : 1,
				   num : 4
			   },
			   success: function(data){
				   var r = eval("("+data+")");
				   if(r.success){
					   var result = r.result.entry;
					   if(result.length > 0){
						   //var jksgUl=$(".jksg");
						   var floor_tit=$("<ul id='jksg' class='clear floor_tit'></ul>");
							var name = $("<li>"+mobile_floor_obj.name+"</li>");
							var more = $("<li><a href='" + _rootUrl + "/goods/getOEM.htm?type="+mobile_floor_obj.type+"'>更多...</a></li>");
							var ul=$("<ul class='clear floor_cn '></ul>");
							floor_tit.append(name).append(more);
							floor_vid.append(floor_tit).append(ul);
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
							   ul.append(li);
						   }
					   }
				   }
			   }
		});
			
	}
	
});