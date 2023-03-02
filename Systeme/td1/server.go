package main

import (
	"log"
	"net"
)

func main() {
	listener, err := net.Listen("unix", "tmp/test.sock")
	if err != nil {
		log.Println("listen error:", err)
		return
	}
	defer listener.Close()

	conn, err := listener.Accept()
	if err != nil {
		log.Println("accept error:", err)
		return
	}
	defer conn.Close()

	log.Println("Le client s'est connecté")
	buf := make([]byte, 128)

	n, err := conn.Read(buf)
	if err != nil {
		log.Println("erreur :", err)
	}
	log.Println("len :", n)
	log.Println("msg :", string(buf))
	//time.Sleep(10 * time.Second)

}
