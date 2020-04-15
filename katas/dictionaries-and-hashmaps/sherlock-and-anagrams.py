#!/bin/python3

import math
import os
import random
import re
import sys
import math

# Two strings are anagrams of each other if the letters of one string can be 
# rearranged to form the other string. Given a string, find the number of pairs 
# of substrings of the string that are anagrams of each other.

def sherlockAndAnagrams(s):
    substrings = {}
    totalAnagrams = 0

    for i in range(len(s)):
        for j in range(i+1, len(s)+1):
            substring = s[i:j]
            sortedSubstring = ''.join(sorted(substring))

            substrings.setdefault(sortedSubstring, 0)
            substrings[sortedSubstring] += 1

    for key in substrings:
        if substrings[key] > 1:
            n = substrings[key]
            r = 2
            combinations = math.factorial(n) / (math.factorial(r)*math.factorial(n-r))

            totalAnagrams += int(combinations)

    print(substrings)

    return totalAnagrams

if __name__ == '__main__':
    fptr = open(os.environ['OUTPUT_PATH'], 'w')

    q = int(input())

    for q_itr in range(q):
        s = input()

        result = sherlockAndAnagrams(s)

        fptr.write(str(result) + '\n')

    fptr.close()
