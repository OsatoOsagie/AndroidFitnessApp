<?php
require "conn.php";


$user_id= $_GET["user_id"];



$sql_qry= "select * from Custom_plan where user_id= '$user_id' ";



$result= mysqli_query($conn,$sql_qry);




if(mysqli_num_rows($result)>0){
$status = "plan not empty";


  $json_array= array();

while($r = mysqli_fetch_assoc($result)) {
	
    $json_array[]= $r; 
    
    
   

    
}

}else{
$status = "plan empty";
}

echo json_encode(array("response" =>$status,"custom_plan_array"=>$json_array));













$conn->close();

?>