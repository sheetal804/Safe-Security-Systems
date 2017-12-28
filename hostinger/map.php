<?php
//require("phpsqlajax_dbinfo.php");

function parseToXML($htmlStr)
{
$xmlStr=str_replace('<','&lt;',$htmlStr);
$xmlStr=str_replace('>','&gt;',$xmlStr);
$xmlStr=str_replace('"','&quot;',$xmlStr);
$xmlStr=str_replace("'",'&#39;',$xmlStr);
$xmlStr=str_replace("&",'&amp;',$xmlStr);
return $xmlStr;
}

// Opens a connection to a MySQL server
$con = mysqli_connect("mysql.hostinger.in","u456135040_raspi","sheetal1995","u456135040_raspi");
if (!$con) {
  die('Not connected : ' . mysql_error());
}



// Select all the rows in the markers table
$query = "SELECT * FROM Track WHERE 1";
$result = $con->query($query);
if (!$result) {
  die('Invalid query: ' . mysql_error());
}

header("Content-type: text/xml");

// Start XML file, echo parent node
echo '<markers>';

// Iterate through the rows, printing XML nodes for each
while ($row = $result->fetch_assoc()){
  // Add to XML document node
  echo '<marker ';
    echo 'lat="' . $row['Latitude'] . '" ';
  echo 'lng="' . $row['Longitude'] . '" ';
  echo '/>';
}

// End XML file
echo '</markers>';

?>

