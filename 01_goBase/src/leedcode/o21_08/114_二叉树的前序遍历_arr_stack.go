package main

import "fmt"

type TreeNodeS struct {
	Val   int
	Left  *TreeNodeS
	Right *TreeNodeS
}

func main() {
	node := TreeNodeS{
		Val: 1,
		Right: &(TreeNodeS{
			Val: 2,
			Left: &(TreeNodeS{
				Val: 3,
			}),
		}),
	}
	traversal := preorderTraversal(&node)
	fmt.Println(traversal)
}

func preorderTraversal(root *TreeNodeS) (res []int) {
	stack := make([]*TreeNodeS, 0)
	for root != nil || len(stack) > 0 {
		for root != nil {
			if root.Right != nil {
				stack = append(stack, root.Right)
			}
			res = append(res, root.Val)
			root = root.Left
		}
		length := len(stack)
		if length > 0 {
			root = stack[length-1]
			stack = stack[:length-1]
		}
	}
	return
}
