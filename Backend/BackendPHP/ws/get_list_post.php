<?php

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

include '../backend/function.php';

$catelory_id= (!empty($_GET['catelory_id']))? $_GET['catelory_id']:1;
$limit =(!empty($_GET['limit']))? $_GET['limit']:3; 
$offset = (!empty($_GET['offset']))? $_GET['offset']:0;

$posts= get_post_by_catelory_id($catelory_id, $limit, $offset);

header('Content-Type: application/json');
echo json_encode($posts);