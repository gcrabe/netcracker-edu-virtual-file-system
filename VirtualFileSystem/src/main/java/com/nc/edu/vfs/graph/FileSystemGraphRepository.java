/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nc.edu.vfs.graph;

/**
 *
 * @author gc
 */
public class FileSystemGraphRepository {
    
    private static final FileSystemGraph GRAPH = new FileSystemGraph();
    
    public static void clearGraph() {
        GRAPH.clear();
    }
    
    public static void pushVertex(Vertex vertex) {
        GRAPH.pushVertex(vertex);
    }
    
    public static void cerrGraph() {
        GRAPH.cerr();
    }
    
    public static void deleteSubtree(String path) {
        GRAPH.deleteSubtree(path);
    }

    public static int checkParentByPath(String path) {
        return GRAPH.checkParentByPath(path);
    }
}
