<?php
require "conn.php";

$id_user = $_GET["user_id"];
$protein = $_GET["rec_protein"];
$carbs =  $_GET["rec_carbs"];
$calories =$_GET["rec_calories"];
$water =$_GET["rec_water"];
$fat=$_GET["rec_fat"];


$sql= "select * from Users where id= '$id_user'";


$result= mysqli_query($conn,$sql);


if(mysqli_num_rows($result)>0){




$mysql_qry = "UPDATE Users SET rec_proteinIntake= '$protein',rec_carbIntake= '$carbs', rec_caloricIntake = '$calories',rec_fatIntake='$fat',rec_waterIntake='$water' WHERE id= '$id_user'";


if($conn->query($mysql_qry)=== TRUE){
$status = "ok";
}else{
$status = "error";
}


}


echo json_encode(array("response" =>$status));









$conn->close();

?>