<?php
require "conn.php";



$user_id=$_GET["user_id"];
$exercise_id= $_GET["exercise_id"]; 



$sql_qry= "select * from Deafault_Exercises where Id= '$exercise_id'";

$result= mysqli_query($conn,$sql_qry);





if(mysqli_num_rows($result)>0){
$status = "exists";


    $json_array= array();

while($r = mysqli_fetch_assoc($result)) {

	

    $json_array[]= $r; 
    
    
   

    
}


$name=$json_array[0]["Name"];
$image=$json_array[0]["images"];


$sql_qry_insert= "insert into Custom_plan (exercise_id, ex_name, ex_image, user_id) values ('$exercise_id','$name','$image','$user_id')";

if($conn->query($sql_qry_insert)=== TRUE){
$status = "added to plan";
}

else{
$status = "error";
}



//echo("Error description: " . mysqli_error($conn));

echo json_encode(array("response" =>$status));
}else{
$status = "plan is empty";
echo json_encode(array("response" =>$status));
}





$conn->close();
?>