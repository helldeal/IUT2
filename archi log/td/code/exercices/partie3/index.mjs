"use strict";
import Hapi from "@hapi/hapi";
//notre modèle un Utiilisateur et l'ensemble des utilisateurs
//les utilisateurs sont identifiés par leur login
class User {
  login;
  password;
  constructor(obj) {
    Object.assign(this, obj);
  }
}
class Users {
  users = [];
}
//pas de bd uniquement en mémoire
const model = new Users();
//notre DAO
const userDao = {
  //tous les utilisteurs
  findAll: () => model.users,
  //ajout un utilisateur
  //renvoie l'utilisateur ajouté ou null sinon
  findByLogin: (login) => {
    const users = model.users.filter((user) => user.login == login);
    return users.length != 0 ? users[0] : null;
  },
  //supprime un utilisateur
  //renvoie l'utilisateur supprimé ou null sinon
  deleteByLogin: (login) => {
    const user = userDao.findByLogin(login);
    if (user == null) return null;
    model.users = model.users.filter((user) => user.login != user.login);
    return user;
  },
  //ajout un utilisateur
  //renvoie l'utilisateur ajouté ou null si il était déjà présent
  add: (user) => {
    const userByLogin = userDao.findByLogin(user.login);
    if (userByLogin != null) return null;
    model.users.push(user);
    return user;
  },
  //Modifie un utilisateur
  //premd en paramètre le login du user à modifier et la modification
  //renvoie le user modifier ou null
  update: (login, user) => {
    const userByLogin = userDao.findByLogin(login);
    if (user == null) return null;
    userDao.deleteByLogin(login);
    userDao.deleteByLogin(user.login);
    userDao.add(user);
    return user;
  },
};
//la methode qui permettra de lancer le serveur
const init = async () => {
  //Un serveur qui écoute sur le port 3000
  const server = Hapi.server({
    port: 3000,
    host: "localhost",
  });
  //un tableau de route
  //une route est définie par
  //son verbre
  //son path
  //un handler qui reçoit l'information dans request
  // et produit une réponse avec h
  server.route([
    {
      method: "GET",
      path: "/user",
      handler: (request, h) => {
        //le message renvoyé et le code hhtp
        return h.response(userDao.findAll()).code(200);
      },
    },
    {
      method: "GET",
      //une route avec un parametre
      //utilisable avec request.params.login
      path: "/user/{login}",
      handler: (request, h) => {
        const user = userDao.findByLogin(request.params.login);
        if (user != null) return h.response(user).code(200);
        else return h.response({ message: "not found (findByLogin)" }).code(404);
      },
    },
    {
      method: "POST",
      path: "/user",
      handler: (request, h) => {
        //Le body est accessible via request.payload
        const userToAdd = request.payload;
        const user = userDao.add(userToAdd);
        if (user != null) return h.response(user).code(201);
        else return h.response({ message: "user already exist" }).code(400);
      },
    },
    {
      method: "DELETE",
      path: "/user/{login}",
      handler: (request, h) => {
        const user = userDao.deleteByLogin(request.params.login);
        if (user != null) return h.response(user).code(200);
        else return h.response({ message: "not found (deleteByLogin)" }).code(404);
      },
    },
    {
      method: "PUT",
      path: "/user/{login}",
      handler: (request, h) => {
        const user = userDao.update(request.params.login, request.payload);
        if (user != null) return h.response(user).code(200);
        else return h.response({ message: "not found (update)" }).code(400);
      },
    },
  ]);
  //lancement du serveur
  await server.start();
  console.log("Server running on %s", server.info.uri);
};
//evenement capturé en cas d'erreur
process.on("unhandledRejection", (err) => {
  console.log(err);
  process.exit(1);
});
init();
