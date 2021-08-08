package main

import (
	"bufio"
	"fmt"
	"os"
	"reflect"
)

func main() {

	fmt.Println("-------------------创建map----------------------")
	if true {
		// make
		var resMap map[int]string

		fmt.Println(resMap, reflect.TypeOf(resMap), resMap == nil)
		resMap = make(map[int]string)
		fmt.Println(resMap, reflect.TypeOf(resMap), resMap == nil)
		resMap = nil
		fmt.Println(resMap, reflect.TypeOf(resMap), resMap == nil)
		resMap = map[int]string{}
		fmt.Println(resMap, reflect.TypeOf(resMap), resMap == nil)
		resMap = map[int]string{1: "str1"}
		fmt.Println(resMap, reflect.TypeOf(resMap), resMap == nil)
	}
	fmt.Println("-------------------calcName----------------------")
	calcName()
	fmt.Println("-------------------calcName----------------------")

	fmt.Println("-------------------判断是否赋值----------------------")
	if true {
		// make
		var resMap map[int][]int
		resMap = make(map[int][]int)
		fmt.Println(resMap, reflect.TypeOf(resMap), resMap == nil)
		var val []int
		var ok bool
		val, ok = resMap[0]
		fmt.Println("before", val, ok)
		val, ok = resMap[0]
		fmt.Println("before2", val, ok)
		resMap[0] = nil
		val, ok = resMap[0]
		fmt.Println("setZero", val, ok)
	}

	fmt.Println("-------------------删除键值对----------------------")
	if true {
		// make
		type Person struct {
			Name string
			Age  int
		}

		var resMap map[Person]string
		resMap = make(map[Person]string)

		var key = Person{Name: "lisi", Age: 1}
		var per2 = Person{Name: "lisi", Age: 2}

		resMap[key] = "key"
		resMap[per2] = "per2"
		val, ok := resMap[key]
		fmt.Println("before", resMap, val, ok, resMap[per2])
		delete(resMap, key)
		val, ok = resMap[key]
		fmt.Println("delete", resMap, val, ok, resMap[per2])

	}

	fmt.Println("-------------------forRange----------------------")
	if true {
		// make
		type Person struct {
			Name string
			Age  int
		}

		var resMap map[Person]string
		resMap = make(map[Person]string)

		var per1 = Person{Name: "lisi", Age: 1}
		var per2 = Person{Name: "lisi", Age: 2}

		resMap[per1] = "per1"
		resMap[per2] = "per2"

		//per2.Age = 1
		fmt.Println(resMap)
		for key, val := range resMap {
			fmt.Println(key, val, key == per1, key == per2)
		}
	}

	fmt.Println("-------------------forRange2----------------------")
	if true {
		var resMap map[string]string
		resMap = make(map[string]string)

		resMap["Alma"] = "per1"
		resMap["Rohit"] = "per2"
		resMap["Carl"] = "per2"
		resMap["Carl"] = "per2"
		resMap["123"] = "per2"

		//per2.Age = 1
		fmt.Println(resMap)
		for key, val := range resMap {
			fmt.Println(key, val)
		}

	}

}

func calcName() {
	file, err := os.Open("/Users/judy/workspace/test/2021/07_go_test/01_goBase/src/main/data.txt")
	fmt.Println("file=", file, "err=", err)
	scan := bufio.NewScanner(file)

	myMap := map[string]int{}
	strArr := []string{}
	intArr := []int{}
outer:
	for scan.Scan() {
		text := scan.Text()
		myMap[text]++
		for idx, name := range strArr {
			if name == text {
				intArr[idx]++
				continue outer
			}
		}
		strArr = append(strArr, text)
		intArr = append(intArr, 1)
	}
	fmt.Println(myMap)
	fmt.Println(strArr)
	fmt.Println(intArr)
}
