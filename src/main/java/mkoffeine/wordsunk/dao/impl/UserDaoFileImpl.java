package mkoffeine.wordsunk.dao.impl;

import mkoffeine.wordsunk.dao.UserDao;
import mkoffeine.wordsunk.entity.UserEntity;
import mkoffeine.wordsunk.utils.MaxValue;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class UserDaoFileImpl implements UserDao {
    public static final String PATH_TO_FILE = "x:\\_Myhaylo\\idea13\\000\\WordsUnk\\myWords\\myWordsTest.txt";
    private List<UserEntity> users = new ArrayList<>();

    public UserDaoFileImpl() {
        UserEntity userEntity = new UserEntity();

        userEntity.setId(1);
        userEntity.setLoginName("FAKE_User");
        userEntity.setPassword("mmm");
        userEntity.setUserGroup("admin");
//        userEntity.setWords("the test words again");
        userEntity.setWords(getWordsFromFile(PATH_TO_FILE));
        users.add(userEntity);

        userEntity = new UserEntity();
        userEntity.setId(2);
        userEntity.setLoginName("mmm");
        userEntity.setPassword("");
        userEntity.setUserGroup("admin");
        userEntity.setWords("the test words");
        users.add(userEntity);
    }

    @Override
    public UserEntity getUserById(int id) {
        for (UserEntity u : users) {
            if (u.getId() == id) {
//              if("FAKE_User".equals(u.getLoginName())) {
                u.setWords(getWordsFromFile(PATH_TO_FILE));
//              }
                return u;
            }
        }
        return null;
    }

    private String getWordsFromFile(String path) {
        File f = new File(path);
        String words = "";
        if (f.exists()) {
            byte[] encoded = new byte[0];
            try {
                encoded = Files.readAllBytes(Paths.get(path));
            } catch (IOException e) {
                e.printStackTrace();
            }
            words = new String(encoded, StandardCharsets.UTF_8);
        } else {
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
//        System.out.println(words);
        System.err.println("    --" + words.substring(0, 50));
//        System.err.println("    --" + words.substring(words.length() - 50));
        return words;
    }

    @Override
    public UserEntity getUserByName(String name) {
        for (UserEntity u : users) {
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
    public void saveUser(UserEntity userEntity) {
        userEntity.setId(MaxValue.getMax());
        users.add(userEntity);
        throw new UnsupportedOperationException("method saveUser isn't ready");
    }

    @Override
    public void saveUsersWords(UserEntity user) {
        if (user.getId() == 1) {
            try {
//                Files.write(Paths.get(PATH_TO_FILE), user.getWords().getBytes());
                Files.write(Paths.get(PATH_TO_FILE + "___" + user.getLoginName()), user.getWords().getBytes());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
