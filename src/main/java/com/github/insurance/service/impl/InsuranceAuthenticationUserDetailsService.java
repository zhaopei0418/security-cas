package com.github.insurance.service.impl;

import com.github.insurance.domain.CasAuthUser;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jasig.cas.client.authentication.AttributePrincipal;
import org.jasig.cas.client.validation.Assertion;
import org.springframework.security.cas.authentication.CasAssertionAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class InsuranceAuthenticationUserDetailsService implements AuthenticationUserDetailsService<CasAssertionAuthenticationToken> {

    private static Log logger = LogFactory.getLog(InsuranceAuthenticationUserDetailsService.class);

    /**
     * cas登录成功后，从token中获取到用户的名称及额外属性，
     * 额外属性参考cas服务提供的数据
     * @param token
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserDetails(CasAssertionAuthenticationToken token) throws UsernameNotFoundException {

        logger.info("username = " + token.getName());
        Assertion assertion = token.getAssertion();
        AttributePrincipal principal = assertion.getPrincipal();
        Map<String, Object> attributes = principal.getAttributes();
        logger.info("token attributes size = " + attributes.size());
        for (Map.Entry<String, Object> entry : attributes.entrySet()) {
            logger.info("token key: [" + entry.getKey() + "] value: [" + (String)entry.getValue() + "]");
        }
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        CasAuthUser casAuthUser = new CasAuthUser(token.getName(), authorities);
        // 从token中获取用户额外属性
//        casAuthUser.setId((String)attributes.get("id"));
//        casAuthUser.setBooknumber((String)attributes.get("booknumber"));
//        casAuthUser.setStatus((String)attributes.get("status"));
//        casAuthUser.setOrgCode((String)attributes.get("orgCode"));
//        casAuthUser.setName((String)attributes.get("name"));
//        casAuthUser.setCreditCode((String)attributes.get("creditCode"));
//        casAuthUser.setBooktype((String)attributes.get("booktype"));
//        try {
//            casAuthUser.setEmail(URLDecoder.decode((String)attributes.get("email"), "utf-8"));
//            casAuthUser.setCompanyName(URLDecoder.decode((String)attributes.get("companyName"), "utf-8"));
//        } catch (UnsupportedEncodingException e) {
//            logger.error("decode company name error", e);
//        }
//        casAuthUser.setMobile((String)attributes.get("mobile"));
        return casAuthUser;
    }
}
