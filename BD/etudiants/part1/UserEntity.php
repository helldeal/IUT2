<?php


class UserEntity
{
    private string $login;
    private string $password;
    private string $slug;
    /**
     * User constructor.
     * @param string $login
     * @param string $password
     * @param string $slug
     */
    public function __construct(string $login, string $password, string $slug)
    {
        $this->login = $login;
        $this->password = $password;
        $this->slug = $slug;
    }

    public function isValidePassword(string $password):bool{
        return $this->password == $password;
    }

    /**
     * @return string
     */
    public function getSlug(): string
    {
        return $this->slug;
    }

    /**
     * @param string $slug
     */
    public function setSlug(string $slug): void
    {
        $this->slug = $slug;
    }

    /**
     * @return string
     */
    public function getLogin(): string
    {
        return $this->login;
    }

    /**
     * @return string
     */
    public function getPassword(): string
    {
        return $this->password;
    }


}