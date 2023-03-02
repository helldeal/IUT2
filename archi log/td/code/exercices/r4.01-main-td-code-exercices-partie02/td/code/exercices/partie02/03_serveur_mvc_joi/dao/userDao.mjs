'use strict'
import User from '../model/user.mjs'

//pour simuler notre bd
class Users {
    users = []
}
//pas de bd uniquement en mémoire
const model = new Users()

export const userDao = {
    //tous les utilisteurs
    findAll : () => model.users,

    //ajout un utilisateur
    //renvoie l'utilisateur ajouté ou null sinon
    findByLogin : (login) => {
        const users = model.users.filter((user)=>user.login==login)
        return users.length!=0 ? users[0]:null
    },
    //supprime un utilisateur
    //renvoie l'utilisateur supprimé ou null sinon
    deleteByLogin: (login) => {
        const user = userDao.findByLogin(login)
        if (user==null)
            return null
        model.users = model.users.filter((user)=>user.login != user.login)
        return user
    },
    //ajout un utilisateur
    //renvoie l'utilisateur ajouté ou null si il était déjà présent
    add: (user) => {
        const userByLogin = userDao.findByLogin(user.login)
        if (userByLogin != null)
            return null
        model.users.push(user)
        return user
    },

    //Modifie un utilisateur
    //premd en paramètre le login du user à modifier et la modification
    //renvoie le user modifier ou null
    update: (login, user) => {
        const userByLogin = userDao.findByLogin(login)
        if (user==null)
            return null
        userDao.deleteByLogin(login)
        userDao.deleteByLogin(user.login)
        userDao.add(user)
        return user
    }
}