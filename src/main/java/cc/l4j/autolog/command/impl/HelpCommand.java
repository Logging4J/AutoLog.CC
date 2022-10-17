package cc.l4j.autolog.command.impl;

import cc.l4j.autolog.command.Command;

public class HelpCommand extends Command {

    public HelpCommand(){
        super("Help", "Provides help on commands", "help");
    }

    @Override
    public void exc(String[] args) {
        sendMessage("=========Commands=========");
        sendMessage("                          ");
        sendMessage(";help");
        sendMessage("-provides help to the user");
        sendMessage("                          ");

    }
}
