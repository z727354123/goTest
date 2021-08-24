package main

import (
	"fmt"
	"html/template"
	"io/ioutil"
	"log"
	"net/http"
	"net/url"
	"strings"
)

func main() {
	http.HandleFunc("/", viewHandler2)
	err := http.ListenAndServe(":8183", nil)
	if err != nil {
		log.Fatal(err)
	}

}

func toHtmlStr22(title string, content string) []byte {
	indexFileName := "./01_goBase/src/o2108/index.html"
	bytes, err := ioutil.ReadFile(indexFileName)
	if err != nil {
		log.Fatal(err)
	}
	tmpStr := string(bytes)
	tmpStr = strings.Replace(tmpStr, "${title}", title, 1)
	tmpStr = strings.Replace(tmpStr, "${content}", content, 1)
	return []byte(tmpStr)
}

func viewHandler2(writer http.ResponseWriter, request *http.Request) {

	queryStr := request.URL.RawQuery
	unescape, _ := url.QueryUnescape(queryStr)
	queryMap := getQueryMap2(unescape)
	title := getStr2(queryMap, "title")
	content := getStr2(queryMap, "content")

	fileNames := []string{
		"./01_goBase/src/o2108/header.html",
		"./01_goBase/src/o2108/index.html",
		"./01_goBase/src/o2108/content.html",
	}

	html, err := template.ParseFiles(fileNames...)
	fmt.Println("htmls", err)
	//param := struct{
	//	Name string
	//	age string
	//}{Name: "lisi", age: "123"}
	param := map[string]string{"Name": "lisi", "age": "123"}
	err = html.Execute(writer, param)
	fmt.Println("err", err)
	fmt.Println(err)
	fmt.Println(title, content)
}

func getStr2(queryMap map[string]string, key string) string {
	val, ok := queryMap[key]
	if !ok {
		val = "defTitle"
	}
	return val
}

func getQueryMap2(queryStr string) (res map[string]string) {
	res = make(map[string]string)
	for _, item := range strings.Split(queryStr, "&") {
		index := strings.Index(item, "=")
		var key, val string
		if index == -1 {
			key = item
		} else {
			key = item[:index]
			val = item[index+1:]
		}
		res[key] = val
	}
	return
}
