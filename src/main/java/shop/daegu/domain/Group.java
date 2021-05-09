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
    @Column(name = "file_name")
    private String fileName;
    @Column(name = "sheet_name")
    private String sheetName;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="x", column=@Column(name="item_x")),
            @AttributeOverride(name="y", column=@Column(name="item_y"))
    })
    private Point itemPoint;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="x", column=@Column(name="client_x")),
            @AttributeOverride(name="y", column=@Column(name="client_y"))
    })
    private Point clientPoint;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="x", column=@Column(name="order_x")),
            @AttributeOverride(name="y", column=@Column(name="order_y"))
    })
    private Point orderPoint;

    public Group(String name, Integer ord) {
        this.name = name;
        this.ord = ord;
    }
    public Group(GroupForm form) {
        this.name = form.getName();
        this.ord = form.getOrd();
    }
}
