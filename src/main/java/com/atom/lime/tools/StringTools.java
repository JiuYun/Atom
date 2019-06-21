package com.atom.lime.tools;

import lombok.extern.slf4j.Slf4j;

/**
 * @author lime
 */
@Slf4j
public class StringTools {

    public static final StringBuffer BUFFER = new StringBuffer();
    public static final StringBuilder BUILDER = new StringBuilder();

    /**
     * 格式 user_info userInfo
     * 处理字符  得到驼峰命名
     *
     * @param character    原字符
     * @param delCharacter 要去掉的字符 比如 _  -
     * @return
     */
    public static String characterProcessingTf(String character, String delCharacter) {
        String[] characters = character.split(delCharacter);
        StringBuffer sb = new StringBuffer();
        sb.append(characters[0]);
        for (int i = 1, len = characters.length; i < len; i++) {
            characters[i] = firstCharacterToUpperCase(characters[i]);
            sb.append(characters[i]);
        }
        return sb.toString();
    }


    /**
     * 格式 s_user_info  UserInfo
     * 处理字符  得到帕斯卡命名
     *
     * @param character    原字符
     * @param delCharacter 要去掉的字符 比如 _  -
     * @return
     */
    public static String characterProcessingPsk(String character, String delCharacter) {
        String[] characters = character.split(delCharacter);
        StringBuffer sb = new StringBuffer();
        for (int i = 1, len = characters.length; i < len; i++) {
            characters[i] = firstCharacterToUpperCase(characters[i]);
            sb.append(characters[i]);
        }
        return sb.toString();
    }

    public static String firstCharacterToUpperCase(String character) {
        return bufferAppendToString(character.toUpperCase().charAt(0), character.substring(1));
    }


    public static StringBuilder builderAppend(Object... params) {
        BUFFER.delete(0, BUFFER.length());
        for (Object param : params) {
            BUILDER.append(param);
        }
        return BUILDER;
    }

    public static String builderAppendToString(Object... params) {
        return builderAppend(params).toString();
    }

    public static StringBuffer bufferAppend(Object... params) {
        BUFFER.delete(0, BUFFER.length());
        for (Object param : params) {
            BUFFER.append(param);
        }
        return BUFFER;
    }

    public static String bufferAppendToString(Object... params) {
        return bufferAppend(params).toString();
    }

    public static void main(String[] args) {
        log.info(characterProcessingTf("user_info", "_"));
    }
}
