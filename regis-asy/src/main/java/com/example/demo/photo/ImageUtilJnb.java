//package com.example.demo.photo;
//
//import org.apache.logging.log4j.util.Base64Util;
//
//import javax.imageio.ImageIO;
//import java.awt.*;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.IOException;
//import java.text.DecimalFormat;
//
//public class ImageUtilJnb {
//
//    private static int mb = 1048576;// 1MB
//
//    public static void main(String[] args) {
//        String fullPath="D:\\123.jpg";
//        String newPath="D:\\321322198511205873F.jpg";
//
//        long length = new File(fullPath).length();
//        if (length / 1048576 >= JnbConstants.JNB_IMAGE_RATE) {// 如果图片大于等于0.5MB 则按80%缩小
//            try {
//                ImageUtilJnb.zoom(fullPath, newPath, length, JnbConstants.JNB_IMAGE_RATE);
//            } catch (IOException e) {
//            }
//        }
//
//        String base64Str = Base64Util.getBase64Str(newPath);
//        System.out.println("base64Str===" + base64Str);
//
//    }
//
//    // 缩图
//    public static void zoom(String oldFile, String zoomFile,long length,double newRate) throws IOException {
//        DecimalFormat df = new DecimalFormat("0.00");// 设置保留位数
//        double rate=0.8;
//        long newfile = new File(oldFile).length();
//        int i = 1;
//        // 如果首次压缩还大于2MB则继续处理
//        while ((float) newfile / mb >= newRate) {
//            rate = rate - 0.05;// 暂定按照0.03频率压缩
//            BufferedImage srcImage = ImageIO.read(new File(oldFile));
//            int WIDTH = (int) (srcImage.getWidth() * rate);
//            int HEIGHT = (int) (srcImage.getHeight() * rate);
//            BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
//            Graphics g = image.getGraphics();
//            g.drawImage(srcImage, 0, 0, WIDTH, HEIGHT, null);
//            // 缩小
//            ImageIO.write(image, "jpg", new File(zoomFile));
//            i++;
//            newfile = new File(zoomFile).length();
//        }
//        // 调整方向
//        BufferedImage newImage = ImageIO.read(new File(zoomFile));
//        BufferedImage image1 = Rotate(newImage, 90);// 顺时针旋转90度
//        ImageIO.write(image1, "jpg", new File(zoomFile));
//    }
//    /**
//     * 对图片进行旋转
//     *
//     * @param src
//     *            被旋转图片
//     * @param angel
//     *            旋转角度
//     * @return 旋转后的图片
//     */
//    public static BufferedImage Rotate(Image src, int angel) {
//        int src_width = src.getWidth(null);
//        int src_height = src.getHeight(null);
//        // 计算旋转后图片的尺寸
//        Rectangle rect_des = CalcRotatedSize(new Rectangle(new Dimension(src_width, src_height)), angel);
//        BufferedImage res = null;
//        res = new BufferedImage(rect_des.width, rect_des.height, BufferedImage.TYPE_INT_RGB);
//        Graphics2D g2 = res.createGraphics();
//        // 进行转换
//        g2.translate((rect_des.width - src_width) / 2, (rect_des.height - src_height) / 2);
//        g2.rotate(Math.toRadians(angel), src_width / 2, src_height / 2);
//
//        g2.drawImage(src, null, null);
//        return res;
//    }
//
//
//    public static Rectangle CalcRotatedSize(Rectangle src, int angel) {
//        // 如果旋转的角度大于90度做相应的转换
//        if (angel >= 90) {
//            if (angel / 90 % 2 == 1) {
//                int temp = src.height;
//                src.height = src.width;
//                src.width = temp;
//            }
//            angel = angel % 90;
//        }
//
//        double r = Math.sqrt(src.height * src.height + src.width * src.width) / 2;
//        double len = 2 * Math.sin(Math.toRadians(angel) / 2) * r;
//        double angel_alpha = (Math.PI - Math.toRadians(angel)) / 2;
//        double angel_dalta_width = Math.atan((double) src.height / src.width);
//        double angel_dalta_height = Math.atan((double) src.width / src.height);
//
//        int len_dalta_width = (int) (len * Math.cos(Math.PI - angel_alpha - angel_dalta_width));
//        int len_dalta_height = (int) (len * Math.cos(Math.PI - angel_alpha - angel_dalta_height));
//        int des_width = src.width + len_dalta_width * 2;
//        int des_height = src.height + len_dalta_height * 2;
//        return new Rectangle(new Dimension(des_width, des_height));
//    }
//}
