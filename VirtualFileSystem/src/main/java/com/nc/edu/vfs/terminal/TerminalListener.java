/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nc.edu.vfs.terminal;

import com.nc.edu.vfs.command.CommandInfo;
import com.nc.edu.vfs.command.CommandParser;
import com.nc.edu.vfs.executors.CpExecutor;
import com.nc.edu.vfs.executors.RmExecutor;
import com.nc.edu.vfs.executors.ScanExecutor;
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
    
    private static ScanExecutor se;
    private static RmExecutor re;
    private static CpExecutor ce;
    
    public static void waitForCommand() {
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.print("> ");
            
            String commandLine = scanner.nextLine();
            CommandInfo info = CommandParser.parseCommand(commandLine);
            
            if (info.getCommand().equals(ERROR)) {
                System.out.print("Error: Command not supported!\n");
            }
            
            if (info.getCommand().equals(EXIT)) {
                break;
            }
            
            if (info.getCommand().equals(SCAN)) {
                String path = info.getArguments()[1];
                se = new ScanExecutor(path);
                se.execute();
            }
            
            if (info.getCommand().equals(RM)) {
                String path = info.getArguments()[1];
                re = new RmExecutor(path);
                re.execute();
            }
            
            if (info.getCommand().equals(CP)) {
                String from = info.getArguments()[1];
                String dest = info.getArguments()[2];
                ce = new CpExecutor(from, dest);
                ce.execute();
            }
        }
    }
}
