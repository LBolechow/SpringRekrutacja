package pl.lukbol.SpringRekrutacja.Exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


public record ErrorResponse(int status, String message) {}
