import * as path from 'path';
import {readFileSync} from 'fs';
import {findTopCalories} from './Day01';

describe('test Day01', () => {
    const testFile1 = readFileSync(path.resolve(`${__dirname}/test1.txt`))?.toString();
    const testFile2 = readFileSync(path.resolve(`${__dirname}/test2.txt`))?.toString();

    test('findTopCalories', () => {
        const result1 = findTopCalories(testFile1);
        const result2 = findTopCalories(testFile2);
        expect(result1).toBe(24000);
        expect(result2).toBe(10000);

        const result3 = findTopCalories(testFile1, 2);
        const result4 = findTopCalories(testFile2, 3);
        expect(result3).toBe(35000);
        expect(result4).toBe(20034);
    });
});
