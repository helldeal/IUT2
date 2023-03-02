package main

import (
	"fmt"
	"sync"
)

var x, y int
var w sync.WaitGroup
var mutex sync.Mutex

func switchxy() {
	mutex.Lock()
	for i := 0; i < 1000; i++ {
		x, y = y, x
	}
	w.Done()
	mutex.Unlock()
}

func main() {
	x = 5
	y = 7
	for i := 0; i < 1000; i++ {
		w.Add(1)
		go switchxy()
	}
	w.Wait()
	fmt.Println("x vaut", x, "et y vaut", y)
}
