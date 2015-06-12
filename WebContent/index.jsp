<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="initial-scale=1, maximum-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>reCAPTCHA Example</title>

<script type="text/javascript">
	var onloadCallback = function() {
		widgetId1 = grecaptcha.render('reCAPTCHA', {
			'sitekey' : '6Le32AYTAAAAAHgdBXngWDQpA_6QRGEOzczzSy69',
			'callback' : verifyCallback,
			'theme' : 'light'
		});
  	};

  	var verifyCallback = function(response) {
  		$.ajax({
            url:'ReCaptchaVerify',
            type:'post',
            data: {"response":response},
            success:function(data){
				alert('verify succses.');
            },
            error: function (data) {
                alert('verify failed.');
            }
        })
	};

	function checkForm() {
	
	
		var recaptchaElement = document.getElementById("reCAPTCHA");
		var recaptchaResponse = "";
		try {
			recaptchaResponse = grecaptcha.getResponse(widgetId1);
			
		} catch (error) {
			alert("ERROR : " + error.decsription);
			return false;
		}
		
		if (recaptchaResponse == "") {
			alert("fail");
			return false;
		} else {
			alert("success");	
			return true;
		}
	}

</script>
</head>
<body>
<form onsubmit="return checkForm();">
<div id="reCAPTCHA"></div>
<input type="submit" value="전송" id="ok" name="ok"/>
</form>
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script src="//www.google.com/recaptcha/api.js?hl=ko&onload=onloadCallback&render=explicit" async defer></script>
</body>
</html>