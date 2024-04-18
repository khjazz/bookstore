package hkmu.comps380f.dao;

import hkmu.comps380f.exception.UserNotFound;
import hkmu.comps380f.model.TicketUser;
import hkmu.comps380f.model.UserRole;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserManagementService {
    @Resource
    private TicketUserRepository tuRepo;

    @Resource
    private UserRoleRepository urRepo;
    @Transactional
    public List<TicketUser> getTicketUsers() {
        return tuRepo.findAll();
    }

    @Transactional
    public TicketUser getTicketUsers(String username)
            throws UserNotFound {
        TicketUser user = tuRepo.findById(username).orElse(null);
        if (user == null) {
            throw new UserNotFound(username);
        }
        return user;
    }

    @Transactional
    public void delete(String username) {
        TicketUser ticketUser = tuRepo.findById(username).orElse(null);
        if (ticketUser == null) {
            throw new UsernameNotFoundException("User '" + username + "' not found.");
        }
        tuRepo.delete(ticketUser);
    }
    @Transactional
    public void createTicketUser(String username, String password, String email, String delivery, String[] roles) {
        TicketUser user = new TicketUser(username, password, email, delivery, roles);
        tuRepo.save(user);
    }

    @Transactional(rollbackFor = UserNotFound.class)
    public void updateUser(String username, String password, String email, String delivery, String[] roles)
            throws UsernameNotFoundException {
        TicketUser user = tuRepo.findById(username).orElse(null);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setDelivery(delivery);

        tuRepo.save(user);

        urRepo.deleteByUser(user);
        for (String role : roles) {
            UserRole userRole = new UserRole();
            userRole.setRole(role);
            userRole.setUser(user);
            urRepo.save(userRole);
        }
    }

    @Transactional(rollbackFor = UserNotFound.class)
    public void editUser(String username, String password, String email, String delivery)
            throws UsernameNotFoundException {
        TicketUser user = tuRepo.findById(username).orElse(null);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setDelivery(delivery);

        tuRepo.save(user);
    }

    @Transactional
    @PostConstruct
    public void init() {
        if (tuRepo.count() == 0){
            TicketUser user1 = new TicketUser("keith", "keithpw", "abc.com", "abc", new String[]{"ROLE_ADMIN", "ROLE_USER"});
            TicketUser user2 = new TicketUser("john", "johnpw", "bbb@abc.com", "bbb", new String[]{"ROLE_ADMIN"});
            TicketUser user3 = new TicketUser("mary", "marypw", "ccc@abc.com", "ccc", new String[]{ "ROLE_USER"});
            tuRepo.save(user1);
            tuRepo.save(user2);
            tuRepo.save(user3);
        }
    }

}

