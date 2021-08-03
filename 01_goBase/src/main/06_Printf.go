package main

import "fmt"

func main() {

	var dig float32 = 0.7777777777777
	fmt.Printf("val [] over", dig)
	fmt.Println("")

	fmt.Printf("val [%s] over", dig)
	fmt.Println("")

	fmt.Printf("val [%-1f] over", dig)
	fmt.Println("")

	fmt.Printf("val [%8f] over", dig)
	fmt.Println("")

	fmt.Printf("val [%9f] over", dig)
	fmt.Println("")

	fmt.Printf("val [%20f] over", dig)
	fmt.Println("")

	fmt.Printf("val [%0.0f] over", dig)
	fmt.Println("")

	fmt.Printf("val [%1.0f] over", dig)
	fmt.Println("")

	fmt.Printf("val [%2.0f] over", dig)
	fmt.Println("")

	fmt.Printf("val [%1.5f] over", dig)
	fmt.Println("")
}
