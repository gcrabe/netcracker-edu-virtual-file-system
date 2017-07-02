/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nc.edu.vfs.executors;

import com.nc.edu.vfs.database.DbGateway;
import com.nc.edu.vfs.graph.Vertex;
import com.nc.edu.vfs.graph.FileSystemGraphRepository;
import java.io.File;

/**
 *
 * @author gc
 */
public class ScanExecutor {
    
    private static String path;
    private static int id;
    
    public ScanExecutor(String path) {
        ScanExecutor.path = path;
        id = 0;
    }
    
    public static String getPath() {
        return path;
    }
    
    public void setPath(String path) {
        ScanExecutor.path = path;
    }
    
    public static int getStaticId() {
        return id;
    }
    
    public static void increaseId() {
        id++;
    }
    
    public void rec(File dir, int dirNum) {
        File[] children = dir.listFiles();
        int[] nums = new int[children.length];
        
        for (int i = 0; i < children.length; i++) {
            String type = children[i].isFile() ? "file" : "folder";
            nums[i] = ++id;
            Vertex vertex = new Vertex(nums[i], dirNum, children[i].getAbsolutePath(), type);
            String parentPath = dir.getAbsolutePath();
            FileSystemGraphRepository.pushVertex(vertex, parentPath);
        }
        
        for (int i = 0; i < children.length; i++) {
            if (children[i].isDirectory()) {
                rec(children[i], nums[i]);
            }
        }
    }
    
    public void execute() {
        File rootDirectory = new File(path);
        
        if (!rootDirectory.exists()) {
            System.out.print("Error: can't open dir!\n");
            return;
        }
        
        if (!rootDirectory.isDirectory()) {
            System.out.print("Error: it's not folder!\n");
            return;
        }
        
        DbGateway.clear();
        
        FileSystemGraphRepository.clearGraph();
        rec(rootDirectory, 0);
        
        DbGateway.update();
        
        FileSystemGraphRepository.cerrGraph();
    }
}
