package shop.daegu.dto.group;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class GroupForm {

    @NotEmpty(message = "이름은 필수 값 입니다.")
    private String name;

    private Integer ord;
}
