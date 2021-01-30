package at.eliastrummer.utils;

public class WindowNotExistingException extends RuntimeException {

    public WindowNotExistingException() {
    }

    public WindowNotExistingException(String string) {
        super(string);
    }

    public WindowNotExistingException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }

    public WindowNotExistingException(Throwable thrwbl) {
        super(thrwbl);
    }

    public WindowNotExistingException(String string, Throwable thrwbl, boolean bln, boolean bln1) {
        super(string, thrwbl, bln, bln1);
    }
}
