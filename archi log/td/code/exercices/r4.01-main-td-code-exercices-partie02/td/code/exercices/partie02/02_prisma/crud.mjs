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
        const dist =  Math.acos(
            Math.sin(degreesToRadians(this.latitude))*Math.sin(degreesToRadians(latitude))+
            Math.cos(degreesToRadians(this.latitude))*
            Math.cos(degreesToRadians(latitude))*
            Math.cos(degreesToRadians(this.longitude-longitude)))*6371
        return dist
    }
}

const parkingDao = {
    //Retourne la liste de tous les parkings
    findParkings: async () => (
            await prisma
            .parking
            .findMany()
    ).map(elt => new Parking(elt)),
    //Retourne un parking suivant  son identifiant ou null
    findParkingByIdentifiant: async (id) => {
        const elt = await prisma.parking.findUnique({where: {identifiant: id}})
        return elt == null ? null : new Parking(elt)
    },
    //Retourne un parking suivant  son nom ou null
    findParkingByNom : async (nom) => {
        const elt = await prisma.parking.findUnique({where: {nom: nom}})
        return elt == null ? null : new Parking(elt)
    },
    //Supprime tous les parkings ne renvoie rien
    deleteParkings: async () => await prisma
        .parking
        .deleteMany()
    ,
    //Ajout un parking et renvoie le parking ajouté
    addParking: async (parking) => {
        try {
            const elt = await prisma.parking.create({
                data: parking
            })
            return new Parking(elt)
        }
        catch (e) {return Promise.reject(e)}
    },
    //Met un ajour un parking connu par son identifiant
    //renvoie le marking modifié ou une exception
    updateParking: async (identifiant, parking) => {
        try {
            const elt = await prisma.parking.update({
                where: {
                    identifiant: identifiant
                },
                data: parking
            })
            return new Parking(elt)
        } catch (e) {
            return Promise.reject(e)
        }
    },
    //Met un ajour un parking connu par son identifiant si l'horodatage est postérieur
    //renvoie le marking modifié ou une exception
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