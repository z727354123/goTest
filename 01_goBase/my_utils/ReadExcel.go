package main

import (
	"encoding/json"
	"fmt"
	"github.com/xuri/excelize/v2"
	"io/ioutil"
	"strings"
)

func main() {
	// 获取文件
	arr := getCurXlsxArr()
	for _, val := range arr {
		// 处理文件
		fmt.Printf("%s 处理文件 : [%s]\n\n", getStr(1), val)
		parseXlsx(val)
	}
	// 阻塞
	block2()
}

func getStr(count int) string {
	item := "---- "
	res := "---- "
	for i := 1; i < count; i++ {
		res = res + item
	}
	return strings.TrimRight(res, " ")
}

/**
解析文件内容
*/
func parseXlsx(file string) {
	excel, err := excelize.OpenFile(file)
	if err != nil {
		println(err.Error())
		fmt.Printf("解析 Excel文件 异常 [%s]: 打开文件失败 \n\n", file)
		return
	}
	// 获取工作表中指定单元格的值
	list := excel.GetSheetList()
	for _, sheetName := range list {
		fmt.Printf("%s 解析 sheet : [%s]\n\n", getStr(2), sheetName)
		parseSheet(excel, sheetName)
	}
}

/**
解析sheetName内容
*/
func parseSheet(excel *excelize.File, sheetName string) {

	rows, err := excel.Rows(sheetName)
	if err != nil {
		println(err.Error())
		fmt.Printf("解析 sheet 异常 [%s]: 读取行失败 \n\n", sheetName)
		return
	}
	// 源 / 译 idx
	srcIdx, targetIdx := getIdx(rows, sheetName)
	if srcIdx == 0 || targetIdx == 0 {
		fmt.Printf("解析 sheet 异常 [%s]: 第一行找不到 源/译 对应列 \n\n", sheetName)
		return
	}
	rowIdx := 1
	for rows.Next() {
		row, err := rows.Columns()
		if err != nil {
			fmt.Printf("解析 sheet 异常 [%s]: 读取内容行失败 \n\n", sheetName)
			return
		}
		parseContentRow(srcIdx, targetIdx, row, rowIdx, sheetName)
		rowIdx = rowIdx + 1
	}

}

func parseContentRow(srcIdx int, targetIdx int, row []string, rowIdx int, sheetName string) {
	srcStr := row[srcIdx]
	tarStr := row[targetIdx]
	_, err := JsonToMap(srcStr)
	if err != nil {
		return
	}
	_, err2 := JsonToMap(tarStr)
	if err2 != nil {
		fmt.Printf("解析 Json 异常 [%s], 行[%d], 内容[%s] \n\n", sheetName, rowIdx, tarStr)
		return
	}
}

func JsonToMap(jsonStr string) (map[string]string, error) {
	m := make(map[string]string)
	err := json.Unmarshal([]byte(jsonStr), &m)
	if err != nil {
		return nil, err
	}
	//for k, v := range m {
	//	//fmt.Printf("%v: %v\n\n", k, v)
	//}

	return m, nil
}

func getIdx(rows *excelize.Rows, sheetName string) (int, int) {
	srcIdx := 0
	targetIdx := 0
	if rows.Next() {
		row, err := rows.Columns()
		if err != nil {
			fmt.Printf("解析 sheet 异常 [%s]: 读取第一行失败 \n\n", sheetName)
			return 0, 0
		}
		for idx, val := range row {
			if strings.Contains(val, "源") {
				srcIdx = idx
			}
			if strings.Contains(val, "译") {
				targetIdx = idx
			}
		}
	}
	return srcIdx, targetIdx
}

func block2() {
	for true {
		fmt.Println("\n\n____end 阻塞：")
		//当程序只是到fmt.Scanln(&name)程序会停止执行等待用户输入
		var name = ""
		_, err2 := fmt.Scanln(&name)
		if err2 != nil {
			continue
		}
	}
}

/**
获取当前目录下 xlsx 文件
*/
func getCurXlsxArr() []string {
	files, _ := ioutil.ReadDir("./")
	arr := []string{}
	for _, f := range files {
		name := f.Name()
		index := len(name) - strings.LastIndex(name, ".xlsx")
		if index == 5 {
			arr = append(arr, "./"+name)
		}
	}
	return arr
}
