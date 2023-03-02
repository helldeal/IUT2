'use strict'
export default class User {
    login
    password
    constructor(obj) {
        Object.assign(this,obj)
    }
}