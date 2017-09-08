package com.fullteem.modules.zhenghe.api.utils;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * 类名: AbstractUpload</br> 
 * 包名：com.fullteem.modules.zhenghe.api.utils </br> 
 * 描述: 文件上传抽象类</br>
 * 发布版本号：</br>
 * 开发人员： chenx</br>
 * 创建时间： 2015年12月11日
 */
public abstract class AbstractFileUpload {
	
	/**
	 * 
	 * 方法名: </br>
	 * 详述: 文件保存的路径,子类自己定义路径，父类不管这些</br>
	 * 开发人员：chenx</br>
	 * 创建时间：2015年12月12日</br>
	 * @return
	 */
	public abstract String getPath();
	/**
	 * 
	 * 方法名: </br>
	 * 详述: 允许上传的文件格式，子类自己定义允许上传的文件格式，父类只负责校验，其他不管</br>
	 * 开发人员：chenx</br>
	 * 创建时间：2015年12月12日</br>
	 * @return
	 */
	public abstract Map<String,String> getAllowExtMap();
	
	/**
	 * 
	 * 方法名: </br>
	 * 详述: </br>
	 * 开发人员：chenx</br>
	 * 创建时间：2015年12月11日</br>
	 * @param request
	 * @param file
	 * @return 返回1:file空,2:file类型错误,3:上传出错,url(如果有多个的话，中间以'|'分割)
	 */
	public String uploadFile(HttpServletRequest request,List<MultipartFile> fileList){

		String path = getPath();
		StringBuilder sb = new StringBuilder();
		Map<String, String> allowExtMap = getAllowExtMap();
		for(MultipartFile file:fileList){
			String url = "";
			try {
				if (null == file || file.getSize() == 0) {
					return "1";//文件为空
				}
				String fileName = file.getOriginalFilename();
				// 获取文件后缀
				String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1)
						.toLowerCase();
				if (!allowExtMap.containsKey(fileExt.toLowerCase())) {
					return "2";//文件类型有误
				}
				fileName = DataBaseUtil.getUUID32Code() + "." + fileExt;
				String realPath = request.getSession().getServletContext()
						.getRealPath("/").replaceAll("\\\\", "/")
						+ path;
				File targetFile = new File(realPath + "/", fileName);
				if (!targetFile.getParentFile().exists()) {
					targetFile.getParentFile().mkdirs();
				}
				if (!targetFile.exists()) {
					targetFile.mkdirs();
				}
				// 保存
				file.transferTo(targetFile);
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
				url = path + md5 + "." + fileExt;
				sb.append(url).append("|");
			} catch (Exception e) {
				e.printStackTrace();
				return "3";
			}
		}
		return sb.toString();
	}

}
