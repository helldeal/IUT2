'use strict'
import {userDao} from "../dao/userDao.mjs";
export const userController = {
    findAll : () => userDao.findAll(),
    findByLogin : async (login) => userDao.findByLogin(login),
    deleteByLogin: async (login) => userDao.deleteByLogin(),
    add:async (user) => userDao.add(user),
    update: async (login, user) => userDao.update(login,user)
}