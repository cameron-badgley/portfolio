#!/bin/python3

import math
import os
import random
import re
import sys

# There are a number of people queued up, and each person wears a sticker indicating 
# their initial position in the queue. Initial positions increment by 1 from 1 at the 
# front of the line to  at the back.
# 
# Any person in the queue can bribe the person directly in front of them to swap 
# positions. If two people swap positions, they still wear the same sticker denoting 
# their original places in line. One person can bribe at most two others. 
#
# Given a queue return the minimum number of bribes that took place to get the queue 
# into its current state

def minimumBribes(q):
    bribes = 0

    for i in range(len(q) - 1, -1, -1):
        originalPosition = q[i]

        # Position starts at 1 rather than array index of 0
        currentPosition = i + 1
        
        improvementInPosition = originalPosition - currentPosition
        if improvementInPosition > 2:
            print('Too chaotic')
            return

        # Look for people who have jumped us. We only need to look up until 2
        # ahead of our original position because no one can jump more than 2
        # places in line
        for j in range(max(0, originalPosition - 2), i):
            potentialBribersOriginalPosition = q[j]

            if potentialBribersOriginalPosition > originalPosition:
                bribes += 1

    print(bribes)

if __name__ == '__main__':
    t = int(input())

    for t_itr in range(t):
        n = int(input())

        q = list(map(int, input().rstrip().split()))

        minimumBribes(q)