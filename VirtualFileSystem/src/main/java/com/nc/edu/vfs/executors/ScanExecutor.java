/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nc.edu.vfs.executors;

import com.nc.edu.vfs.graph.Vertex;
import com.nc.edu.vfs.graph.FileSystemGraphRepository;
import java.io.File;

/**
 *
 * @author gc
 */
public class ScanExecutor {
    
    private String path;
    private static int id;
    
    public ScanExecutor(String path) {
        this.path = path;
        id = 0;
    }
    
    public String getPath() {
        return path;
    }
    
    public void setPath(String path) {
        this.path = path;
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
            nums[i] = ++id;
            Vertex vertex = new Vertex(nums[i], dirNum, children[i].getAbsolutePath());
            FileSystemGraphRepository.pushVertex(vertex);
        }
        
        for (int i = 0; i < children.length; i++) {
            if (children[i].isDirectory()) {
                rec(children[i], nums[i]);
            }
        }
    }
    
    public void execute() {
        File rootDirectory = new File(path);
        
        //System.err.println(rootDirectory.getAbsolutePath());
        
        if (!rootDirectory.exists()) {
            System.out.println("Error: can't open dir!");
            return;
        }
        
        if (!rootDirectory.isDirectory()) {
            System.out.print("Error: it's not folder");
            return;
        }
        
        //database.clear
        
        FileSystemGraphRepository.clearGraph();
        rec(rootDirectory, 0);
        
        //graph.to_base
        
        FileSystemGraphRepository.cerrGraph();
    }
}
