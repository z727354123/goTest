package main

import "fmt"

type Money int

func (money Money) String() string {
	return fmt.Sprintf("money is %d", money)
}

type Empty interface{}

func main() {
	var money Money
	fmt.Println(money)

}
