<?php
	require "config".DIRECTORY_SEPARATOR."config.inc.php";

    spl_autoload_register(function($class) {

        $prefix = '..'. DIRECTORY_SEPARATOR . "system".DIRECTORY_SEPARATOR;

        if (file_exists($prefix.$class . '.php')) {
            require_once($prefix.$class . '.php');
        }
        $prefix = '..'. DIRECTORY_SEPARATOR . "app".DIRECTORY_SEPARATOR;
        if (file_exists($prefix."controller".DIRECTORY_SEPARATOR.$class . '.php')) {
            require_once($prefix."controller".DIRECTORY_SEPARATOR.$class . '.php');
        }
        if (file_exists($prefix."model".DIRECTORY_SEPARATOR."entity".DIRECTORY_SEPARATOR.$class . '.php')) {
            require_once($prefix."model".DIRECTORY_SEPARATOR."entity".DIRECTORY_SEPARATOR.$class . '.php');
        }
        if (file_exists($prefix."model".DIRECTORY_SEPARATOR."repository".DIRECTORY_SEPARATOR.$class . '.php')) {
            require_once($prefix."model".DIRECTORY_SEPARATOR."repository".DIRECTORY_SEPARATOR.$class . '.php');
        }
    });
    $router = new Router();
    $router->route();

?>