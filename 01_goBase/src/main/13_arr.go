package main

import (
	"bufio"
	"fmt"
	"reflect"
	"time"
)

func main() {
	fmt.Println("-------------------华丽分割线----------------------")
	// 1. 默认值
	if true {
		var arr *[]int
		// arr[0] = 1
		// panic: runtime error: invalid memory address or nil pointer dereference 空指针
		fmt.Println("1", arr)
	}
	if true {
		var arr1 [2]int
		var arr2 [2]rune
		var arr3 [2]time.Time
		var arr4 [2]bufio.Reader
		var arr5 [2]*time.Time
		var arr6 *[2]time.Time
		// arr[0] = 1
		// panic: runtime error: invalid memory address or nil pointer dereference 空指针
		fmt.Println("1", arr1, len(arr1), reflect.TypeOf(arr1))
		fmt.Println("2", arr2, len(arr2), reflect.TypeOf(arr2))
		fmt.Println("3", arr3, len(arr3), reflect.TypeOf(arr3))
		fmt.Println("4", arr4, len(arr4), reflect.TypeOf(arr4))
		fmt.Println("5", arr5, len(arr5), reflect.TypeOf(arr5))
		fmt.Println("6", arr6, reflect.TypeOf(arr6))
	}
	fmt.Println("-------------------定义方式----------------------")
	if true {
		var arr1 []int = []int{1, 1, 1}
		var arr2 [2]int = [...]int{2, 2}
		var arr3 [3]int = [3]int{3}
		var arr4 [4]int = [4]int{1, 2, 3, 4}
		var arr5 [2]int
		var arr6 [2]int
		// arr[0] = 1
		// panic: runtime error: invalid memory address or nil pointer dereference 空指针
		fmt.Println("1", arr1, len(arr1), reflect.TypeOf(arr1))
		fmt.Println("2", arr2, len(arr2), reflect.TypeOf(arr2))
		fmt.Println("3", arr3, len(arr3), reflect.TypeOf(arr3))
		fmt.Println("4", arr4, len(arr4), reflect.TypeOf(arr4))
		fmt.Println("5", arr5, len(arr5), reflect.TypeOf(arr5))
		fmt.Println("6", arr6, reflect.TypeOf(arr6))
	}
	fmt.Println("-------------------多行数组----------------------")
	if true {
		//var arr1 []int = []int{0,
		//}
		// syntax error: unexpected newline, expecting comma or }

		//var arr1 []int = []int{0,
		//	,1}
		//syntax error: unexpected comma, expecting expression

		var arr1 []int = []int{0,
			2, 1}
		var arr2 [2]int = [...]int{2, 2}
		var arr3 [3]int = [3]int{3}
		var arr4 [4]int = [4]int{1, 2, 3, 4}
		var arr5 [2]int
		var arr6 [2]int
		// arr[0] = 1
		// panic: runtime error: invalid memory address or nil pointer dereference 空指针
		fmt.Println("1", arr1, len(arr1), reflect.TypeOf(arr1))
		fmt.Println("2", arr2, len(arr2), reflect.TypeOf(arr2))
		fmt.Println("3", arr3, len(arr3), reflect.TypeOf(arr3))
		fmt.Println("4", arr4, len(arr4), reflect.TypeOf(arr4))
		fmt.Println("5", arr5, len(arr5), reflect.TypeOf(arr5))
		fmt.Println("6", arr6, reflect.TypeOf(arr6))
	}
	fmt.Println("-------------------二维数组----------------------")
	if true {
		var arr1 [3][2]int = [3][2]int{
			{1},
			{2, 3},
		}
		var arr2 [2]int = [...]int{2, 2}
		var arr3 [3]int = [3]int{3}
		var arr4 [4]int = [4]int{1, 2, 3, 4}
		var arr5 [2]int
		var arr6 [2]int
		// arr[0] = 1
		// panic: runtime error: invalid memory address or nil pointer dereference 空指针
		fmt.Printf("%#v \n", arr1)
		fmt.Println("1", arr1, len(arr1), reflect.TypeOf(arr1))
		fmt.Println("2", arr2, len(arr2), reflect.TypeOf(arr2))
		fmt.Println("3", arr3, len(arr3), reflect.TypeOf(arr3))
		fmt.Println("4", arr4, len(arr4), reflect.TypeOf(arr4))
		fmt.Println("5", arr5, len(arr5), reflect.TypeOf(arr5))
		fmt.Println("6", arr6, reflect.TypeOf(arr6))
	}
	fmt.Println("-------------------循环打印----------------------")
	if true {
		var arr1 [4][2]int = [4][2]int{
			{1},
			{2, 3},
		}
		for i := 0; i < len(arr1); i++ {
			fmt.Println(i, arr1[i])
		}
		for index, val := range arr1 {
			fmt.Println(index, val)
		}
	}

}
