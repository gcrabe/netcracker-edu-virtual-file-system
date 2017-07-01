/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nc.edu.vfs.executors;

import com.nc.edu.vfs.graph.FileSystemGraphRepository;
import java.io.File;

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
        File record = new File(path);
        
        if (!record.exists()) {
            System.out.println("Error: destination point does not exist!");
            return;
        }
        
        if (!record.isDirectory() && !record.isFile()) {
            System.out.print("Error: it's not folder/file!");
            return;
        }
        
        String subtreePath = record.getAbsolutePath();
        FileSystemGraphRepository.deleteSubtree(subtreePath);
        
        // db.update
        
        FileSystemGraphRepository.cerrGraph();
    }
}
