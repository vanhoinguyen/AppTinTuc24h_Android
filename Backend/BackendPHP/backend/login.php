<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->

<?php
session_start();
session_unset();

$LoginOk=true;

if(!empty($_POST)){
    $user_name = $_POST['user_name'];
    $password = $_POST['password'];

    $error=false;
    if($user_name =='admin' && $password=='123456')
    {
        $_SESSION['user_name']=$user_name;
        header('Location: index.php');
    }
    else {
        $error=true;
    }
    
}
?>
<html>
    <head>
        <meta charset="UTF-8">
        <title></title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
        <script src="js/kickstart.js"></script> <!-- KICKSTART -->
        <link rel="stylesheet" href="css/kickstart.css" media="all" /> <!-- KICKSTART -->
    </head>
    <body>
        <form style="width: 20%;margin: auto;" class="vertical" action="" method="post">
            <fieldset>
                <legend> Đăng Nhập </legend>
                
                <div class="notice error <?php if($error){echo 'show';}else {echo 'hide';}?>" ><i class="fa fa-warning"></i>Sai tài khoản mật khẩu</div>
                
                <label for="user_name">Tài khoản: </label>
                <input type="text" name="user_name" id="user_name"/>
                
                <label for="password">Mật khẩu: </label>
                <input type="password" name="password" id="password"/>
                
                <button class="blue" type="submit">Đăng nhập</button>
            </fieldset>
        </form>
    </body>
</html>
