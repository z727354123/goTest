package main

import "fmt"

func main() {
	if true {
		var myChan chan string = make(chan string, 12)
		myChan <- "ddd"
		fmt.Println(<-myChan)
	}
	if true {
		var myChan chan<- string = make(chan<- string)
		fmt.Println(myChan)
	}
	if true {
		var myChan <-chan string = make(<-chan string)
		fmt.Println(myChan)
	}
	if true {
		var myChan chan string = make(chan string)
		fmt.Println(myChan)
	}
}
