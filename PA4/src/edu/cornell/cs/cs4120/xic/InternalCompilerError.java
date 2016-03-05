package edu.cornell.cs.cs4120.xic;

/** Exception thrown when the compiler is confused. */
public class InternalCompilerError extends RuntimeException {

    //protected Position pos;

    public InternalCompilerError(String msg) {
        super(msg);
    }

    public InternalCompilerError(Throwable cause) {
        super(cause.getMessage(), cause);
    }

    public InternalCompilerError(String msg, Throwable cause) {
        super(msg, cause);
    }

    public String message() {
        return super.getMessage();
    }

    @Override
    public String getMessage() {
        return message();
    }
}
