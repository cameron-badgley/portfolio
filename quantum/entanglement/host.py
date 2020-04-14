import qsharp

from qsharp import Result
from Quantum.Bell import TestBellState

# Initialize known states
initials = (Result.Zero, Result.One)

for i in initials:
    # Perform 'count' measurements of each known state and
    # display the number of Zero's measured, the number of One's
    # measured, and the number of times the measurement of both
    # qbits were identical (this should equal 'count')
    count = 1000
    res = TestBellState.simulate(count=count, initial=i)
    (num_zeros, num_ones, agree) = res
    
    if(count > 0):
        print(f'--')
        print(f'Initial value:{i}, measurements:{count}')
        print(f'0\'s:{num_zeros}, 1\'s:{num_ones}')
        print(f'qbit agreement:{agree} ({agree/count})')
