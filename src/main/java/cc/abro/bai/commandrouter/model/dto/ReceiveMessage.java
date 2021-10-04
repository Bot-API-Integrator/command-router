package cc.abro.bai.commandrouter.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReceiveMessage {
    private String text;
    private List<Photo> photos;
    private String authorId;
    private Long sendTime;
    private String integratorTopic;
}
