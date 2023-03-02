'use strict'
//acces au .env
import * as dotenv from 'dotenv'
dotenv.config()
const PARKING_BASE_URL = process.env. PARKING_BASE_URL
const proxy = process.env.https_proxy

import fetch from 'node-fetch';
import HttpsProxyAgent from 'https-proxy-agent';

let agent = null
if (proxy != undefined) {
    agent =  new HttpsProxyAgent(proxy);
}
else {
    //pour pouvoir consulter un site avec un certificat invalide
    process.env.NODE_TLS_REJECT_UNAUTHORIZED = "0";
}

import Parking from '../model/parking.mjs'




export const parkingHTTPDao = {
    //parking
    findAll : async () => {
        try {
            let response = agent!=null ? await fetch(PARKING_BASE_URL, {agent: agent}):await fetch(PARKING_BASE_URL)
            let data = await response.json()
            const nb_hits = data.nhits
            const urlFull = PARKING_BASE_URL + `&rows=${nb_hits}`
            response = agent!=null ? await fetch(urlFull, {agent: agent}) : await fetch(urlFull)
            data = await response.json()

             const parkings = data.records.flatMap(record => record.fields.location==undefined || record.fields.location==undefined ? [] :
                    [ new Parking(
                     {
                         identifiant: record.fields.grp_identifiant,
                         nom: record.fields.grp_nom,
                         ouvert: record.fields.grp_statut == 5,
                         horodatage: record.fields.grp_horodatage,
                         nombreDePlacesDisponibles: record.fields.grp_disponible,
                         latitude: record.fields.location!=undefined?record.fields.location[0]:null,
                         longitude: record.fields.location!=undefined?record.fields.location[1]:null
                     }
                 )]
             )
          return parkings

        } catch (e) {
            console.log(e)
            return Promise.reject(e)
        }
    },
}