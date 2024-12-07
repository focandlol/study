package focandlol.service;

import focandlol.converter.code.OrganizationCode;
import focandlol.converter.code.ProductCode;
import focandlol.domain.ProductInfo;
import focandlol.dto.FindProductInfoDto;
import focandlol.dto.ProductInfoDto;
import focandlol.exception.error.ProductException;
import focandlol.repository.ProductInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static focandlol.exception.errorcode.ProductErrorCode.ALREADY_PRODUCT;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductInfoRepository productInfoRepository;

    public void save(ProductInfoDto.Request request) {
        if(check(request)){
            throw new ProductException(ALREADY_PRODUCT);
        }
        productInfoRepository.save(request.toEntity());
    }

    private boolean check(ProductInfoDto.Request request) {
        Optional<ProductInfo> pi = productInfoRepository.findByProductCodeAndOrganizationCode(
                ProductCode.findProduct(request.getProductCode()), OrganizationCode.findOrganization(request.getOrganizationCode())
        );

        if(pi.isPresent()) {
            return true;
        }
        return false;
    }

    public List<FindProductInfoDto> findProductInfo(OrganizationCode organizationCode) {
        return productInfoRepository.findByOrganizationCode(organizationCode)
                .stream()
                .map(a -> FindProductInfoDto.from(a))
                .collect(Collectors.toList());
    }
}
