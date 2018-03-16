package ThoughtWorks.tools;

import java.util.regex.Pattern;

/**工具类，检查是否合法*/
public class Checking {
    //Test
    /*
        System.out.println(Checking.isValidId("fasdfge#$d544"));//false
        System.out.println(Checking.isValidId("fasdfged544"));//true
        System.out.println(Checking.isValidId("544"));//true
        System.out.println(Checking.isValidId("f"));//true
        System.out.println(Checking.isValidId("")); //false*/
    /**检查一个无人机ID是否合法
     * 无人机ID应由数字和字母组成
     * @param droneId 待测的无人机ID*/
    public static boolean isValidId(String droneId) {
        //TODO 正则
        /*for (char i : droneId.toCharArray()) {
            if (!((i >= '0' && i <= '9')
                    || (i >= 'a' && i <= 'z')
                    || (i >= 'A' && i <= 'Z'))) {
                return false;
            }
        }
        return true;*/

        String pattern = "(\\d|[a-z]|[A-Z])+";

        return Pattern.matches(pattern, droneId);
    }
}
