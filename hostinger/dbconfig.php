<?php
$l1=$_GET['l1'];
$l2=$_GET['l2'];
$con = mysqli_connect("mysql.hostinger.in","u456135040_raspi","sheetal1995","u456135040_raspi");
// Check connection
if (mysqli_connect_errno())
  {
  echo "Failed to connect to MySQL: " . mysqli_connect_error();
  }
$sql = "INSERT INTO Track (Latitude, Longitude)
VALUES ($l1,$l2)";

if (mysqli_query($con, $sql)) {
    echo "New record created successfully";
} else {
    echo "Error: " . $sql . "<br>" . mysqli_error($con);
}

mysqli_close($con);
?> 