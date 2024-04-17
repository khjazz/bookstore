package hkmu.comps380f.dao;

import hkmu.comps380f.controller.UserManagementController;
import hkmu.comps380f.exception.BookNotFound;
import hkmu.comps380f.exception.UserNotFound;
import hkmu.comps380f.model.Book;
import hkmu.comps380f.model.TicketUser;
import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class UserManagementService {
    @Resource
    private TicketUserRepository tuRepo;
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
    public void updateUser(String username, String password, String email, String delivery)
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

}

