<?php
require "conn.php";



$exercise_name= $_GET["exercise_name"];
$user_id= $_GET["user_id"];


//sql query to delete exercise from favourites 
$sql_qry_delete= "DELETE FROM Favourites WHERE exercise_name= '$exercise_name' and user_id = '$user_id'";

//executing sql query
$result= mysqli_query($conn,$sql_qry_delete);

//checking if the number of results returned is 0
if(mysqli_num_rows($result)==0){

$status = "deleted";



}else{

$status = "not deleted";

}






//sending response back to server
echo json_encode(array("response" =>$status));



$conn->close();

?>