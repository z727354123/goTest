package _2021_08

import (
	"bufio"
	"fmt"
	"log"
	"os"
	"reflect"
)

func main() {

	_03_IF()
	_03_IfError()

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
