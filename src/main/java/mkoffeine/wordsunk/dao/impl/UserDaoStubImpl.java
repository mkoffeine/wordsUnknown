package mkoffeine.wordsunk.dao.impl;

import mkoffeine.wordsunk.dao.UserDao;
import mkoffeine.wordsunk.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michael on 05.04.2015.
 */
public class UserDaoStubImpl implements UserDao {
    private List<UserEntity> users = new ArrayList<>();
    public UserDaoStubImpl() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1);
        userEntity.setLoginName("mmm");
        userEntity.setPassword("mmm");
        userEntity.setUserGroup("admin");
        userEntity.setWords("the test words");
        users.add(userEntity);

        userEntity = new UserEntity();
        userEntity.setId(2);
        userEntity.setLoginName("aaa");
        userEntity.setPassword("");
        userEntity.setUserGroup("admin");
        userEntity.setWords("the test words");
        users.add(userEntity);

        userEntity = new UserEntity();
        userEntity.setId(3);
        userEntity.setLoginName("FAKE_User");
        userEntity.setPassword("mmm");
        userEntity.setUserGroup("admin");
        userEntity.setWords("the test words again");
        users.add(userEntity);
    }

    @Override
    public UserEntity getUserById(int id) {
        for(UserEntity u : users) {
            if (u.getId() == id) {
                return u;
            }
        }
        return null;
    }

    @Override
    public UserEntity getUserByName(String name) {
        for(UserEntity u : users) {
            if (u.getLoginName().equals(name)) {
                return u;
            }
        }
        return null;
    }

    @Override
    public List<UserEntity> getAll() {
        return users;
    }

    @Override
    public void insertUser(UserEntity user) {
        users.add(user);
    }

    @Override
    public void deleteUser(UserEntity user) {
        users.remove(user);
    }
    @Override
    public void saveUser(UserEntity goal) {
        throw new UnsupportedOperationException("method saveUser isn't ready");
    }

    @Override
    public void saveUsersWords(UserEntity goal) {
        throw new UnsupportedOperationException("method saveUsersWords isn't ready");
    }
}
