package edu.cornell.cs.cs4120.xic.ir;

import edu.cornell.cs.cs4120.util.InternalCompilerError;

/**
 * Binary operators
 */
public enum OpType {
    ADD, SUB, MUL, HMUL, DIV, MOD, AND, OR, XOR, LSHIFT, RSHIFT, ARSHIFT,
    EQ, NEQ, LT, GT, LEQ, GEQ;

    @Override
    public String toString() {
        switch (this) {
        case ADD:
            return "ADD";
        case SUB:
            return "SUB";
        case MUL:
            return "MUL";
        case HMUL:
            return "HMUL";
        case DIV:
            return "DIV";
        case MOD:
            return "MOD";
        case AND:
            return "AND";
        case OR:
            return "OR";
        case XOR:
            return "XOR";
        case LSHIFT:
            return "LSHIFT";
        case RSHIFT:
            return "RSHIFT";
        case ARSHIFT:
            return "ARSHIFT";
        case EQ:
            return "EQ";
        case NEQ:
            return "NEQ";
        case LT:
            return "LT";
        case GT:
            return "GT";
        case LEQ:
            return "LEQ";
        case GEQ:
            return "GEQ";
        }
        throw new InternalCompilerError("Unknown op type");
    }
};