package mkoffeine.wordsunk.dao.impl;

import mkoffeine.wordsunk.dao.UserDao;
import mkoffeine.wordsunk.entity.UserEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Michael on 04.04.2015.
 */
public class UserDaoDbImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Transactional
    public UserEntity getUserById(int id) {
        return (UserEntity) getSession().get(UserEntity.class, id);
    }

    @Transactional
    public UserEntity getUserByName(String name) {
        //todo use criteria
        Query query = getSession().createQuery("from UserEntity where loginName=?");
        query.setString(0, name);
        List list = query.list();
        UserEntity userEntity = null;
        if (list.size() > 0) {
            userEntity = (UserEntity) list.get(0);
        }
        return userEntity;
    }

    @Transactional
    public List<UserEntity> getAll() {
        return getSession().createCriteria(UserEntity.class).list();
    }

    @Transactional
    public void insertUser(UserEntity user) {
        getSession().persist(user);
        System.out.println("Insert " + user);
        //mysql> insert into userentity (loginName, password, userGroup) values ("Mike", "$2a$10$04TVADrR6/SPLBjsK0N30.Jf5fNjBugSACeGv1S69dZALR7lSov0y", "admin");
    }

    @Transactional
    public void deleteUser(UserEntity user) {
        getSession().delete(user);
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public void saveUser(UserEntity user) {
        throw new UnsupportedOperationException("method saveUser isn't ready");
    }

    @Override
    public void saveUsersWords(UserEntity goal) {
        throw new UnsupportedOperationException("method saveUsersWords isn't ready");
    }
}
