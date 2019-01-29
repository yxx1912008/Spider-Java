package cn.luckydeer.spider.common.utils.wechat;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import cn.luckydeer.spider.common.enums.wechat.WeixinMsgType;
import cn.luckydeer.spider.common.model.cat.wechat.WeixinPicAndText;
import cn.luckydeer.spider.common.model.cat.wechat.WeixinPicTextItem;
import cn.luckydeer.spider.common.model.cat.wechat.WeixinTextMsg;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;

/**
 * 消息相关工具类
 * 
 * @author yuanxx
 * @version $Id: WeixinOffAccountUtil.java, v 0.1 2018年9月27日 下午4:45:25 yuanxx Exp $
 */
public class WeixinOffAccountUtil {

    private static final Log   logger            = LogFactory.getLog(WeixinOffAccountUtil.class);

    public final static String characterEncoding = "UTF-8";

    /**
     * 
     * 注解：将请求转换为 xml
     * @param request
     * @return
     * @throws Exception
     * @author yuanxx @date 2018年9月27日
     */
    @SuppressWarnings("unchecked")
    public static Map<String, String> parseXml(HttpServletRequest request) throws Exception {
        Map<String, String> map = new HashMap<String, String>();

        InputStream inputStream = request.getInputStream();
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        Element root = document.getRootElement();
        List<Element> elementList = root.elements();

        for (Element e : elementList) {
            map.put(e.getName(), e.getText());
        }
        inputStream.close();
        inputStream = null;
        return map;
    }

    public static void outWriteText(HttpServletResponse response, String str) {
        writerString(response, "text/plain; charset=utf-8", str);
    }

    private static void writerString(HttpServletResponse response, String type, String str) {
        response.setContentType(type);
        response.setCharacterEncoding(characterEncoding);
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.print(str);
        } catch (IOException e) {
            logger.error("输出失败：" + e);
        } finally {
            out.flush();
            out.close();
        }
    }

    /**
     * 
     * 注解：从 request 中获取 String
     * @param request
     * @param key
     * @return
     * @author yuanxx @date 2018年9月27日
     */
    public static String loadString(HttpServletRequest request, String key) {
        try {
            request.setCharacterEncoding(characterEncoding);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String str = request.getParameter(key);
        if (str == null) {
            return "";
        } else {
            return str.replaceAll("[']", "");
        }
    }

    /**
    * 发送消息xml
    * @param toUserName
    * @param fromUserName
    * @param content
    * @return
    */
    public static String messageText(String fromUserName, String toUserName, String content) {
        WeixinTextMsg text = new WeixinTextMsg();
        text.setToUserName(fromUserName);
        text.setFromUserName(toUserName);
        text.setCreateTime(new Date().getTime());
        text.setMsgType(WeixinMsgType.TEXT.getCode());
        text.setContent(content);
        String result = textMessageToXml(text);
        return result;
    }

    /**
     * 
     * 注解：将文本消息转换为xml
     * @param textMessage
     * @return
     * @author yuanxx @date 2018年9月28日
     */
    public static String textMessageToXml(WeixinTextMsg textMessage) {
        xstream.alias("xml", textMessage.getClass());
        return xstream.toXML(textMessage);
    }

    /**
     * 
     * 注解：将图文消息转换为 xml
     * @param textMessage
     * @return
     * @author yuanxx @date 2018年9月28日
     */
    public static String picAndTextMessageToXml(WeixinPicAndText picAndText) {
        xstream.processAnnotations(picAndText.getClass());
        return xstream.toXML(picAndText);
    }

    /**
     * 
     * 注解：微信发送图文消息
     * @param fromUserName
     * @param toUserName
     * @param content
     * @return
     * @author yuanxx @date 2018年9月28日
     */
    public static String sendTextAndPic(String fromUserName, String toUserName,
                                        List<WeixinPicTextItem> articles) {
        WeixinPicAndText picAndText = new WeixinPicAndText();
        picAndText.setArticleCount(articles.size());
        picAndText.setArticles(articles);
        picAndText.setCreateTime(new Date().getTime());
        picAndText.setFromUserName(toUserName);
        picAndText.setToUserName(fromUserName);
        picAndText.setMsgType(WeixinMsgType.NEWS.getCode());
        String result = picAndTextMessageToXml(picAndText);
        return result;
    }

    private static XStream xstream = new XStream(new XppDriver() {
                                       public HierarchicalStreamWriter createWriter(Writer out) {
                                           return new PrettyPrintWriter(out) {

                                               boolean cdata = true;

                                               @SuppressWarnings("rawtypes")
                                               public void startNode(String name, Class clazz) {
                                                   super.startNode(name, clazz);
                                               }

                                               protected void writeText(QuickWriter writer,
                                                                        String text) {
                                                   if (cdata) {
                                                       writer.write("<![CDATA[");
                                                       writer.write(text);
                                                       writer.write("]]>");

                                                   } else {
                                                       writer.write(text);
                                                   }
                                               }
                                           };
                                       }
                                   });
}
