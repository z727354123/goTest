package main

import (
	"fmt"
	"io/ioutil"
	"log"
	"net/http"
	"net/url"
	"strings"
)

func main() {
	http.HandleFunc("/hello", viewHandler)
	http.HandleFunc("/", viewHandler)
	err := http.ListenAndServe(":8181", nil)
	if err != nil {
		log.Fatal(err)
	}

}

func toHtmlStr(title string, content string) []byte {
	bytes, err := ioutil.ReadFile("./01_goBase/src/o2108/index.html")
	if err != nil {
		log.Fatal(err)
	}
	tmpStr := string(bytes)
	tmpStr = strings.Replace(tmpStr, "${title}", title, 1)
	tmpStr = strings.Replace(tmpStr, "${content}", content, 1)
	return []byte(tmpStr)
}

func viewHandler(writer http.ResponseWriter, request *http.Request) {

	queryStr := request.URL.RawQuery
	unescape, _ := url.QueryUnescape(queryStr)
	queryMap := getQueryMap(unescape)
	title := getStr(queryMap, "title")
	content := getStr(queryMap, "content")
	var htmlBytes = toHtmlStr(title, content)
	_, err := writer.Write(htmlBytes)
	fmt.Println("err", err)
}

func getStr(queryMap map[string]string, key string) string {
	val, ok := queryMap[key]
	if !ok {
		val = "defTitle"
	}
	return val
}

func getQueryMap(queryStr string) (res map[string]string) {
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
