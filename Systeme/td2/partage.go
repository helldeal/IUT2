package main

import "fmt"

var turn1 bool

func routine1(c1, c2 chan bool) {
	for {
		<-c1
		fmt.Println("routine1")
		c2 <- true
	}
}

func routine2(c1, c2 chan bool) {
	for {
		<-c2
		fmt.Println("routine2")
		c1 <- true
	}
}

func main() {
	var turn1 chan bool = make(chan bool, 0)
	var turn2 chan bool = make(chan bool, 0)
	go routine1(turn1, turn2)
	turn1 <- true
	routine2(turn1, turn2)
}
