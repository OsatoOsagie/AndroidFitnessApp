<?php
require "conn.php";


//this script is responsible for adding a user's body fat percentage

//getting the date sent by the user
$date=$_GET["date"];
//getting the measurement sent by the user
$measurement= $_GET["measurement"];
//getting the users ID
$user_id= $_GET["user_id"];



//sql query to add body-fat percentage to the database
$mysql_qry = "insert into BodyFatPercentage (date,measurement, user_id) values ('$date','$measurement','$user_id') ";

//checking if the users query was successful
if($conn->query($mysql_qry)=== TRUE){
$status = "measurement added";
}

else{
$status = "error";
}


echo json_encode(array("response" =>$status));
$conn->close();

?>