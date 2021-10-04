package cc.abro.bai.commandrouter.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddListener {
    private String prefix;
    private String topicName;
}
