package main

import (
	"fmt"
	"reflect"
)

func main() {
	var str string

	fmt.Println(str)
	fmt.Println(len(str))
	fmt.Println(str == "")
	as := "123"[2]
	fmt.Println(as)
	fmt.Println(reflect.TypeOf(as))
	fmt.Println("-------------------华丽分割线----------------------")
}
