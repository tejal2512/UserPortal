$(document).ready(function(){
console.log("This is running")
        $.ajax({
            url: window.location + "/countrydropdown",
            cache: false,
            dataType:'json',
            success:function(result){
               $.each(result,function(key,value){
                        console.log(value);
                        $('<option>').val(key).text(value).appendTo("#countryId");
               });
            }
        });
});
$(document).on("change","#countryId",function(){
    $("#stateId").find('option').remove();
    $('<option>').val("").text("-Select State-").appendTo("#stateId");
    $("#cityId").find('option').remove();
    $('<option>').val("").text("-Select City-").appendTo("#cityId");
    var selectedCntryId=$("#countryId").val();
    $.ajax({
                url: window.location + "/statedropdown",
                data:{
                    countryId : selectedCntryId
                },
                dataType:'json',
                success:function(result){
                   $.each(result,function(key,value){
                            console.log(value);
                            $('<option>').val(key).text(value).appendTo("#stateId");
                   });
                }
    });
});
$(document).on("change","#stateId",function(){
    $("#cityId").find('option').remove();
    $('<option>').val("").text("-Select City-").appendTo("#cityId");
    var selectedStateId=$("#stateId").val();
    $.ajax({
                url: window.location + "/citydropdown",
                data:{
                    stateId : selectedStateId
                },
                dataType:'json',
                success:function(result){
                   $.each(result,function(key,value){
                            console.log(value);
                            $('<option>').val(key).text(value).appendTo("#cityId");
                   });
                }
    });
});
$(function(){

    $("#email").blur(function () {
        console.log("Email validation  is running")
        var enteredEmail=$("#email").val();
        $.ajax({
            url:window.location + "/validateEmail",
            data:"email=" +enteredEmail,
            success:function(result){
                if(result=='Duplicate'){
                    $("#emailMsg").html("Email already registered");
                    $("#email").focus();
                    $("#submit").prop("disabled",true);
                }else{
                     $("#emailMsg").html("");
                     $("#submit").prop("disabled",false);
                }
            }
        });
    });


});
