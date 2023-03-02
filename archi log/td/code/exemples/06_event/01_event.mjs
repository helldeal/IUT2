import events from "events"

const em = new events.EventEmitter();

em.on('firstEvent', function (data) {
    console.log(data);
});

em.on('secondEvent', function (data) {
    console.log(data);
});

em.emit('firstEvent',"hello")
setTimeout(() => em.emit('secondEvent',10), 10)