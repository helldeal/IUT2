package main

import "fmt"

func main() {

	var c chan bool = make(chan bool, 3)

	c <- true
	c <- false


	fmt.Println(<-c)
	fmt.Println(<-c)

}
