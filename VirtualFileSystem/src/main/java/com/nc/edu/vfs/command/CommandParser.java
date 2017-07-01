/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nc.edu.vfs.command;

import com.nc.edu.vfs.command.CommandInfo;

/**
 *
 * @author gc
 */
public class CommandParser {
    
    public static CommandInfo parseCommand(String inputLine) {
        CommandInfo info = null;
        
        String words[] = inputLine.split(" ");
        String command = words[0];
        
        switch (words.length) {
            case 1:
                {
                    if (command.equals("exit") || command.equals("help")) {
                        info = new CommandInfo(command, null, false);
                    } else {
                        info = new CommandInfo("error", null, true);
                    }       break;
                }
            case 2:
                {
                    if (command.equals("scan") || command.equals("rm")) {
                        info = new CommandInfo(command, words, false);
                    } else {
                        info = new CommandInfo("error", null, true);
                    }       break;
                }
            case 3:
                {
                    if (command.equals("cp") || command.equals("mv")) {
                        info = new CommandInfo(command, words, false);
                    } else {
                        info = new CommandInfo("error", null, true);
                    }       break;
                }
            default:
                info = new CommandInfo("error", null, true);
                break;
        }
        
        return info;
    } 
}
