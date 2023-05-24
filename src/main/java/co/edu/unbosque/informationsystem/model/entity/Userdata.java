package co.edu.unbosque.informationsystem.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import javax.validation.constraints.*;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "userdata")
public class Userdata implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "no puede estar vacio")
    @Column(unique = true, length = 20)
    private String username;
    @NotNull(message = "no puede estar vacio")
    @Column(length = 60)
    private String password;

    @NotNull(message = "no puede estar vacio")
    private String name;

    @NotNull(message = "no puede estar vacio")
    private String lastname;


    @OneToMany(fetch = FetchType.LAZY,mappedBy = "userdata",cascade = CascadeType.ALL)
    @OrderColumn(name = "type_identification_order")
    private List<TypeIdentification> type_identification;

    @NotNull(message = "no puede estar vacio")
    @Column(unique = true)
    private String university_email;
    @Column(unique = true)
    private String personal_email;

    private Long identification;

    private String phone;

    private String cellphone;

    private String address;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name="userdata_roles", joinColumns= @JoinColumn(name="userdata_id"),
            inverseJoinColumns=@JoinColumn(name="role_id"),
            uniqueConstraints= {@UniqueConstraint(columnNames= {"userdata_id", "role_id"})})
    @OrderColumn(name = "order_index")
    private List<Role> roles;

    public Userdata() {
        type_identification = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUniversity_email() {
        return university_email;
    }

    public void setUniversity_email(String university_email) {
        this.university_email = university_email;
    }

    public String getPersonal_email() {
        return personal_email;
    }

    public void setPersonal_email(String personal_email) {
        this.personal_email = personal_email;
    }

    public List<TypeIdentification> getType_identification() {
        return type_identification;
    }

    public void setType_identification(List<TypeIdentification> type_identification) {
        this.type_identification = type_identification;
    }

    public Long getIdentification() {
        return identification;
    }

    public void setIdentification(Long identification) {
        this.identification = identification;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    /**
     *
     */
    private static final long serialVersionUID = 1L;

}
