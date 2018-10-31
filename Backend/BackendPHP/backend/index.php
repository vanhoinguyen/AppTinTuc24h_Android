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
$current_page=0;
if(!empty($_GET['page']))
{
    $current_page=$_GET['page'];
}

$posts= get_posts($current_page);
$total_page= get_total_page();

?>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Trang Chu</title>
            
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
        <script src="js/kickstart.js"></script> <!-- KICKSTART -->
        <link rel="stylesheet" href="css/kickstart.css" media="all" /> <!-- KICKSTART -->
    </head>
    <body>
        <?php
        // put your code here
        ?>
        <div>
            <div>
                <a href="detail.php" style="float: left;" class="button blue small"><i class="fa fa-plus"></i>New Post</a>
                <a style="float: right;" class="button red" href="logout.php">Logout</a>
            </div>
            
            <br /> <br />
            <table class="sortable">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Title</th>
                        <th>Action</th>
                    </tr>
                </thead>
                
                <tbody>
                    <?php  if(!empty($posts)):   ?>
                    
                     <?php  for($i =0; $i<count($posts);$i++):   ?>
                    <tr>
                        <td><?php echo $posts[$i]['post_id']; ?></td>
                        <td><?php echo $posts[$i]['post_title']; ?></td>
                        <td>
                            <a href="delete.php?id=<?php echo $posts[$i]['post_id']; ?>&page=<?php echo $current_page; ?> " onclick="return confirm('Are you sure??');" class="button small red">Delete</a>
                            <a href="edit.php?id=<?php echo $posts[$i]['post_id']; ?>" class="button small blue">Edit</a>
                        </td>
                    </tr>
                    <?php endfor ?>
                 
                    
                     <?php  else:   ?>
                        <td class="center" colspan="3">Không có dữ liệu!!!!</td>
                    </tr>
                    <?php  endif;   ?>
                    
                </tbody>
            </table>
            
            <form method="get" action="">
                Trang: <input style="width: 45px;" type="number" id="page" name="page" value="<?php echo $current_page; ?>" />
                / <?php echo $total_page; ?> <button type="submit" class="blue small ">Go</button>
                
            </form>
        </div>
        
    </body>
</html>
