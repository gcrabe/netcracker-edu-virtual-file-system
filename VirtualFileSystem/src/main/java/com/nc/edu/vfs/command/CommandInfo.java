/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nc.edu.vfs.command;

import java.util.Arrays;

/**
 *
 * @author gc
 */
public class CommandInfo {
    private String command;
    private String[] arguments;
    private boolean hasError;

    CommandInfo(String command, String[] arguments, boolean hasError) {
        this.command = command;
        this.arguments = arguments;
        this.hasError = hasError;
    }
    
    public String getCommand() {
        return command;
    }
    
    public void setCommand(String command) {
        this.command = command;
    }
    
    public String[] getArguments() {
        return arguments;
    }
    
    public void setArguments(String[] arguments) {
        this.arguments = arguments;
    }
    
    public boolean hasError() {
        return hasError;
    }
    
    public void setError(boolean hasError) {
        this.hasError = hasError;
    }
    
    @Override
    public String toString() {
        return "{" + this.command + ", "
                + Arrays.toString(this.arguments) + ", "
                + this.hasError + "}";
    }
}
