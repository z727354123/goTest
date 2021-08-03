package main

import (
	"fmt"
	"reflect"
)

func main() {
	var intNum int = 10
	var intObj *int = &intNum

	changeIntObj(intObj)
	fmt.Println(intNum)
	fmt.Println(intObj)
	fmt.Println("-------------------另外で一条，华丽の分割线----------------------")

	var boolVal bool = true
	var boolObj *bool = &boolVal
	_10_CaseBool(boolObj)
	fmt.Println("-------------------另外で一条，华丽の分割线----------------------")
	fmt.Println(boolObj)
	fmt.Println(*boolObj)
}

func _10_CaseBool(obj *bool) {
	fmt.Println(obj)
	fmt.Println(*obj)

	var boolVal = false
	obj = &boolVal
	*obj = false

	fmt.Println(obj, &obj)
	fmt.Println("before", boolVal)
	testPointer(obj, *obj)
	fmt.Println("last", boolVal)
}

func testPointer(stdin *bool, file bool) {
	fmt.Println("-------------------华丽分割线----------------------")
	stdin2 := &stdin
	fmt.Println(stdin2, stdin)
	ppval := &stdin
	p2val := ppval
	p3val := &ppval
	fmt.Println("stdin", stdin)
	fmt.Println("&stdin", &stdin)
	fmt.Println("&stdin2", &stdin2)
	fmt.Println("&ppval", &ppval)
	fmt.Println("&p2val", &p2val)
	fmt.Println("&p3val", &p3val)

	fmt.Println(&p3val, p3val, reflect.TypeOf(p3val), reflect.TypeOf(ppval), reflect.TypeOf(stdin), reflect.TypeOf(file))
	fmt.Println(&ppval, ppval, stdin, file)
	fmt.Println(&ppval, ppval, *ppval, **ppval)
	fmt.Println(p3val, *p3val, **p3val, ***p3val)
	p3val = &p2val
	fmt.Println(&p3val, "reset")
	fmt.Println(p3val, *p3val, **p3val, ***p3val)

	***p3val = true
	fmt.Println(**p3val == stdin, *ppval == stdin)
	stdin = &file
	var bool2 = true
	stdin = &bool2
	fmt.Println(**p3val == stdin, *ppval == stdin)
	fmt.Println(ppval, stdin, bool2, &bool2)
	fmt.Println(*p3val, **p3val, ***p3val)
	fmt.Println(*p3val, *ppval, **ppval)
	fmt.Println("-------------------另外で一条，华丽の分割线----------------------")

}
func changeIntObj(obj *int) {
	fmt.Println("-------------------华丽分割线----------------------")
	fmt.Println(obj)
	fmt.Println(*obj)

	*obj = 20

	fmt.Println(obj)
	fmt.Println(*obj)
	fmt.Println("-------------------另外で一条，华丽の分割线----------------------")
}
