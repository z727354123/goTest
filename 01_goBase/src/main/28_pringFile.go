package main

import (
	"fmt"
	"io/ioutil"
)

func main() {
	ReadFile(0, "./")
}

func ReadFile(num int, dirName string) {
	var str = ""
	for i := 0; i < num; i++ {
		str += "\t"
	}
	str += fmt.Sprintf("%d", num)
	files, _ := ioutil.ReadDir(dirName)
	for idx, file := range files {
		fmt.Println(str, idx, file.Name())
		if file.IsDir() {
			ReadFile(num+1, dirName+"/"+file.Name())
		}
	}
}
