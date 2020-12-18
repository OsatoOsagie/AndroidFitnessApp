<?php
require "conn.php";
$user_email =$_GET["user_email"]; 
$user_pass = $_GET["password"];

//sql query using the user email to see if the user exits 
$sql_qry =  "select * from Users where email= '$user_email'";


$result_DBEmail= mysqli_query($conn,$sql_qry);

 $password_hash="empty";
 
 //if the user is found the the database and retrieve their details
if(mysqli_num_rows($result_DBEmail)>0){
$status = "exists";



while($row = mysqli_fetch_array($result_DBEmail)) {
    $password_hash= $row['password']; 
    $user_name = $row['name'];
    $user_email= $row['email'];
    $user_id= $row['id'];
    $user_sex= $row['sex'];
    $user_dob= $row['birthday'];
    $user_height= $row['height'];
    $user_goal= $row['goal'];
    
    $user_protein= $row['rec_proteinIntake'];
    $user_carb= $row['rec_carbIntake'];
    $user_calories= $row['rec_caloricIntake'];
    $user_fatIntake= $row['rec_fatIntake'];
    $user_waterIntake= $row['rec_waterIntake'];
    
}


}else{
$status = "quote doesnt exist";
}

//verifying the the users password matches the hash
if(password_verify($user_pass,$password_hash)){

$row= mysqli_fetch_assoc($result);
$name= $user_name;
$email= $user_email;
$status = "ok";
$id= $user_id;
$user_password=$user_pass;


echo json_encode(array("response" =>$status,"name"=>$name,"email"=>$email,"id"=>$id, "password"=> $user_pass, "height" => $user_height, "sex" =>$user_sex,
"dob" =>$user_dob,"goal" => $user_goal,
"protein" =>$user_protein,"carb"=> $user_carb, "calories" => $user_calories, "fat" =>  $user_fatIntake, "water" => $user_waterIntake ));
}else{

$status = "failed";
echo json_encode(array("response" =>$status, "passwordDB" =>$password_hash ));
}



$conn->close();
?>