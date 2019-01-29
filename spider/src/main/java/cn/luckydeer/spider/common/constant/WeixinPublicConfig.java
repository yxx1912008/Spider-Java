package cn.luckydeer.spider.common.constant;

/**
 * 微信公众号设置
 * 
 * @author yuanxx
 * @version $Id: WeixinPublicConfig.java, v 0.1 2018年9月27日 下午5:12:27 yuanxx Exp $
 */
public interface WeixinPublicConfig {

    //公众号 app_id
    public static final String APP_ID               = "wx3abc1e8ab4969a25";

    //Token 
    public static final String TOKEN                = "8ff953dd97c4405234a04291dee39e0b";

    //自动关注回复
    public static final String WEIXIN_PUBLIC_RETURN = "/:heart欢迎关注购物猫 /:rose\r\n/:heart查找优惠券，请在前面加 “优惠券“\r\n/:heart例如：想找耳机优惠券，输入： 优惠券 耳机 \r\n/:heart指定商品查找优惠券，先复制淘宝商品完整标题 \r\n/:heart输入 :优惠券 淘宝标题，然后发给我\r\n/:coffee查找电影，请在电影名前加“电影”\r\n/:coffee要求管理员添加电影 请在电影名前加“添加”";

    //微信 APP_SECRET
    public static final String APP_SECRET           = "86e3f561593bda04257ed4d0084d5fe2";

}
