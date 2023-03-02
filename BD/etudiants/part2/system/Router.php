<?php

/**
 * Class Router
 */
class Router
{
    /**
     * permet d'éxécuter la méthode d'un controleur choisi
     * si la méthode est omise alors index est choisi
     * Les URL saisies sont de la forme
     *      site/controleur
     *      site/controleur/method
     *      site/controleur/method/param1/...
     */
    function route()
    {
        $scriptName = $_SERVER["SCRIPT_NAME"];
        $requestURI = $_SERVER["REQUEST_URI"];

        //Le script name contient index.php on le supprime
        $prefix = substr($scriptName, 0, strlen($scriptName) - 9);
        //Le suffixe est le requestURI privé du préfix
        $suffix = substr($requestURI,strlen($prefix));

        $params = explode("/", $suffix);

        if (count($params) == 0) {
            echo "no controller found";
            die();
        }

        $controller = $params[0];
        array_shift($params);

        if (count($params)==0)
            $controllerMethod="index";
        else
            $controllerMethod=$params[0];

        array_shift($params);
        //try {
            $controllerinstance = new $controller();
            $controllerinstance->$controllerMethod($params);
        //}
        /*catch (Error $e){
            echo "route not found $controller $controllerMethod ";
            var_dump($params);

        };*/
    }
}