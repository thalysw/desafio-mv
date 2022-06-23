package proposta.mv.desafio.exceptions.business;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CityExistException extends RuntimeException{

    public CityExistException(String msg) {
        super(msg);
    }

    public CityExistException(String msg, Throwable cause) {
        super(msg, cause);
    }
}