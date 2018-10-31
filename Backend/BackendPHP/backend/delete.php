<?php

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
include './function.php';

$post_id=0;
$page=0;
 
if(!empty($_GET['id']))
{
  $post_id=  $_GET['id'];
}

if(!empty($_GET['page']))
{
  $page =  $_GET['page'];
}

$r= delete_post($post_id);

if($r==TRUE)
{
    echo '<center> Xóa bài viết thành công </center>';
    
}
else
{
    echo '<center> Xóa bài viết Không thành công </center>';
}
header('Refresh:1;index.php?page='.$page);