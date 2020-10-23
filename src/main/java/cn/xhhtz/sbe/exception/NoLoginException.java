package cn.xhhtz.sbe.exception;

public class NoLoginException extends Exception {
    private static final long serialVersionUID = 1L;
    private String message;

    @Override
    public String getMessage() {
        return this.message;
    }

    public NoLoginException(String message) {
        this.message = message;
    }

}
