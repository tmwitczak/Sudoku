//////////////////////////////////////////////////////////////////////// Package
package jptw.sudoku;

/////////////////////////////////////////////////////////////// Class definition
class Calculator {
    ////////////////////////////////////////////////////////////////// [Methods]
    //--------------------------------------------------------------- Addition <
    int add(final int a, final int b) {
        return a + b;
    }

    double add(final double a, final double b) {
        return a + b;
    }

    //------------------------------------------------------------ Subtraction <
    int diff(final int a, final int b) {
        return a - b;
    }

    double diff(final double a, final double b) {
        return a - b;
    }

    //--------------------------------------------------------- Multiplication <
    int mul(final int a, final int b) {
        return a * b;
    }

    double mul(final double a, final double b) {
        return a * b;
    }

    //--------------------------------------------------------------- Division <
    int div(final int a, final int b)
            throws ArithmeticException {
        return a / b;
    }

    double div(final double a, final double b) {
        return a / b;
    }
}
////////////////////////////////////////////////////////////////////////////////