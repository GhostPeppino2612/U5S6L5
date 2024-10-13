package giuseppeacquaviva.U5S6L5.exceptions;

import giuseppeacquaviva.U5S6L5.payloads.ErrorPayloadList;
import giuseppeacquaviva.U5S6L5.payloads.ErrorsPayload;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorPayloadList handleBadRequest(BadRequestException ex) {
        List<String> errorMessages = new ArrayList<>();
        if (ex.getErrors() != null) {
            errorMessages = ex.getErrors().stream().map(err -> err.getDefaultMessage()).toList();
        }
        return new ErrorPayloadList(ex.getMessage(), LocalDate.now(), errorMessages);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleResourceNotFound(NotFoundException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorsPayload handleGeneric(Exception e) {
        e.printStackTrace();
        return new ErrorsPayload("Errore generico, risolveremo il prima possibile", LocalDate.now());
    }
}
