<?php
require "conn.php";

$id_num= $_GET["id"];

$sql= "select * from quotes where id= '$id_num'";


$result= mysqli_query($conn,$sql);

 $quote="empty";
if(mysqli_num_rows($result)>0){
$status = "exists";



while($row = mysqli_fetch_array($result)) {
    $quote= $row['quote']; 
}


}else{
$status = "quote doesnt exist";
}




echo json_encode(array("response" =>$status,"quote" =>$quote));
$conn->close();
?>