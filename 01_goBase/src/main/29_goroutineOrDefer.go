package main

import (
	"fmt"
	"runtime"
	"time"
)

func main() {
	// 测试执行顺序
	var bird Bird = "0"
	tmp := bird.Say()
	defer tmp.Say()
	tmp2 := bird.Say()
	go tmp2.Say()
	fmt.Println("-------------------star----------------------")
	time.Sleep(time.Second)
	fmt.Println("-------------------end----------------------")
}

type Bird string

func (bird Bird) Say() Bird {
	fmt.Println("say "+bird, runtime.Goid())
	return bird + "-"
}
