<?php
require "conn.php";

$user_id= $_GET["measure_Id"];


$sql_qry_weight= "select * from Weight_measurement where user_id= '$user_id' ORDER BY date ASC";

$sql_qry_bodyFat= "select * from BodyFatPercentage where user_id= '$user_id' ORDER BY date ASC";

$sql_qry_musclePercentage= "select * from MusclePercentage where user_id= '$user_id' ORDER BY date ASC";

$sql_qry_WaterPecentage= "select * from WaterPercentage where user_id= '$user_id' ORDER BY date ASC";



$result_weight= mysqli_query($conn,$sql_qry_weight);

$result_bodyfat= mysqli_query($conn,$sql_qry_bodyFat);

$result_bodyMusclePercentage= mysqli_query($conn,$sql_qry_musclePercentage);

$result_bodyWaterPercentage= mysqli_query($conn,$sql_qry_WaterPecentage);




if(mysqli_num_rows($result_weight)>0){
$status = "exists";


    
    while($weight = mysqli_fetch_assoc($result_weight)) {
	$json_array[]= $weight; 
  $user_weight= $weight["measurement"];
   

    
}    




   
}



if(mysqli_num_rows($result_bodyfat)>0){
$status = "exists";


    while($body_fat= mysqli_fetch_assoc($result_bodyfat)) {
	
  $user_fat= $body_fat["measurement"];
   

    
}    




   
}













if(mysqli_num_rows($result_bodyMusclePercentage)>0){
$status = "exists";


    while($body_muscle= mysqli_fetch_assoc($result_bodyMusclePercentage)) {
	
  $user_muscle= $body_muscle["measurement"];
   

    
}    
   
}







if(mysqli_num_rows($result_bodyWaterPercentage)>0){
$status = "exists";


    while($body_water= mysqli_fetch_assoc($result_bodyWaterPercentage)) {
	
  $user_water= $body_water["measurement"];
   

    
}     
}



echo json_encode( array("user_weight"=>$user_weight,
"response" =>$status,
"user_fat"=>$user_fat,
"user_muscle"=>$user_muscle,
"user_water"=>$user_water
));







$conn->close();

?>