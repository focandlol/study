package focandlol.dividends.persist;

import focandlol.dividends.persist.entity.DividendEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DividendRepository extends JpaRepository<DividendEntity,Long> {
}
