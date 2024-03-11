package com.example.financialtransactionsystemimpl.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Client  implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;
    private String password;

    @NotNull
    @OneToOne
    @Cascade(CascadeType.PERSIST)
    @JoinColumn(name = "bank_account_id")
    private BankAccount bankAccount;

    @OneToMany
    @Cascade({CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "client_id")
    private List<Phone> phone;

    @Size(min = 1)
    @OneToMany
    @Cascade({CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "client_id")
    private List<Email> email;

    private String fio;
    private String birthDate;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
