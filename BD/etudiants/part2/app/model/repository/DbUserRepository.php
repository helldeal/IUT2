<?php


class DbUserRepository implements UserRepositoryInterface
{

    private $connexion;
    public function __construct()
    {
        $dsn = "sqlite:".CFG["db"]["host"].CFG["db"]["database"];
        $this->connexion = SPDO::getInstance($dsn,CFG["db"]["login"],CFG["db"]["password"],CFG["db"]["options"],CFG["db"]["exec"])
            ->getConnexion();
    }

    public function findByLogin(string $login):?UserEntity {
        //TODO
        return null;
    }

}