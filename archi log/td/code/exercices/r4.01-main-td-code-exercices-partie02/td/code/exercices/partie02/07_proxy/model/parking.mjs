'use strict'
const degreesToRadians = (degrees) =>
{
    const pi = Math.PI;
    return degrees * (pi/180);
}
export default class Parking {
    identifiant
    nom
    ouvert
    horodatage
    nombreDePlacesDisponibles
    latitude
    longitude
    constructor(obj) {
        Object.assign(this,obj)
    }

    distance(latitude, longitude) {
        const dist =  Math.acos(
            Math.sin(degreesToRadians(this.latitude))*Math.sin(degreesToRadians(latitude))+
            Math.cos(degreesToRadians(this.latitude))*
            Math.cos(degreesToRadians(latitude))*
            Math.cos(degreesToRadians(this.longitude-longitude)))*6371
        return dist
    }
}