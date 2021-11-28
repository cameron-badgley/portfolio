#!/bin/python3

import math
import os
import random
import re
import sys

# Starting with a 1-indexed array of zeros and a list of operations, for each 
# operation add a value to each of the array element between two given indices, 
# inclusive. Once all operations have been performed, return the maximum value 
# in your array.

def arrayManipulation(n, queries):
    maxValue = 0
    currentValue = 0
    differenceMap = [0]*(n)

    #print('Length of differenceMap: ', len(differenceMap))
    for i in range(len(queries)):
        startRange = queries[i][0]
        endRange = queries[i][1]
        value = queries[i][2]

        #print('Start: ', startRange, ', end: ', endRange, ': value: ', value)
        differenceMap[startRange-1] += value
        #print('Add: ', value, ', at: ', startRange-1)

        if endRange < len(differenceMap):
            #print('Subtract: ', value, ', at: ', endRange)
            differenceMap[endRange] -= value

    for difference in differenceMap:
        currentValue += difference
        if currentValue > maxValue:
            maxValue = currentValue

    return maxValue

if __name__ == '__main__':
    fptr = open(os.environ['OUTPUT_PATH'], 'w')

    nm = input().split()

    n = int(nm[0])

    m = int(nm[1])

    queries = []

    for _ in range(m):
        queries.append(list(map(int, input().rstrip().split())))

    result = arrayManipulation(n, queries)

    fptr.write(str(result) + '\n')

    fptr.close()
