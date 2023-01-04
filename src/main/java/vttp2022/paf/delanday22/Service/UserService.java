package vttp2022.paf.delanday22.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp2022.paf.delanday22.Model.User;
import vttp2022.paf.delanday22.Repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepo;

    public boolean authenticate (User user) {
        boolean result = userRepo.authenticate(user);
        return result;
    }

    public boolean createUser(final User user) throws Exception {
        int count = userRepo.createUser(user);
        System.out.printf("Insert count: %d\n", count);
        return count > 0;
    }

}
