/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nc.edu.vfs.terminal;

import com.nc.edu.vfs.command.CommandInfo;
import com.nc.edu.vfs.command.CommandParser;
import com.nc.edu.vfs.executors.CpExecutor;
import com.nc.edu.vfs.executors.MvExecutor;
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
    private static final String ERROR = "error";
    
    private static ScanExecutor se = null;
    private static RmExecutor re;
    private static CpExecutor ce;
    private static MvExecutor me;
    
    public static boolean scanInited(CommandInfo info) {
        return !((info.getCommand().equals(MV)
                || info.getCommand().equals(CP)
                || info.getCommand().equals(RM))
                && se == null);
    }
    
    public static void waitForCommand() {
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.print("> ");
            
            String commandLine = scanner.nextLine();
            CommandInfo info = CommandParser.parseCommand(commandLine);
            
            if (info.getCommand().equals(ERROR)) {
                System.out.print("Error: Command not supported or incorrect format of command!\n");
                System.out.print("Use \'help\' to find correct commands info\n");
            }
            
            if (info.getCommand().equals(EXIT)) {
                break;
            }
            
            if (info.getCommand().equals(SCAN)) {
                String path = info.getArguments()[1];
                se = new ScanExecutor(path);
                se.execute();
            }
            
            if (!scanInited(info)) {
                System.out.print("Error: \'scan\' command must be executed at least once\n");
            } else {
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
                
                if (info.getCommand().equals(MV)) {
                    String from = info.getArguments()[1];
                    String dest = info.getArguments()[2];
                    me = new MvExecutor(from, dest);
                    me.execute();
                }
            }
        }
    }
}
