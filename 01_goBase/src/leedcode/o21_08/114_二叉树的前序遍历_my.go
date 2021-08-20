package main

import "fmt"

type TreeNodeMy struct {
	Val   int
	Left  *TreeNodeMy
	Right *TreeNodeMy
}

func main() {
	node := TreeNodeMy{
		Val: 1,
		Right: &(TreeNodeMy{
			Val: 2,
			Left: &(TreeNodeMy{
				Val: 3,
			}),
		}),
	}
	traversal := preorderTraversal2(&node)
	fmt.Println(traversal)
}

func preorderTraversal2(root *TreeNodeMy) []int {
	if root == nil {
		return nil
	}
	length := 0
	myMap := make(map[int]*TreeNodeMy)
	res := make([]int, 0)
	for true {
		if root == nil && length <= 0 {
			return res
		}
		res = append(res, root.Val)
		rightNotNil := root.Right != nil
		leftNotNil := root.Left != nil
		if leftNotNil {
			if rightNotNil {
				length++
				myMap[length] = root.Right
			}
			root = root.Left
		} else {
			if rightNotNil {
				root = root.Right
			} else {
				root = myMap[length]
				length--
			}
		}
	}
	return res
}
