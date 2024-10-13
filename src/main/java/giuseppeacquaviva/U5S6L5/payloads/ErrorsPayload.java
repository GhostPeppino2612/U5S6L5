package giuseppeacquaviva.U5S6L5.payloads;

import java.time.LocalDate;

public record ErrorsPayload(String message, LocalDate time) {
}