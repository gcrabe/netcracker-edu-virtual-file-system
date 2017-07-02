/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nc.edu.vfs.executors;

import com.nc.edu.vfs.database.DbGateway;
import com.nc.edu.vfs.graph.FileSystemGraphRepository;

/**
 *
 * @author gc
 */
public class RmExecutor {
    
    private String path;
    
    public RmExecutor(String path) {
        this.path = path;
    }
    
    public String getPath() {
        return path;
    }
    
    public void setPath(String path) {
        this.path = path;
    }
    
    public void execute() {
        if (!FileSystemGraphRepository.contains(path)) {
            System.out.print("Error: destination point does not exist!\n");
            return;
        }
        
        FileSystemGraphRepository.deleteSubtree(path);
        
        DbGateway.clear();
        DbGateway.update();
        
        FileSystemGraphRepository.cerrGraph();
    }
}
