package com.BarnDoor.service;

import com.BarnDoor.model.CustomUserDetails;
import com.BarnDoor.model.ResponseDTO;
import com.BarnDoor.model.User;
import com.BarnDoor.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;


@Service
public class UserLoginService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private HttpServletResponse httpServletResponse;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    private static final Logger logger = LoggerFactory.getLogger(UserLoginService.class);


    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);
        System.out.println("found user"+user);
        if (null == user) {
            System.out.println("found user"+user);
            throw new UsernameNotFoundException(username);
        }


        return new CustomUserDetails(user);

    }
    private static Collection<? extends GrantedAuthority> getAuthorities(User user) {
        String[] userRoles = user.getRoles().stream().map((role) -> role.getName()).toArray(String[]::new);
        Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(userRoles);
        return authorities;
    }

    public ResponseDTO login(User requestUser) {


        UsernamePasswordAuthenticationToken authenticationTokenRequest = new
                UsernamePasswordAuthenticationToken(requestUser.getUsername(), requestUser.getPassword());
        try {
            Authentication authentication = this.authenticationManager.authenticate(authenticationTokenRequest);
            SecurityContext securityContext = SecurityContextHolder.getContext();
            securityContext.setAuthentication(authentication);

            String user = authentication.getName();
            logger.info("Logged in user: {}", user);
            return new ResponseDTO(HttpStatus.OK, "logged in user", user);

        } catch (BadCredentialsException ex) {
            throw  new BadCredentialsException("Wrong user credentials"+HttpStatus.BAD_REQUEST,ex);
        }
        catch (Exception e){
            throw  new BadCredentialsException("Wrong user credentials"+HttpStatus.INTERNAL_SERVER_ERROR,e);
        }
    }


    public ResponseDTO logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(
                    httpServletRequest,
                    httpServletResponse,
                    authentication);
        }
        return new ResponseDTO(HttpStatus.OK, "successfully logged out");
    }

    public ResponseDTO saveUsers(User user) {
       return new ResponseDTO(HttpStatus.OK, "successfully logged out",userRepository.save(user));

    }
}
