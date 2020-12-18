<?php
require "conn.php";


$date= $_GET["date"];

$reps= $_GET["reps"];

$sets= $_GET["sets"];

$weight= $_GET["weight"];

$user_id=$_GET["user_id"];

$exercise_name= $_GET["exercise_name"];








$sql_workout_num= "select max(date) from workout_history where User_id='$user_id'";

$result_sql= mysqli_query($conn,$sql_workout_num);

if(mysqli_num_rows($result_sql)>0){

$json_array= array();

while($r = mysqli_fetch_assoc($result_sql)) {

	

    $json_array[]= $r; 
    
    
    
    
   

    
}


$max_date= $json_array[0]['max(date)'];

}




$sql= "select * from workout_history where date='$date'";

$result= mysqli_query($conn,$sql);



if(mysqli_num_rows($result)>0){


$json_array= array();

while($r = mysqli_fetch_assoc($result)) {

	

     $workout_number = $r['workout_number'];
    
    
   

    
}

}else{



if($max_date > $date){
$ststus_update="success";

$sql_workout_num= "select * from workout_history where date <'$date' order by date desc";

$result_sql= mysqli_query($conn,$sql_workout_num);

if(mysqli_num_rows($result_sql)>0){

$json_array= array();

while($r = mysqli_fetch_assoc($result_sql)) {

	

    $json_array[]= $r; 
    
    
    
    
   

    
}

}
$workout_number= $json_array[0]['workout_number']+1;

$Sql_update= "update workout_history set workout_number = workout_number +1 where date > '$date'";
 
 
if($conn->query($Sql_update)=== TRUE){
$status_updated = "changed";
}

else{
$status_updated = "failed";
}




}else{

$sql_workout_num= "select max(workout_number) from workout_history where user_id='$user_id'";

$result_sql= mysqli_query($conn,$sql_workout_num);

if(mysqli_num_rows($result_sql)>0){

$json_array= array();

while($r = mysqli_fetch_assoc($result_sql)) {

	

    $json_array[]= $r; 
    
    
   

    
}

$workout_number=$json_array[0]["max(workout_number)"]+1;


}
	
}



}




$mysql_qry = "insert into workout_history (exercise_name,date, user_id, workout_number,sets, reps, weight) values ('$exercise_name','$date','$user_id', '$workout_number','$sets', '$reps','$weight')";


if($conn->query($mysql_qry)=== TRUE){
$status = "history added";
}else{
$status = "error";
}


echo json_encode(array("response" =>$status, "update?" => $status_updated, "random" => $ststus_update));
$conn->close();

?>