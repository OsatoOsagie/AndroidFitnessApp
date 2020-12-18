<?php
require "conn.php";

$id_user= $_GET["user_id"];
$body_part=$_GET["bodyPart_id"];
$sql_qry= "select * from bodymeasures where profile_id= '$id_user' and bodypart_id ='$body_part' and  YEAR(date) = YEAR(CURDATE())ORDER BY measure DESC";

$result_DBEmail= mysqli_query($conn,$sql_qry);


if(mysqli_num_rows($result_DBEmail)>0){
$status = "exists";


    $json_array= array();

while($r = mysqli_fetch_assoc($result_DBEmail)) {
	
    $json_array[]= $r; 
    
   $status = "Measurement Found";

    
}





echo json_encode( array("measurements_array"=>$json_array,"response" =>$status));




}else{
$status = "No measures entered by user";
echo json_encode(array("response" =>$status));
}

$conn->close();

?>