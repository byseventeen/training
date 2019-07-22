$(".two").click(function() {
        $(".one").show();
});

$(".down").click(function() {
    $(".down").hide();
    $(".listul2").show();
    $(".up").show();
});
$(".up").click(function() {
    $(".up").hide();
    $(".listul2").hide();
    $(".down").show();
});

$(document).ready(function(){
 
  $(".up").hide();
 
});
