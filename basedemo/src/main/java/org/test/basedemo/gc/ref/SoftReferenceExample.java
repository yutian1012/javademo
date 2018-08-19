package org.test.basedemo.gc.ref;

import java.lang.ref.SoftReference;
import java.util.Map;

/**
 * https://www.cnblogs.com/dolphin0520/p/3784171.html
 * 设计思路是：用一个HashMap来保存图片的路径 和 相应图片对象关联的软引用之间的映射关系，
 * 在内存不足时，JVM会自动回收这些缓存图片对象所占用的空间，从而有效地避免了OOM的问题。
 * 在Android开发中对于大量图片下载会经常用到。
 */
public class SoftReferenceExample {
	/*private Map<String, SoftReference<Bitmap>> imageCache = new HashMap<String, SoftReference<Bitmap>>();
	public void addBitmapToCache(String path) {
        // 强引用的Bitmap对象
        Bitmap bitmap = BitmapFactory.decodeFile(path);
        // 软引用的Bitmap对象
        SoftReference<Bitmap> softBitmap = new SoftReference<Bitmap>(bitmap);
        // 添加该对象到Map中使其缓存
        imageCache.put(path, softBitmap);
    }
	public Bitmap getBitmapByPath(String path) {
        // 从缓存中取软引用的Bitmap对象
        SoftReference<Bitmap> softBitmap = imageCache.get(path);
        // 判断是否存在软引用
        if (softBitmap == null) {
            return null;
        }
        // 取出Bitmap对象，如果由于内存不足Bitmap被回收，将取得空
        Bitmap bitmap = softBitmap.get();
        return bitmap;
    }*/
}
