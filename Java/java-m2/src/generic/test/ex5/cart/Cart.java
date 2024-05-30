package generic.test.ex5.cart;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<Product, Integer> cartMap = new HashMap<>();
    // 코드 작성

    public void add(Product product, int quantity) {
        int existingQuantity = cartMap.getOrDefault(product, 0);
        cartMap.put(product, existingQuantity + quantity);

    }

    public void minus(Product product, int quantity) {
        Integer qu = cartMap.get(product);
        qu = qu-quantity;
        if(qu <=0){
            cartMap.remove(product);
        }else{
            cartMap.put(product, qu);
        }
    }

    public void printAll(){
        System.out.println("==모든 상품 출력==");
        for (Product product : cartMap.keySet()) {
            System.out.println("상품 : " + product + " 수량: " + cartMap.get(product));
        }
    }
}
