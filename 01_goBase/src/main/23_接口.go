package main

import (
	"animal"
	"fmt"
)

type Ani interface {
	Say()
}

type Ani2 interface {
	Catch()
}

func main() {
	var ani Ani
	ani = animal.Dog{Name: "23"}
	fmt.Println(ani)

	dog := animal.Dog{Name: "dogOne"}
	cat := animal.Cat{Name: "catOne"}

	fmt.Println(dog, cat)
	sayWhat(dog)
	sayWhat(cat)
	catchWhat(dog)
	catchWhat(cat)

	myErr := OverheatError(1.3333)
	printError(myErr)
	fmt.Println(myErr.Error())
}

func printError(err error) {
	fmt.Println(err)
}

type OverheatError float64

func (o OverheatError) Error() string {
	return fmt.Sprintf("Overheating by %0.2f degrees!", o)
}

func Say() {
	fmt.Println("-------------------华丽分割线----------------------")
}

func sayWhat(ani Ani) {
	ani.Say()
	//dog := ani.(animal.Dog)

	dog, ok := ani.(animal.Dog)

	fmt.Println(dog, ok)
	dog.Name = "lisi3333"
	fmt.Println(dog, ok)
	dog.Catch()
	if false {
		dog.Catch()
	}
}

func catchWhat(ani Ani2) {
	ani.Catch()
}
