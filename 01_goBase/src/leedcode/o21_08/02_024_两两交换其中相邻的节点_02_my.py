# Definition for singly-linked list.
class ListNode:
	def __init__(self, val=0, next=None):
		self.val = val
		self.next = next


class Solution:
	def swapPairs(self, head: ListNode) -> ListNode:
		dummyNode = ListNode(next=head)
		preNode = dummyNode
		nextNode = dummyNode.next
		while nextNode and nextNode.next:
			preNode.next = nextNode.next
			nextNode.next = preNode.next.next
			preNode.next.next = nextNode
			preNode = nextNode
			nextNode = preNode.next
		return dummyNode.next


if True:
	node = ListNode(1, ListNode(2, ListNode(3, ListNode(4))))
	sol = Solution()
	sol.swapPairs(node)
