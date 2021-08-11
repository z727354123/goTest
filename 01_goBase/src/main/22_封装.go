package main

import (
	"crypto/md5"
	"date"
	"encoding/hex"
	"fmt"
	"hash"
	"strings"
	"time"
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

	fmt.Println(time.Now().UnixNano() / 1e6)

	// 最终需要 方式 1
	var bytes16 [16]byte = md5.Sum([]byte("lisi"))
	str := hex.EncodeToString(bytes16[:])
	fmt.Println(strings.ToUpper(str))
	// 最终需要 方式 1-2
	str = fmt.Sprintf("%x", bytes16)
	fmt.Println(strings.ToUpper(str))
	// 最终需要 方式 2
	var hash1 hash.Hash = md5.New()
	hash1.Write([]byte("lisi"))
	var bytes []byte = hash1.Sum(nil)
	str = hex.EncodeToString(bytes)
	fmt.Println(strings.ToUpper(str))
}
