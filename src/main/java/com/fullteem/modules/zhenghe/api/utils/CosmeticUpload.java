package com.fullteem.modules.zhenghe.api.utils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.coobird.thumbnailator.Thumbnails;
import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * 类名: CosmeticUpload</br> 
 * 包名：com.fullteem.modules.cosmetic.api.utils </br> 
 * 描述: 文件上传</br>
 * 发布版本号：</br>
 * 开发人员： 朱恋青</br>
 * 创建时间： 2015年11月2日
 */
public class CosmeticUpload {
	public static final String USERAVATAR = "/zhengheImage/userAvatar/";//用户头像
	public static final String PRODUCTIMAGE = "/zhengheImage/productImage/";//商品提交检测图片
	public static final String ELEMENTIMAGE = "/zhengheImage/elementImage/";//成分提交检测图片
	public static final String EXPERIENCEIMAGE = "/zhengheImage/experienceImage/";//心得相关图片
	
	/**
	 * 
	 * 方法名: </br>
	 * 详述:上传图片（jpg、png、gif、bmp） </br>
	 * 开发人员：朱恋青</br>
	 * 创建时间：2015年11月2日</br>
	 * @param request
	 * @param type，1为上传用户头像，2为上传商品相关图片，3为上传成分相关图片，4为心得相关图片
	 * @param imgFile 传MultipartFile类型的file
	 * @return 返回1：file空，2：file类型错误，3：上传出错，url
	 */
	public static String uploadFile(HttpServletRequest request,String type,MultipartFile imgFile){
		
		
		Map<String, String> allowExtMap = new HashMap<String, String>(3);
		allowExtMap.put("jpg", null);
		allowExtMap.put("png", null);
		allowExtMap.put("gif", null);
		allowExtMap.put("bmp", null);
		String pic_path=USERAVATAR;
		if(type.equals("2")){
			pic_path=PRODUCTIMAGE;
		}else if(type.equals("3")){
			pic_path=ELEMENTIMAGE;
		}else if(type.equals("4")){
			pic_path=EXPERIENCEIMAGE;
		}
		
		String url = "";
		try {
			if (null == imgFile || imgFile.getSize() == 0) {
				return "1";//文件为空
			}
			String fileName = imgFile.getOriginalFilename();
			// 获取文件后缀
			String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1)
					.toLowerCase();
			if (!allowExtMap.containsKey(fileExt.toLowerCase())) {
				return "2";//文件类型有误
			}
			fileName = DataBaseUtil.getUUID32Code() + "." + fileExt;
			String realPath = request.getSession().getServletContext()
					.getRealPath("/").replaceAll("\\\\", "/")
					+ pic_path;
			File targetFile = new File(realPath + "/", fileName);
			if (!targetFile.getParentFile().exists()) {
				targetFile.getParentFile().mkdirs();
			}
			if (!targetFile.exists()) {
				targetFile.createNewFile();
			}
			// 保存
			Thumbnails.of(imgFile.getInputStream()).size(320,320).outputQuality(1f).toFile(targetFile);

			// 保存
			//imgFile.transferTo(targetFile);
			// 使用文件md5码检查是否有重复文件存在
			String md5 = GetBigFileMD5.getMD5(targetFile);
			File findFile = new File(realPath + "/" + md5 + "." + fileExt);
			if (findFile.exists()) {// 存在则删除上传的文件
				targetFile.delete();
			} else {
				// 不存在则重命名
				targetFile.renameTo(new File(realPath + "/" + md5 + "."
						+ fileExt));
			}
			url = pic_path + md5 + "." + fileExt;
		} catch (Exception e) {
			e.printStackTrace();
			return "3";
		}
		return url;
	}
}
