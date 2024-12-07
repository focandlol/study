package focandlol.controller;

import focandlol.converter.code.OrganizationCode;
import focandlol.dto.ProductInfoDto;
import focandlol.response.CustomResponse;
import focandlol.response.ResponseCode;
import focandlol.service.ProductService;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fintech/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/product")
    public String product(){
        return "hello product";
    }

    @GetMapping("{organizationCode}")
    public ResponseEntity<CustomResponse> findProduct(@PathVariable OrganizationCode organizationCode){
        return ResponseEntity.ok().body(new CustomResponse(productService.findProductInfo(organizationCode),ResponseCode.SUCCESS));
    }

    @PostMapping("/information")
    public ResponseEntity<CustomResponse> information(@RequestBody @Parameter(description = "product") ProductInfoDto.Request request){
        productService.save(request);
        return ResponseEntity.ok(new CustomResponse<>(ResponseCode.SUCCESS));
    }
}
