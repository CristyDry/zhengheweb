package com.fullteem.modules.zhenghe.api.utils;

import com.fullteem.common.utils.SpringContextHolder;
import com.fullteem.modules.zhenghe.entity.ZhengheCommonImage;
import com.fullteem.modules.zhenghe.service.ZhengheCommonImageService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by LeWis on 2017/10/13.
 */
public class ProductPicUtils {

    static ZhengheCommonImageService commonImageService;

    public static String server;
    public static String getImageServer(){
        if(server!=null){
           return server;
        }
        Resource resource = new ClassPathResource("/fullteem.properties");
        try {
            Properties props = PropertiesLoaderUtils.loadProperties(resource);
            String view = props.getProperty("zhenghe.img.server", "");
            server = view;
            return view;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";

    }
    //设置商品图
    public static String getPic(String id){
        List<String> images = getImage(id);
        if(images!=null){
            if(images.size()==0){
                return "";
            }else{
                return images.get(0);
            }
        }else{
            return "";
        }
    }

    /**
     *
     * 方法名: </br>
     * 详述: </br>
     * 开发人员：chenx</br>
     * 创建时间：2016年1月14日</br>
     * @return
     */
    public static List<String> getImage(String id){

        //HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String basePath = getImageServer();//request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
        if(commonImageService == null){
            commonImageService =  SpringContextHolder.getBean(ZhengheCommonImageService.class);
        }
        //查询图片
        ZhengheCommonImage image = commonImageService.findByRelevanceId(id);

        if(image==null)
            return null;

        List<String> images = new ArrayList<String>();
        Class<? extends ZhengheCommonImage> clazz = image.getClass();
        try {
            for(int i=0;i<9;i++){
                String str = "getImage"+(i+1);
                Method method = clazz.getDeclaredMethod(str);
                Object obj = method.invoke(image);
                if(obj!=null&&!StringUtils.isBlank(obj.toString())){
                    String url = basePath+obj.toString();
                    images.add(url);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return images;
    }
}
