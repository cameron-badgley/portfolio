#!/bin/python3

import math
import os
import random
import re
import sys

# You are given an array and you need to find number of tripets of indices (i, j, k) 
# such that the elements at those indices are in geometric progression for 
# a given common ratio r and i < j < k.
def countTriplets(arr, r):
    firstCount = {}
    secondCount = {}
    tripletsFound = 0

    for key in arr:
        firstCount.setdefault(key, 0)
        secondCount.setdefault(key, 0)

        # Does this value complete a triplet?
        tripletsFound += secondCount.get(key/r, 0)
        # Does this value complete a double?
        secondCount[key] += firstCount.get(key/r, 0)
        # Increment the count for this value individually
        firstCount[key] += 1

    return tripletsFound

if __name__ == '__main__':
    fptr = sys.stdout
    #os.environ['OUTPUT_PATH'] = 'count-triplets.txt'
    #fptr = open(os.environ['OUTPUT_PATH'], 'w')

    nr = input().rstrip().split()

    n = int(nr[0])

    r = int(nr[1])

    arr = list(map(int, input().rstrip().split()))

    ans = countTriplets(arr, r)

    fptr.write(str(ans) + '\n')

    fptr.close()
