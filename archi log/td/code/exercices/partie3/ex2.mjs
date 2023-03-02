"use strict"
//importation du client
import { PrismaClient } from '@prisma/client'
const prisma = new PrismaClient()
const degreesToRadians = (degrees) =>
{
    const pi = Math.PI;
    return degrees * (pi/180);
}
class Parking {
    identifiant
    nom
    ouvert
    horodatage
    nombreDePlacesDisponibles
    latitude
    longitude
    constructor(obj) {
        //declare et instancie les attribut en recopiant ceux de obj
        Object.assign(this, obj)
    }
    //distance en kilimotre avec une autre coordonnee
    distance(latitude, longitude) {
        const dist = Math.acos(
        Math.sin(degreesToRadians(this.latitude))*Math.sin(degreesToRadians(latitude))+
        Math.cos(degreesToRadians(this.latitude))*
        Math.cos(degreesToRadians(latitude))*
        Math.cos(degreesToRadians(this.longitude-longitude)))*6371
        return dist
    }
}
const parkingDao = {
    //Retourne la liste de tous les parkings
    findParkings: async () =>(await prisma.parking
    .findMany()).map(obj => new Parking(obj)),

    //Retourne un parking suivant son identifiant ou null
    findParkingByIdentifiant: async (id) => {
        const park=(await prisma.parking.findUnique({
            where: {
            identifiant: id
            }
        }))
        if (park!=null)return new Parking(park)}
    ,

    //Retourne un parking suivant son nom ou null
    findParkingByNom : async (nom) => {
        const park=(await prisma.parking.findUnique({
            where: {
            nom: nom
            }
        }))
        if(park!=null)return new Parking(park)
    }
    ,

    //Supprime tous les parkings ne renvoie rien
    deleteParkings: async () => prisma.parking.deleteMany(),
    //Ajout un parking et renvoie le parking ajouté
    //En cas d'echec renvoie Promise.reject(...)
    addParking: async (parking) => new Parking(await prisma.parking.create({
        data: parking
    })),

    //Met un ajour un parking connu par son identifiant
    //renvoie le marking modifié ou Promise.reject(...)
    updateParking: async (identifiant, parking) => new Parking(await prisma.parking.update({
        where: {
        identifiant: identifiant
        },
        data: parking
    })),
    
    //Met un ajour un parking connu par son identifiant si l'horodatage est postérieur
    //renvoie le marking modifié ou Promise.reject(...)
    updateParkingIfModified: async (identifiant,date, parking) => {
        try {
        const elt = await prisma.parking.updateMany({
            where: {
            identifiant: identifiant,
            horodatage: {lt: date}
            },
            data: parking
        })
        return await parkingDao.findParkingByIdentifiant(identifiant)
        } catch (e) {
            return Promise.reject(e)
        }
    }
}
export {Parking, parkingDao}