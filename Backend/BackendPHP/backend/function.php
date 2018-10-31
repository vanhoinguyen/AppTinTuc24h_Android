<?php

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

include '../libs/Medoo.php';

// Initialize
$database = new \Medoo\Medoo([
    'database_type' => 'mysql',
    'database_name' => 'tintuctonghop',
    'server' => 'localhost',
    'username' => 'root',
    'password' => ''
]);

function get_posts($page=0)
{
    global $database;
    $total_post_in_page =10;
   $posts = $database->select('post', '*',[
     'LIMIT' => [$page*$total_post_in_page,$total_post_in_page]
   ]);
   
  
   return $posts;
}

function get_total_page()
{
    global $database;
    $total_post_in_page =10;
    $count=$database->count('post');
    $total_page=$count/$total_post_in_page;
    return floor($total_page) ;
    
}

function delete_post($post_id)
{
    global $database;
   
    $r = $database->delete('post', [
        'post_id' => $post_id
    ]);
    return $r;
}

function save_post($post_title,$post_desc,$post_thumb,$post_content,$catelory_id)
{
    global $database;
    
    $r=$database->insert('post', [
        'post_title'=> $post_title,
        'post_desc' => $post_desc,
        'post_thumb' => $post_thumb,
        'post_content' => $post_content,
        'catelory_id' => $catelory_id
    ]);
    return $r;
}

function get_post_by_id($post_id)
{
    global $database;
    
    $post=$database->get('post', '*',[
       'post_id' => $post_id 
    ]);
    return $post;
}

function update_post($post_id,$post_title,$post_desc,$post_thumb,$post_content,$catelory_id)
{
    global $database;
    
    $r=$database->update('post', [
        'post_title'=> $post_title,
        'post_desc' => $post_desc,
        'post_thumb' => $post_thumb,
        'post_content' => $post_content,
        'catelory_id' => $catelory_id],
            [
                'post_id' => $post_id
            ]
    );
    return $r;
}

function get_post_by_catelory_id($catelory_id,$limit,$offset)
{
  
    global $database;
    $posts = $database ->select('post', [
        'post_id','post_title','post_desc','post_thumb','catelory_id'
    ],[
        'catelory_id' => $catelory_id,'LIMIT'=>[$offset,$limit] 
    ]);
    return $posts;
}