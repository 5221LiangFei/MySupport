package com.cqvie.wy.floyd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * 运算工具包 CONNECT连接 SORTING排序 DELEQU删除重复元素 DEL删除指定下标 INSERT插入值到指定下标
 * arrycopy改变数组最大长度 REV逆置元素 COUNT计数 AVERAGE求平均值 MAX取最大值 MIN取最小值 HEX二进制转十六进制
 * DEC转为十进制 OCT十进制转为八进制 BIN十进制转为二进制 INT四舍五入取整 NUMtoSTR数字转字符串 STRtoNUM字符串转数字
 * 
 *
 */
public class MyComputingFormula {

	/*
	 * 进制转换 System.out.println("十转二：" + Integer.toBinaryString(120));
	 * System.out.println("十转八：" + Integer.toOctalString(120));
	 * System.out.println("十转十六：" + Integer.toHexString(120));
	 * System.out.println("二转十：" + Integer.valueOf("101", 2));
	 * System.out.println("八转十：" + Integer.valueOf("125", 8));
	 * System.out.println("十六转十：" + Integer.valueOf("7B", 16));
	 * System.out.println("十六转二：" + Integer.toBinaryString(Integer.valueOf("F",
	 * 16))); System.out.println("二转八：" +
	 * Integer.toOctalString(Integer.valueOf("1011", 2)));
	 * System.out.println("二转十六：" +
	 * Integer.toHexString(Integer.valueOf("1011",2)));
	 * 
	 * 按位取：与(&)、非(~)、或(|)、异或(^) 用法（a&b）（a~b） System.out.println("a 非的结果是：" + (~1
	 * & 0xFFFF));
	 * 
	 * 
	 * 
	 */

	/*
	 * 字符串连接
	 */
	@SuppressWarnings("unused")
	static String CONNECT(String str, String content) {
		return str = str + content;
	}

	/**
	 * 从小到大排序或从大到小排序
	 * 
	 * @param datas
	 * @param rules
	 *            ture为从小到大排序,false为从大到小排序
	 * @return
	 */
	public static int[] SORTING(int[] datas, Boolean rules) {

		if (rules) {
			Arrays.sort(datas);
		} else {
			Arrays.sort(datas);
			datas = REVint(datas);

		}

		return datas;
	}

	/**
	 * 删除重复元素
	 * 
	 * @param datas
	 * @param content
	 * @return
	 */
	public static int[] DELEQU(int[] datas, int content) {

		int count = 0;
		for (int i = 0; i < datas.length; i++) {
			if (datas[i] == content) {
				for (int j = i; j < datas.length - 1; j++) {
					datas[j] = datas[j + 1];
				}
				i--;
				count++;
			}
		}
		datas = arrycopy(datas, -count);

		return datas;
	}

	/**
	 * 删除指定下标的数据
	 * 
	 * @param datas
	 * @param index
	 * @return
	 */
	public static int[] DEL(int[] datas, int index) {

		for (int i = index; i < datas.length - 1; i++) {
			datas[i] = datas[i + 1];
		}
		datas = arrycopy(datas, -1);

		return datas;

	}

	/**
	 * 数组：指定下标插入指定数字
	 * 
	 * @param datas
	 * @param content
	 * @param index
	 *            下标必须小于datas1.length
	 * @return
	 */

	public static int[] INSERT(int[] datas, int content, int index) {

		if (index > datas.length) {
			System.out.println("下标错误,必须小于数组的长度");
			return null;
		}
		// 数组长度加1
		datas = arrycopy(datas, 1);

		int n = 0;
		for (int i = index; i < datas.length; i++) {
			datas[datas.length - n - 1] = datas[datas.length - n - 2];
			n++;
		}
		datas[index] = content;

		return datas;

	}

	/**
	 * 数组扩容
	 * 
	 * @param objs
	 *            原数组
	 * @param n
	 *            需要增加的容量
	 * @return
	 */
	public static int[] arrycopy(int[] objs, int n) {

		int[] newObjs = new int[objs.length + n];
		if (n > 0) {
			System.arraycopy(objs, 0, newObjs, 0, objs.length);
		} else {
			System.arraycopy(objs, 0, newObjs, 0, objs.length + n);
		}
		return newObjs;
	}

	/**
	 * int数组逆置数据 StringBuffer.reverse().toString();字符串逆置
	 * 
	 * @param datas
	 * @return
	 */
	public static int[] REVint(int[] datas) {
		int temp;
		for (int i = 0; i < datas.length / 2; i++) {
			temp = datas[i];
			datas[i] = datas[datas.length - 1 - i];
			datas[datas.length - 1 - i] = temp;
		}
		return datas;
	}

	/**
	 * String数组逆置数据 StringBuffer.reverse().toString();字符串逆置
	 * 
	 * @param datas
	 * @return
	 */
	public static String[] REVstring(String[] datas) {
		String temp;
		for (int i = 0; i < datas.length / 2; i++) {
			temp = datas[i];
			datas[i] = datas[datas.length - 1 - i];
			datas[datas.length - 1 - i] = temp;
		}
		return datas;
	}

	/**
	 * 计数
	 * 
	 * @param values
	 * @return
	 */
	public static int COUNT(Object... values) {
		return values.length;

	}

	/**
	 * 求平均值
	 * 
	 * @param values
	 *            double类型
	 * @return
	 */
	public static double AVERAGE(int... values) {
		double num = 0;
		for (int i = 0; i < values.length; i++) {
			num += values[i];
		}
		return num / values.length;

	}

	/**
	 * 求最小值
	 * 
	 * @param values
	 *            int数组
	 * @return
	 */
	public static int MIN(int... values) {
		// 从小大到排序
		Arrays.sort(values);
		// 取第一个(最小值)
		return values[0];
	}

	/**
	 * 求最大值
	 * 
	 * @param values
	 *            int数组
	 * @return
	 */
	public static int MAX(int... values) {
		// 从小大到排序
		Arrays.sort(values);
		// 取最后一个值(最大值)
		return values[values.length - 1];
	}

	/**
	 * 二进制转为十六进制
	 * 
	 * @param num
	 *            十六进制值
	 * @return
	 */
	public static String HEX(String num) {
		String hex = Integer.toHexString(Integer.parseInt(num, 2));
		return hex;

	}

	/**
	 * 转换为十进制
	 * 
	 * @param data
	 *            需要转换的数值
	 * @param radix
	 *            转换前是什么进制
	 * @return
	 */
	public static int DEC(String data, int radix) {
		int results = Integer.parseInt(data, radix);
		return results;

	}

	/**
	 * 十进制转换为八进制
	 * 
	 * @param num
	 *            int类型数字
	 * @return
	 */
	public static String OCT(int num) {
		String results = Integer.toOctalString(num);
		return results;

	}

	/**
	 * 十进制转换为二进制
	 * 
	 * @param num
	 *            int类型的数字
	 * @return
	 */
	public static String BIN(int num) {
		String results = Integer.toBinaryString(num);
		return results;

	}

	/**
	 * 四舍五入求整数
	 * 
	 * @param data
	 *            带小数点的数字
	 * @return
	 */
	public static int INT(double data) {
		int results = (int) Math.round(data);
		return results;
	}

	/**
	 * int转string
	 * 
	 * @param num
	 *            int数字
	 * @return
	 */
	public static String NUMtoSTR(int num) {
		String results = String.valueOf(num);
		return results;
	}

	/**
	 * string字符串转为int类型
	 * 
	 * @param str
	 *            字符串类型的数字
	 * @return
	 */
	public static int STRtoNUM(String str) {
		int results = Integer.valueOf(str);
		return results;
	}

	

}
