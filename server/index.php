<?php
 
if($_GET["barkod"]){
	// Create connection
	$con=mysqli_connect("localhost","root","","hekaton");
	// Check connection
	if (mysqli_connect_errno())
	{
	  echo "Failed to connect to MySQL: " . mysqli_connect_error();
	}
	 
	// Select from our stocks from table 'urunler'
	$sql = "SELECT urun_adi,vegan,vejeteryan,non_pork,gluten,fruktoz,yer_fistigi FROM urunler WHERE barkod = ".$_GET["barkod"];
	 
	// Confirm there are results
	if ($result = mysqli_query($con, $sql))
	{
		if(!$row = $result->fetch_object()){
			echo "Error";
			exit();
		}
		echo json_encode($row,JSON_UNESCAPED_UNICODE);
	}
	// Close connections
	mysqli_close($con);
}
?>