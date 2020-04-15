#!/bin/python3

import math
import os
import random
import re
import sys

# You are given an unordered array consisting of consecutive integers [1, 2, 3, ..., n] 
# without any duplicates. You are allowed to swap any two elements. You need to find the 
# minimum number of swaps required to sort the array in ascending order.

def minimumSwaps(arr):
    swaps = 0
    i = 0
    while i < len(arr):
        currentPosition = i+1
        currentValue = arr[i]
        if(currentPosition != currentValue):
            arr[i], arr[currentValue-1] = arr[currentValue-1], arr[i]
            swaps += 1
        else:
            i += 1
    return swaps

if __name__ == '__main__':
    fptr = open(os.environ['OUTPUT_PATH'], 'w')

    n = int(input())

    arr = list(map(int, input().rstrip().split()))

    res = minimumSwaps(arr)

    fptr.write(str(res) + '\n')

    fptr.close()
