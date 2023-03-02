<?php


class User
{
    private UserRepositoryInterface $repository;
    public function __construct()
    {
        $this->repository = new DbUserRepository();
    }

    function debut(){
        //TODO
    }

    function check(){
        //TODO

    }

    function error($param){
        //TODO
    }

}