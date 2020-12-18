<?php
require "conn.php";


$muscle_group= $_GET["muscle_group"];

if($muscle_group== 0){

$sql_qry= "select * from Deafault_Exercises";

}else{

$sql_qry= "select * from Deafault_Exercises where Muscle_group= '$muscle_group'";
}


$result_DBMuscle_group= mysqli_query($conn,$sql_qry);




if(mysqli_num_rows($result_DBMuscle_group)>0){
$status = "exists";


    $json_array= array();

while($r = mysqli_fetch_assoc($result_DBMuscle_group)) {
	
    $json_array[]= $r; 
    
   

    
}





echo json_encode( array("exercises_array"=>$json_array,"response" =>$status));




}else{
$status = "Muscle group does not exist";
echo json_encode(array("response" =>$status));
}















$conn->close();

?>