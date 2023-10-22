package fr.formation.discord.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "La vidéo n'a pas été trouvé")
public class VideoNotFoundException extends RuntimeException {
}
