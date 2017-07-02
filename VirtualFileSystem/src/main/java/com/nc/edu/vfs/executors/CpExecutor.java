/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nc.edu.vfs.executors;

import com.nc.edu.vfs.database.DbGateway;
import com.nc.edu.vfs.graph.FileSystemGraphRepository;
import com.nc.edu.vfs.graph.Vertex;

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
        if (!FileSystemGraphRepository.contains(from)) {
            System.out.print("Error: \'from\' destination point does not exist!\n");
            return;
        }
        
//        if (!file.isFile()) {
//            System.out.print("Error: it's not file!\n");
//            return;
//        }
        
        String destParent = "";
        
        for (int i = dest.length() - 1; i >= 0; i--) {
            if (dest.charAt(i) == '\\') {
                destParent = dest.substring(0, i);
                break;
            }
        }
        
        StringBuilder tempConcat = new StringBuilder();
        
        for (int i = 0; i < destParent.length(); i++) {
            if (destParent.charAt(i) == '/') {
                tempConcat.append('\\');
            } else {
                tempConcat.append(destParent.charAt(i));
            }
        }
        
        destParent = tempConcat.toString();
        
        int pos = FileSystemGraphRepository.checkParentByPath(destParent);
        
        if (pos == -1) {
            System.out.print("Error: can't copy to destination path!\n");
            return;
        }
        
        String parentPath = FileSystemGraphRepository.getParentPathById(pos);
        
        if (parentPath == null) {
            System.out.print("Error: can't find parent of vertex!\n");
        }
        
        ScanExecutor.increaseId();
        Vertex vertex = new Vertex(ScanExecutor.getStaticId(), pos, dest, "file");
        
        FileSystemGraphRepository.pushVertex(vertex, parentPath);
        
        DbGateway.clear();
        DbGateway.update();
        
        FileSystemGraphRepository.cerrGraph();
    }
}
