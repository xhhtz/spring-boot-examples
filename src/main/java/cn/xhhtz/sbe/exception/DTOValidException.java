package cn.xhhtz.sbe.exception;

public class DTOValidException extends Exception {
    private static final long serialVersionUID = 1L;
    private String message;

    @Override
    public String getMessage() {
        return this.message;
    }

    public DTOValidException(String message) {
        this.message = message;
    }

}
