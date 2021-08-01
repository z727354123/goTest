package _2021_08

import "fmt"

func main() {
	left := 1.1
	right := 2.1

	count := _07_add(left, right)
	fmt.Println(count)

	num := complex(1, 1)
	num2 := complex(2, 1)

	fmt.Println(num * num2)
	flag := true

	fmt.Println(flag)
	intNum := 1
	intNum, intNum = _07_returnFunc(intNum, intNum)
	intNum, intNum = _07_returnFuncOne(1, intNum), _07_returnFuncOne(2, intNum)
	fmt.Println(intNum)
}

func _07_returnFuncOne(num int, num2 int) (int) {
	fmt.Println("-------------------华丽分割线----------------------")
	fmt.Println(num, &num)
	return num + num2
}

func _07_returnFunc(num int, num2 int) (int, int) {
	return num + num2, num + 10
}

func _07_add(left, right float64) (count float64) {
	return left + right
}
