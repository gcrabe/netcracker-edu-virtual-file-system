/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nc.edu.vfs;

import java.util.ArrayList;

/**
 *
 * @author gc
 */
public class FileSystemGraph {
    
    private ArrayList<Pair> graph;
    
    public void clear() {
        graph = new ArrayList<>();
    }
    
    public void pushVertex(Vertex vertex) {
        boolean contains = false;
        
        for (int i = 0; i < graph.size(); i++) {
            if (graph.get(i).indexValue == vertex.getParent()) {
                contains = true;
                graph.get(i).list.add(vertex);
            }
        }
        
        if (!contains) {
            Pair pair = new Pair();
            pair.indexValue = vertex.getParent();
            pair.list = new ArrayList<>();
            pair.list.add(vertex);
            graph.add(pair);
        }
    }
    
    public void cerr() {
        for (Pair line : graph) {
            System.out.print("Line: " + line.indexValue + "\n");
            
            for (Vertex v : line.list) {
                System.out.println("id = " + v.getId() + ", name = [" + v.getName() +  "]");
            }
        }
    }
}
