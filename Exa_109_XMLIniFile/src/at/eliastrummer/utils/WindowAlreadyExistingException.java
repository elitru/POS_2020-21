package at.eliastrummer.utils;

public class WindowAlreadyExistingException extends RuntimeException{

    public WindowAlreadyExistingException() {
    }

    public WindowAlreadyExistingException(String string) {
        super(string);
    }

    public WindowAlreadyExistingException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }

    public WindowAlreadyExistingException(Throwable thrwbl) {
        super(thrwbl);
    }

    public WindowAlreadyExistingException(String string, Throwable thrwbl, boolean bln, boolean bln1) {
        super(string, thrwbl, bln, bln1);
    }
}
