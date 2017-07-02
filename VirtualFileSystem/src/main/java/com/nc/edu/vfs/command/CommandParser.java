/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nc.edu.vfs.command;

import com.nc.edu.vfs.command.CommandInfo;
import java.util.ArrayList;

/**
 *
 * @author gc
 */
public class CommandParser {
    
    public static CommandInfo parseCommand(String inputLine) {
        CommandInfo info = null;
        
        String inarr[] = inputLine.split(" ");
        String command = inarr[0];
        
        ArrayList<String> list = new ArrayList<>();
        list.add(command);
        
        if (inarr.length > 1) {
            StringBuilder temp = new StringBuilder();
            String input = inputLine.substring(inarr[0].length() + 1);
            boolean open = false;
            
            for (char c : input.toCharArray()) {
                if (c == '\'' && !open) {
                    open = true;
                } else if (c == '\'' && open) {
                    list.add(temp.toString());
                    temp = new StringBuilder();
                    open = false;
                } else if (c == ' ' && !open) {
                    continue;
                } else {
                    temp.append(Character.toString(c));
                }
            }
        }
        
        String[] words = new String[list.size()];
        
        for (int i = 0; i < words.length; i++) {
            words[i] = Character.toLowerCase(list.get(i).charAt(0)) + 
                    list.get(i).substring(1);
        }
        
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
