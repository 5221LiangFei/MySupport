package com.cqvie.wy.floyd;

/**
 * 2017年12月25日 兼容新设备 通过传入floyd所得的指令、坐标，并指定初始车头朝向方向 可实现从任意坐标点自动行进至目标坐标点
 * 
 */
public class Path {

	// 执行不同命令时，速度与码盘不同
	public static final int LINE_SPEED = 50;// 默认循迹速度
	public static final int SWERVE_SPEED = 80;// 默认转弯速度
	// 与转弯相关
	public static final int SWERVE_ON_LINE_MIDDLE = 105;// 循迹线中转弯码盘
	public static final int CODE_DISK = 47;// 默认涉及转弯处的码盘

	/**
	 * 黑金刚floyd最短路径->执行
	 * 
	 * @param command
	 *            String command = CarShortestWay.getPath(new PathPoints(A, B));
	 * @param coordinates
	 *            String[] coordinates = CarShortestWay.result3;
	 * @param client
	 * @param startToward
	 *            初始车头方向（东南西北）
	 */
	public static void getParsingPath20170505(String command,
			String[] coordinates, String startToward, String endToward) {
		/** ***************初始化************** */

		if (command.equals("") && !startToward.equals(endToward)
				|| command.equals(null) && !startToward.equals(endToward)) {

			if (!endToward.equals("") || !endToward.equals(null)) {
				// 坐标相同方向相同的执行
				adjustmentInDirection(initDirection(startToward),
						initDirection(endToward), coordinates[0]);
			}

		} else {
			// 指令由string转为char数组
			char[] str = StringToChars(command);
			int coordinatesIndex;// 控制当前坐标的下标
			String currentCoordinates = null;// 当前坐标
			// 得到初始方向hashCode
			int currentDirections = initDirection(startToward);// 当前方向
			int endDirections = initDirection(endToward);// 结束方向
			int count;// 循环次数计数
			int nextDifferenceCode;// 需要转到的方向(下一个方向)
			int difference;// 得到方向差值
			boolean isEdge;// 是否为边缘坐标点
			boolean isHorizontal;// 前进方向是否为水平的
			boolean isCrossroad;// 是否是十字路口
			boolean isContinuousTwo;// 是否循迹
			boolean isContinuousThree;// 是否十字路口双循迹
			boolean isContinuousFour;// 是否非十字路口双循迹
			// ==-================"←"=="↑"=="→"=="↓"
			// int[] directions={8592,8593,8594,8595};
			/* 8592"←" 8593"↑" 8594"→" 8595"↓" */
			/** ******************核心部分********************* */
			count = 0;
			coordinatesIndex = 0;
			// 结束坐标点的第一位（字母位）
			String endCoordinatesOne = coordinates[coordinates.length - 1]
					.substring(0, 1);// 表示从下标0截取到下标1（不包含下标1）
			// 结束坐标点的第二位（数字位）
			String endCoordinatesTwo = coordinates[coordinates.length - 1]
					.substring(1);// 表示从下标1截取到最后一位
			// 判断最终点是否为A1~7(G1~7)边缘点
			isEdge = false;// 结束点是否为边缘点
			isEdge = getIsEdge(coordinates, endCoordinatesOne, isEdge);
			for (int i = 0; i < str.length; i++) {
				System.out.println("Path->" + "********进入Path核心第" + (++count)
						+ "次循环*********");
				// 需要转到的方向
				nextDifferenceCode = String.valueOf(str[i]).hashCode();// 下一个方向
				// 得到方向差值
				difference = nextDifferenceCode - currentDirections;
				// 当前坐标
				currentCoordinates = coordinates[coordinatesIndex];
				System.out.println("Path:" + "|当前坐标:" + currentCoordinates
						+ "|i=" + i);
				// 每次循环初值为false
				isHorizontal = false;// 前进方向是否为水平的
				isCrossroad = false;// 是否是十字路口
				isContinuousTwo = false;// 是否循迹
				isContinuousThree = false;// 是否十字路口双循迹
				isContinuousFour = false;// 是否非十字路口双循迹
				// 判断纵横方向
				isHorizontal = getIsHorizontal(currentDirections, isHorizontal);
				// 是否循迹，两个相同方向指令达成循迹条件
				isContinuousTwo = getIsContinuousTwo(str, nextDifferenceCode,
						isContinuousTwo, i);
				// 如果是十字路口，hashCode%2为零表示是十字路口，否则不是
				if (currentCoordinates.hashCode() % 2 == 0) {
					isCrossroad = true;
					if (i + 2 < str.length) {
						isContinuousThree = getIsContinuousThree(str, i,
								nextDifferenceCode, isContinuousThree);
					}
					if (i + 3 < str.length) {
						isContinuousFour = getIsContinuousFour(str, i,
								nextDifferenceCode, isContinuousFour);
					}
				} else {// 如果不是十字路口
					isCrossroad = false;
					if (i + 2 < str.length) {
						isContinuousThree = getIsContinuousThree(str, i,
								nextDifferenceCode, isContinuousThree);
					}
					if (i + 3 < str.length) {
						isContinuousFour = getIsContinuousFour(str, i,
								nextDifferenceCode, isContinuousFour);
					}
				}
				// 处理3次转向变为1次转向
				difference = ProcessingDirection(difference);
				// 边缘坐标判定
				difference = edgeJudgment(coordinates, currentCoordinates,
						difference);
				// 方向校正(只执行转向)
				currentDirections = adjustmentInDirection(currentDirections,
						nextDifferenceCode, coordinates[coordinatesIndex]);
				// 逻辑执行循迹前进循迹，i自增，坐标下标自增
				if (isCrossroad) {
					System.out.println("Path:" + "是十字路口");
					if (isContinuousFour) {// 是否循迹前进循迹
						System.out.println("Path:" + "循迹前进循迹");

						// client.lineTwoLattice();

						i += 3;
						coordinatesIndex += 3;
					} else {// 不执行循迹前进循迹
						// 如果有两个连续相同指令，或者坐标数字位为1或7则执行循迹
						if (isContinuousTwo || "1".equals(endCoordinatesTwo)
								|| "7".equals(endCoordinatesTwo)) {

							System.out.println("Path:" + "循迹");

							// client.line(LINE_SPEED);
							i++;
							coordinatesIndex++;
						} else {
							if (isHorizontal) {
								if (isEdge) {
									System.out.println("Path:" + "边缘横向半循迹");
									// //client.lineHalf(LINE_SPEED, 800);
									// client.lineCodeDisk(LINE_SPEED, 35);
								} else {
									System.out.println("Path:" + "横向半循迹");
									// //client.lineHalf(LINE_SPEED, 1100);
									// client.lineCodeDisk(LINE_SPEED, 60);
								}
							} else {
								System.out.println("Path:" + "纵向半循迹");
								// //client.lineHalf(LINE_SPEED, 500);
								// client.lineCodeDisk(LINE_SPEED, 55);
							}
							// 半循迹代表任务结束，continue避免执行多余命令
							i++;
							coordinatesIndex++;
							continue;
						}
					}
				} else {
					System.out.println("Path:" + "非十字路口");
					if (isContinuousThree) {
						System.out.println("Path:" + "循迹前进循迹");

						// client.lineTwoLattice();

						i += 2;
						coordinatesIndex += 2;
					} else {
						System.out.println("Path:" + "循迹");

						// client.line(LINE_SPEED);
					}
				}
				if (i + 1 < str.length) {// 逻辑执行前进或前进转弯
					// 是否前进转弯
					if (currentDirections != String.valueOf(str[i + 1])
							.hashCode()) {
						difference = String.valueOf(str[i + 1]).hashCode()
								- currentDirections;
						// 处理转弯超过三次的情况
						difference = ProcessingDirection(difference);
						switch (difference) {
						case 1:// 前进右转
							System.out.println("Path:" + "前进右转");
							// client.goRight();
							currentDirections = String.valueOf(str[i + 1])
									.hashCode();
							break;

						case -1:// 前进左转
							System.out.println("Path:" + "前进左转");
							// client.goLeft();
							currentDirections = String.valueOf(str[i + 1])
									.hashCode();
							break;

						default:
							break;
						}
					} else if (!endCoordinatesTwo.equals("7")
							&& !endCoordinatesTwo.equals("1")) {// 前进
						System.out.println("Path:" + "前进");
						// client.go(LINE_SPEED, CODE_DISK);
					}
				} else if (!endCoordinatesTwo.equals("7")
						&& !endCoordinatesTwo.equals("1")) {// 前进
					System.out.println("Path:" + "前进");
					// client.go(LINE_SPEED, CODE_DISK);
				}

				// 循环结束坐标下标日常自增
				if (coordinatesIndex + 1 < coordinates.length) {
					coordinatesIndex++;
				}
				System.out.println("Path:循环结束时坐标"
						+ coordinates[coordinatesIndex]);
			}

			if (!endToward.equals("") || !endToward.equals(null)) {
				// 控制结束方向
				adjustmentInDirection(currentDirections, endDirections,
						coordinates[coordinatesIndex]);
			}

		}
	}

	/**
	 * 判断车头纵横朝向，东西为横，南北为纵
	 * 
	 * @param currentDirections
	 *            当前坐标点的hashCode
	 * @param isHorizontal
	 * @return
	 */
	private static boolean getIsHorizontal(int currentDirections,
			boolean isHorizontal) {
		if (currentDirections % 2 == 0) {
			isHorizontal = true;
		}
		return isHorizontal;
	}

	/**
	 * 判断是否执行循迹，两个连续相同方向的指令时为真
	 * 
	 * @param str
	 * @param nextDifferenceCode
	 * @param isContinuousTwo
	 * @param i
	 * @return
	 */
	private static boolean getIsContinuousTwo(char[] str,
			int nextDifferenceCode, boolean isContinuousTwo, int i) {
		if (i + 1 < str.length) {
			if (nextDifferenceCode == String.valueOf(str[i + 1]).hashCode()) {
				isContinuousTwo = true;
			}
		}
		return isContinuousTwo;
	}

	/**
	 * 判断是否为A（A1~A7）或G（G1~G7）边缘点
	 * 
	 * @param coordinates
	 * @param isEdge
	 * @return
	 */
	private static boolean getIsEdge(String[] coordinates,
			String endCoordinatesOne, boolean isEdge) {
		if ("A".equals(endCoordinatesOne) || "G".equals(endCoordinatesOne)) {
			if (coordinates[coordinates.length - 1].hashCode() % 2 == 1) {
				isEdge = true;
			}
		}
		return isEdge;
	}

	/**
	 * String转char[]
	 * 
	 * @param command
	 * @return
	 */
	private static char[] StringToChars(String command) {
		char[] str = new char[command.length()];
		for (int i = 0; i < command.length(); i++) {
			str = command.toCharArray();
		}
		return str;
	}

	/**
	 * 是否十字路口循迹前进循迹，连续4个指令为相同方向为真
	 */
	private static boolean getIsContinuousFour(char[] str, int i,
			int nextDifferenceCode, boolean isContinuousFour) {
		if (nextDifferenceCode == String.valueOf(str[i + 1]).hashCode()
				&& nextDifferenceCode == String.valueOf(str[i + 2]).hashCode()
				&& nextDifferenceCode == String.valueOf(str[i + 3]).hashCode()) {
			isContinuousFour = true;
		}
		return isContinuousFour;
	}

	/**
	 * 是否非十字路口循迹前进循迹，连续3个指令为相同方向为真
	 */
	private static boolean getIsContinuousThree(char[] str, int i,
			int nextDifferenceCode, boolean isContinuousThree) {
		if (nextDifferenceCode == String.valueOf(str[i + 1]).hashCode()
				&& nextDifferenceCode == String.valueOf(str[i + 2]).hashCode()) {
			isContinuousThree = true;
		}
		return isContinuousThree;
	}

	/**
	 * 将方向调整到指定方向，之后即可执行循迹等操作
	 * 
	 * @param currentDirections
	 *            当前方向
	 * @param nextDifferenceCode
	 *            目标方向
	 * @param coordinates
	 * @param difference
	 *            计算出的方向hashCode差值
	 * @return
	 */
	public static int adjustmentInDirection(int currentDirections,
			int nextDifferenceCode, String coordinates) {
		// 方向差值
		System.out.println("当前方向:" + currentDirections + "结束方向:"
				+ nextDifferenceCode);
		// 当前位置为十字路口时才执行超过1次的转弯
		// 当前方向不等于目标方向
		if (nextDifferenceCode != currentDirections) {
			int difference = ProcessingDirection(nextDifferenceCode
					- currentDirections);
			// 当前坐标点为十字路口
			if (coordinates.hashCode() % 2 == 0) {
				// System.out.println(difference);
				switch (difference) {
				// 右转
				case 1:
					System.out.println("Path:" + "右转");
					// client.right(SWERVE_SPEED);
					break;
				// 右转2
				case 2:
					System.out.println("Path:" + "右转2次");
					// client.rightChangeDirection();
					break;
				// 左转
				case -1:
					System.out.println("Path:" + "左转");
					// client.left(SWERVE_SPEED);
					break;
				// 左转2
				case -2:
					System.out.println("Path:" + "左转2次");
					// client.leftChangeDirection();
					break;

				default:
					break;
				}
				// 更新当前方向
			} else {// 当前坐标点非十字路口
				// System.out.println(difference);
				// 当前方向取模2=0表示起始为横向
				if (currentDirections % 2 == 0) {// 当前方向为横向
					if (Math.abs(difference) == 2) {// 例如从西转到东
						System.out.println("Path:" + "横向寻迹线中右转");
						// client.right(SWERVE_SPEED);
					} else {// 循迹线上半转
						if (difference == 1) {
							System.out.println("Path:" + "横向寻迹线中右半转");
							// client.rightCodeDisk(SWERVE_SPEED,
							// SWERVE_ON_LINE_MIDDLE);
						} else if (difference == -1) {
							System.out.println("Path:" + "横向寻迹线中左半转");
							// client.leftCodeDisk(SWERVE_SPEED,
							// SWERVE_ON_LINE_MIDDLE);
						}
					}
				} else {// 当前方向为纵向
					if (Math.abs(difference) == 2) {// 例如北转到南
						System.out.println("Path:" + "纵向寻迹线中右转");
						// client.right(SWERVE_SPEED);
					} else {// 循迹线上半转
						if (difference == 1) {
							System.out.println("Path:" + "纵向寻迹线中右半转");
							// client.rightCodeDisk(SWERVE_SPEED,
							// SWERVE_ON_LINE_MIDDLE);
						} else if (difference == -1) {
							System.out.println("Path:" + "纵向寻迹线中左半转");
							// client.leftCodeDisk(SWERVE_SPEED,
							// SWERVE_ON_LINE_MIDDLE);
						}
					}
				}
			}
		}

		return nextDifferenceCode;
	}

	/**
	 * 判断是否为边缘点（除数字部分为1或7的部分），控制A或G坐标转向次数为1
	 * 
	 * @param coordinates
	 * @param currentCoordinates
	 * @param difference
	 * @return
	 */
	private static int edgeJudgment(String[] coordinates,
			String currentCoordinates, int difference) {
		if (!currentCoordinates.substring(1).equals("1")
				&& !currentCoordinates.substring(1).equals("7")) {
			if (currentCoordinates.hashCode() % 2 == 1) {
				if (currentCoordinates.hashCode() < coordinates[coordinates.length - 1]
						.hashCode()) {
					difference = 1;
				} else {
					difference = -1;
				}
			}
		}
		return difference;
	}

	/**
	 * 
	 * @param toward
	 *            传入string类型方向（东南西北）
	 * @param currentDirections
	 *            转换为相应的hashCode
	 * @return
	 */
	private static int initDirection(String toward) {
		if ("西".equals(toward)) {
			return 8592;
		} else if ("北".equals(toward)) {
			return 8593;
		} else if ("东".equals(toward)) {
			return 8594;
		} else if ("南".equals(toward)) {
			return 8595;
		}

		return 0;
	}

	/**
	 * 处理转向次数超过2（例如3次转弯）的情况，修改为转向1次即可完成
	 * 
	 * @param difference
	 *            方向hashCode差值
	 * @return
	 */
	private static int ProcessingDirection(int difference) {
		if (difference > 2) {
			difference = difference - 4;
		}
		if (difference < -2) {
			difference = difference + 4;
		}
		return difference;
	}

}
