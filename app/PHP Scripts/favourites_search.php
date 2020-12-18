<?php
require "conn.php";

//this variable retrieves the user ID
$id_user= $_GET["user_id"];



$sql_qry= "select * from Favourites where user_id= '$id_user' ";


$result_DB= mysqli_query($conn,$sql_qry);


if(mysqli_num_rows($result_DB)>0){
$status = "favourites found";


    $json_array= array();

while($r = mysqli_fetch_assoc($result_DB)) {
	
    $json_array[]= $r; 
    

    
}


echo json_encode( array("favourites_array"=>$json_array,"response" =>$status));




}else{
$status = "No favourites found";
echo json_encode(array("response" =>$status));
}


$conn->close();

?>