/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nc.edu.vfs;

/**
 *
 * @author gc
 */
public class Vertex {
    
    private int id;
    private int parent;
    private String name;
    
    public Vertex(int id, int parent, String name) {
        this.id = id;
        this.parent = parent;
        this.name = name;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getParent() {
        return parent;
    }
    
    public void setParent(int parent) {
        this.parent = parent;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
}
