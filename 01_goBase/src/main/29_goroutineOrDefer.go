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

	RFC3339Str := "2020-11-08T08:18:46+80:00"
	ts, err := time.Parse(time.RFC3339, RFC3339Str)
	if err != nil {
		fmt.Println(err)
	}
	cst := ts.In(time.Local).Format("2006-01-02 15:04:05")
	fmt.Println(cst)
}

type Bird string

func (bird Bird) Say() Bird {
	fmt.Println("say "+bird, runtime.Goid())
	return bird + "-"
}
