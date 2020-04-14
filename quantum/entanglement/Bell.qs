namespace Quantum.Bell {
    open Microsoft.Quantum.Intrinsic;
    open Microsoft.Quantum.Canon;

    operation Set(desired : Result, q1 : Qubit) : Unit {
        if (desired != M(q1)) {
            X(q1);
        }
    }

    operation TestBellState(count :Int, initial : Result) : (Int, Int, Int) {

        // Count the number of 1's that were measureed
        mutable numOnes = 0;

        // Count the number of times the entangled qbit measurements agree. 
        // This should always equal 'count'.
        mutable agree = 0;

        // Lock the qbits to prevent concurrent measurement / decoherence until 
        // we're finished with them
        using ((q0, q1) = (Qubit(), Qubit())) {
            
            // Perform 'count' measurements of both qbits
            for (test in 1..count) {
                
                // Put q0 into superposition
                Set(initial, q0);
                H(q0);

                // Entangle q1 with q0. 
                // CNOT will flip q1 if z0 is Zero.
                // Otherwise, it will leave q1 as One.
                Set(Zero, q1);
                CNOT(q0, q1);

                // Measure q0 and q1 and verify that they agree
                let res = M(q0);

                if (M(q1) == res) {
                    set agree += 1;
                }

                // Count the number of ones that were measured
                if (res == One) {
                    set numOnes += 1;
                }

                // Reset both qbits to a known state before
                // releasing them
                Set(Zero, q0);
                Set(Zero, q1);
            }
        }

        // Return the number of times we measured a 0, the number 
        // of times we measured a 1, and the number of times 
        // q0 === q1.
        return (count-numOnes, numOnes, agree);
    }
}