$(function(){$(".gidvIg img").each(function() {var b=$(this),a=b.attr("src");challs_flash_alterImg(b);$(b).attr("src",a);});});function challs_flash_alterImg(a){a.load(function(){a.removeAttr("style");var b=a.width(),c=a.height();if(b>c){a.width(75);a.height((75*c)/b);a.css("top",(75-a.height())/2+"px");}else if(b<c){a.height(75);a.width((75*b)/c);a.css("left",(75-a.width())/2+"px");}else{a.width(75);a.height(75);}a.show();});}