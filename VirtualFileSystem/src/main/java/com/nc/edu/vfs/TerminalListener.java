/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nc.edu.vfs;

import java.util.Scanner;

/**
 *
 * @author gc
 */
public class TerminalListener {
    
    private static final String EXIT = "exit";
    private static final String HELP = "help";
    private static final String SCAN = "scan";
    private static final String MV = "mv";
    private static final String CP = "cp";
    private static final String RM = "rm";
    private static final String ERROR  = "error";
    
    public static void waitForCommand() {
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            TerminalInterfaceHelper.writePrompt();
            String commandLine = scanner.nextLine();
            CommandInfo info = CommandParser.parseCommand(commandLine);
            
            if (info.getCommand().equals(ERROR)) {
                System.out.print("Error: Command not supported!");
            }
            
            if (info.getCommand().equals(EXIT)) {
                break;
            }
            
            if (info.getCommand().equals(SCAN)) {
                String path = info.getArguments()[1];
                ScanExecutor se = new ScanExecutor(path);
                se.execute();
            }
        }
    }
}
