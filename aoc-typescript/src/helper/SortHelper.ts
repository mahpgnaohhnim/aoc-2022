export const sortDesc = (numA: number, numB: number): number => {
    if (numA === numB) {
        return 0;
    }
    return numA > numB ? -1 : 1;
};