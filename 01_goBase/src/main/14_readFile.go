package main

import (
	"bufio"
	"fmt"
	"log"
	"os"
)

func main() {
	fmt.Println("-------------------读取文件----------------------")
	// 读取文件
	if false {
		file, err := os.Open("/Users/main.go")
		if err != nil {
			log.Fatalln(err)
		}
		scanner := bufio.NewScanner(file)
		for scanner.Scan() {
			fmt.Println(scanner.Text())
		}
		err = file.Close()
		if err != nil {
			log.Fatalln(err)
		}
		if scanner.Err() != nil {
			log.Fatalln(scanner.Err())
		}
		fmt.Println("-------------------end----------------------")
	}

	fmt.Println("-------------------切片----------------------")
	if true {
		var arr1 []int = []int{0, 1, 2, 3, 4, 5}
		var arr2 []int = nil
		var arr3 []int

		fmt.Println(arr1, len(arr1), cap(arr1))
		fmt.Println(arr2, len(arr2), cap(arr2))
		arr2 = nil
		for index, val := range arr2 {
			fmt.Println("index", index, val)
		}
		fmt.Println(arr2 == nil, arr2, len(arr2), cap(arr2))
		var arr4 []int = make([]int, 0)
		fmt.Println(arr3 == nil, arr3, len(arr3), cap(arr3))
		fmt.Println(arr4 == nil, arr4, len(arr4), cap(arr4))
	}
}
