<?php

require "conn.php";

$email = $_GET["user_email"];
//"test@yahoo.com";
$random_password = $_GET["random_password"];
//"aesthetic123";

$sql= "select * from Users where email= '$email'";

//hasing the users new random password
$password_hash = password_hash($random_password, PASSWORD_DEFAULT);


//executing the SQL query
$result= mysqli_query($conn,$sql);

//checking if the user email exists
if(mysqli_num_rows($result)>0){


$sql_insert= "UPDATE Users SET password = '$password_hash' WHERE email =  '$email' ";
$result_insert_query= mysqli_query($conn,$sql_insert);


if($conn->query($sql_insert)=== TRUE){
$status = "changed";
}

else{
$status = "failed";
}


}else{
$status = "email doesn't exist";
}

echo json_encode(array("response" =>$status,"email" =>$email));
$conn->close();
?>