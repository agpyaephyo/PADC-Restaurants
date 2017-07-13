<?php    
    $json_data = file_get_contents('restaurants-ygn.json');            
    //$json_data = 'data here';
    $return_json = array('code'=> 200 , 
    	'message' => 'success', 
    	'timestamp' => date("Y-m-d h:i:sa"),
    	'restaurants' => json_decode($json_data));
    header('Content-type: application/json');
    echo json_encode($return_json);
?>
