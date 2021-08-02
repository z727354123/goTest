package main

import (
	"fmt"
	"log"
)

func main() {
	fmt.Printf("hello world")

	const const1 int = 1
	const const2 = 1

	var num0 int
	var num1 int = 1
	var num2 = 1
	num3 := 2

	const const3, const4 = 1, 2
	const const7, const8 = 1, "2"
	const (
		const9         = 1
		const10 string = "1"
	)
	const const5, const6 int = 1, 2

	var1, var2 := 4, 5
	var3, var4 := "4", 5

	var10 := "1"
	var10, var11 := "2", 11
	_ = var10
	log.Fatal("1")
	fmt.Println(num0)
	fmt.Println(num1)
	fmt.Println(num2)
	fmt.Println(num3)
	fmt.Println(var1)
	fmt.Println(var2)
	fmt.Println(var3)
	fmt.Println(var4)
	fmt.Println(var10)
	fmt.Println(var11)
}
