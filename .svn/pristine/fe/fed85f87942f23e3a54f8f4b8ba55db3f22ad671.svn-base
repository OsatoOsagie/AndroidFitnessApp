<?php
require "conn.php";


$measure_Id= $_GET["measure_Id"];


$sql_qry= "DELETE FROM bodymeasures WHERE _id= '$measure_Id'";


$result= mysqli_query($conn,$sql_qry);


if(mysqli_num_rows($result)==0){

$status = "deleted";



}else{

$status = "not deleted";

}






echo json_encode(array("response" =>$status));





$conn->close();

?>