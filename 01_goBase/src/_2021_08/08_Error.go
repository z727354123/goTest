package _2021_08

import (
	"errors"
	"fmt"
	"log"
)

func main() {
	err := errors.New("错了")

	log.Fatal(err)
	fmt.Println(err.Error())
	fmt.Println(err)

}
