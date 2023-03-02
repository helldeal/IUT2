'use strict'
import { PrismaClient } from '@prisma/client'

import User from '../model/user.mjs'

let prisma = new PrismaClient()

export const userDao = {
    //tous les utilisteurs
    findAll : async () => {
        try {
            const users = (await prisma.user.findMany()).map(obj => new User(obj))
            return users
        } catch (e) {
            return Promise.reject(e)
        }
        },


    //renvoie l'utilisateur ajouté ou une erreur sinon
    findByLogin : async (login) => {
        try {
            const elt = await prisma.user.findUnique({where: {login: login}})
            return elt == null ? null : new User(elt)
        } catch (e) {
            return Promise.reject(e)
        }
    },
    //supprime un utilisateur
    //renvoie l'utilisateur supprimé ou erreur sinon
    deleteByLogin: async (login) => {
        try {
            const elt = await prisma.user.delete({where: {login: login}})
            return new User(elt)
        }
        catch (e) {
            return Promise.reject(e)
        }
    },
    //ajout un utilisateur
    //renvoie l'utilisateur ajouté ou une erreur si il était déjà présent
    add: async (user) => {
        try {
            const elt = await prisma.user.create({data: user})
            return new User(elt)
        }
        catch (e) {
            return Promise.reject(e)
        }
    },

    //Modifie un utilisateur
    //premd en paramètre le login du user à modifier et la modification
    //renvoie le user modifier ou une erreur
    update: async (login, user) => {
        try {
            const elt = await prisma.user.update({
                where: {
                    login: login
                },
                data: user
            })
            return new User(elt)
        } catch (e) {
            return Promise.reject(e)
        }
    }
}