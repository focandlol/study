package kkm.securityProject.security.service;

import kkm.securityProject.security.mapper.UrlRoleMapper;

import java.util.Map;

public class DynamicAuthorizationService {
    private final UrlRoleMapper delegate;

    public DynamicAuthorizationService(UrlRoleMapper delegate) {
        this.delegate = delegate;
    }

    public Map<String,String> getUrlRoleMappings(){
        return delegate.getUrlRoleMappings();
    }
}
