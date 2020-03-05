package tk.nenua4e.mc.server.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tk.nenua4e.mc.server.model.User;
import tk.nenua4e.mc.server.repository.UserRepository;

@Service
public class AuthService implements UserDetailsService
{
    private UserRepository users;

    public AuthService(UserRepository users)
    {
        this.users = users;
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException
    {
        return this.users.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
