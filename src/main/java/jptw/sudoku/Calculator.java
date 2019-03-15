//////////////////////////////////////////////////////////////////////// Package
package jptw.sudoku;

/////////////////////////////////////////////////////////////// Class definition
class Calculator {
    ////////////////////////////////////////////////////////////////// [Methods]
    //--------------------------------------------------------------- Addition <
    final int add(final int a, final int b) {
        return a + b;
    }

    final double add(final double a, final double b) {
        return a + b;
    }

    //------------------------------------------------------------ Subtraction <
    final int diff(final int a, final int b) {
        return a - b;
    }

    final double diff(final double a, final double b) {
        return a - b;
    }

    //--------------------------------------------------------- Multiplication <
    final int mul(final int a, final int b) {
        return a * b;
    }

    final double mul(final double a, final double b) {
        return a * b;
    }

    //--------------------------------------------------------------- Division <
    final int div(final int a, final int b)
            throws ArithmeticException {
        return a / b;
    }

    final double div(final double a, final double b) {
        return a / b;
    }
}
////////////////////////////////////////////////////////////////////////////////