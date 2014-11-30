<?php

$host = "localhost";
$user_name = "dbcosner_uvento";
$password = "Luogbyf10";
$database = "dbcosner_uvento";

/*
$host = "localhost";
$user_name = "root";
$password = "";
$database = "dbcosner_evento";
*/
$id;
$cat_id = "";
$type_id = "";
$cat_type = "";

if (isset($_POST["id"])){
	$id = $_POST["id"];
}

if (isset($_POST["catId"])) {
	$cat_id = $_POST["catId"];
}

if (isset($_POST["typeId"])) {
	$type_id = $_POST["typeId"];
}

if (isset($_POST["catType"])) {
	$cat_type = $_POST["catType"];
}

$query_type = $_POST["queryType"];
$query = "";

switch ($query_type) {
	case "getAllUniversities":
		$query = "SELECT * FROM universities";
		break;
	case "getAllEvents":
		$query = "SELECT * FROM events";
		break;
	case "getAllEventsByUniversity":
		$query = "SELECT e.*, et.type , cat.name FROM events e , categories cat , event_type et WHERE e.CAT_ID = cat.ID AND e.TYPE_ID = et.ID  AND e.cat_id= cat.id AND e.uni_id = " . $id;
		break;
	case "getAllEventsByCategory":
		$query = "SELECT e.*, et.type , cat.name FROM events e , categories cat , event_type et WHERE e.CAT_ID = cat.ID AND e.TYPE_ID = et.ID  AND e.cat_id=\"" . $cat_id . "\"";
		break;
	case "getAllEventsByType":
		$query = "SELECT e.*, et.type , cat.name FROM events e , categories cat , event_type et WHERE e.CAT_ID = cat.ID AND e.TYPE_ID = et.ID  AND e.type_id=\"" . $type_id . "\"";
		break;
	case "getEventDetails":
		$query = "SELECT * FROM events WHERE id=" . $id;
		break;
	case "getAllCategories":
		$query = "SELECT * FROM categories";
		break;
	case "getAllCategoriesByUniversity":
		$query = "SELECT * FROM categories WHERE uni_id=" . $id;
		break;
	case "getAllCategoriesByCategoryType":
		$query = "SELECT * FROM categories WHERE cat_type=\"" . $cat_type . "\" AND uni_id = " . $id;
		break;
	case "getAllEventTypes":
		$query = "SELECT * FROM event_type";
		break;
	case "getAllEventTypesByUniversity":
		$query = "SELECT * FROM event_type WHERE uni_id=" .$id;
		break;
    case "getUniveristyById":
        $query = "SELECT * FROM universities WHERE id=" .$id;
        break;
        
}

//echo "id: " . $id . "<br>cat_name: " . $cat_name . "<br>type_name: " . $type_name ."<br>cat_type: " . $cat_type;
//echo $query . '<br>';

$connection = mysqli_connect($host, $user_name, $password, $database);
if (mysqli_connect_errno($connection)) {
	echo "Failed to connect to MySQL: " . mysqli_connect_error();
}

$result = mysqli_query($connection, $query);
$rows = array();

while($row = mysqli_fetch_assoc($result)) {
	echo json_encode($row);
	echo '|';
}

mysqli_close($connection);
?>