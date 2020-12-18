<?php
require "conn.php";


$date=$_GET["date"];
$measurement= $_GET["measurement"];
$user_id=$_GET["user_id"];



$mysql_qry = "insert into WaterPercentage (date,measurement, user_id) values ('$date','$measurement','$user_id') ";

if($conn->query($mysql_qry)=== TRUE){
$status = "measurement added";
}

else{
$status = "error";
}


echo json_encode(array("response" =>$status));
$conn->close();

?>