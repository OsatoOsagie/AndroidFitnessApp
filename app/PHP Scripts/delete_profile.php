<?php

require "conn.php";

$email = $_GET["user_email"];


//looking for a user in the database
$sql= "DELETE FROM Users WHERE email= '$email'";



//result of SQL statment
$result= mysqli_query($conn,$sql);


if($conn->query($sql)=== TRUE){
$status = "deleted";
$sql= "DELETE FROM Users WHERE email= '$email'";

}

else{
$status = "failed";

}







echo json_encode(array("response" =>$status));
$conn->close();
?>