package pe.edu.upc.moneyproject.servicesinterfaces;

import pe.edu.upc.moneyproject.entities.Role;
import pe.edu.upc.moneyproject.entities.Usuario;

import java.util.List;

public interface IRolService {
    public List<Role> list();
    public void insert(Role rol);
    public void delete(int id);
    public void update(Role rol);
    public Role listId(int id);
}
