package project.sec.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import project.sec.domain.Member;
import project.sec.repository.MemberRepository;
import project.sec.service.MemberService;
import project.sec.service.MemberValidator;
import project.sec.service.MemberLoginValidator;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;
    private final MemberService memberService;
    private final MemberValidator memberValidator;
    private final MemberLoginValidator memberloginValidator;



    @GetMapping("/members/members")
    public String MemberList(Model model){
        List<Member> members = memberRepository.findAll();
        model.addAttribute("members",members);
        return "members/memberList";
    }

    @GetMapping("/members/signup")
    public String GMemberNew(Model model){
        model.addAttribute("memberForm",new MemberForm());
        return "members/signup";
    }

    @PostMapping("/members/signup")
    public String PMemberNew(@Valid MemberForm memberForm,Model model, Errors errors) {
        memberValidator.validate(memberForm,errors);
        //System.out.println(errors.hasErrors());
        if (!errors.hasErrors()) {
            Member member = new Member(memberForm);
            memberRepository.save(member);
            return "redirect:/";
        }
        return "members/signup";
    }

    @GetMapping("/members/login")
    public String GMemberLogin(Model model){
        model.addAttribute("memberLoginForm" , new MemberLoginForm());
        return "members/login";
    }


    /*
    @PostMapping("/members/login")
    public String PMemberLogin(@Valid MemberLoginForm memberLoginForm,Model model, Errors errors) {
        memberloginValidator.validate(memberLoginForm,errors);
        model.addAttribute("findform", new FindForm());
        if(!errors.hasErrors()){
            System.out.println(1);
            return "movies/find";
        }
        return "members/login";
    }

     */
}
