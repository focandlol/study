package focandlol.weather.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity(name = "date_weather")
@Getter
@Setter
@NoArgsConstructor
public class DateWeather {

    @Id
    private LocalDate date;

    private String weather;
    private String icon;
    private double temperature;
}
