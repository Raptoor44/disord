package fr.formation.discord.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "L'élément n'est pas trouvable")
public class FindExeception extends RuntimeException {

}
