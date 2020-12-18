<?php
require "conn.php";


$measure_Id=$_GET["measure_Id"];
$measurement_type = $_GET["measurement_type"];


$sql_qry= "DELETE FROM $measurement_type WHERE Id= '$measure_Id'";




$result= mysqli_query($conn,$sql_qry);




if(mysqli_num_rows($result)==0){

$status = "deleted";



}else{

$status = "not deleted";

}






echo json_encode(array("response" =>$status));





$conn->close();

?>