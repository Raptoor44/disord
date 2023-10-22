package fr.formation.discord.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Une vidéo avec le même nom existe déjà")
public class VideoAlreadyExistsException extends RuntimeException {
}