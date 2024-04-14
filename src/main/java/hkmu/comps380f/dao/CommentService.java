package hkmu.comps380f.dao;

import hkmu.comps380f.model.Comment;
import hkmu.comps380f.model.TicketUser;
import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CommentService {
    @Resource
    CommentRepository commentRepo;

    @Resource
    TicketUserRepository userRepo;

    public List<Comment> getCommentsByUser(String username)
            throws UsernameNotFoundException {
        TicketUser user = userRepo.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        return commentRepo.findByUser(user);
    }

    public Comment getCommentById(UUID commentId) {
        return commentRepo.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("Comment not found: " + commentId));
    }
}
