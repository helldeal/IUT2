'use strict'
import {parkingBDDao} from "../dao/parkingBDDao.mjs";
export const parkingController = {
    findAll : async () => {
        try {
            return await parkingBDDao.findAll()
        } catch (e) {return Promise.reject({message : "error"})}
    },
    findByCoordinate : async  (latitude,longitude) => {
        try {
            return (await parkingController.findAll())
                .map(parking => [parking,parking.distance(latitude,longitude)])
                .sort((a1,a2) => a1[1] - a2[1])
                .map(obj => obj[0])
        } catch (e) {
            console.log(e)
            return Promise.reject({message : "error"})}
    },
    findByNom : async (nom)=>{
        try {
            return await parkingBDDao.findByNom(nom)
        } catch (e) {return Promise.reject({message : "error"})}
    },
    
}