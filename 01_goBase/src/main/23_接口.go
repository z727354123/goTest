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

}
func Say() {
	fmt.Println("-------------------华丽分割线----------------------")
}

func sayWhat(ani Ani) {

	ani.Say()
}

func catchWhat(ani Ani2) {
	ani.Catch()
}
