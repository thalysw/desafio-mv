package proposta.mv.desafio.exceptions.business;

public class StateNotExistException extends RuntimeException{

    public StateNotExistException(String msg) {
        super(msg);
    }

    public StateNotExistException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
