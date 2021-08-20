package test

import (
	"fmt"
	"testing"
)

func TestTwo(t *testing.T) {
	if false {
		panic("sdfs")
	}
	fmt.Println("two")
	println("dsd")
}

func TestOne(t *testing.T) {
	panic("sdfs")
	if false {
		t.Error("TestOne", 222, "nowt")
	}
	//
	fmt.Println("one")
	println("111")

}
