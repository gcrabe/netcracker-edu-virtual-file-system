/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nc.edu.vfs;

import com.nc.edu.vfs.terminal.TerminalListener;

/**
 *
 * @author gc
 */
public class Solution {
    
    public static void main(String[] args) {
        System.out.print("Welcome to File System Manager!\n");
        System.out.print("Write \'exit\' for exit from the application.\n");
        System.out.print("Write \'help\' for help infarmation.\n");
        
        TerminalListener.waitForCommand();
    }
}
