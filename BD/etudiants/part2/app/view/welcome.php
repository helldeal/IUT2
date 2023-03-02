<?php
session_start();
if (!isset($_SESSION['login'])) {
    $message = "accès impossible";
    require "error.php";die();
} else {
    $login = $_SESSION['login'];
}
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>
<p> Welcome : <?= $login?></p>
</body>
</html>
