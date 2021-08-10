package main

import (
	"date"
	"fmt"
)

func main() {
	// 1. 修改致小写
	// 2. 指针方法 修改数据
	// 3. 错误 返回 errors.New("err msg")
	dateTime := date.Date{Day: 48}
	fmt.Println(dateTime, dateTime.Year())
	dateTime.SetYear(2019)
	fmt.Println(dateTime, dateTime.Year())

	event := date.Event{Count: 213}

	fmt.Println(event, event.Day, event.Date)
	event.SetYear(10)
	fmt.Println(event, event.Day, event.Date)
	fmt.Println(event.BBQ)
	fmt.Println(event.Date.DJ())
	fmt.Println(event.DJ())

}
