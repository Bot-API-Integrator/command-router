package cc.abro.bai.commandrouter.model.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrefixMapping {
    private String prefix;
    private String topicName;
}
