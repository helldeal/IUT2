import {init} from '../server.mjs';

import chai from 'chai'
import chaiHttp from 'chai-http'

let should = chai.should()

chai.use(chaiHttp)

describe('user', () => {
    let server;

    beforeEach(async () => {
        server = await init();
    });

    afterEach(async () => {
        await server.stop();
    });

    it('must be empty', async () => {

        const res = await server.inject({
            method: 'get',
            url: '/user'
        });
        chai.expect(res.statusCode).to.equal(200);
        chai.expect(res.result).to.be.eql([])
    })

    it('add user ok', async () => {

        const res = await server.inject({
            method: 'post',
            url: '/user',
            payload: {login:"jojo",password:"pass"}
        });
        chai.expect(res.statusCode).to.equal(201);
        chai.expect(res.result).to.be.eql({login:"jojo",password:"pass"})
    })
    it('add existing user ', async () => {
        const res = await server.inject({
            method: 'post',
            url: '/user',
            payload: {login:"jojo",password:"pass"}
        });
        chai.expect(res.statusCode).to.equal(400);
        chai.expect(res.result).to.be.eql({message:"error"})
    })
    it('must be equal to added ', async () => {
        const res = await server.inject({
            method: 'get',
            url: '/user',
            payload: {login:"jojo",password:"pass"}
        });
        chai.expect(res.statusCode).to.equal(200);
        chai.expect(res.result).to.be.eql([{login:"jojo",password:"pass"}])
    })
    it('delete not present', async () => {
        const res = await server.inject({
            method: 'delete',
            url: '/user/xxx',
        });
        chai.expect(res.statusCode).to.equal(404);
        chai.expect(res.result).to.be.eql({ message: 'not found' })
    })
    it('add and delete user', async () => {
        let res = await server.inject({
            method: 'post',
            url: '/user',
            payload: {login:"jojoD",password:"pass"}
        });

        chai.expect(res.statusCode).to.equal(201);
        chai.expect(res.result).to.be.eql({login:"jojoD",password:"pass"})


         res = await server.inject({
            method: 'delete',
            url: '/user/jojoD',
        });
        chai.expect(res.statusCode).to.equal(200);
        chai.expect(res.result).to.be.eql({login:"jojoD",password:"pass"})
    })
    it('get user ok  ', async () => {
        const res = await server.inject({
            method: 'get',
            url: '/user/jojo',
        });
        chai.expect(res.statusCode).to.equal(200);
        chai.expect(res.result).to.be.eql({login:"jojo",password:"pass"})
    })

    it('get user not present  ', async () => {
        const res = await server.inject({
            method: 'get',
            url: '/user/xxx',
        });
        chai.expect(res.statusCode).to.equal(404);
        chai.expect(res.result).to.be.eql({message: "not found"})
    })

    it('update user not present ', async () => {
        const res = await server.inject({
            method: 'put',
            url: '/user/xxx',
            payload: {login:"jojo",password:"pass"}
        });
        chai.expect(res.statusCode).to.equal(400);
        chai.expect(res.result).to.be.eql({message: "not found"})
    })
    it('update user ok same login ', async () => {
        const res = await server.inject({
            method: 'put',
            url: '/user/jojo',
            payload: {login:"jojo",password:"passU"}
        });
        chai.expect(res.statusCode).to.equal(200);
        chai.expect(res.result).to.be.eql({login:"jojo",password:"passU"})
    })

    it('update user ok other login ', async () => {
        const res = await server.inject({
            method: 'put',
            url: '/user/jojo',
            payload: {login:"jojoU",password:"passU"}
        });
        chai.expect(res.statusCode).to.equal(200);
        chai.expect(res.result).to.be.eql({login:"jojoU",password:"passU"})
    })

})

describe('default route', () => {
        let server;

        beforeEach(async () => {
            server = await init();

        });

        afterEach(async () => {
            await server.stop();
        });

        it('expected default route ', async () => {

            const res = await server.inject({
                method: 'get',
                url: '/api/v1/brasserie'
            });
            chai.expect(res.statusCode).to.equal(404);
            chai.expect(res.result).to.be.eql({message: 'not found'})
        })
})


describe('wrong payload', () => {
    let server;

    beforeEach(async () => {
        server = await init();

    });

    afterEach(async () => {
        await server.stop();
    });

    it('expected error put bad body ', async () => {

        const res = await server.inject({
            method: 'put',
            url: 'user/xxx',
            payload: {login: "", password: "passU"}
        });
        chai.expect(res.statusCode).to.equal(400);
        chai.expect(res.result).to.be.eql({error: "Bad Request", message: "Invalid URL", statusCode: 400})
    })

    it('expected error put no body ', async () => {

        const res = await server.inject({
            method: 'put',
            url: 'user/xxx',

        });
        chai.expect(res.statusCode).to.equal(400);
        chai.expect(res.result).to.be.eql({error: "Bad Request", message: "Invalid URL", statusCode: 400})
    })
})

