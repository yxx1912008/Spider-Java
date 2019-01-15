package cn.luckydeer.spider.common.model.tuling;

import java.io.Serializable;

/**
 * 
 * 
 * @author yuanxx
 * @version $Id: TuPerception.java, v 0.1 2019年1月15日 上午10:13:01 yuanxx Exp $
 */
public class TuPerception implements Serializable {

    /**  */
    private static final long serialVersionUID = -1580523130104379369L;

    private TuInputText       inputText;

    public TuInputText getInputText() {
        return inputText;
    }

    public void setInputText(TuInputText inputText) {
        this.inputText = inputText;
    }

    public TuPerception(TuInputText inputText) {
        super();
        this.inputText = inputText;
    }

}
