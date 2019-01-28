package cn.luckydeer.spider.common.utils.email;

import java.io.File;
import java.io.Serializable;

/**
 * 发送email参数模型
 * 
 * @author yuanxx
 * @version $Id: EmailOrder.java, v 0.1 2018年9月27日 上午11:04:11 yuanxx Exp $
 */
public class EmailOrder extends EmailConfig implements Serializable {

    /**  */
    private static final long serialVersionUID = 1043661939101350578L;

    //标题
    private String            title;

    //正文内容(html格式)
    private String            content;

    //收件人群邮箱地址:  "***@qq.com"
    private String[]          receives;

    /** 附件可以为空 */
    private File              file;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String[] getReceives() {
        return receives;
    }

    public void setReceives(String[] receives) {
        this.receives = receives;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

}
