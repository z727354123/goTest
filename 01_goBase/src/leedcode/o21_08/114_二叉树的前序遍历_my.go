package main

import "fmt"

type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

func main() {
	node := TreeNode{
		Val: 1,
		Right: &(TreeNode{
			Val: 2,
			Left: &(TreeNode{
				Val: 3,
			}),
		}),
	}
	traversal := preorderTraversal(&node)
	fmt.Println(traversal)
}

func preorderTraversal(root *TreeNode) []int {
	if root == nil {
		return nil
	}
	length := 0
	myMap := make(map[int]*TreeNode)
	res := make([]int, 1)
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
