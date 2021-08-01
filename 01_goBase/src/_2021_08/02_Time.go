package _2021_08

import (
	"bufio"
	"fmt"
	"log"
	"os"
	"strings"
	"time"
)

func main() {
	fmt.Println("hello world")

	var now time.Time = time.Now()

	year := now.Year()
	fmt.Println(year)

	year = 2

	fmt.Println(year)

	_02Replacer()

	//_03Read()

	_02_Log()
}

func _02_Log() {
	// 报告错误 并停止运行
	log.Fatalln("-------------------另外で一条，华丽の分割线----------------------")
	fmt.Println("-------------------华丽分割线----------------------")
}

func _03Read() {
	fmt.Println("-------------------华丽分割线----------------------")
	var a int = 100
	var b int = 200
	_02_Func(a, b)
	fmt.Println(a, b)
	b, a = a, b
	fmt.Println(a, b)

	var stdin *os.File = os.Stdin
	var reader *bufio.Reader = bufio.NewReader(stdin)

	var readString, err = reader.ReadString('A')

	fmt.Println(readString)
	fmt.Println(err)

	readString, err = reader.ReadString('A')

	fmt.Println(readString)
	fmt.Println(err)

}

func _02_Func(a int, b int) {
	a = 1
	b = 2
	fmt.Println(a, b)
}
func _02Replacer() {
	var replacer *strings.Replacer = strings.NewReplacer("\\d", "#")

	str := "book12DDD"

	resStr := replacer.Replace(str)
	fmt.Println(resStr, str)
}
