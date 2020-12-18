<?php
require "conn.php";



$exercise_name= $_GET["exercise_name"];
$user_id=$_GET["user_id"];



$sql_qry= "select * from Deafault_Exercises where Name= '$exercise_name'";

$result_DBMuscle_group= mysqli_query($conn,$sql_qry);

if ($conn -> connect_errno) {
  echo "Failed to connect to MySQL: " . $conn -> connect_error;
  exit();
}


if(mysqli_num_rows($result_DBMuscle_group)>0){
$status = "exists";


    $json_array= array();

while($r = mysqli_fetch_assoc($result_DBMuscle_group)) {

	

    $json_array[]= $r; 
    
    
   

    
}


$exercise_Id= $json_array[0]["Id"];
$name=$json_array[0]["Name"];
$image=$json_array[0]["images"];


$sql_qry_insert= "insert into Favourites (exercise_id, exercise_name, exercise_image, user_id) values ('$exercise_Id','$name','$image','$user_id')";

if($conn->query($sql_qry_insert)=== TRUE){
$status = "Favourited";
}

else{
$status = "error";
}



//echo("Error description: " . mysqli_error($conn));

echo json_encode(array("response" =>$status));
}else{
$status = "favourites is empty";
echo json_encode(array("response" =>$status));
}








$conn->close();

?><?php
