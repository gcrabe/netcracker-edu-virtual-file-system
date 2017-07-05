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
public class MvExecutor extends CpExecutor{
    
    public MvExecutor(String from, String dest) {
        super(from, dest);
    }
    
    @Override
    public void execute() {
        super.execute();
        
        RmExecutor re = new RmExecutor(super.getFrom());
        re.execute();
    }
}
