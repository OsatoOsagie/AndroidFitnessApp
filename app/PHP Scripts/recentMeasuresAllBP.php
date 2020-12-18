<?php
require "conn.php";


$id_user= $_GET["user_id"];
$sql_qry= "select* from bodymeasures where profile_id= 154";


$result_User= mysqli_query($conn,$sql_qry);





if(mysqli_num_rows($result_User)>0){

$status = "Measurement found";

//left arm
$jason_arrayLeft_arm=array();
$sql_qry_latest_LeftBicep=   "select  * from bodymeasures where profile_id= 154 and bodypart_id =1 ORDER BY date DESC";
$result_leftBicept= mysqli_query($conn,$sql_qry_latest_LeftBicep);


if(mysqli_num_rows($result_leftBicept)>0){
while($y = mysqli_fetch_assoc($result_leftBicept)){

$jason_arrayLeft_arm[]= $y; 



}

}else{

$jason_arrayLeft_arm[]="No measurement";

}


//right arm
$jason_arrayRight_arm=array();
$sql_qry_latest_rightBicep=   "select  * from bodymeasures where profile_id= 154 and bodypart_id =2 ORDER BY date DESC";
$result_RightBicept= mysqli_query($conn,$sql_qry_latest_rightBicep);


if(mysqli_num_rows($result_RightBicept)>0){
while($y = mysqli_fetch_assoc($result_RightBicept)){

$jason_arrayRight_arm[]= $y; 



}
}else{

$jason_arrayRight_arm[]="No measurement";

}

//chest
$jason_array_chest=array();


$sql_qry_latest_chest=   "select * from bodymeasures where profile_id= 154 and bodypart_id =3 ORDER BY date DESC";
$result_chest= mysqli_query($conn,$sql_qry_latest_chest);

if(mysqli_num_rows($result_chest)>0){
while($y = mysqli_fetch_assoc($result_chest)){

$jason_array_chest[]= $y; 



}

}else{

$jason_array_chest[]="No measurement";

}

//waist


$jason_array_waist=array();

$sql_qry_latest_waist=   "select * from bodymeasures where profile_id= 154 and bodypart_id =4 ORDER BY date DESC";

$result_waist= mysqli_query($conn,$sql_qry_latest_waist);
if(mysqli_num_rows($result_waist)>0){
while($y = mysqli_fetch_assoc($result_waist)){

$jason_array_waist[]= $y; 



}
}else{

$jason_array_waist[]="No measurement";
}
//hips

$jason_array_hips=array();


$sql_qry_latest_hips=   "select * from bodymeasures where profile_id= '$id_user' and bodypart_id =5 ORDER BY date DESC";


$result_hips= mysqli_query($conn,$sql_qry_latest_hips);



if(mysqli_num_rows($result_hips)>0){

while($y = mysqli_fetch_assoc($result_hips)){

$jason_array_hips[]= $y; 



}
}else{

$jason_array_hips[]=new stdClass();
}

//leftthigh

$jason_array_leftThigh=array();

$sql_qry_latest_leftTigh=   "select date from bodymeasures where profile_id= 154 and bodypart_id =6 ORDER BY date DESC";

$result_leftTigh= mysqli_query($conn,$sql_qry_latest_leftTigh);


if(mysqli_num_rows($result_leftTigh)>0){
while($y = mysqli_fetch_assoc($result_leftTigh)){

$jason_array_leftThigh[]= $y; 



}
}else{

$jason_array_leftThigh[]=new stdClass();


}

//RightThigh
$jason_array_rightThigh=array();

$sql_qry_latest_rightTigh=   "select * from bodymeasures where profile_id= '$id_user' and bodypart_id =7 ORDER BY date DESC";

$result_rightTigh= mysqli_query($conn,$sql_qry_latest_rightTigh);



if(mysqli_num_rows($result_rightTigh)>0){


while($y = mysqli_fetch_assoc($result_rightTigh)){






$jason_array_rightThigh[]= $y; 




}

}else{
$jason_array_rightThigh[]=new stdClass();

}


//leftCalf
$jason_array_leftCalf=array();

$sql_qry_latest_leftCalf=   "select * from bodymeasures where profile_id= 154 and bodypart_id =8 ORDER BY date DESC";

$result_leftCalf= mysqli_query($conn,$sql_qry_latest_leftCalf);


if(mysqli_num_rows($result_leftCalf)>0){
while($y = mysqli_fetch_assoc($result_leftCalf)){

$jason_array_leftCalf[]= $y; 



}
}else{

$jason_array_leftCalf[]= "No measurement"; 

}


//rightCalf
$jason_array_rightCalf=array();

$sql_qry_latest_rightCalf=   "select * from bodymeasures where profile_id= 154 and bodypart_id =9 ORDER BY date DESC";

$result_rightCalf= mysqli_query($conn,$sql_qry_latest_rightCalf);


if(mysqli_num_rows($result_rightCalf)>0){
while($y = mysqli_fetch_assoc($result_rightCalf)){




$jason_array_rightCalf[]= $y; 






}
}else{
$jason_array_rightCalf[]= new stdClass();

}

//measurements all put into a sing array of objects and sent back
$measuremnets_array=array();
$left_arm = reset($jason_arrayLeft_arm);
$right_arm =  reset($jason_arrayRight_arm);
$chest= reset($jason_array_chest);
$waist=reset($jason_array_waist);
$hips=reset($jason_array_hips);
$left_tigh=reset($jason_array_leftThigh);
$right_tigh=reset($jason_array_rightThigh);
$left_calf=reset($jason_array_leftCalf);
$right_calf=reset($jason_array_rightCalf);


array_push($measuremnets_array,$left_arm,$right_arm,$chest,$waist,$hips,$left_tigh,$right_tigh,$left_calf,$right_calf);
    
}else{
$status = "No measures entered by user";


}



echo json_encode( array("measurements_array" => $measuremnets_array,
"response" =>$status));





$conn->close();

?>