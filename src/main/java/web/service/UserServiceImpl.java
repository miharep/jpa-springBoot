package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;
import web.repository.UserRepository;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    
    private final UserRepository userRepository;
    
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<User> listUsers() {
        return userRepository.findAll();
    }
    
    @Override
    @Transactional
    public void addUser(User user) {
        userRepository.save(user);
    }
    
    @Override
    @Transactional(readOnly = true)
    public User getUser(Long id) {
        return userRepository.getReferenceById(id);
    }
    
    @Override
    @Transactional
    public void updateUser(User editUser, Long id) {
        User userToBeUpdated = getUser(id);
        userToBeUpdated.setFirstName(editUser.getFirstName());
        userToBeUpdated.setLastName(editUser.getLastName());
        userToBeUpdated.setEmail(editUser.getEmail());
        userRepository.save(userToBeUpdated);
    }
    
    @Override
    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}