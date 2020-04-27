# You are given q queries. Each query is of the form two integers 
# described below:
# 1 - x : Insert x in your data structure.
# 2 - y : Delete one occurence of y from your data structure, if present.
# 3 - z : Check if any integer is present whose frequency is exactly z. 
# If yes, print 1 else 0.
# 
# The queries are given in the form of a 2-D array queries of size q where queries[i][0] contains the operation, 
# and queries[i][1] contains the data element. For example, you are given array [(1,1),(2,2),(3,2),(1,1),(1,1),(2,1),(3,2)].
# The results of each operation are:
# 
# Operation   Array   Output
# (1,1)       [1]
# (2,2)       [1]
# (3,2)                   0
# (1,1)       [1,1]
# (1,1)       [1,1,1]
# (2,1)       [1,1]
# (3,2)                   1
# Return an array with the output: [0, 1].

#!/bin/python3

import math
import os
import random
import re
import sys

def freqQuery(queries):
    frequency = {} # The frequency for each number
    frequencyCount = {} # The total amount of numbers with a given frequency
    results = []

    # Determine whether we're inserting (1), deleting (2), or evaluating (3)
    for key in queries:
        action = key[0]
        value = key[1]

        if(action == 1):
            # Increment the frequency count for the given value
            frequency.setdefault(value, 0)
            previousFrequency = frequency[value]
            newFrequency = previousFrequency + 1
            frequency[value] = newFrequency

            # Decrement the number of values with the old frequency
            frequencyCount.setdefault(previousFrequency, 0)
            if(frequencyCount[previousFrequency] > 0):
                frequencyCount[previousFrequency] -= 1

            # Increment the number of values with the new frequency
            frequencyCount.setdefault(newFrequency, 0)
            frequencyCount[newFrequency] += 1
        
        elif(action == 2):
            # Delete one instance of the given value if it exists
            frequency.setdefault(value, 0)
            previousFrequency = frequency[value]
            newFrequency = previousFrequency - 1

            if(previousFrequency > 0):
                frequency[value] = newFrequency

            # Decrement the number of values with the old frequency
            frequencyCount.setdefault(previousFrequency, 0)
            if(frequencyCount[previousFrequency] > 0):
                frequencyCount[previousFrequency] -= 1

            # Increment the number of values with the new frequency
            frequencyCount.setdefault(newFrequency, 0)
            frequencyCount[newFrequency] += 1

        # Determine whether we have any values with the given frequency
        elif(action == 3):
            # We have at least one number with a matching frequency, so append 1 to the results            
            if(frequencyCount.get(value, 0)):
                results.append(1)

            # We weren't able to find any numbers with a matching frequency, so append 0 
            # to the results            
            else:
                results.append(0)
                
    return results

if __name__ == '__main__':
    fptr = sys.stdout
    #os.environ['OUTPUT_PATH'] = 'count-triplets.txt'
    #fptr = open(os.environ['OUTPUT_PATH'], 'w')

    q = int(input().strip())

    queries = []

    for _ in range(q):
        queries.append(list(map(int, input().rstrip().split())))

    ans = freqQuery(queries)

    fptr.write('\n'.join(map(str, ans)))
    fptr.write('\n')

    fptr.close()