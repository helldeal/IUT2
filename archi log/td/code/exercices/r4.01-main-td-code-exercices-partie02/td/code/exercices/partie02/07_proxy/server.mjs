'use strict'

import Hapi from '@hapi/hapi'
import Joi from 'joi'


import Inert from '@hapi/inert'
import Vision from '@hapi/vision';
import HapiSwagger from 'hapi-swagger';

import {parkingController} from "./controller/parkingController.mjs";

const joiParking = Joi.object({
    id: Joi.number().integer().required().description("blabla"),
    nom: Joi.number().integer().required().description("blabla"),
    ouvert: Joi.boolean().required().description("blabla"),
    horodatage: Joi.number().integer().required().description("blabla"),
    nombreDePlacesDisponibles: Joi.number().integer().required().description("blabla"),
    latitude: Joi.number().precision(20).required().description("blabla"),
    longitude: Joi.number().precision(20).required().description("blabla"),
}).description('Parking')

const joiParkings = 
Joi.array().items(joiParking).description("A collection of Parking")

const notFound = Joi.object({
    message: "not found"
})

const errorMessage = Joi.object({
    message: "error"
})

const swaggerOptions = {
    info: {
        title: "L'API des parkings",
        version: '2.0.0',
    }
};

const routes =[
    {
        method: '*',
        path: '/{any*}',
        options: {
            description: 'Unknowed route',
            notes: 'return 404',
            tags: ['api']
        },
        handler: function (request, h) {
            return h.response({message: "not found"}).code(404)
        }
    },
    {
        method: 'GET',
        path: '/parking',
        options: {
            description: 'Get All Parkings',
            notes: 'Returns an array of Parkings',
            tags: ['api']
        },

        handler: async (request, h) => {

            try {
                const  parking = await parkingController.findAll()
                return h.response(parking).code(200)
            } catch (e) {
                return h.response(e).code(400)
            }
        }
    },
    {
        method: 'GET',
        options: {
            validate : {
                params: Joi.object({
                    latitude : Joi.number(),
                    longitude: Joi.number()
                })
            },
            description: 'Get Parkings sort by shortest to coord',
            notes: 'Returns an array of Parkings',
            tags: ['api']
        },
        path: '/parking/distance/{latitude}/{longitude}',

        handler: async (request, h) => {

            try {
                const latitude = request.params.latitude
                const longitude = request.params.longitude

                const  parking = await parkingController.findByCoordinate(latitude,longitude)
                return h.response(parking).code(200)
            } catch (e) {
                return h.response(e).code(400)
            }
        }
    },
    {
        method: 'GET',
        options: {
            validate : {
                params: Joi.object({
                    nom : Joi.string()
                    .required()
                })
            },
            description: 'Get Parking that match the input name',
            notes: 'Returns a Parking',
            tags: ['api']
        },
        path: '/parking/find/{nom}',

        handler: async (request, h) => {

            try {
                const  parking = await parkingController.findByNom(request.params.nom)
                if (parking == null)
                    return h.response({message: 'not found'}).code(404)
                else
                    return h.response(parking).code(200)
            } catch (e) {
                return h.response(e).code(400)
            }
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
    await server.register([
        Inert,
        Vision,
        {
            plugin: HapiSwagger,
            options: swaggerOptions
        }
    ]);
    await server.start();
    console.log(`Server running at: ${server.info.uri}`);
    return server;
};


process.on('unhandledRejection', (err) => {

    console.log(err);
    process.exit(1);
});


