package shop.daegu.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.daegu.dto.group.GroupForm;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "Groups")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Group {

    @Id @GeneratedValue
    @Column(name = "group_id")
    private Long id;
    private String name;
    private Integer ord;

    public Group(String name, Integer ord) {
        this.name = name;
        this.ord = ord;
    }
    public Group(GroupForm form) {
        this.name = form.getName();
        this.ord = form.getOrd();
    }
}
