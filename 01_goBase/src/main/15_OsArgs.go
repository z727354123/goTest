package main

import (
	"fmt"
	"os"
)

func main() {
	args := os.Args
	fmt.Println(args)
	for i, val := range args {
		fmt.Println(i, val)
	}

}
