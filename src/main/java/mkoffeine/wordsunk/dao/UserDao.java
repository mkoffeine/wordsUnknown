package mkoffeine.wordsunk.dao;

import mkoffeine.wordsunk.entity.UserEntity;

import java.util.List;

/**
 * Created by Michael on 04.04.2015.
 */
public interface UserDao {
    public UserEntity getUserById(int id);
    public UserEntity getUserByName(String name);
    public List<UserEntity> getAll() ;
    public void insertUser(UserEntity goal);
    public void deleteUser(UserEntity goal);
    public void saveUser(UserEntity goal);
    public void saveUsersWords(UserEntity goal);
}
