package shop.daegu.dto.client;

import lombok.Data;
import shop.daegu.domain.Client;

@Data
public class CleintGroupDto {

    private Long clientId;
    private String clientName;
    private String displayName;
    private int clientOrd;
    private Long groupId;
    private String groupName;

    public CleintGroupDto(Client client) {
        this.clientId = client.getId();
        this.displayName = client.getDisplayName();
        this.clientName = client.getName();
        this.clientOrd = client.getOrd();
        this.groupId = client.getGroup().getId();
        this.groupName = client.getGroup().getName();
    }
}
