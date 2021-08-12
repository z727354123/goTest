package animal

import "fmt"

type Dog struct {
	Name string
}

func (d Dog) Say() {
	fmt.Println("旺旺", d.Name)
}

func (d Dog) Catch() {
	fmt.Println("旺旺Catch", d.Name)
}

type Cat struct {
	Name string
}

func (d Cat) Say() {
	fmt.Println("喵喵", d.Name)
}
func (d Cat) Catch() {
	fmt.Println("喵喵Catch", d.Name)
}
