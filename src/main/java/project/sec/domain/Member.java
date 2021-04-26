package project.sec.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import project.sec.controller.MemberForm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
public class Member implements UserDetails {

    @GeneratedValue @Id
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nicName;

    @Column(name="auth")
    private String auth;

    public Member(MemberForm memberForm) {
        this.email = memberForm.getEmail();
        this.password = memberForm.getPassword();
        this.nicName = memberForm.getNicname();
        this.auth = memberForm.getAuth();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        Set<GrantedAuthority> roles = new HashSet<>();
        for(String role : auth.split(",")){
            roles.add(new SimpleGrantedAuthority(role));
        }
        return roles;
    }

    @Override
    public String getUsername() {
        return email;
    }
    @Override
    public String getPassword() {
        return password;
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
