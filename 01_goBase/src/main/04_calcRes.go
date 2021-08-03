package main

import (
	"bufio"
	"fmt"
	"log"
	"os"
	"strconv"
	"strings"
)

func main() {
	// 生命多个
	var (
		name string
		book error
	)
	fmt.Println(book)
	fmt.Println(name)

	_04_IfError()

}

func _04_IfError() {
	fmt.Println("-------------------华丽分割线----------------------")
	var stdin *os.File = os.Stdin
	var reader *bufio.Reader = bufio.NewReader(stdin)
	readString, err := reader.ReadString('\n')
	if err != nil {
		log.Fatalln(err)
	}
	// 未去除空格
	fmt.Println(readString)

	var nums float64 = 1.2
	// 去除空格
	res := strings.TrimSpace(readString)
	fmt.Println(res)
	// 转换成 float64
	nums, err2 := strconv.ParseFloat(res, 64)
	if err2 != nil {
		log.Fatalln(err)
	}
	fmt.Println(nums)
	var resStr string
	if nums >= 10.4 {
		resStr = "bigger than 10.4"
	} else {
		resStr = "less 10.4"
	}
	fmt.Println(resStr)
	fmt.Println("-------------------另外で一条，华丽の分割线----------------------")
}
