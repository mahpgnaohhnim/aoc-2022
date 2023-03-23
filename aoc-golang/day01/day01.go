package day01

import (
	fileutil "aoc-golang/utils"
	"sort"
	"strconv"
	"strings"
)

func Part01() {
	var textinput = fileutil.GetTextFromFile("day01/input.txt")
	var elves = strings.Split(textinput, "\n\n")

	var max = 0
	for _, elve := range elves {
		var calories = calculateElveTotalCalories(elve)
		if calories > max {
			max = calories
		}
	}
	print("Day01 Part01 Max calories should be 66719: ")
	print(max == 66719)
	print(": ")
	println(max)
}

func Part02() {
	var textinput = fileutil.GetTextFromFile("day01/input.txt")
	var elves = strings.Split(textinput, "\n\n")
	var length = len(elves)

	var elveTotals = make([]int, length)

	for index, elve := range elves {
		var calories = calculateElveTotalCalories(elve)
		elveTotals[index] = calories
	}

	sort.Ints(elveTotals)

	var sumTop3 = elveTotals[length-1] + elveTotals[length-2] + elveTotals[length-3]

	print("Day01 Part02 sum top 3 should be 198551. ")
	print(sumTop3 == 198551)
	print(": ")
	println(sumTop3)
}

func calculateElveTotalCalories(elve string) int {
	var calories = strings.Split(elve, "\n")
	var sum = 0
	for _, calorieString := range calories {
		var calorie, _ = strconv.Atoi(calorieString)
		sum += calorie
	}
	return sum
}
