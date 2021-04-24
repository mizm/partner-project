package shop.daegu.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.daegu.dto.client.ClientForm;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Client {

    @Id @GeneratedValue
    @Column(name = "client_id")
    private Long id;
    private String name;
    private String displayName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private Group group;
    private Integer ord;

    public Client(ClientForm clientForm, Group group) {
        name = clientForm.getName();
        displayName = clientForm.getDisplayName();
        ord = clientForm.getOrd();
        this.group = group;
    }

    public Client(String name, String displayName, Group group, Integer ord) {
        this.name = name;
        this.displayName = displayName;
        this.group = group;
        this.ord = ord;
    }

    public void change(ClientForm clientForm, Group group) {
        this.name = clientForm.getName();
        this.displayName = clientForm.getDisplayName();
        this.group = group;
        this.ord = clientForm.getOrd();
    }
}
