/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import java.util.List;

/**
 *
 * @author Praktek
 */
public interface Model_DAO <A>{
    public int autonumber (A object);
    public void insert (A object);
    public void update (A object);
    public void delete (Integer id);
    public List <A> getAll();
    public List <A> getCari (String key);
}
