package cn.luckydeer.spider.common.model.tuling;

import java.io.Serializable;

/**
 * 
 * 
 * @author yuanxx
 * @version $Id: TuInputText.java, v 0.1 2019年1月15日 上午10:12:31 yuanxx Exp $
 */
public class TuInputText implements Serializable {

    /**  */
    private static final long serialVersionUID = -2899516970307441101L;

    private String            text;

    public TuInputText(String text) {
        super();
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
