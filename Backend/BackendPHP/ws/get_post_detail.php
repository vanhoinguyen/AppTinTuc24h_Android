<?php

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

include '../backend/function.php';

$post_id =(!empty($_GET['post_id']))? $_GET['post_id']:0 ;
$post= get_post_by_id($post_id);

header('Content-Type: application/json');
echo json_encode($post);