package project.sec.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class EvalList {

    @GeneratedValue
    @Id
    @Column(nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member_id;

    @Column(nullable = false)
    private String movie_name;

    @Column(nullable = false)
    private int score;
}
