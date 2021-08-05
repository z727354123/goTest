package main

import (
	"fmt"
	"reflect"
)

func main() {

	myFunc(0, "1")
	myFunc(1, "1", "2")
	myFunc(2, "1", "2", "3")
	var strs []string = []string{"3", "2", "1"}
	fmt.Println(strs)
	myFunc(7, strs...)

}
func myFunc(num int, strs ...string) {

	fmt.Println(num, strs)
	//fmt.Println(strs...)

	fmt.Println(reflect.TypeOf(strs))
}
