package utils

import (
	"fmt"
	"os"
	"strings"
)

func GetLinesFromFile(filePath string) []string {
	readFile, err := os.ReadFile(filePath)

	if err != nil {
		fmt.Println(err)
	}
	return strings.Split(string(readFile), "\n")
}
func GetTextFromFile(filePath string) string {
	readFile, err := os.ReadFile(filePath)

	if err != nil {
		fmt.Println(err)
	}
	return string(readFile)
}
