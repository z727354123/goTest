package main

import (
	"fmt"
	"github.com/headfirstgo/keyboard"
	"github.com/headfirstgo/sonp"
	"sonp/son"
)

func main() {
	fmt.Println("-------------------华丽分割线----------------------")
	nums, _ := keyboard.GetFloat()
	testNum := nums
	fmt.Println(testNum)
	sonp.Say()
	son.Son3()

}
