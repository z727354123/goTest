package main

import "fmt"

/**

给定一个二叉树，返回其节点值的锯齿形层序遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。

例如：
给定二叉树 [3,9,20,null,null,15,7],

    3
   / \
  9  20
    /  \
   15   7
返回锯齿形层序遍历如下：

[
  [3],
  [20,9],
  [15,7]
]
通过次数171,304提交次数299,740
*/

type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

func main() {
	node := &TreeNode{
		Val: 3,
		Left: &TreeNode{
			Val:   9,
			Left:  nil,
			Right: nil,
		},
		Right: &TreeNode{
			Val: 20,
			Left: &TreeNode{
				Val:   15,
				Left:  nil,
				Right: nil,
			},
			Right: &TreeNode{
				Val:   7,
				Left:  nil,
				Right: nil,
			},
		},
	}
	fmt.Println(zigzagLevelOrder(node))
}

func zigzagLevelOrder(root *TreeNode) [][]int {
	res := [][]int(nil)
	// 过滤
	if root == nil {
		return res
	}
	// 添加第一个
	firstArr := []*TreeNode{root}
	lastArr := []*TreeNode{}
	tmp := []int(nil)
	for len(firstArr) > 0 || len(lastArr) > 0 {
		// 遍历左边
		for i := len(firstArr) - 1; i >= 0; i-- {
			node := firstArr[i]
			tmp = append(tmp, node.Val)
			// 先左边后右边
			if node.Left != nil {
				lastArr = append(lastArr, node.Left)
			}
			if node.Right != nil {
				lastArr = append(lastArr, node.Right)
			}
		}
		firstArr = firstArr[:0]
		if len(tmp) > 0 {
			res = append(res, append([]int(nil), tmp...))
			tmp = tmp[:0]
		}
		// 遍历右边边
		for i := len(lastArr) - 1; i >= 0; i-- {
			node := lastArr[i]
			tmp = append(tmp, node.Val)
			// 先右边后左边
			if node.Right != nil {
				firstArr = append(firstArr, node.Right)
			}
			if node.Left != nil {
				firstArr = append(firstArr, node.Left)
			}
		}
		lastArr = lastArr[:0]
		if len(tmp) > 0 {
			res = append(res, append([]int(nil), tmp...))
			tmp = tmp[:0]
		}
	}
	return res
}
