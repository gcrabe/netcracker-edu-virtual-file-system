/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nc.edu.vfs.executors;

/**
 *
 * @author gc
 */
public class MvExecutor {
    
    private String from;
    private String dest;
    
    public MvExecutor(String from, String dest) {
        this.from = from;
        this.dest = dest;
    }
    
    public String getFrom() {
        return from;
    }
    
    public void setFrom(String from) {
        this.from = from;
    }
    
    public String getDest() {
        return dest;
    }
    
    public void setDest(String dest) {
        this.dest = dest;
    }
    
    public void execute() {
        CpExecutor ce = new CpExecutor(from, dest);
        ce.execute();
        
        RmExecutor re = new RmExecutor(from);
        re.execute();
    }
}
