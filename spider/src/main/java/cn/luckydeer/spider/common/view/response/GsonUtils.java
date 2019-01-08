package cn.luckydeer.spider.common.view.response;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

/**
 * <p class="detail">
 * 功能： mvc给客户端的格式化后的json
 * </p>
 * 
 * @ClassName: GsonUtils
 * @version V1.0
 * @date 2016年6月22日
 * @author panwuhai Copyright 2016 icaomei.com, Inc. All rights reserved
 */
public class GsonUtils {

    private static final Log logger = LogFactory.getLog("BUSINESS-WARNING-LOG");

    private static Gson      gson;

    static {
        gson = new GsonBuilder().serializeNulls().disableHtmlEscaping()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .registerTypeAdapter(String.class, new TypeAdapter<String>() {
                @Override
                public String read(JsonReader reader) throws IOException {
                    return reader.nextString();
                }

                @Override
                public void write(JsonWriter writer, String str) throws IOException {
                    writer.value(str == null ? "" : str);
                }
            }).create();
    }

    // 无参构造器不容许new
    private GsonUtils() {

    }

    public static Gson getGson() {
        if (null == gson) {
            synchronized (GsonUtils.class) {
                if (null == gson) {
                    logger.warn("重新初始化gson");
                    gson = new GsonBuilder().serializeNulls().disableHtmlEscaping()
                        .setDateFormat("yyyy-MM-dd HH:mm:ss")
                        .registerTypeAdapter(String.class, new TypeAdapter<String>() {
                            @Override
                            public String read(JsonReader reader) throws IOException {
                                return reader.nextString();
                            }

                            @Override
                            public void write(JsonWriter writer, String str) throws IOException {
                                writer.value(str == null ? "" : str);
                            }
                        }).create();
                }
            }
        }
        return gson;

    }
}
