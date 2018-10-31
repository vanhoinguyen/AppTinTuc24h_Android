<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<?php 
session_start();
include 'function.php';
if(empty($_SESSION['user_name']))
{
    echo '<center>Bạn chưa đăng nhập, yêu cầu bạn đăng nhập! </center>';
   
    header('Refresh:1; url=login.php');
    return;
} 

$post_id=0;
if(!empty($_GET['id']))
{
    $post_id=$_GET['id'];
}

$post= get_post_by_id($post_id);

    $post_title=$post['post_title'];
    $post_desc=$post['post_desc'];
    $post_thumb=$post['post_thumb'];
    $catelory_id=$post['catelory_id'];
    $post_content=$post['post_content'];

if(!empty($_POST))
{
    $post_title=$_POST['post_title'];
    $post_desc=$_POST['post_desc'];
    $post_thumb=$_POST['post_thumb'];
    $catelory_id=$_POST['catelory_id'];
    $post_content=$_POST['post_content'];
    
    $is_ok = true; 
    
    $errors =array();
    if(empty($post_title))
    {
        $errors['post_title']='Tiêu đề không được bỏ trống';
        $is_ok = false; 
    }
    
    if(empty($post_desc))
    {
        $errors['post_desc']='Mô tả không được bỏ trống';
        $is_ok = false; 
    }
    
    if(empty($post_thumb))
    {
        $errors['post_thumb']='Hình ảnh không được bỏ trống';
        $is_ok = false; 
    }
    
    if(empty($post_content))
    {
        $errors['post_content']='Nội dung không được bỏ trống';
        $is_ok = false; 
    }
    
     if(!empty($post_content)&&strlen($post_content)<200)
    {
        $errors['post_content']='Nội dung quá ngắn';
        $is_ok = false; 
    }
    
    if($is_ok)
    {
        // save to database
//        $r=save_post($post_title,$post_desc,$post_thumb,$post_content,$catelory_id);
//        
//        
//        unset($post_title);
//        unset($post_desc);
//        unset($post_thumb);
//        unset($catelory_id);
//        unset($post_content);
        
        //update
        $r = update_post($post_id,$post_title,$post_desc,$post_thumb,$post_content,$catelory_id);
        
        
   
    }
}
    

?>

<html>
    <head>
        <meta charset="UTF-8">
        <title>Detail</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
        <script src="js/kickstart.js"></script> <!-- KICKSTART -->
        <link rel="stylesheet" href="css/kickstart.css" media="all" /> <!-- KICKSTART -->
        <script src="js/ckeditor/ckeditor.js"></script> 
        
    </head>
    <body>
        <form accept-charset="utf-8" class="vertical" style="width: 50% ; margin: auto;" action="" method="post">
            <fieldset>
                <a href="index.php" class="button small blue">Trang chủ</a>                
                <legend>Bài viết </legend>
                <div class="notice success <?php if($r) {echo 'show';} else { echo 'hide';} ?>">Sửa bài viết thành công!!!</div>
                
                <label for="post_title">Tiêu đề:<span class="right"><?php if(!empty($errors['post_title'])){echo $errors['post_title'];} ?></span></label>
                <input class="<?php if(!empty($errors['post_title'])){echo 'error';} ?>" type="text" name="post_title" id="post_title" value="<?php if(!empty($post_title)){echo $post_title;} ?>" />
               
                <label class="<?php if(!empty($errors['post_desc'])){echo 'error';} ?> for="post_desc">Mô tả:<span class="right"> <?php if(!empty($errors['post_desc'])){echo $errors['post_desc'];} ?></span></label>
                <textarea name="post_desc" id="post_desc" ><?php if(!empty($post_desc)){echo $post_desc;} ?></textarea>
                
                <label for="post_thumb">Ảnh:<span class="right"><?php if(!empty($errors['post_thumb'])){echo $errors['post_thumb'];} ?></span></label>
                <input class="<?php if(!empty($errors['post_thumb'])){echo 'error';} ?>" type="text" name="post_thumb" id="post_thumb" value="<?php if(!empty($post_thumb)){echo $post_thumb;} ?>"/>
                
                <label for="catelory_id">Chuyên mục:</label>
                <select name="catelory_id" id="catelory_id">
                    <option <?php if(empty($catelory_id) || $catelory_id == 1){ echo "selected";} ?> value="1"> Thời sự </option>
                    <option <?php if(!empty($catelory_id) && $catelory_id == 2){ echo "selected";} ?> value="2"> Thể thao </option>
                    <option <?php if(!empty($catelory_id) && $catelory_id == 3){ echo "selected";} ?> value="3"> Kinh tế </option>
                    <option <?php if(!empty($catelory_id) && $catelory_id == 4){ echo "selected";} ?> value="4"> Chính trị </option>
                </select>
                
                <label class="<?php if(!empty($errors['post_content'])){echo 'error';} ?>  for="post_content">Nội dung:<span class="right"> <?php if(!empty($errors['post_content'])){echo $errors['post_content'];} ?></span></label>
                <textarea name="post_content" id="post_content" ><?php if(!empty($post_content)){echo $post_content;} ?></textarea>
                <script>
                // Replace the <textarea id="editor1"> with a CKEditor
                // instance, using default configuration.
                CKEDITOR.replace( 'post_content' );
            </script>
                
            <button style="float: right; margin-top: 20px;" type="submit" class="small blue">Lưu</button>
            </fieldset>
        </form>
    </body>
</html>
