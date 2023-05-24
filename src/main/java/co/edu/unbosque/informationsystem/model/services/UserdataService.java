package co.edu.unbosque.informationsystem.model.services;

import co.edu.unbosque.informationsystem.model.dao.IUserdataDao;
import co.edu.unbosque.informationsystem.model.entity.Userdata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

public class UserdataService implements IUserdataService, UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(UserdataService.class);

    @Autowired
    private IUserdataDao userdataDao;

    @Override
    @Transactional(readOnly=true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Userdata userdata = userdataDao.findByUsername(username);

        if(userdata == null) {
            logger.error("Error en el login: no existe el usuario '"+username+"' en el sistema!");
            throw new UsernameNotFoundException("Error en el login: no existe el usuario '"+username+"' en el sistema!");
        }

        List<GrantedAuthority> authorities = userdata.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .peek(authority -> logger.info("Role: " + authority.getAuthority()))
                .collect(Collectors.toList());

        return new User(userdata.getUsername(), userdata.getPassword(), true, true, true, true, authorities);
    }

    @Override
    @Transactional(readOnly=true)
    public Userdata findByUsername(String username) {
        return userdataDao.findByUsername(username);
    }
}
