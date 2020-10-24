package util;

import io.github.xiaoyureed.shopeecommon.util.StringUtils;

/**
 * @author : xiaoyu 775000738@qq.com
 * @since : 2020/10/24
 */
public class StringUtilsTests {

    public static void main(String[] args) {
        testGenChineseNameJianHan();
    }

    static void testGenRandomJanHan() {
        String result = StringUtils.genRandomJianHan(3);
        System.out.println(result);
    }

    static     void testGenChineseNameJianHan() {
        String result = StringUtils.genChineseNameJianHan(true, 3);
        System.out.println(result);
    }
}
