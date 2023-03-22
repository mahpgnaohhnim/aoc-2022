use std::fs;

fn get_lines_from_file() -> Vec<String> {
    let path = "src/day01/input.txt";
    let contents = fs::read_to_string(path)
        .expect("Should have been able to read the file");

    contents.lines().map(|line| line.to_string()).collect()
}

pub fn part1() {
    let lines = get_lines_from_file();
    let mut largest = 0;

    for elve in lines.split(|line| line == "") {
        let mut total = 0;
        for calorie in elve {
            /*let calories = calorie.parse::<i32>().unwrap();*/
            let num = calorie.parse::<i32>().unwrap();
            total = total + num
        }

        if total > largest {
            largest = total;
        }
    }

    assert_eq!(largest, 66719);
    println!("Day01 Part 1 largest: {}", largest);
}

pub fn part2() {
    let lines = get_lines_from_file();

    let mut calories = Vec::new();

    for elve in lines.split(|line| line == "") {
        let mut total = 0;
        for calorie in elve {
            /*let calories = calorie.parse::<i32>().unwrap();*/
            let num = calorie.parse::<i32>().unwrap();
            total = total + num
        }
        calories.push(total)
    }

    calories.sort();
    calories.reverse();
    let sum_top_3 = calories[0] + calories[1] + calories[2];

    assert_eq!(sum_top_3, 198551);
    println!("Day01 Part 2 sum top3: {}", sum_top_3);
}