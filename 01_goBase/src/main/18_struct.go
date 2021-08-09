package main

import (
	"encoding/json"
	"fmt"
)

func main() {
	fmt.Println("==============================简单定义==============================")
	if true {
		var per struct {
			Name string
			Age  int
		}
		fmt.Println(per)
		per.Name = "lisi"
		fmt.Println(per)
		var point *int
		fmt.Println(point == nil)

		var per2 struct {
			Name string
			Age  int
		}
		per2 = per
		fmt.Println(per, per2)
		per.Age = 998
		fmt.Println(per, per2)

	}
	fmt.Println("==============================定义type==============================")
	if true {
		type Person struct {
			Name string
			Age  int
		}
		var per Person
		fmt.Println(per)
		per.Name = "lisi"
		fmt.Println(per)
	}
	fmt.Println("==============================字面量==============================")
	if true {
		type Person struct {
			Name string
			Age  int
		}
		var per2 Person = Person{}
		per2 = Person{Age: 33}

		var per1 struct {
			Name string
			Age  int
		} = struct {
			Name string
			Age  int
		}{}

		type MyInt int

		fmt.Println(per1, per2)
		per1.Name = "lisi"
		per2.Name = "Dj"
		fmt.Println(per1, per2)
		jsonObj, err := json.Marshal(per1)
		fmt.Println(string(jsonObj), err)
		jsonObj, _ = json.Marshal([]int{1, 2, 3})
		fmt.Println(string(jsonObj))

		var arr []int
		err = json.Unmarshal(jsonObj, arr)
		fmt.Println(arr)
		fmt.Println(jsonObj)
	}

	fmt.Println("==============================Arr等号==============================")

	if true {
		var arr = []int{1, 2, 3}
		var arr2 = []int{2, 3, 4}
		arr2 = arr
		fmt.Println(arr, arr2)
		arr2[0] = 998
		fmt.Println(arr, arr2)
	}
	fmt.Println("==============================struct嵌套等号==============================")
	if true {
		type Person struct {
			Name string
			Age  int
		}
		type MyClass struct {
			Name string
			Per1 Person
			Per2 Person
		}
		var per1 = Person{Name: "per1", Age: 18}
		var per2 = Person{Name: "per2", Age: 10}
		var my1 = MyClass{Name: "my1", Per1: per1, Per2: per2}
		var my2 MyClass
		fmt.Println(my1, my2)
		per1.Age = 998
		my2 = my1
		fmt.Println(my1, my2)
		my2.Name = "my222"
		my2.Per1.Name = "per11111111"
		fmt.Println(my1, my2)
	}
	fmt.Println("==============================point==============================")
	if true {
		per := Person{Name: "lisi", Age: 18, string: "ddd"}
		fmt.Println(per)
		var myPers *Person = new(Person)
		fmt.Println(myPers, myPers == nil)
		myPers = &per
		changePer(&myPers)
		fmt.Println(per)

	}

	fmt.Println("==============================newMap==============================")
	if true {
		var myMaps = new(map[int]string)
		fmt.Println(myMaps, myMaps == nil, *myMaps == nil)
		fmt.Println(myMaps, myMaps == nil)

	}
	fmt.Println("==============================嵌入struct==============================")

	if true {
		type Address1 struct {
			Add1 string
			Age  int
		}
		type Address2 struct {
			Add2 string
			Age  int
		}
		type Person1 struct {
			Name string
			Address2
			Address1
		}
		var per = Person1{}
		per.Add1 = "Add1"
		per.Add2 = "ds"
		per.Address1.Age = 12
		fmt.Println(per)
	}

}

type Person struct {
	Name string
	Age  int
	string
}

func changePer(pers **Person) {
	(**pers).Name = "zhs"
}
