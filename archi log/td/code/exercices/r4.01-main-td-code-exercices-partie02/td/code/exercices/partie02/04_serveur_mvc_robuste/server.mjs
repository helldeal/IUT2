'use strict'

import Hapi from '@hapi/hapi'
import  Joi from 'joi'

import {userController} from "./controller/userController.mjs";

const joiUser = Joi.object({
        login: Joi.string().required(),
        password: Joi.string().required()
})

const routes =[
    {
        method: '*',
        path: '/{any*}',
        handler: function (request, h) {
            return h.response({message: "not found"}).code(404)
        }
    },
    {
        method: 'GET',
        path: '/user',
        handler: async (request, h) => {
            //le message renvoyé et le code hhtp
            try {
                const  users = await userController.findAll()
                return h.response(users).code(200)
            } catch (e) {
                return h.response(e).code(400)
            }
        }
    },
    {
        method: 'GET',
        //une route avec un parametre
        //utilisable avec request.params.login
        path: '/user/{login}',
        handler: async (request, h) => {
            try {
                const user = await userController.findByLogin(request.params.login)
               if (user == null)
                    return h.response({message: 'not found'}).code(404)
                else
                    return h.response(user).code(200)
            } catch (e) {
                return h.response({message: 'not found'}).code(404)
            }
        }
    },
    {
        method: 'POST',
        path: '/user',
        options: {
            validate: {
                payload: joiUser
            }
        },
        handler: async (request, h) => {
            try {
                //Le body est accessible via request.payload
                const userToAdd = request.payload
                const user = await userController.add(userToAdd)
                return h.response(user).code(201)
            } catch (e) {
                return h.response({message: 'error'}).code(400)
            }
        }
    },
    {
        method: 'DELETE',
        path: '/user/{login}',
        handler: async (request, h) => {
            try {
                const user = await userController.deleteByLogin(request.params.login)
                return h.response(user).code(200)
            } catch (e) {
                return h.response({message: 'not found'}).code(404)
            }
        }
        },
    {
        method: 'PUT',
        path: '/user/{login}',
        options: {
            validate: {
                payload: joiUser
            }
        },
        handler: async (request, h) => {
            try {
                const user = await userController.update(request.params.login, request.payload)
                return h.response(user).code(200)
            } catch (e) { return h.response({message: 'not found'}).code(400)}
        }
    }
]

const server = Hapi.server({
    port: 3000,
    host: 'localhost'
});

server.route(routes);

export const init = async () => {

    await server.initialize();
    return server;
};

export  const start = async () => {
    await server.start();
    console.log(`Server running at: ${server.info.uri}`);
    return server;
};


process.on('unhandledRejection', (err) => {

    console.log(err);
    process.exit(1);
});



