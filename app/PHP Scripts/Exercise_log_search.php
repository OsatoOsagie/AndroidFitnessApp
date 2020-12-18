<?php
require "conn.php";

//this variable retrieves the user ID
$id_user= $_GET["user_id"];



$sql_qry= "SELECT max(date)-1 as landmark from MusclePercentage";



$result_DB= mysqli_query($conn,$sql_qry);


if(mysqli_num_rows($result_DB)>0){
$status = "favourites found";


    $json_array= array();

while($r = mysqli_fetch_assoc($result_DB)) {
	
    $json_array[]= $r; 
    

    
}
$id_user=$json_array[0];



echo json_encode( array("favourites_array"=>$json_array,"response" =>$status,"tester"=> $id_user ));





}else{
$status = "No favourites found";
echo json_encode(array("response" =>$status));
}


$conn->close();

?>