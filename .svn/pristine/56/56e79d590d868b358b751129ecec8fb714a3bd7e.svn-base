<?php

require "conn.php";

$email = $_GET["user_email"];
//"test@yahoo.com";

$new_email = $_GET["new_email"];




//looking for the new email in the database
$sql_new_email= "select * from Users where email= '$new_email'";

$result_sql= mysqli_query($conn,$sql_new_email);

if(mysqli_num_rows($result_sql)>0){
$status = "Email already exits";

}else{

$sql_insert= "UPDATE Users SET  email= '$new_email' WHERE email =  '$email' ";
$result_insert_query= mysqli_query($conn,$sql_insert);


if($conn->query($sql_insert)=== TRUE){
$status = "changed";
}

else{
$status = "failed";
}
}


echo json_encode(array("response" =>$status));
$conn->close();
?>