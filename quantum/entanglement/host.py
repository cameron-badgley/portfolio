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
    res = TestBellState.simulate(count=1000, initial=i)
    (num_zeros, num_ones, agree) = res
    print(f'Init:{i} 0s={num_zeros} 1s={num_ones} agree={agree}')