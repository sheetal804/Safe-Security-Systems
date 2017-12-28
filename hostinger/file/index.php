<?php
/** 
  * @source code designed by Muhsin Mohamed Pc for http://www.howi.in
  * Report bugs and errors to waphunt@gmail.com
  * @This script was coded with the help of many other github projects. I thanks to them
  * @Take backup before editing the script
*/
include('func.php');
error_reporting(0);
//include('head.php');
//set_time_limit(0);
$connection = new mysqli("mysql.hostinger.in","u456135040_raspi","sheetal1995","u456135040_raspi");
if (!$connection) {
  die('Not connected : ' . mysql_error());
}


// Set the active MySQL database

$query = "SELECT * FROM number WHERE 1";
$result = $connection->query($query);
while ($row = $result->fetch_assoc()){

$ser="http://site24.way2sms.com/";
$ckfile = tempnam ("/tmp", "CURLCOOKIE");
$login=$ser."Login1.action";
// * Reciving Username of Way2sms A/c from Html form //
//$uid=input($_REQUEST['uid']);
$uid='8093194313';
// * Reciving Password of Way2sms A/c from Html form //
//$pwd=input($_REQUEST['pwd']);
$pwd='K2328S';
// * To whome the message send to //
//$to=input($_REQUEST['to']);
$to=$row['mobile'];
// * Message to be sended //
//$msg=input($_REQUEST['msg']);
$msg='Please Help!!!...Track me on http://safesystems.esy.es/b.php ';
if(!$to)
{ $to=$uid; }
if(!$msg)
{ $msg=rword(5).rword(5).rword(5).rword(5).rword(5); }
$captcha=input($_REQUEST['captcha']);
flush();
if($uid && $pwd)
{
$ibal="0";
$sbal="0";
$lhtml="0";
$shtml="0";
$khtml="0";
$qhtml="0";
$fhtml="0";
$te="0";
echo '<div class="content">User: <span class="number"><b>'.$uid.'</b></span><br>';
flush();

$loginpost="gval=&username=".$uid."&password=".$pwd."&Login=Login";

$ch = curl_init();
$lhtml=post($login,$loginpost,$ch,$ckfile);
////curl_close($ch);

if(stristr($lhtml,'Location: '.$ser.'vem.action') || stristr($lhtml,'Location: '.$ser.'MainView.action') || stristr($lhtml,'Location: '.$ser.'ebrdg.action'))
{
preg_match("/~(.*?);/i",$lhtml,$id);
$id=$id['1'];
if(!$id)
{
show('<div class="w3-container w3-section w3-red">
<span onclick="this.parentElement.style.display="none"" class="w3-closebtn">&times;</span>
  <p>Check Your Username and Password Inorder to Send SMS</p>
</div> ','darkred');
goto end;
}
// * Login Sucess Message//
show('<div class="w3-container w3-section w3-blue">
<span onclick="this.parentElement.style.display="none"" class="w3-closebtn">&times;</span>
  <p>Login Sucess - Now Wait for SMS Send</p>
</div>','darkgreen');
goto bal;
}
elseif(stristr($lhtml,'Location: http://site2.way2sms.com/entry'))
{
// * Login Faild or SMS Send Error Message 3//
show('<div class="w3-container w3-section w3-red">
<span onclick="this.parentElement.style.display="none"" class="w3-closebtn">&times;</span>
  //<p>Check Your Username and Password Inorder to Send SMS</p>
</div> ','darkred');
goto end;
}
else
{
// * Login Faild or SMS Send Error Message 2//
show('<div class="w3-container w3-section w3-red">
<span onclick="this.parentElement.style.display="none"" class="w3-closebtn">&times;</span>
 // <p>Check Your Username and Password Inorder to Send SMS</p>
</div> ','darkred');
goto end;
}
bal:
///$ch = curl_init();
$msg=urlencode($msg);
$main=$ser."smstoss.action";
$ref=$ser."sendSMS?Token=".$id;
$conf=$ser."smscofirm.action?SentMessage=".$msg."&Token=".$id."&status=0";

$post="ssaction=ss&Token=".$id."&mobile=".$to."&message=".$msg."&Send=Send Sms&msgLen=".strlen($msg);
$mhtml=post($main,$post,$ch,$ckfile,$proxy,$ref);
if(stristr($mhtml,'smscofirm.action?SentMessage='))
// * Message Sended Sucessfull Message//
{ show('<div class="w3-container w3-section w3-green">
<span onclick="this.parentElement.style.display="none"" class="w3-closebtn">&times;</span>
 // <p>SMS Sended Sucessfully..!!</p>
</div>'.$to.'.','darkgreen');}
else
// * Login Faild or SMS Send Error Message 1//
{ show('<div class="w3-container w3-section w3-red">
<span onclick="this.parentElement.style.display="none"" class="w3-closebtn">&times;</span>
  //<p>Check Your Username and Password Inorder to Send SMS</p>
</div> ','darkred');}
curl_close($ch);

end:

echo "</div>";
flush();
}
}
// * You can change the form content from the below code //
//echo '<div class="content"><a href="b.html"> click here</a><br></div>';
//include('foot.php');
/** 
  * @source code designed by Muhsin Mohamed Pc for http://www.howi.in
  * Report bugs and errors to waphunt@gmail.com
  * @This script was coded with the help of many other github projects. I thanks to them
  * @Take backup before editing the script
*/
?>