"use strict"
import fetch from 'node-fetch';
import HttpsProxyAgent from 'https-proxy-agent';

(async () => {
    const proxy = process.env.https_proxy
    let agent = null
    if (proxy != undefined) {
    console.log(`Le proxy est ${proxy}`)
    agent = new HttpsProxyAgent(proxy);
    }
    else {
    //pour pouvoir consulter un site avec un certificat invalide
    process.env.NODE_TLS_REJECT_UNAUTHORIZED = "0";
    console.log("Pas de proxy trouvé")
    }
    const urlBase = 'https://data.nantesmetropole.fr/api/records/1.0/search'+
    '/?dataset=244400404_parkings-publics-nantes-disponibilites'+
    '&q=&facet=grp_nom&facet=grp_statut'

    let response = agent!=null ? await fetch(urlBase, {agent: agent}):await fetch(urlBase)
    let json = await response.json()
    console.log(json)
    const nb_hits = json.nhits
    console.log(`Nombre de lignes trouvées ${nb_hits}`)
})();


