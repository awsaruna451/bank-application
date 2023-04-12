package com.aruna.entity;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Setter
@Table(name = "USER")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Data
public class UserEntity implements UserDetails {

    @Id
    @Column(name = "USER_ID_PK", length = 20)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userIdPk;
    @Column(name = "USER_NAME")
    private String userName;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "EMAIL")
    private String userEmail;
    @Column(name = "CUSTOMER_TYPE")
    private String customerType;
    @Column(name = "BRANCH_ID_FK")
    private String branchIdFk;
    @Column(name = "CREATED_DATE")
    private LocalDateTime createdDate;
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "CUSTOMER_ID_FK", referencedColumnName = "CUSTOMER_ID_PK")
    private CustomerEntity customerEntity;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }


    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
