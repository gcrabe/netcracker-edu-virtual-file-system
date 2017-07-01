/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nc.edu.vfs.executors;

import com.nc.edu.vfs.graph.FileSystemGraphRepository;
import com.nc.edu.vfs.graph.Vertex;
import java.io.File;

/**
 *
 * @author gc
 */
public class CpExecutor {
    
    private String from;
    private String dest;
    
    public CpExecutor(String from, String dest) {
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
        File file = new File(from);
        
        if (!file.exists()) {
            System.out.println("Error: destination point does not exist!");
            return;
        }
        
        if (!file.isFile()) {
            System.out.print("Error: it's not file!");
            return;
        }
        
        String destParent = null;
        
        for (int i = dest.length() - 1; i >= 0; i--) {
            if (dest.charAt(i) == '/') {
                destParent = dest.substring(0, i);
                break;
            }
        }
        
        StringBuilder tempConcat = new StringBuilder();
        
        for (int i = 0; i < destParent.length(); i++) {
            if (destParent.charAt(i) == '/') {
                tempConcat.append("\\");
            } else {
                tempConcat.append(destParent.charAt(i));
            }
        }
        
        destParent = tempConcat.toString();
        
        int pos = FileSystemGraphRepository.checkParentByPath(destParent);
        
        if (pos == -1) {
            System.out.print("Error: can't copy to destination path");
            return;
        }
        
        ScanExecutor.increaseId();
        Vertex vertex = new Vertex(ScanExecutor.getStaticId(), pos, dest);
        
        FileSystemGraphRepository.pushVertex(vertex);
        
        // db.update
        
        FileSystemGraphRepository.cerrGraph();
    }
}
