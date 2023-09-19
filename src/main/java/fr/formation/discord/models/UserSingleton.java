package fr.formation.discord.models;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
public class UserSingleton {
    public static List<User> users = new LinkedList<User>();
}
