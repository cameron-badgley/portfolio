 #!/bin/python3
import sys
import math

t = int(input().strip())
for a0 in range(t):
    n = int(input().strip())

    # Iterating for n and adding up the multiples would take O(n).
    # Instead, we can calculate the finite arithmetic series in O(1) using: 
    # 
    # 1/2 * n * (a_1 + a_n) 
    # 
    # Where:
    # n = the number of terms being added
    # a_1 = the first element in the sequence
    # a_n = the last element in the sequence
    
    multiplesOfThree = (n - 1) // 3
    sumOfMultiplesOfThree = 3 * (multiplesOfThree * (multiplesOfThree + 1)) // 2

    multiplesOfFive = (n - 1) // 5
    sumOfMultiplesOfFive = 5 * (multiplesOfFive * (multiplesOfFive + 1)) // 2

    # Find the multiples that were counted twice (for both 3 and 5)
    multiplesOfFifteen = (n - 1) // 15
    sumOfMultiplesOfFifteen = 15 * (multiplesOfFifteen * (multiplesOfFifteen + 1)) // 2

    print(int(sumOfMultiplesOfThree + sumOfMultiplesOfFive - sumOfMultiplesOfFifteen))
