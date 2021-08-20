package main

import "testing"

func TestTwo(t *testing.T) {
	t.Error("TestTwo", 333)
}

func TestOne(t *testing.T) {
	t.Error("TestOne", 222, "nowt")

}
