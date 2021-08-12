package main

import (
	"fmt"
	"runtime"
	"time"
)

func main() {

	go printId("start")

	go func() {
		printId("inner")
	}()

	printId("over")
	time.Sleep(2 * time.Second)
	time.Sleep(time.Duration(2) * time.Second)
	printId("over2")

	var method func(par string) string = func(str string) func(par string) string {
		return func(par string) string {
			str += par
			return str
		}
	}("book")
	fmt.Println(method(" lisi"))
	fmt.Println(method(" zhangsan"))
	fmt.Println(method(" webjun"))

}

func printId(str string) (int, error) {
	return fmt.Println(runtime.Goid(), str)
}
