package main

import (
	"fmt"
	"io/ioutil"
	"log"
	"os"
	"path/filepath"
	"strings"
	"time"
)

func main() {
	//name:= parseName()
	fmt.Println("-------------------华丽分割线----------------------")
	name := exeParam()
	if name != "" {
		fullName := fmt.Sprintf("%s_%s", getFileNamePre(), parseName(name))
		os.Create(fullName)
		fmt.Printf("创建成功: %s\n", fullName)
		return
	}

	for true {
		fmt.Println("\n请输入文件名：")
		//当程序只是到fmt.Scanln(&name)程序会停止执行等待用户输入
		_, err2 := fmt.Scanln(&name)
		if err2 != nil {
			continue
		}
		fullName := fmt.Sprintf("%s_%s", getFileNamePre(), parseName(name))
		file, err := os.Create(fullName)
		if err != nil {
			log.Fatalln(err)
		} else {
			fmt.Printf("创建成功: %s\n", fullName)
		}
		file.Close()
	}
	fmt.Println("-------------------华丽分割线----------------------")

}

/*
读取目录文件
*/
func getFileNamePre() string {
	yearPath := time.Now().Format("2006")
	monthPath := time.Now().Format("2006_01")
	// 获取当前 月日
	dirPath := fmt.Sprintf("./%s/%s", yearPath, monthPath)
	os.MkdirAll(dirPath, os.ModePerm)
	files, err := ioutil.ReadDir(dirPath)
	if err != nil {
		log.Fatalln(err)
		panic(err)
	}
	// 记录 _01 出现次数
	itemMap := map[string]bool{}
	for _, file := range files {
		name := file.Name()
		if len(name) >= 8 {
			itemMap[name[:8]] = true
		}
	}
	dayPre := time.Now().Format("01_02_")
	for i := 0; i <= 99; i++ {
		filePre := fmt.Sprintf("%s%02d", dayPre, i)
		if !itemMap[filePre] {
			return fmt.Sprintf("%s/%s", dirPath, filePre)
		}
	}
	panic("count > 99")
}

/*
获取名称
*/
func parseName(res string) string {
	// 获取当前 月日
	if res == "" {
		res = "nor.md"
	} else if !strings.Contains(res, ".") {
		res += ".md"
	}
	return res
}

func exeParam() string {
	args := os.Args
	if len(args) > 1 {
		return args[1]
	}
	return ""
}

/**
获取 当前路径
*/
func getPath() string {
	ex, err := os.Executable()
	if err != nil {
		panic(err)
	}
	exPath := filepath.Dir(ex)
	return exPath
}
