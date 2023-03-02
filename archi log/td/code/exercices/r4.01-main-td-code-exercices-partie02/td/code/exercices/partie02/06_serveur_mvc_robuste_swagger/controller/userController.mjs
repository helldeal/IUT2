'use strict'
import {userDao} from "../dao/userDao.mjs";
export const userController = {
    findAll : async () => {
        try {
            return await userDao.findAll()
        } catch (e) {return Promise.reject({message : "error"})}
    },
    findByLogin : async (login) => {
    try {
        return await userDao.findByLogin(login)
    } catch(e) { return Promise.reject({message: "error"})}
    },
    deleteByLogin: async (login) =>{
        try {
            return await userDao.deleteByLogin(login)
        } catch(e)
        { return Promise.reject({message: "error"})}
    },
    add:async (user) => {
        try {
            return  await userDao.add(user)
        } catch(e) {
            return Promise.reject({message: "error"})}
    },
    update: async (login, user) => {
        try {
            return await userDao.update(login, user)
        } catch (e) {
            return Promise.reject({message: "error"})
        }
    }
}