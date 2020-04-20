#!/bin/python3

import math
import os
import random
import re
import sys

# You are given an array and you need to find number of tripets of indices (i, j, k) 
# such that the elements at those indices are in geometric progression for 
# a given common ratio r and i < j < k.

# TODO: enforce i < j < k
# We could iterate in reverse and then only check those that had already been processed
# Or we could continue working forward and check for value/r and value/r^2
def countTriplets(arr, r):
    valueCount = {}
    tripletsFound = 0

    # Store the number of instances of each value
    for i in range(len(arr)):
        key = arr[i]
        valueCount.setdefault(key, 0)
        valueCount[key] += 1

    # Return the total number of triplets that are in geometric position by finding
    # the total combinations of triplets for each value
    for key in valueCount:
        firstTriplet = valueCount.get(key, 0)
        secondTriplet = valueCount.get(key*r, 0)
        thirdTriplet = valueCount.get(key*r**2, 0)

        minTriplets = firstTriplet * secondTriplet * thirdTriplet

        tripletsFound += minTriplets

    return tripletsFound

if __name__ == '__main__':
    #fptr = sys.stdout   # stdout is already an open stream
    #os.environ['OUTPUT_PATH'] = 'count-triplets.txt'
    fptr = open(os.environ['OUTPUT_PATH'], 'w')

    nr = input().rstrip().split()

    n = int(nr[0])

    r = int(nr[1])

    arr = list(map(int, input().rstrip().split()))

    ans = countTriplets(arr, r)

    fptr.write(str(ans) + '\n')

    fptr.close()
