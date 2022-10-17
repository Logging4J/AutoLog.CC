package cc.l4j.autolog.command;

import cc.l4j.autolog.command.impl.HelpCommand;
import net.minecraft.util.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandManager {
    public static List<Command> commands = Util.make(new ArrayList<>(), CommandManager::init);

    public static void init(List<Command> commands) {
        commands.add(new HelpCommand());
    }

    public static List<Command> getCommands(){
        return commands;
    }

    public static Command getByAlias(String alias){
        for(Command command : getCommands()){
            if(Arrays.stream(command.triggers).anyMatch(s -> s.equalsIgnoreCase(alias))){
                return command;
            }
        }
        return null;
    }
}