<?php
require "conn.php";



$date= "2020-03-18";



$sql_workout_num= "select * from workout_history where date <'$date' order by date desc";

$result_sql= mysqli_query($conn,$sql_workout_num);

if(mysqli_num_rows($result_sql)>0){

$json_array= array();

while($r = mysqli_fetch_assoc($result_sql)) {

	

    $json_array[]= $r; 
    
    
    
    
   

    
}


$workout_number= $json_array[0]['workout_number']+1;

$Sql_update= "update workout_history set workout_number = workout_number+1 where date > '$date'";
 
 


if($conn->query($Sql_update)=== TRUE){
$status_update = "changed";
}

else{
$status_update = "failed";
}


}








echo json_encode(array("response" =>$status_update));
$conn->close();

?>