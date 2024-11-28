package focandlol.dividends.service;

import focandlol.dividends.model.Company;
import focandlol.dividends.model.ScrapedResult;
import focandlol.dividends.persist.CompanyRepository;
import focandlol.dividends.persist.DividendRepository;
import focandlol.dividends.persist.entity.CompanyEntity;
import focandlol.dividends.persist.entity.DividendEntity;
import focandlol.dividends.scraper.Scraper;
import lombok.AllArgsConstructor;
import org.apache.commons.collections4.Trie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class CompanyService {

    private final Trie trie;
    private final Scraper yahooFinanceScraper;
    private final DividendRepository dividendRepository;
    private final CompanyRepository companyRepository;

    public Company save(String ticker){
        boolean exists = companyRepository.existsByTicker(ticker);
        if(exists){
            throw new RuntimeException("already exists ticker -> " + ticker);
        }
        return storeCompanyAndDividend(ticker);
    }

    private Company storeCompanyAndDividend(String ticker){
        Company company = yahooFinanceScraper.scrapCompanyByTicker(ticker);
        if(ObjectUtils.isEmpty(company)){
            throw new RuntimeException("failed to scrap ticker -> " + ticker);
        }

        ScrapedResult scrapedResult = yahooFinanceScraper.scrap(company);

        CompanyEntity companyEntity = companyRepository.save(new CompanyEntity(company));
        List<DividendEntity> dividendEntities = scrapedResult.getDividend().stream()
                .map(e -> new DividendEntity(companyEntity.getId(), e))
                .collect(Collectors.toList());

        dividendRepository.saveAll(dividendEntities);

        return company;
    }

    public Page<CompanyEntity> getAllCompany(Pageable pageable){
        return companyRepository.findAll(pageable);
    }

    public List<String> getCompanyNamesByKeyword(String keyword){
        PageRequest limit = PageRequest.of(0, 10, Sort.by("name").descending());
        return companyRepository.findByNameStartingWithIgnoreCase(keyword,limit).stream()
                .map(a -> a.getName()).collect(Collectors.toList());
    }

    /**
     * 자동 완성
     */
    public void addAutocompleteKeyword(String keyword){
        trie.put(keyword,null);
    }

    public List<String> autocomplete(String keyword){
        return (List<String>) trie.prefixMap(keyword).keySet().stream()
                .collect(Collectors.toList());
    }

    public void deleteAutocompleteKeyword(String keyword){
        trie.remove(keyword);
    }

    public String deleteCompany(String ticker){
        CompanyEntity company = companyRepository.findByTicker(ticker)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 회사입니다"));

        dividendRepository.deleteAllByCompanyId(company.getId());
        companyRepository.delete(company);

        deleteAutocompleteKeyword(company.getName());
        return company.getName();
    }
}
