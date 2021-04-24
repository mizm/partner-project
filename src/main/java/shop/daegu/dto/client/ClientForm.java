package shop.daegu.dto.client;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class ClientForm {

    private Long id;

    @NotEmpty(message = "회원 이름은 필수입니다.")
    private String name;

    private String displayName;

    @NotNull(message = "순서는 필수 입니다.")
    private Integer ord;

    @NotNull(message = "그룹아이디는 필수 입니다.")
    private Long groupId;

    public ClientForm(CleintGroupDto dto) {
        id = dto.getClientId();
        name = dto.getClientName();
        displayName = dto.getDisplayName();
        ord = dto.getClientOrd();
        groupId = dto.getGroupId();
    }
}
