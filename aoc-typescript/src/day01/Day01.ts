import {sortDesc} from '../helper/SortHelper';
import {readFileSync} from 'fs';

export const findTopCalories = (input: string, top: number = 1): number => {
    const calories = input.split('\n\n')
                          ?.map(elv => elv.split('\n')
                                          ?.map(value => parseInt(value))
                                          ?.reduce((previousValue, currentValue) => {
                                              return previousValue + currentValue;
                                          }, 0))
                          ?.sort(sortDesc);

    calories.length = top;
    return calories.reduce((prev, next) => prev + next, 0);
};

export const challengeDay01 = () => {
    const inputFile = readFileSync(`${__dirname}/input.txt`);

    const challengePart1 = findTopCalories(inputFile.toString());
    const challengePart2 = findTopCalories(inputFile.toString(), 3);

    console.log(`
    Day 01 Part1:
    ${challengePart1}
    
    Day 01 Part2:
    ${challengePart2}
    `);
};
