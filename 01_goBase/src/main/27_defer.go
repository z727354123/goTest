package main

import (
	"fmt"
	"runtime"
	"strconv"
	"time"
)

func main() {

	if true {
		var num int

		// 总结
		// 1. 多个 defer, 最后运行的deffer最先执行
		// 1.1 多个 defer, 有的defer 可能不执行, 代码要运行到 deffer 那里才行, return panic 都会结束代码
		// 1.2 代码 for 循环 执行 同一个 defer 多次, 那么 该 defer 也会执行多次
		// 2. 底部 deffer 未 recover(), 则返回到 下(上层)一个 deffer
		// 3. 原函数 panicA 后, 第一个 deffer 未 revover(), 再 panicB,
		//	后面的 deffer 只能 recover() 获取 panicB, panicA 丢失
		go func() {
			for i := 0; i < 2; i++ {
				defer func(num2 int) {
					//i := recover()
					//fmt.Println(i, reflect.TypeOf(i))
					//i = recover()
					//fmt.Println(i, reflect.TypeOf(i))
					fmt.Println("-------------------另外で一条，华丽の分割线--11---------------", runtime.Goid(), num2)
				}(i)
			}
			return
			defer func() {
				//var i interface{} = recover()
				//fmt.Println(i, reflect.TypeOf(i))
				fmt.Println("-------------------另外で一条，华丽の分割线---22-----------------", runtime.Goid())
				panic("hello")
			}()
			fmt.Println(1 / num)
		}()

		fmt.Println("------------------111------------------", runtime.Goid())
		time.Sleep(time.Second)
		fmt.Println("------------------222------------------", runtime.Goid())
		nums, err := strconv.ParseFloat("123.123", 64)
		fmt.Println("------------------333------------------", err)
		fmt.Println("------------------444------------------", nums)
	}

}
