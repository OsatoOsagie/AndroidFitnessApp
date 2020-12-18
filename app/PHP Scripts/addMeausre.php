<?php
require "conn.php";


$date= $_GET["date"];
$bodyPart_id= $_GET["bodyPart"]; 
$measurement= $_GET["measurement"];
$profile_id= $_GET["profile_id"];



$mysql_qry = "insert into bodymeasures (date,bodypart_id,measure, profile_id) values ('$date','$bodyPart_id','$measurement','$profile_id') ";

if($conn->query($mysql_qry)=== TRUE){
$status = "measurement added";
}

else{
$status = "error";
}


echo json_encode(array("response" =>$status));
$conn->close();

?>