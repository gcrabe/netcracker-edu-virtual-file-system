/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nc.edu.vfs.graph;

import java.util.ArrayList;

/**
 *
 * @author gc
 */
public class FileSystemGraph {
    
    private ArrayList<AdjacentList> graph;
    
    public void clear() {
        graph = new ArrayList<>();
    }
    
    public void pushVertex(Vertex vertex, String parentPath) {
        boolean contains = false;
        
        for (int i = 0; i < graph.size(); i++) {
            if (graph.get(i).getIndex() == vertex.getParent()) {
                contains = true;
                graph.get(i).getNeighbors().add(vertex);
            }
        }
        
        if (!contains) {
            AdjacentList list = new AdjacentList();
            list.setIndex(vertex.getParent());
            list.setPath(parentPath);
            
            ArrayList<Vertex> temp = new ArrayList<>();
            temp.add(vertex);
            list.setNeighbors(temp);
            
            graph.add(list);
        }
    }
    
    private void recDel(int v) {
        for (AdjacentList list : graph) {
            for (Vertex vertex : list.getNeighbors()) {
                if (vertex.getId() == v) {
                    list.getNeighbors().remove(vertex);
                    break;
                }
            }
        }
        
        ArrayList<Integer> children = new ArrayList<>();
        
        for (AdjacentList list : graph) {
            if (list.getIndex() == v) {
                for (Vertex temp : list.getNeighbors()) {
                    children.add(temp.getId());
                }
            }
        }
        
        for (int child : children) {
            recDel(child);
        }
        
        for (AdjacentList list : graph) {
            if (list.getIndex() == v) {
                graph.remove(list);
                break;
            }
        }
    }
    
    public void deleteSubtree(String path) {
        int startVertex = -1;
        
        for (AdjacentList list : graph) {
            if (list.getPath().equals(path)) {
                startVertex = list.getIndex();
                break;
            }
            
            for (Vertex vertex : list.getNeighbors()) {
                if (vertex.getName().equals(path)) {
                    startVertex = vertex.getId();
                    break;
                }
            }
        }
        
        if (startVertex == -1) {
            System.out.print("Error: can't find forder/file by path!\n");
            return;
        }
        
        recDel(startVertex);
    }
    
    public void cerr() {
        for (AdjacentList line : graph) {
            System.err.print("Line: " + line.getIndex() + "\n");
            
            for (Vertex v : line.getNeighbors()) {
                System.err.println(v.toString());
            }
        }
    }

    public int checkParentByPath(String path) {
        for (AdjacentList list : graph) {
            if (list.getPath().equals(path)) {
                return list.getIndex();
            }
            
            for (Vertex v : list.getNeighbors()) {
                if (v.getName().equals(path)) {
                    return v.getId();
                }
            }
        }
        
        return -1;
    }

    public String getParentPathById(int pos) {
        for (AdjacentList list : graph) {
            if (list.getIndex() == pos) {
                return list.getPath();
            }
            
            for (Vertex v : list.getNeighbors()) {
                if (v.getId() == pos) {
                    return v.getName();
                }
            }
        }
        
        return null;
    }

    public boolean contains(String path) {
        for (AdjacentList list : graph) {
            if (list.getPath().equals(path)) {
                return true;
            }
            
            for (Vertex v : list.getNeighbors()) {
                if (v.getName().equals(path)) {
                    return true;
                }
            }
        }
        
        return false;
    }
}
