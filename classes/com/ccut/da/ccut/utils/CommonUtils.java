package ccut.utils;

import ccut.Exception.CustomizeException;
import ccut.common.ErrorEnum;
import ccut.model.pojo.User;
import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;


public class CommonUtils {
    public static String md5(String password) {
        return DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));
    }

    public static User getSafetyUser(User user) {
        User user1 = new User();
        user1.setUsername(user.getUsername());
        user1.setAvatar(user.getAvatar());
        user1.setSex(user.getSex());
        user1.setPhoneNumber(user.getPhoneNumber());
        user1.setCreateDate(user.getCreateDate());
        user1.setUpdateDate(user.getUpdateDate());
        return user1;
    }


    public static String geCode() {
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            stringBuilder.append(random.nextInt(10));
        }
        return stringBuilder.toString();
    }


    public static void checkPhone(String phone) {
        if (StringUtils.isEmpty(phone)) {
            throw new CustomizeException(ErrorEnum.EMPTY_PHONE_FORMAT);
        }

        Pattern compile = Pattern.compile("^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$");
        Matcher matcher = compile.matcher(phone);

        if (!matcher.matches()) {
            throw new CustomizeException(ErrorEnum.INCORRECT_CELL_PHONE_FORMAT);
        }
    }


    public static void checkCreditCode(String creditCode) {
        if (StringUtils.isEmpty(creditCode)) {
            throw new CustomizeException(ErrorEnum.PARAMETER_EXCEPTION_NULL);
        }

        Pattern compile = Pattern.compile("[0-9A-HJ-NPQRTUWXY]{2}\\d{6}[0-9A-HJ-NPQRTUWXY]{10}");
        Matcher matcher = compile.matcher(creditCode);

        if (!matcher.matches())
            throw new CustomizeException(ErrorEnum.INCORRECT_CELL_CreditCode_FORMAT);
    }
}


