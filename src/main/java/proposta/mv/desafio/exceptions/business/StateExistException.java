package proposta.mv.desafio.exceptions.business;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class StateExistException extends RuntimeException{

    public StateExistException(String msg) {
        super(msg);
    }

    public StateExistException(String msg, Throwable cause) {
        super(msg, cause);
    }
}