/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package edu.cse231.interfaces;

/**
 *
 * @author Admin
 */
public interface Searchable<T> {
    
    T searchById(String id);
    T searchByName(String nameQuery);
}

