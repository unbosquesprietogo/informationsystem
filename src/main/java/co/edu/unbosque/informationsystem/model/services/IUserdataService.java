package co.edu.unbosque.informationsystem.model.services;

import co.edu.unbosque.informationsystem.model.entity.Userdata;

public interface IUserdataService {
    public Userdata findByUsername(String username);
}
