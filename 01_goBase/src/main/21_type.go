package main

import (
	"fmt"
)

type Liters float64

func (m Liters) sayVal(num int) int {
	fmt.Println(m)
	return num + 9
}

type Gallons float64

type MyType string

func (this2 MyType) sayVal(num int) int {
	this2 = "sdfsdf"
	return num + 9
}

func (this2 *MyType) sayVal2(num int) int {
	*this2 = "sdfsdf"
	return num + 9
}

func main() {
	var catFuel Gallons
	var busFuel Liters

	catFuel = Gallons(10.0)
	busFuel = Liters(240.0)

	fmt.Println(catFuel, busFuel)
	fmt.Println(busFuel.sayVal(2))

	var val MyType = "123"
	fmt.Println(val)
	val.sayVal(1)
	fmt.Println(val)
	val.sayVal2(1)
	fmt.Println(val)

	myType := MyType("321")
	//myType.sayVal()
	fmt.Println(myType)
}
