<?php

Interface UserRepositoryInterface
{

    public function findByLogin(string $login):?UserEntity;

}