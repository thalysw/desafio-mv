package proposta.mv.desafio.exceptions.business;

public class CityNotExistException extends RuntimeException{

    public CityNotExistException(String msg) {
        super(msg);
    }

    public CityNotExistException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
