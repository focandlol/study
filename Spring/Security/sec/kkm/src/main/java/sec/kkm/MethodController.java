package sec.kkm;

import jakarta.annotation.security.DenyAll;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class MethodController {

    private final DataService dataService;


    @GetMapping("/adminMethod")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String adminMethod() {
        return "adminMethod";
    }

    @GetMapping("/userMethod")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
    public String userMethod() {
        return "userMethod";
    }

    @GetMapping("/isAuthenticated")
    @PreAuthorize("isAuthenticated()")
    public String isuthenticated() {
        return "isAuthenticated";
    }

    @GetMapping("/userMethod/{id}")
    @PreAuthorize("#id == authentication.name")
    public String authentication(@PathVariable String id) {
        return id;
    }

    @GetMapping("/owner")
    @PostAuthorize("returnObject.owner == authentication.name")
    public Account owner(String name){
        return new Account(name,false);
    }

    @GetMapping("/isSecure")
    @PostAuthorize("hasAuthority('ROLE_ADMIN') and returnObject.isSecure")
    public Account isSecure(String name,String isSecure){
        return new Account(name,"y".equals(isSecure));
    }

    @PostMapping("/writeList")
    public List<Account> writeList(@RequestBody List<Account> data){
        return dataService.writeList(data);
    }

    @PostMapping("/writeMap")
    public Map<String,Account> writeMap(@RequestBody List<Account> data){
        Map<String, Account> map = data.stream().collect(Collectors.toMap(account -> account.getOwner(), account -> account));
        return dataService.writeMap(map);
    }

    @GetMapping("/readList")
    public List<Account> readList(){
        return dataService.readList();
    }

    @GetMapping("/userSecured")
    @Secured("ROLE_USER")
    public String userSecured(){
        return "user";
    }

    @GetMapping("/adminRoles")
    @RolesAllowed("ADMIN")
    public String adminRoles(){
        return "adminRoles";
    }

    @GetMapping("/permitAll")
    @PermitAll
    public String permitAll(){
        return "permitAll";
    }

    @GetMapping("/denyAll")
    @DenyAll
    public String denyAll(){
        return "denyAll";
    }

    @GetMapping("/isAdmin")
    @IsAdmin
    public String isAdmin(){
        return "isAdmin";
    }

    @GetMapping("/ownerShip")
    @OwnerShip
    public Account ownerShip(String name){
        return new Account(name,false);
    }

    @GetMapping("/delete")
    @PreAuthorize("@myAuthorizer.isUser(#root)")
    public String delete(){
        return "delete";
    }


}
