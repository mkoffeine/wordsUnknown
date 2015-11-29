package mkoffeine.wordsunk.utils;

import mkoffeine.wordsunk.entity.UserEntity;

/**
 * Created by Michael on 10.10.2015.
 */
public class MaxValue {
    private static int max;

    public static int getMax() {
        max++;
        return max;
    }

    public static void findAndSetMaxValue(UserEntity... userEntities) {
        max = 0;
        for (UserEntity user : userEntities) {
            if (max < user.getId()) {
                max = user.getId();
            }
        }
        max++;
    }
}
