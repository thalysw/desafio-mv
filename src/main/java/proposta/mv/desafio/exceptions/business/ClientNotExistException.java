package proposta.mv.desafio.exceptions.business;

public class ClientNotExistException extends RuntimeException{

    public ClientNotExistException(String msg) {
        super(msg);
    }

    public ClientNotExistException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
