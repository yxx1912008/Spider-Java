package cn.luckydeer.spider.common.utils.valid;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

/**
 *  * 通用的格式校验工具类.
 * <p>
 * <b>注意：在该工具类中添加校验规则时，请确保和前端js的校验保持一致.</b>
 * <p>
 * @author yuanxx
 * @version $Id: ValidateUtil.java, v 0.1 2018年11月15日 下午5:15:32 yuanxx Exp $
 */
public class ValidateUtil {
    /** 手机格式的正则表达式. */
    public final static String REGEX_MOBILE              = "^(([1][234578]{1}\\d{9})|([1](98|99|66)\\d{8}))$";

    /**12开头不是正规手机格式，为便于测试手机账号第二位为2; 手机格式的正则表达式. */
    public final static String DEBUG_REGEX_MOBILE        = "^([1][2]\\d{9})$";

    /** Email格式的正则表达式. */
    public final static String REGEX_EMAIL               = "^([a-zA-Z0-9_\\.\\-\\+])+\\@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{2,4})+$";

    /** 支付宝登录号的正则表达式. */
    public final static String REGEX_LOGON_ID            = "^(([a-zA-Z0-9_\\.\\-\\+])+\\@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{2,4})+)|([1][34578]{1}\\d{9})|([1](98|99|66)\\d{8})$";

    /** 手机6位数字校验码. */
    public final static String REGUX_VALIDATE_CODE       = "^\\d{6}$";

    /** 16位纯数字流水号. */
    public final static String REGEX_VALIDATE_BIZ_NO     = "^\\d{16}$";

    /** 4位图片校验码. */
    public final static String REGEX_PIC_CODE            = "^\\d{4}$";

    /** 电话号码，只能由数字和"-"组成,6到20位. */
    public final static String REGEX_PHONE               = "^[0-9,-]{6,20}$";

    /** 邮政编码,只能6位数字. */
    public final static String POST                      = "^\\d{1,6}$";

    /** 用户姓名,只能由中文汉字或大写英文字母构成. */
    public final static String REGEX_USER_NAME           = "^[^0-9a-z,;:，；：\\|。.’']+\\s?\\.?[^0-9a-z,;:，\\|；：。.’']+$";

    /** 纯数字. */
    public final static String NUM                       = "^(\\d)+$";

    /** 金额，类似于-135,342,12.954. */
    public final static String REGEX_VALIDATE_MONEY      = "^[+-]?\\d+(,\\d{3})*(\\.\\d+)?$";

    /** 正金额，类似于-135,342,12.954. */
    public final static String REGEX_VALIDATE_PLUS_MONEY = "^[+]?\\d+(,\\d{3})*(\\.\\d+)?$";

    /** 手机动态口令，可以不输入，输入长度只能数字长度6位. */
    public final static String MOBILE_PROTECT_ACK_CODE   = "^\\d{0}|\\d{6}$";

    public final static String DOUBLE                    = "^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$";
    /** 
     * <p class="detail">
     * 功能：前端输入的金额校验 小数点后二位
     * </p>
     * @Fields VIEW_MONEY 
     * @author panwuhai
     * @date 2016年5月12日
     */
    public final static String VIEW_MONEY                = "^\\d+(\\.\\d{0,2})?$";

    // 纯字母
    public final static String LETTER                    = "^[A-Za-z]+$";

    // 数字和字母的组合
    public final static String REGEX_LETTER_AND_NUM      = "^[a-zA-Z0-9ａ-ｚＡ-Ｚ０-９]+$";

    /** 金额小数点  */
    public static final String MONEY_SUFFIX_SPLIT_MARK   = ".";

    public final static String REGEX_CHINESE             = "[\u4e00-\u9fa5]";

    /** 短信模板变量的正则表达式  */
    public final static String SMS_VALUE                 = "(\\{[1-9]\\})";

    /**
     * 校验是否是url地址
     */
    public static final String URL                       = "http(s){0,1}://(([a-zA-z0-9]|-){1,}\\.){1,}[a-zA-z0-9]{1,}-*";

    /** <p class="detail">
    * 功能：校验小数点后面为0
    * </p>
    * @author panwuhai
    * @date 2016年5月12日 
    * @param money
    * @return    
    */
    public static boolean validsZeroMallNum(String money) {
        String[] moneys = StringUtils.split(money, MONEY_SUFFIX_SPLIT_MARK);
        if (moneys.length > 1) {
            int smallNum = Integer.parseInt(moneys[1]);
            if (smallNum != 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNum(String src) {
        // 无效的输入，直接返回false
        if (StringUtils.isBlank(src)) {
            return false;
        }

        Pattern p = Pattern.compile(NUM);
        Matcher pm = p.matcher(src);
        return pm.matches();
    }

    /** <p class="detail">
    * 功能：验证页面输入的金额 小数点后二位
    * </p>
    * @author panwuhai
    * @date 2016年5月12日 
    * @param src
    * @return    
    */
    public static boolean isValidViewMoney(String src) {
        // 无效的输入，直接返回false
        if (StringUtils.isBlank(src)) {
            return false;
        }

        Pattern p = Pattern.compile(VIEW_MONEY);
        Matcher pm = p.matcher(src);
        return pm.matches();
    }

    /**
     * 判断输入的字符串是否和传入的正则是否匹配.
     * 
     * @param src
     *            待判断的输入字符串
     * @param reg
     *            正则表达式
     * @return True:输入的字符串是否和传入的正则匹配 False:输入的字符串是否和传入的正则不匹配
     */
    public static boolean isValidReg(String src, String reg) {
        // 无效的输入，直接返回false
        if (StringUtils.isBlank(src)) {
            return false;
        }
        Pattern p = Pattern.compile(reg);
        Matcher pm = p.matcher(src);
        return pm.matches();
    }

    /**
     * 判断输入的字符串是否是合法用户姓名,长度未限制.
     * 
     * @param src
     *            待判断的输入字符串
     * @return True:是合法的用户姓名;False:不是合法的用户姓名
     */
    public static boolean isValidUserName(String src) {
        // 无效的输入，直接返回false
        if (StringUtils.isBlank(src)) {
            return false;
        }
        Pattern p = Pattern.compile(REGEX_USER_NAME);
        Matcher pm = p.matcher(src);
        return pm.matches();
    }

    /**
     * 判断输入的字符串是否是合法email格式,长度未限制.
     * 
     * @param src
     *            待判断的输入字符串
     * @return True:是合法的email;False:不是合法的email
     */
    public static boolean isValidEmail(String src) {
        // 无效的输入，直接返回false
        if (StringUtils.isBlank(src)) {
            return false;
        }
        Pattern p = Pattern.compile(REGEX_EMAIL);
        Matcher pm = p.matcher(src);
        return pm.matches();
    }

    /**
     * 判断输入的字符串是否是手机号码格式.
     * 
     * @param src
     *            待判断的输入字符串
     * @return True:是合法的手机号码;False:不是合法的手机号码
     */
    public static boolean isValidMobile(String src) {
        // 无效的输入，直接返回false
        if (StringUtils.isBlank(src)) {
            return false;
        }
        Pattern p = Pattern.compile(REGEX_MOBILE);
        Matcher pm = p.matcher(src);

        return pm.matches();
    }

    /**
     * 判断输入的字符串是否是手机号码格式.
     * 
     * @param src
     *            待判断的输入字符串
     * @return True:是合法的手机号码;False:不是合法的手机号码
     */
    public static boolean isDebugValidMobile(String src) {
        // 无效的输入，直接返回false
        if (StringUtils.isBlank(src)) {
            return false;
        }
        Pattern p = Pattern.compile(DEBUG_REGEX_MOBILE);
        Matcher pm = p.matcher(src);

        return pm.matches();
    }

    /**
     * 判断输入的字符串是否是合法的固话.
     * 
     * @param src
     *            待判断的输入字符串
     * @return True:合法;False:不合法
     */
    public static boolean isValidPhone(String src) {
        // 无效的输入，直接返回false
        if (StringUtils.isBlank(src)) {
            return false;
        }
        Pattern p = Pattern.compile(REGEX_PHONE);
        Matcher pm = p.matcher(src);
        return pm.matches();
    }

    /**
     * 判断输入的字符串是否是合法的支付宝登录号.
     * <p>
     * 支付宝登录号有两种格式。其一是手机号码；其二是Email（长度未限制）.
     * 
     * @param src
     *            待判断的输入字符串
     * @return True:是合法的登录号;False:不是合法的登录号
     */
    public static boolean isVliadAlipayLogonId(String src) {
        // 无效的输入，直接返回false
        if (StringUtils.isBlank(src)) {
            return false;
        }
        Pattern p = Pattern.compile(REGEX_LOGON_ID);
        Matcher pm = p.matcher(src);
        return pm.matches();
    }

    /**
     * 判断是否是合法的流水号，标准:流水号为16位纯数字.
     * 
     * @param src
     *            待判断的输入字符串
     * @return true是合法的流水号;false表示不是合法的流水号
     */
    public static boolean isValidBizNo(String src) {
        // 输入为空则直接返回false
        if (StringUtils.isBlank(src)) {
            return false;
        }

        Pattern p = Pattern.compile(REGEX_VALIDATE_BIZ_NO);
        Matcher pm = p.matcher(src);
        return pm.matches();
    }

    /**
     * 判断是否是合法的金额，标准:<br/>
     * <ul>
     * <li>不为空</li>
     * <li>允许3个连续数字用,分隔</li>
     * <li>允许小数点</li>
     * <li>小数位允许超过多为的数字(多余2位将四舍五入)</li>
     * <li>允许正负金额</li>
     * </ul>
     * 
     * @param src
     *            待判断的输入字符串
     * @return true表示是合法的金额;false表示不是合法的金额
     */
    public static boolean isValidMoney(String src) {
        // 无效的输入，直接返回false
        if (StringUtils.isBlank(src)) {
            return false;
        }

        Pattern p = Pattern.compile(REGEX_VALIDATE_MONEY);
        Matcher pm = p.matcher(src);
        return pm.matches();
    }

    /**
     * 判断是否是合法的正金额，标准:<br/>
     * <ul>
     * <li>不为空</li>
     * <li>允许3个连续数字用,分隔</li>
     * <li>允许小数点</li>
     * <li>小数位允许超过多为的数字(多余2位将四舍五入)</li>
     * <li>允许正金额</li>
     * </ul>
     * 
     * @param src
     *            待判断的输入字符串
     * @return true表示是合法的金额;false表示不是合法的金额
     */
    public static boolean isValidPlusMoney(String src) {
        // 无效的输入，直接返回false
        if (StringUtils.isBlank(src)) {
            return false;
        }

        Pattern p = Pattern.compile(REGEX_VALIDATE_PLUS_MONEY);
        Matcher pm = p.matcher(src);
        return pm.matches();
    }

    /**
     * 判断是否是纯字母组成的字符.
     * 
     * @param src
     *            你输入的字符串
     * @return true表示是纯字母 false表示不是纯字母
     */
    public static boolean isValidLetter(String src) {
        // 无效的输入 直接返回false
        if (StringUtils.isBlank(src)) {
            return false;
        }
        Pattern p = Pattern.compile(LETTER);
        Matcher pm = p.matcher(src.trim());
        return pm.matches();
    }

    /**
     * 判断输入的字符串是否是邮政编码格式.
     * 
     * @param src
     *            待判断的输入字符串
     * @return True:是合法的邮政编码;False:不是合法的邮政编码
     */
    public static boolean isValidPost(String src) {
        // 无效的输入，直接返回false
        if (StringUtils.isBlank(src)) {
            return false;
        }
        Pattern p = Pattern.compile(POST);
        Matcher pm = p.matcher(src);
        return pm.matches();
    }

    /**
     * 校验是否是合法的url地址
     * @param url
     * @return
     */
    public static boolean isUrl(String url) {
        // 无效的输入，直接返回false
        if (StringUtils.isBlank(url)) {
            return false;
        }
        Pattern pattern = Pattern.compile(URL);
        Matcher matcher = pattern.matcher(url);
        if (matcher.find()) {
            return true;
        }
        return false;
    }

    /**
     * 校验是否是double
     * @param src
     * @return
     */
    public static boolean isDouble(String src) {
        // 无效的输入，直接返回false
        if (StringUtils.isBlank(src)) {
            return false;
        }

        Pattern p = Pattern.compile(DOUBLE);
        Matcher pm = p.matcher(src);
        return pm.matches();
    }

    public static boolean isContainChinese(String str) {

        Pattern p = Pattern.compile(REGEX_CHINESE);
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }

    public static int getValueCont(String str) {

        Pattern p = Pattern.compile(SMS_VALUE, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(str);
        int count = 0;
        while (m.find()) {
            count++;
        }

        return count;
    }

}
