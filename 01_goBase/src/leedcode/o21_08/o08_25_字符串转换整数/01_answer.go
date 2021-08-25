package main

import (
	"math"
	"unicode"
)

// 定义 状态
const (
	START = iota
	SIGN
	IN_NUMBER
	END
)

// 状态下对应变化状态
// 0 ' '
// 1 +/-
// 2 数字
// 3 其他
var statusMap = map[int][4]int{
	START:     {START, SIGN, IN_NUMBER, END},
	SIGN:      {END, END, IN_NUMBER, END},
	IN_NUMBER: {END, END, IN_NUMBER, END},
	END:       {END, END, END, END},
}

// 存储结果
type Automaton struct {
	ans    int64
	sign   int64
	status int
}

// 计算并终止
// @return true 终止
func (maton *Automaton) calc(char rune) bool {
	// 更新当前状态
	maton.updateStatus(char)
	// 判断当前状态
	switch maton.status {
	case SIGN:
		// 计算符号
		if char == '-' {
			maton.sign = -1
		} else {
			maton.sign = 1
		}
	case IN_NUMBER:
		// 添加数字
		maton.ans = maton.ans*10 + int64(char) - int64('0')
		if maton.sign > 0 {
			if maton.ans > math.MaxInt32 {
				maton.ans = math.MaxInt32
				return true
			}
		} else {
			if maton.ans > -math.MinInt32 {
				maton.ans = -math.MinInt32
				return true
			}
		}
	case END:
		return true
	}
	return false
}

// 更新状态
func (maton *Automaton) updateStatus(char rune) {
	var idx int
	if char == ' ' {
		idx = 0
	} else if char == '+' || char == '-' {
		idx = 1
	} else if unicode.IsDigit(char) {
		idx = 2
	} else {
		idx = 3
	}
	maton.status = statusMap[maton.status][idx]
}

// 更新状态
func (maton *Automaton) getAns() int {
	return int(maton.ans * maton.sign)
}
func myAtoi(str string) int {
	// 初始化 变量
	automaton := Automaton{status: START, sign: 1, ans: 0}
	for _, char := range str {
		if automaton.calc(char) {
			return automaton.getAns()
		}
	}
	return automaton.getAns()
}
