package com.Labs.Patterns.service;

import com.Labs.Patterns.dao.IRoleDao;
import com.Labs.Patterns.dao.IUserDao;
import com.Labs.Patterns.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private IUserDao iuserDao;

    @Autowired
    private IRoleDao iroleDao;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        HttpServletRequest request=((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String password =request.getParameter("password");
        String nickname=request.getParameter("nickname");
        User user=this.iuserDao.findByNicknameAndPassword(nickname,password);
        if(user==null)
        {
            throw new UsernameNotFoundException("User"+nickname+"was not found in the database");
        }
        System.out.println("Found User with nickname "+nickname);

        List<String> roleNames=this.iroleDao.getRoles(nickname);
        List<GrantedAuthority> grantList=new ArrayList<GrantedAuthority>();
        for(String role:roleNames)
        {
            GrantedAuthority authority=new SimpleGrantedAuthority(role);
            grantList.add(authority);
        }
        UserDetails userDetails=(UserDetails)new org.springframework.security.core.userdetails.User(user.getNickname()
                ,user.getPassword(),grantList);
        return userDetails;
    }
}
