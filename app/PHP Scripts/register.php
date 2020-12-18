<?php
require "conn.php";
$name = $_GET["name"];
$surname = $_GET["surname"]; 
$useremail = $_GET["useremail"];
$password = $_GET["password"];

$password_hash = password_hash($password, PASSWORD_DEFAULT);
$sql= "select * from Users where email= '$useremail'";


$result= mysqli_query($conn,$sql);


if(mysqli_num_rows($result)>0){
$status = "exists";

}
else{
$mysql_qry = "insert into Users (name, surname, email, password) values ('$name','$surname','$useremail','$password_hash') ";

if($conn->query($mysql_qry)=== TRUE){
$status = "ok";
$sql_qry= "select * from Users where email= '$useremail' ";

$result= mysqli_query($conn,$sql_qry);


if(mysqli_num_rows($result)>0){



  $json_array= array();

while($r = mysqli_fetch_assoc($result)) {
	
    $json_array[]= $r; 
    
    
   

    
}


}
}

else{
$status = "error";
}

}
echo json_encode(array("response" =>$status, "user_details" => $json_array ));
$conn->close();

?>