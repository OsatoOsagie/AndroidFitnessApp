<?php

require "conn.php";

$user_id = $_GET["user_id"];
$user_gender = $_GET["user_gender"];
$user_DOB = $_GET["user_DOB"];
$user_height = $_GET["user_height"];
$user_goal = $_GET["user_goal"];


$sql= "select * from Users where id= '$user_id'";


$result= mysqli_query($conn,$sql);


if(mysqli_num_rows($result)>0){




$mysql_qry = "UPDATE Users SET  sex= '$user_gender',birthday= '$user_DOB', height='$user_height', goal='$user_goal' WHERE id= '$user_id'";


if($conn->query($mysql_qry)=== TRUE){
$status = "ok";
}else{
$status = "error";
}


}


echo json_encode(array("response" =>$status));


$conn->close();
?>