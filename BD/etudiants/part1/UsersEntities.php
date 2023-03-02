<?php
require_once "UserEntity.php";

class UsersEntities
{
    private array $users;

    public function __construct(){
        $this->users = array(
            new UserEntity("u1","p1","s1"),
            new UserEntity("u2","p2","s2"),
            new UserEntity("u3","p3","s3"));
    }

    public function findAll():array {
        return $this->users;
    }

    public function findByLogin(string $login):?UserEntity {
        $res = array_filter($this->users, fn ($user)=>
            $user->getLogin() == $login
        );
        return count($res)==0? null : array_pop($res);
    }
}