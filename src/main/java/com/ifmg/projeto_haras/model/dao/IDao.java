
package com.ifmg.projeto_haras.model.dao;

import java.util.List;

public interface IDao {
    public void save(Object obj);
    
    public boolean delete(Object obj);
            
    public Object find(int id);
}
