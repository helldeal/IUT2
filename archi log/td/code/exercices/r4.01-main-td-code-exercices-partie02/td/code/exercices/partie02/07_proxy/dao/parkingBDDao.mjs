'use strict'
import { PrismaClient } from '@prisma/client'

import Parking from '../model/parking.mjs'
import {parkingHTTPDao} from "./parkingHTTPDao.mjs";
let prisma = new PrismaClient()

let lastUpdate = null
const TEN_MINUTES = 10*60*1000

const updateBD = async () => {
    if (lastUpdate == null || Date.now() - lastUpdate > TEN_MINUTES) {
        let res = null
        res =  await Promise.all([parkingHTTPDao.findAll(), parkingBDDao.deleteAll()])
        await parkingBDDao.saveAll(res[0])
        lastUpdate = Date.now()
    }
}
export const parkingBDDao = {
    //parking
    findAll : async () => {
        try {
            let parkings =[]
            await updateBD()
           parkings = (await prisma.parking.findMany()).map(obj => new Parking(obj))
            return parkings
        } catch (e) {
            return Promise.reject(e)
        }
    },
    findByNom : async(nom) =>{
        try {
            await updateBD()
            const parking = await prisma.parking.findUnique({where: {nom: nom}})
            return parking == null ? null : new Parking(parking)
        } catch (e) {
            return Promise.reject(e)
        }
    },
    deleteAll : async () => {
        try {
            await prisma.parking.deleteMany({})
        }catch(e){
            return Promise.reject(e)
        }},

    saveAll : async (parkings) => {
            //create many non supporté par SQLite
            try {
                const promesses = parkings.map(parking => prisma.parking.create({
                    data: parking
                }))
                await Promise.all(promesses)
            } catch (e) {
                return Promise.reject(e)
            }
        }
}