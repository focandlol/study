package focandlol.dividends.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.websocket.OnOpen;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Company {

    private String ticker;
    private String name;
}
