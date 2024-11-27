package focandlol.dividends.service;

import focandlol.dividends.model.Company;
import focandlol.dividends.model.Dividend;
import focandlol.dividends.model.ScrapedResult;
import focandlol.dividends.persist.CompanyRepository;
import focandlol.dividends.persist.DividendRepository;
import focandlol.dividends.persist.entity.CompanyEntity;
import focandlol.dividends.persist.entity.DividendEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class FinanceService {

    private final DividendRepository dividendRepository;
    private final CompanyRepository companyRepository;

    public ScrapedResult getDividendByCompanyName(String companyName){

        // 1. 회사명을 기준으로 회사 정보를 조회
        CompanyEntity company = companyRepository.findByName(companyName)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 회사명입니다"));

        //2. 조회된 회사 id로 배당금 정보 조회
        List<DividendEntity> dividendEntities = dividendRepository.findAllByCompanyId(company.getId());
        //3. 결과 조합 후 반환

        List<Dividend> dividend = dividendEntities.stream().map(e -> Dividend.builder()
                .dividend(e.getDividend())
                .date(e.getDate())
                .build()).collect(Collectors.toList());

        return new ScrapedResult(Company.builder()
                .ticker(company.getTicker())
                .name(company.getName())
                .build(),dividend
        );

    }

}
