package main

import (
	"bufio"
	"fmt"
	"log"
	"os"
	"reflect"
)

func main() {

	//_03_IF()
	//_03_IfError()

	arr1 := []int{1, 2, 3, 4, 5}
	arr2 := []int{10, 20, 30, 40, 50}

	fmt.Println("-------------------for label----------------------")
label1:
	for _, val1 := range arr1 {
		for _, val2 := range arr2 {
			fmt.Println(val1, val2)
			if val2 == 40 {
				break label1
			}

		}
	}
}

func _03_IfError() {
	fmt.Println("-------------------华丽分割线----------------------")
	var int *os.File = os.Stdin
	var reader *bufio.Reader = bufio.NewReader(int)
	var er2 error
	readString, err := reader.ReadString('.')
	if err != nil {
		log.Fatalln(err)
	}
	fmt.Println(readString)
	fmt.Println(er2)
	fmt.Println(reflect.TypeOf(er2))
	fmt.Println(reflect.TypeOf(err))
}

func _03_IF() {
	var flag bool = false
	if flag {
		fmt.Println("ddd")
	} else if !flag {
		fmt.Println("222")
	}
}
