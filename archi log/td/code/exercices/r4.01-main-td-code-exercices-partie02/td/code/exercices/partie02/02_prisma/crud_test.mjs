"use strict"
import assert from 'node:assert'
import {Parking, parkingDao} from './crud.mjs'

await parkingDao.deleteParkings()
let parkings = await parkingDao.findParkings()
assert.deepStrictEqual(parkings,[])
let parking = await parkingDao.findParkingByIdentifiant("001")
assert.equal(parking,null)
parking = await parkingDao.findParkingByNom("Feydeau")
assert.equal(parking,null)

const parkingToInsert = new Parking({
    identifiant: "001",
    nom: "Feydeau",
    ouvert: true,
    horodatage: new Date(),
    nombreDePlacesDisponibles: 10,
    latitude: 47.21407529499999,
    longitude: -1.552558781000016
})
parking = await parkingDao.addParking(parkingToInsert)
assert.deepStrictEqual(parking, parkingToInsert)
//Clef primaire non unique
assert.rejects(parkingDao.addParking(parkingToInsert))


parkings = await parkingDao.findParkings()
assert.deepStrictEqual(parkings, [parkingToInsert])


parking = await parkingDao.findParkingByIdentifiant("001")
assert.deepStrictEqual(parking, parkingToInsert)
parking = await parkingDao.findParkingByNom("Feydeau")
assert.deepStrictEqual(parking, parkingToInsert)

let parkingToUpdate = new Parking({
    identifiant: "001M",
    nom: "Feydo",
    ouvert: true,
    horodatage: new Date(),
    nombreDePlacesDisponibles: 10,
    latitude: 47.21407529499999,
    longitude: -1.552558781000016
})


parking = await parkingDao.updateParking("001", parkingToUpdate)
assert.deepStrictEqual(parking, parkingToUpdate)
//identifiant inconnu
assert.rejects(parkingDao.updateParking("XXX", parkingToUpdate))
parking = await parkingDao.findParkingByIdentifiant("001M")
assert.deepStrictEqual(parking, parkingToUpdate)

const now = new Date();
parkingToUpdate = new Parking({
    identifiant: "001M",
    nom: "FeydoD",
    ouvert: true,
    horodatage: now,
    nombreDePlacesDisponibles: 10,
    latitude: 47.21407529499999,
    longitude: -1.552558781000016
})

parking = await parkingDao.updateParkingIfModified("001M", now, parkingToUpdate)
assert.deepStrictEqual(parking, parkingToUpdate)

const parkingToUpdateTry = new Parking({
    identifiant: "001",
    nom: "FeydoDD",
    ouvert: true,
    horodatage: now,
    nombreDePlacesDisponibles: 10,
    latitude: 47.21407529499999,
    longitude: -1.552558781000016
})

parking = await parkingDao.updateParkingIfModified("001M", now, parkingToUpdateTry)
assert.deepStrictEqual(parking, parkingToUpdate)

assert.equal(parking.distance(parking.latitude, parking.longitude),0)
assert.equal(parking.distance(47.218371, -1.553621), 0.4843510610155017)