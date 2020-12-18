<?php
require "conn.php";


$user_id= $_GET["user_id"];
$exercise_id= $_GET["exercise_id"];


//sql query to delete exercise from favourites 
$sql_qry_delete= "DELETE FROM Custom_plan WHERE exercise_id = '$exercise_id' and user_id = '$user_id'";



//executing sql query
$result= mysqli_query($conn,$sql_qry_delete);


//checking if the number of results returned is 0
if(mysqli_num_rows($result)==0){

$status = "removed from plan";



}else{

$status = "not removed from plan";

}






//sending response back to server
echo json_encode(array("response" =>$status));




$conn->close();

?>