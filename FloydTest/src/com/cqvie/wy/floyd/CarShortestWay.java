package com.cqvie.wy.floyd;

/**
 * 测试代码
 * 
 * @author 小宇
 * 
 */
public class CarShortestWay {
	/**
	 * 弗洛伊德测试
	 */

	public static void main(String[] args) {
		arrayInit();
		CarShortestWay.getPath(new PathPoints("F1", "B7"));
	}

	public static MGraph mg = new MGraph();
	public static String[] result3;

	public static CreateMgraph cm = new CreateMgraph();
	public static int x = MGraph.NULL;

	// 第0行数据代表从坐标0到1~48坐标的距离。
	public static int[][] array = new int[48][48];

	public static void setMainCarCoordinates(String... str) {
		int x = 1000;
		String[] string = GetShortestPath.string;
		for (int i = 0; i < str.length; i++) {
			String strSubOne = str[i].substring(0, 1);
			String strSubTwo = str[i].substring(1);
			for (int j = 0; j < string.length; j++) {
				int index = j;
				if (str[i].equals(string[j]) && !strSubOne.equals("A")
						&& !strSubOne.equals("G")) {
					switch (Integer.parseInt(strSubTwo)) {
					case 1:
						// System.out.println("判断为上边缘点" + str.charAt(1));
						CarShortestWay.array[index][index + 7] = x;
						CarShortestWay.array[index + 7][index] = x;

						break;
					case 7:
						// System.out.println("判断为下边缘点" + str.charAt(1));
						CarShortestWay.array[index][index - 7] = x;
						CarShortestWay.array[index - 7][index] = x;

						break;

					case 3:
						// System.out.println("判断为下边缘点" + str.charAt(1));
						CarShortestWay.array[index][index + 7] = x;
						CarShortestWay.array[index][index - 7] = x;

						CarShortestWay.array[index + 7][index] = x;
						CarShortestWay.array[index - 7][index] = x;

						break;

					case 5:
						// System.out.println("判断为下边缘点" + str.charAt(1));
						CarShortestWay.array[index][index + 7] = x;
						CarShortestWay.array[index][index - 7] = x;

						CarShortestWay.array[index + 7][index] = x;
						CarShortestWay.array[index - 7][index] = x;

						break;

					default:
						// System.out.println("判断为非边缘点" + str.charAt(1));
						CarShortestWay.array[index][index - 1] = x;
						CarShortestWay.array[index][index + 1] = x;
						CarShortestWay.array[index][index + 7] = x;
						CarShortestWay.array[index][index - 7] = x;

						CarShortestWay.array[index - 1][index] = x;
						CarShortestWay.array[index + 1][index] = x;
						CarShortestWay.array[index + 7][index] = x;
						CarShortestWay.array[index - 7][index] = x;
						break;
					}

					System.out.println("避开" + str[i] + "禁止通行");
				} else if (str.equals(string[i]) && !strSubTwo.equals(1)
						&& !strSubTwo.equals(7)) {
					switch ((int) (strSubOne.toCharArray()[0])) {
					case 65:// A
						// System.out.println("判断为左边缘点" + str.charAt(1));
						CarShortestWay.array[index][index + 1] = x;
						CarShortestWay.array[index + 1][index] = x;

						break;
					case 71:// G
						// System.out.println("判断为右边缘点" + str.charAt(1));
						CarShortestWay.array[index][index - 1] = x;
						CarShortestWay.array[index - 1][index] = x;

					default:
						break;
					}
					System.out.println("避开" + str[i] + "禁止通行");
				}
			}
		}
	}

	public static void refreshCarCoordinates(String... str) {
		int x = 1;
		String[] string = GetShortestPath.string;
		for (int i = 0; i < str.length; i++) {
			String strCharAt0 = String.valueOf(str[i].charAt(0));
			String strCharAt1 = String.valueOf(str[i].charAt(1));
			for (int j = 0; j < string.length; j++) {
				int index = j;
				if (str[i].equals(string[j]) && !strCharAt0.equals("A")
						&& !strCharAt0.equals("G")) {
					switch (Integer.parseInt(strCharAt1)) {
					case 1:
						// System.out.println("判断为上边缘点" + str.charAt(1));
						CarShortestWay.array[index][index + 7] = x;
						CarShortestWay.array[index + 7][index] = x;

						break;
					case 7:
						// System.out.println("判断为下边缘点" + str.charAt(1));
						CarShortestWay.array[index][index - 7] = x;
						CarShortestWay.array[index - 7][index] = x;
						break;

					case 3:
						// System.out.println("判断为下边缘点" + str.charAt(1));
						CarShortestWay.array[index][index + 7] = x;
						CarShortestWay.array[index][index - 7] = x;

						CarShortestWay.array[index + 7][index] = x;
						CarShortestWay.array[index - 7][index] = x;

						break;

					case 5:
						// System.out.println("判断为下边缘点" + str.charAt(1));
						CarShortestWay.array[index][index + 7] = x;
						CarShortestWay.array[index][index - 7] = x;

						CarShortestWay.array[index + 7][index] = x;
						CarShortestWay.array[index - 7][index] = x;

						break;

					default:
						// System.out.println("判断为非边缘点" + str.charAt(1));
						CarShortestWay.array[index][index - 1] = x;
						CarShortestWay.array[index][index + 1] = x;
						CarShortestWay.array[index][index + 7] = x;
						CarShortestWay.array[index][index - 7] = x;

						CarShortestWay.array[index - 1][index] = x;
						CarShortestWay.array[index + 1][index] = x;
						CarShortestWay.array[index + 7][index] = x;
						CarShortestWay.array[index - 7][index] = x;
						break;
					}

					System.out.println(str[i] + "允许通行");
				} else if (str.equals(string[i]) && !strCharAt1.equals(1)
						&& !strCharAt1.equals(7)) {
					switch ((int) (strCharAt0.toCharArray()[0])) {
					case 65:// A
						// System.out.println("判断为左边缘点" + str.charAt(1));
						CarShortestWay.array[index][index + 1] = x;
						CarShortestWay.array[index + 1][index] = x;

						break;
					case 71:// G
						// System.out.println("判断为右边缘点" + str.charAt(1));
						CarShortestWay.array[index][index - 1] = x;
						CarShortestWay.array[index - 1][index] = x;

					default:
						break;
					}
					System.out.println(str[i] + "允许通行");
				}
			}
		}
	}

	public static String getPath(PathPoints points) {
		cm.createMat(mg, array, 48);
		String A, B;
		A = points.getA();
		B = points.getB();
		System.out.println(A + "到" + B);
		GetShortestPath.Floyd(mg);
		String result1 = null;
		// 正 A2->G6 distance:10,path: A2->B2->C2->D2->D3->D4->E4->F4->F5->F6->G6
		for (int i = 0; i < GetShortestPath.result.length; i++) {
			for (int j = 0; j < GetShortestPath.result[i].length; j++) {
				if (GetShortestPath.result[i][j] != null) {
					String[] str = GetShortestPath.result[i][j].split(" ");
					// System.out.println(str[0]);
					if (str[0].equals(A + "->" + B)) {
						// System.out.println(A+"->"+B+"正");
						// System.out.println("正 " +
						// GetShortestPath.result[i][j]);
						result1 = "正 " + GetShortestPath.result[i][j];
					} else if (str[0].equals(B + "->" + A)) {
						// System.out.println(B+"->"+A+"反");
						// System.out.println("反 " +
						// GetShortestPath.result[i][j]);
						result1 = "反 " + GetShortestPath.result[i][j];
					}
				}
			}
		}
		// 加工处理

		StringBuffer sb = new StringBuffer();
		if (result1 != null) {
			System.out.println(result1);
			String[] result2 = result1.split(" ");
			result3 = result2[3].split("->");
			if ("反".equals(result2[0])) {// 得到相反方向的路径，逆序
				MyComputingFormula.REVstring(result3);
			}
			// for (String string : result3) {
			// System.out.print(string+" ");
			// }
			// System.out.println();

			int[] pathCode = new int[result3.length - 1];
			for (int i = 0; i < result3.length; i++) {// 测试得到纵向移动hashCode增减1(↑从下往上为增),横向移动增减31(←从右向左为增)
				// System.out.println(path[i]+","+path[i].hashCode());
				if (i < result3.length - 1) {
					// System.out.println(path[i].hashCode()-path[i+1].hashCode());
					pathCode[i] = result3[i].hashCode()
							- result3[i + 1].hashCode();
				}
			}
			String[] directionOfMotion = new String[pathCode.length];
			for (int i = 0; i < pathCode.length; i++) {
				// System.out.println(pathCode[i]);
				switch (pathCode[i]) {
				case 1:
					directionOfMotion[i] = "↑";
					// directionOfMotion[i]="1";
					break;
				case -31:
					directionOfMotion[i] = "→";
					// directionOfMotion[i]="2";
					break;
				case -1:
					directionOfMotion[i] = "↓";
					// directionOfMotion[i]="3";
					break;
				case 31:
					directionOfMotion[i] = "←";
					// directionOfMotion[i]="4";
					break;

				default:
					break;
				}
				// 测试路径是否有错误
				if (pathCode[i] != 31 && pathCode[i] != -31 && pathCode[i] != 1
						&& pathCode[i] != -1) {
					System.err.println(result3[i] + "坐标存在错误");
				}
			}
			for (String string1 : directionOfMotion) {
				sb.append(string1);
			}
			System.out.println(sb.toString());

		}
		return sb.toString();
	}

	/**
	 * 初始化矩阵
	 */
	public static void arrayInit() {
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array.length; j++) {
				array[i][j] = x;// 全部置为最大值
			}
		}
		// 以各个十字路口为中心，上下左右均可通行，置为1
		refreshCarCoordinates("B2", "D2", "F2", "B4", "D4", "F4", "B6", "D6",
				"F6");
	}
}
