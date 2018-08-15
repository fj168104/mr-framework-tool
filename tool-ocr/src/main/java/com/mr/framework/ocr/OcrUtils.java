package com.mr.framework.ocr;

import com.mr.framework.core.io.FileUtil;
import com.mr.framework.core.lang.Console;
import com.mr.framework.core.util.StrUtil;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.text.PDFTextStripper;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

import java.util.ArrayList;
import java.util.List;

public class OcrUtils {

	private String downloadDir;

//	private static Log log = LogFactory.get();

	public OcrUtils() {
		this.downloadDir = System.getProperty("java.io.tmpdir");
	}

	public OcrUtils(String downloadDir) {

		if (StrUtil.isEmpty(downloadDir)) {
			this.downloadDir = System.getProperty("java.io.tmpdir");
		}
		this.downloadDir = downloadDir;


	}


	/**
	 * 从img识别文本内容并返回，包含pdf扫描图片识别
	 *
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	public String getTextFromImg(String fileName) throws Exception {
		return recognizeTexts(image2Dir(fileName));
	}

	/**
	 * 解析的dirName下的所有图片
	 * dirName  目录名 = 下载的文件所在目录 + 文件名
	 * 如：下载文件名为 test.pdf,下载的目录为 /home/fengjiang/Documents,
	 * 则新建目录test, dirName = /home/fengjiang/Documents/test, 里面为 test.pdf 转成的若干图片，如0.png, 1.png
	 *
	 * @param dirName
	 */
	public String recognizeTexts(String dirName) throws Exception {
		File testDataDir = new File(dirName);
		//listFiles()方法是返回某个目录下所有文件和目录的绝对路径，返回的是File数组
		File[] files = testDataDir.listFiles();
		int imgCount = files.length;
//		log.info("tessdata目录下共有 " + imgCount + " 个文件/文件夹");
		//解析image
		StringBuilder sbs = new StringBuilder();
		testDataDir.listFiles();
		for (int i = imgCount - 1; i >= 0; i--) {
			sbs.append(recognizeText(files[i]));
		}
		//删除文件夹 dirName
		FileUtil.del(dirName);

		return sbs.toString();
	}

	/**
	 * @param filePath
	 * @Title: getTextFromPdf
	 * @Description: 读取pdf文件内容
	 * @return: 读出的pdf的内容
	 */
	public String getTextFromPdf(String filePath) throws Exception {

		String textFile = readPdf(filePath);
		String res = FileUtil.readString(textFile, "utf-8");
		FileUtil.del(textFile);
		FileUtil.del(downloadDir + File.separator + filePath);
		return res;
	}

	private final static String LANG_OPTION = "-l";

	//line.separator 行分隔符
	private final static String EOL = System.getProperty("line.separator");

	//tesseract文件放在项目文件里了
	private final static String tesseractPath = new File(".").getAbsolutePath();

	/**
	 * 此方法功能：识别图片中的文字并返回到指定txt文件中
	 *
	 * @param image 输入一张图片（这里放在了项目目录）
	 */
	public String recognizeText(File image) throws Exception {

		File outputfile = new File(image.getParentFile(), "output");//输出文件的保存目录

		StringBuffer strB = new StringBuffer();
		List<String> cmd = new ArrayList<String>();//数组各个位置存放东西，如[tesseract.exe的路径，识别的图像，输出文件名，命令选项-l，语言选择]

		if (System.getProperty("os.name").toLowerCase().startsWith("win")) {
			cmd.add(tesseractPath + "\\tesseract");
		} else {
			cmd.add("tesseract");
		}

		cmd.add("");
		cmd.add(outputfile.getName());
		cmd.add(LANG_OPTION);
		cmd.add("chi_sim");
//        cmd.add("eng");
		cmd.set(1, image.getName());//把cmd数组中的第二个位置放置图片
//		log.info("cmd数组:" + cmd);

		ProcessBuilder pb = new ProcessBuilder();//创建有个进程生成器实例  深入研究 http://blog.51cto.com/lavasoft/15662
		pb.directory(image.getParentFile());//设置此进程生成器的工作目录
//		log.info("\n识别的图片名为：" + image.getName());
		pb.command(cmd);// 设置此进程生成器的操作系统程序和参数
		pb.redirectErrorStream(true);
//      前面都是在为组装命令做准备:tesseract.exe 1.jpg 1 -l chi_sim,然后执行命令：Runtime.getRuntime().exec("tesseract.exe 1.jpg 1 -l chi_sim");
		Process process = pb.start();

//		log.info(cmd.toString());//E:\SoftWare_List_D\Tesseract-OCR\下执行命令[tesseract 2.jpg output -l chi_sim]

		int w = process.waitFor();
//		log.info("w的值：" + w);
		if (w == 0) {
			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(outputfile.getAbsolutePath() + ".txt"), "utf-8"));
			String str;
			while ((str = in.readLine()) != null) {
//				log.info(str);
				strB.append(str).append(EOL);
			}
			in.close();
		} else {
			String msg;
			switch (w) {
				case 1:
					msg = "Errors accessing files. There may be spaces in your image's filename.";
					break;
				case 29:
					msg = "Cannot recognize the image or its selected region.";
					break;
				case 31:
					msg = "Unsupported image format.";
					break;
				default:
					msg = "Errors occurred.";
			}
			throw new RuntimeException(msg);
		}
		new File(outputfile.getAbsolutePath() + ".txt").delete();
//        return strB.toString().replaceAll("\\s*","");
		return strB.toString();
	}

	/**
	 * @param fileName 下载的文件名,不是全路径名
	 *                 将文件移到文件夹内，并改名.
	 * @return
	 */
	public String image2Dir(String fileName) {
		return image2Dir(fileName, true);
	}

	/**
	 * @param fileName 下载的文件名,不是全路径名
	 *                 将文件移到文件夹内，并改名.
	 * @return
	 */
	public String image2Dir(String fileName, boolean needDelete) {
		String entirePathName = downloadDir + File.separator + fileName;
		String dirs[] = fileName.split("\\.");
		File dirFile = new File(downloadDir + File.separator + dirs[0]);

		FileUtil.mkdir(dirFile);
		if (dirs[1].equalsIgnoreCase("pdf")) {
			if (needDelete) {
				renameTo(entirePathName, dirFile + File.separator + fileName);
				pdf2image(dirFile + File.separator + fileName);
			} else {
				FileUtil.copy(entirePathName, dirFile + File.separator + fileName, true);
				pdf2image(dirFile + File.separator + fileName);
			}
		} else {
			if (needDelete) {
				renameTo(entirePathName, dirFile + File.separator + "0." + dirs[1]);
			} else {
				FileUtil.copy(entirePathName, dirFile + File.separator + "0." + dirs[1], true);
			}
		}

		return dirFile.getAbsolutePath();
	}

	/**
	 * 将pdf转化为png格式的image
	 *
	 * @param pdfName
	 */

	public void pdf2image(String pdfName) {
		pdf2image(pdfName, true);
	}

	/**
	 * 将pdf转化为png格式的image
	 *
	 * @param needDelete 是否需要删除原pdf文件
	 * @param pdfName
	 */
	public void pdf2image(String pdfName, boolean needDelete) {
		File file = new File(pdfName);
		try {
			PDDocument doc = PDDocument.load(file);
			PDFRenderer renderer = new PDFRenderer(doc);
			int pageCount = doc.getNumberOfPages();
			for (int i = 0; i < pageCount; i++) {
				// 方式1,第二个参数是设置缩放比(即像素)
				BufferedImage image = renderer.renderImageWithDPI(i, 296);
				// 方式2,第二个参数是设置缩放比(即像素)
				// BufferedImage image = renderer.renderImage(i, 2.5f);
				ImageIO.write(image, "PNG", new File(file.getParentFile(), i + ".png"));
			}
			if (needDelete) {
				file.delete();
			}
			doc.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 移动文件
	 *
	 * @param src
	 * @param to
	 */
	public void renameTo(String src, String to) {
		File file = new File(src);   //指定文件名及路径
		file.renameTo(new File(to));   //改名
	}


	/**
	 * @param file 原文件名字
	 * @return 生成的文本文件名字
	 * @throws Exception
	 */
	public String readPdf(String file) throws Exception {
		// 是否排序
		boolean sort = false;
		String tFile = downloadDir + File.separator + file;
		// pdf文件名
		File pdfFile = new File(tFile);
		// 输入文本文件名称
		String textFile = null;
		// 编码方式
		String encoding = "UTF-8";
		// 开始提取页数
		int startPage = 1;
		// 结束提取页数
		int endPage = Integer.MAX_VALUE;
		// 文件输入流，生成文本文件
		Writer output = null;
		// 内存中存储的PDF Document
		PDDocument document = null;
		try {
			document = PDDocument.load(pdfFile);
			if (pdfFile.length() > 4) {
				textFile = tFile.substring(0, tFile.length() - 4)
						+ ".txt";
			}
			// 文件输入流，写入文件倒textFile
			output = new OutputStreamWriter(new FileOutputStream(textFile),
					encoding);
			// PDFTextStripper来提取文本
			PDFTextStripper stripper = null;
			stripper = new PDFTextStripper();
			// 设置是否排序
			stripper.setSortByPosition(sort);
			// 设置起始页
			stripper.setStartPage(startPage);
			// 设置结束页
			stripper.setEndPage(endPage);
			// 调用PDFTextStripper的writeText提取并输出文本
			stripper.writeText(document, output);
		} finally {
			if (output != null) {
				// 关闭输出流
				output.close();
			}
			if (document != null) {
				// 关闭PDF Document
				document.close();
			}
		}
		return textFile;
	}

	public static void main(String[] s) throws Exception {
		OcrUtils ocrUtils = new OcrUtils("/home/fengjiang/Downloads");
		Console.log(ocrUtils.image2Dir("2018016.pdf", false));

//		System.out.println(ocrUtils.getTextFromImg("2055556191-3.jpg"));

//		ocrUtils.pdf2image("/home/fengjiang/Documents/P020180302563551437859.pdf");
	}
}



