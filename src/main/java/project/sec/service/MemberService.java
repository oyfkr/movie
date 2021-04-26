package project.sec.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import project.sec.controller.MemberForm;
import project.sec.domain.Member;
import project.sec.repository.MemberRepository;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final EntityManager em;

    public void jungbokNicName(String name, Errors errors) {
        List<Member> find = em.createQuery("select m from Member m where m.nicName = :name", Member.class)
                .setParameter("name", name)
                .getResultList();

        if(!find.isEmpty()){
            errors.rejectValue("nicname","key","이미 사용자 이름이 존재합니다.");
        }
    }
    public boolean jungbokEmail(String email) {
        List<Member> find = em.createQuery("select m from Member m where m.email = :email", Member.class)
                .setParameter("email", email)
                .getResultList();

        return find.isEmpty();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        List<Member> find = memberRepository.findByEmail(email);
        if(find.size()==1) return find.get(0);
        else{
            new UsernameNotFoundException(email);
            return null;
        }
    }

}
