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
public class AdjacentList {
    
    private int index;
    private String path;
    private ArrayList<Vertex> neighbors;
    
    public AdjacentList() {
    }
    
    public AdjacentList(int index, String path, ArrayList<Vertex> neighbors) {
        this.index = index;
        this.path = path;
        this.neighbors = neighbors;
    }
    
    public int getIndex() {
        return index;
    }
    
    public void setIndex(int index) {
        this.index = index;
    }
    
    public String getPath() {
        return path;
    }
    
    public void setPath(String path) {
        this.path = path;
    }
    
    public ArrayList<Vertex> getNeighbors() {
        return neighbors;
    }
    
    public void setNeighbors(ArrayList<Vertex> neighbors) {
        this.neighbors = neighbors;
    }
}
