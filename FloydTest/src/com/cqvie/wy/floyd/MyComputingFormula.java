package com.cqvie.wy.floyd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * ���㹤�߰� CONNECT���� SORTING���� DELEQUɾ���ظ�Ԫ�� DELɾ��ָ���±� INSERT����ֵ��ָ���±�
 * arrycopy�ı�������󳤶� REV����Ԫ�� COUNT���� AVERAGE��ƽ��ֵ MAXȡ���ֵ MINȡ��Сֵ HEX������תʮ������
 * DECתΪʮ���� OCTʮ����תΪ�˽��� BINʮ����תΪ������ INT��������ȡ�� NUMtoSTR����ת�ַ��� STRtoNUM�ַ���ת����
 * 
 *
 */
public class MyComputingFormula {

	/*
	 * ����ת�� System.out.println("ʮת����" + Integer.toBinaryString(120));
	 * System.out.println("ʮת�ˣ�" + Integer.toOctalString(120));
	 * System.out.println("ʮתʮ����" + Integer.toHexString(120));
	 * System.out.println("��תʮ��" + Integer.valueOf("101", 2));
	 * System.out.println("��תʮ��" + Integer.valueOf("125", 8));
	 * System.out.println("ʮ��תʮ��" + Integer.valueOf("7B", 16));
	 * System.out.println("ʮ��ת����" + Integer.toBinaryString(Integer.valueOf("F",
	 * 16))); System.out.println("��ת�ˣ�" +
	 * Integer.toOctalString(Integer.valueOf("1011", 2)));
	 * System.out.println("��תʮ����" +
	 * Integer.toHexString(Integer.valueOf("1011",2)));
	 * 
	 * ��λȡ����(&)����(~)����(|)�����(^) �÷���a&b����a~b�� System.out.println("a �ǵĽ���ǣ�" + (~1
	 * & 0xFFFF));
	 * 
	 * 
	 * 
	 */

	/*
	 * �ַ�������
	 */
	@SuppressWarnings("unused")
	static String CONNECT(String str, String content) {
		return str = str + content;
	}

	/**
	 * ��С���������Ӵ�С����
	 * 
	 * @param datas
	 * @param rules
	 *            tureΪ��С��������,falseΪ�Ӵ�С����
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
	 * ɾ���ظ�Ԫ��
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
	 * ɾ��ָ���±������
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
	 * ���飺ָ���±����ָ������
	 * 
	 * @param datas
	 * @param content
	 * @param index
	 *            �±����С��datas1.length
	 * @return
	 */

	public static int[] INSERT(int[] datas, int content, int index) {

		if (index > datas.length) {
			System.out.println("�±����,����С������ĳ���");
			return null;
		}
		// ���鳤�ȼ�1
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
	 * ��������
	 * 
	 * @param objs
	 *            ԭ����
	 * @param n
	 *            ��Ҫ���ӵ�����
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
	 * int������������ StringBuffer.reverse().toString();�ַ�������
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
	 * String������������ StringBuffer.reverse().toString();�ַ�������
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
	 * ����
	 * 
	 * @param values
	 * @return
	 */
	public static int COUNT(Object... values) {
		return values.length;

	}

	/**
	 * ��ƽ��ֵ
	 * 
	 * @param values
	 *            double����
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
	 * ����Сֵ
	 * 
	 * @param values
	 *            int����
	 * @return
	 */
	public static int MIN(int... values) {
		// ��С������
		Arrays.sort(values);
		// ȡ��һ��(��Сֵ)
		return values[0];
	}

	/**
	 * �����ֵ
	 * 
	 * @param values
	 *            int����
	 * @return
	 */
	public static int MAX(int... values) {
		// ��С������
		Arrays.sort(values);
		// ȡ���һ��ֵ(���ֵ)
		return values[values.length - 1];
	}

	/**
	 * ������תΪʮ������
	 * 
	 * @param num
	 *            ʮ������ֵ
	 * @return
	 */
	public static String HEX(String num) {
		String hex = Integer.toHexString(Integer.parseInt(num, 2));
		return hex;

	}

	/**
	 * ת��Ϊʮ����
	 * 
	 * @param data
	 *            ��Ҫת������ֵ
	 * @param radix
	 *            ת��ǰ��ʲô����
	 * @return
	 */
	public static int DEC(String data, int radix) {
		int results = Integer.parseInt(data, radix);
		return results;

	}

	/**
	 * ʮ����ת��Ϊ�˽���
	 * 
	 * @param num
	 *            int��������
	 * @return
	 */
	public static String OCT(int num) {
		String results = Integer.toOctalString(num);
		return results;

	}

	/**
	 * ʮ����ת��Ϊ������
	 * 
	 * @param num
	 *            int���͵�����
	 * @return
	 */
	public static String BIN(int num) {
		String results = Integer.toBinaryString(num);
		return results;

	}

	/**
	 * ��������������
	 * 
	 * @param data
	 *            ��С���������
	 * @return
	 */
	public static int INT(double data) {
		int results = (int) Math.round(data);
		return results;
	}

	/**
	 * intתstring
	 * 
	 * @param num
	 *            int����
	 * @return
	 */
	public static String NUMtoSTR(int num) {
		String results = String.valueOf(num);
		return results;
	}

	/**
	 * string�ַ���תΪint����
	 * 
	 * @param str
	 *            �ַ������͵�����
	 * @return
	 */
	public static int STRtoNUM(String str) {
		int results = Integer.valueOf(str);
		return results;
	}

	

}
