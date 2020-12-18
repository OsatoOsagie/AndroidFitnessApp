<?php

require "conn.php";


$user_id=$_GET["user_id"];
$ex_name= $_GET["ex_name"];


//looking for the new email in the database
$sql_workout_num= "select max(workout_number) from workout_history where user_id='$user_id'";

$result_sql= mysqli_query($conn,$sql_workout_num);

if(mysqli_num_rows($result_sql)>0){

$json_array= array();

while($r = mysqli_fetch_assoc($result_sql)) {

	

    $json_array[]= $r; 
    
    
   

    
}

$workout_num=$json_array[0]["max(workout_number)"];

//workout one

$workout_one_sql= "select * from workout_history where workout_number='$workout_num' and user_id='$user_id' and exercise_name='$ex_name'";
$workout_one_result= mysqli_query($conn,$workout_one_sql);




if(mysqli_num_rows($workout_one_result)>0){
$status = "success";
 $workout_one_array= array();
 while($r = mysqli_fetch_assoc($workout_one_result)){
 		
 $workout_one_array[]= $r;
 }

}

//workout two

$workout_two_sql= "select * from workout_history where workout_number='$workout_num'-1 and user_id='$user_id' and exercise_name='$ex_name'";
$workout_two_result= mysqli_query($conn,$workout_two_sql);




if(mysqli_num_rows($workout_two_result)>0){
$status = "success";
 $workout_two_array= array();
 while($r = mysqli_fetch_assoc($workout_two_result)){
 		
 $workout_two_array[]= $r;
 }

}


//workout three

$workout_three_sql= "select * from workout_history where workout_number='$workout_num'-2 and user_id='$user_id' and exercise_name='$ex_name'";
$workout_three_result= mysqli_query($conn,$workout_three_sql);




if(mysqli_num_rows($workout_three_result)>0){
$status = "success";
 $workout_three_array= array();
 while($r = mysqli_fetch_assoc($workout_three_result)){
 		
 $workout_three_array[]= $r;
 }

}

//Workout four

$workout_four_sql= "select * from workout_history where workout_number='$workout_num'-3 and user_id='$user_id' and exercise_name='$ex_name'";
$workout_four_result= mysqli_query($conn,$workout_four_sql);




if(mysqli_num_rows($workout_four_result)>0){
$status = "success";
 $workout_four_array= array();
 while($r = mysqli_fetch_assoc($workout_four_result)){
 		
 $workout_four_array[]= $r;
 }

}

//workout five 

$workout_five_sql= "select * from workout_history where workout_number='$workout_num'-4 and user_id='$user_id' and exercise_name='$ex_name'";
$workout_five_result= mysqli_query($conn,$workout_five_sql);




if(mysqli_num_rows($workout_five_result)>0){
$status = "success";
 $workout_five_array= array();
 while($r = mysqli_fetch_assoc($workout_five_result)){
 		
 $workout_five_array[]= $r;
 }

}

echo json_encode(array("workout_num" =>$workout_num,"workout_one" => $workout_one_array, "workout_two" =>$workout_two_array, "workout_three" =>$workout_three_array, "workout_four" =>$workout_four_array, "workout_five"=>$workout_five_array,"response" => $status ));





}else{
$workout_num = "error";
echo json_encode(array("workout_num" =>$workout_num));
}






$conn->close();
?>