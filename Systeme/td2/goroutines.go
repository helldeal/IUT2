package main

import "fmt"

func iter(ID int) {
	for i := 0; i < 100; i++ {
		fmt.Println("Goroutine", ID, "itération", i)
	}
}

func main() {
	for i:=1; i<=100; i++ {
		go iter(i)
	}
	iter(100)
}
