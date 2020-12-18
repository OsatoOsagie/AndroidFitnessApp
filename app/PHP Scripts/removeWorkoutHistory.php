<?php
require "conn.php";

// this php script is responsible for removing users workout history



$user_id= $_GET["user_id"];
$history_id= $_GET["history_id"];


//sql query to delete exercise from favourites 
$sql_qry_delete= "DELETE FROM workout_history WHERE Id = '$history_id' and user_id = '$user_id'";



//executing sql query
$result= mysqli_query($conn,$sql_qry_delete);


//checking if the number of results returned is 0
if(mysqli_num_rows($result)==0){

$status = "removed from history";



}else{

$status = "not removed from history";

}






//sending response back to server
echo json_encode(array("response" =>$status));




$conn->close();

?>