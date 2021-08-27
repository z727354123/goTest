
class Solution:
    def multiply(self, left: str, right: str) -> str:
        if left == '0' or right == '0':
            return '0'
        # 获取长度
        lLen, rLen = len(left), len(right)
        # 定义结果
        resList = [0] * (lLen + rLen)
        # 遍历并计算
        for lIdx, lStr in enumerate(left):
            for rIdx, rStr in enumerate(right):
                resList[lIdx + rIdx + 1] += int(lStr) * int(rStr)
        # 合并结果
        for idx in range(lLen + rLen - 1, 0, -1):
            resList[idx -1] += int(resList[idx] / 10)
            resList[idx] %= 10
        # 判断是否有开头
        starIdx = 1 if resList[0] == 0 else 0
        ans = "".join(str(x) for x in resList[starIdx:])
        return ans