package co.edu.unbosque.informationsystem.model.dao;

import co.edu.unbosque.informationsystem.model.entity.Userdata;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IUserdataDao extends CrudRepository<Userdata, Long> {
    public Userdata findByUsername(String username);

    @Query("select u from Userdata u where u.username=?1")
    public Userdata findByUsername2(String username);
}
