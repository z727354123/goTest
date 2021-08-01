package _2021_08

import (
	"bufio"
	"fmt"
	"log"
	"math/rand"
	"os"
	"strconv"
	"strings"
	"time"
)

func main() {
	_05testFor()
	fmt.Println("-------------------华丽分割线----------------------")

	// 获取时间
	unix := time.Now().Unix()

	fmt.Println(unix)
	rand.Seed(unix)
	// 相同 Seed 一样
	var num int = rand.Intn(100)

	inputNum := getNum()

	var resStr string
	for inputNum != num {
		if inputNum > num {
			resStr = "big"
		} else {
			resStr = "less"
		}
		fmt.Println(resStr)
		inputNum = getNum()
	}
	fmt.Println("over")
	fmt.Println(num)
}

func _05testFor() {
	for i := 0; i < 10; i++ {
		if i == 5 {
			continue
		}
		fmt.Println(i)
		if i == 7 {
			break
		}
	}
	for true {
		if true {
			break
		}
	}
	num := 20
	num--
	fmt.Println(num)

}

func getNum() int {
	reader := bufio.NewReader(os.Stdin)
	input, err := reader.ReadString('\n')
	LogError(err)
	input = strings.TrimSpace(input)
	inputNum, err := strconv.Atoi(input)
	return inputNum
}

func LogError(err error) {
	if err != nil {
		log.Fatal(err)
	}
}
