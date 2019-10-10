package com.cqvie.wy.floyd;

/**
 * 2017��12��25�� �������豸 ͨ������floyd���õ�ָ����꣬��ָ����ʼ��ͷ������ ��ʵ�ִ�����������Զ��н���Ŀ�������
 * 
 */
public class Path {

	// ִ�в�ͬ����ʱ���ٶ������̲�ͬ
	public static final int LINE_SPEED = 50;// Ĭ��ѭ���ٶ�
	public static final int SWERVE_SPEED = 80;// Ĭ��ת���ٶ�
	// ��ת�����
	public static final int SWERVE_ON_LINE_MIDDLE = 105;// ѭ������ת������
	public static final int CODE_DISK = 47;// Ĭ���漰ת�䴦������

	/**
	 * �ڽ��floyd���·��->ִ��
	 * 
	 * @param command
	 *            String command = CarShortestWay.getPath(new PathPoints(A, B));
	 * @param coordinates
	 *            String[] coordinates = CarShortestWay.result3;
	 * @param client
	 * @param startToward
	 *            ��ʼ��ͷ���򣨶���������
	 */
	public static void getParsingPath20170505(String command,
			String[] coordinates, String startToward, String endToward) {
		/** ***************��ʼ��************** */

		if (command.equals("") && !startToward.equals(endToward)
				|| command.equals(null) && !startToward.equals(endToward)) {

			if (!endToward.equals("") || !endToward.equals(null)) {
				// ������ͬ������ͬ��ִ��
				adjustmentInDirection(initDirection(startToward),
						initDirection(endToward), coordinates[0]);
			}

		} else {
			// ָ����stringתΪchar����
			char[] str = StringToChars(command);
			int coordinatesIndex;// ���Ƶ�ǰ������±�
			String currentCoordinates = null;// ��ǰ����
			// �õ���ʼ����hashCode
			int currentDirections = initDirection(startToward);// ��ǰ����
			int endDirections = initDirection(endToward);// ��������
			int count;// ѭ����������
			int nextDifferenceCode;// ��Ҫת���ķ���(��һ������)
			int difference;// �õ������ֵ
			boolean isEdge;// �Ƿ�Ϊ��Ե�����
			boolean isHorizontal;// ǰ�������Ƿ�Ϊˮƽ��
			boolean isCrossroad;// �Ƿ���ʮ��·��
			boolean isContinuousTwo;// �Ƿ�ѭ��
			boolean isContinuousThree;// �Ƿ�ʮ��·��˫ѭ��
			boolean isContinuousFour;// �Ƿ��ʮ��·��˫ѭ��
			// ==-================"��"=="��"=="��"=="��"
			// int[] directions={8592,8593,8594,8595};
			/* 8592"��" 8593"��" 8594"��" 8595"��" */
			/** ******************���Ĳ���********************* */
			count = 0;
			coordinatesIndex = 0;
			// ���������ĵ�һλ����ĸλ��
			String endCoordinatesOne = coordinates[coordinates.length - 1]
					.substring(0, 1);// ��ʾ���±�0��ȡ���±�1���������±�1��
			// ���������ĵڶ�λ������λ��
			String endCoordinatesTwo = coordinates[coordinates.length - 1]
					.substring(1);// ��ʾ���±�1��ȡ�����һλ
			// �ж����յ��Ƿ�ΪA1~7(G1~7)��Ե��
			isEdge = false;// �������Ƿ�Ϊ��Ե��
			isEdge = getIsEdge(coordinates, endCoordinatesOne, isEdge);
			for (int i = 0; i < str.length; i++) {
				System.out.println("Path->" + "********����Path���ĵ�" + (++count)
						+ "��ѭ��*********");
				// ��Ҫת���ķ���
				nextDifferenceCode = String.valueOf(str[i]).hashCode();// ��һ������
				// �õ������ֵ
				difference = nextDifferenceCode - currentDirections;
				// ��ǰ����
				currentCoordinates = coordinates[coordinatesIndex];
				System.out.println("Path:" + "|��ǰ����:" + currentCoordinates
						+ "|i=" + i);
				// ÿ��ѭ����ֵΪfalse
				isHorizontal = false;// ǰ�������Ƿ�Ϊˮƽ��
				isCrossroad = false;// �Ƿ���ʮ��·��
				isContinuousTwo = false;// �Ƿ�ѭ��
				isContinuousThree = false;// �Ƿ�ʮ��·��˫ѭ��
				isContinuousFour = false;// �Ƿ��ʮ��·��˫ѭ��
				// �ж��ݺ᷽��
				isHorizontal = getIsHorizontal(currentDirections, isHorizontal);
				// �Ƿ�ѭ����������ͬ����ָ����ѭ������
				isContinuousTwo = getIsContinuousTwo(str, nextDifferenceCode,
						isContinuousTwo, i);
				// �����ʮ��·�ڣ�hashCode%2Ϊ���ʾ��ʮ��·�ڣ�������
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
				} else {// �������ʮ��·��
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
				// ����3��ת���Ϊ1��ת��
				difference = ProcessingDirection(difference);
				// ��Ե�����ж�
				difference = edgeJudgment(coordinates, currentCoordinates,
						difference);
				// ����У��(ִֻ��ת��)
				currentDirections = adjustmentInDirection(currentDirections,
						nextDifferenceCode, coordinates[coordinatesIndex]);
				// �߼�ִ��ѭ��ǰ��ѭ����i�����������±�����
				if (isCrossroad) {
					System.out.println("Path:" + "��ʮ��·��");
					if (isContinuousFour) {// �Ƿ�ѭ��ǰ��ѭ��
						System.out.println("Path:" + "ѭ��ǰ��ѭ��");

						// client.lineTwoLattice();

						i += 3;
						coordinatesIndex += 3;
					} else {// ��ִ��ѭ��ǰ��ѭ��
						// ���������������ָͬ�������������λΪ1��7��ִ��ѭ��
						if (isContinuousTwo || "1".equals(endCoordinatesTwo)
								|| "7".equals(endCoordinatesTwo)) {

							System.out.println("Path:" + "ѭ��");

							// client.line(LINE_SPEED);
							i++;
							coordinatesIndex++;
						} else {
							if (isHorizontal) {
								if (isEdge) {
									System.out.println("Path:" + "��Ե�����ѭ��");
									// //client.lineHalf(LINE_SPEED, 800);
									// client.lineCodeDisk(LINE_SPEED, 35);
								} else {
									System.out.println("Path:" + "�����ѭ��");
									// //client.lineHalf(LINE_SPEED, 1100);
									// client.lineCodeDisk(LINE_SPEED, 60);
								}
							} else {
								System.out.println("Path:" + "�����ѭ��");
								// //client.lineHalf(LINE_SPEED, 500);
								// client.lineCodeDisk(LINE_SPEED, 55);
							}
							// ��ѭ���������������continue����ִ�ж�������
							i++;
							coordinatesIndex++;
							continue;
						}
					}
				} else {
					System.out.println("Path:" + "��ʮ��·��");
					if (isContinuousThree) {
						System.out.println("Path:" + "ѭ��ǰ��ѭ��");

						// client.lineTwoLattice();

						i += 2;
						coordinatesIndex += 2;
					} else {
						System.out.println("Path:" + "ѭ��");

						// client.line(LINE_SPEED);
					}
				}
				if (i + 1 < str.length) {// �߼�ִ��ǰ����ǰ��ת��
					// �Ƿ�ǰ��ת��
					if (currentDirections != String.valueOf(str[i + 1])
							.hashCode()) {
						difference = String.valueOf(str[i + 1]).hashCode()
								- currentDirections;
						// ����ת�䳬�����ε����
						difference = ProcessingDirection(difference);
						switch (difference) {
						case 1:// ǰ����ת
							System.out.println("Path:" + "ǰ����ת");
							// client.goRight();
							currentDirections = String.valueOf(str[i + 1])
									.hashCode();
							break;

						case -1:// ǰ����ת
							System.out.println("Path:" + "ǰ����ת");
							// client.goLeft();
							currentDirections = String.valueOf(str[i + 1])
									.hashCode();
							break;

						default:
							break;
						}
					} else if (!endCoordinatesTwo.equals("7")
							&& !endCoordinatesTwo.equals("1")) {// ǰ��
						System.out.println("Path:" + "ǰ��");
						// client.go(LINE_SPEED, CODE_DISK);
					}
				} else if (!endCoordinatesTwo.equals("7")
						&& !endCoordinatesTwo.equals("1")) {// ǰ��
					System.out.println("Path:" + "ǰ��");
					// client.go(LINE_SPEED, CODE_DISK);
				}

				// ѭ�����������±��ճ�����
				if (coordinatesIndex + 1 < coordinates.length) {
					coordinatesIndex++;
				}
				System.out.println("Path:ѭ������ʱ����"
						+ coordinates[coordinatesIndex]);
			}

			if (!endToward.equals("") || !endToward.equals(null)) {
				// ���ƽ�������
				adjustmentInDirection(currentDirections, endDirections,
						coordinates[coordinatesIndex]);
			}

		}
	}

	/**
	 * �жϳ�ͷ�ݺᳯ�򣬶���Ϊ�ᣬ�ϱ�Ϊ��
	 * 
	 * @param currentDirections
	 *            ��ǰ������hashCode
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
	 * �ж��Ƿ�ִ��ѭ��������������ͬ�����ָ��ʱΪ��
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
	 * �ж��Ƿ�ΪA��A1~A7����G��G1~G7����Ե��
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
	 * Stringתchar[]
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
	 * �Ƿ�ʮ��·��ѭ��ǰ��ѭ��������4��ָ��Ϊ��ͬ����Ϊ��
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
	 * �Ƿ��ʮ��·��ѭ��ǰ��ѭ��������3��ָ��Ϊ��ͬ����Ϊ��
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
	 * �����������ָ������֮�󼴿�ִ��ѭ���Ȳ���
	 * 
	 * @param currentDirections
	 *            ��ǰ����
	 * @param nextDifferenceCode
	 *            Ŀ�귽��
	 * @param coordinates
	 * @param difference
	 *            ������ķ���hashCode��ֵ
	 * @return
	 */
	public static int adjustmentInDirection(int currentDirections,
			int nextDifferenceCode, String coordinates) {
		// �����ֵ
		System.out.println("��ǰ����:" + currentDirections + "��������:"
				+ nextDifferenceCode);
		// ��ǰλ��Ϊʮ��·��ʱ��ִ�г���1�ε�ת��
		// ��ǰ���򲻵���Ŀ�귽��
		if (nextDifferenceCode != currentDirections) {
			int difference = ProcessingDirection(nextDifferenceCode
					- currentDirections);
			// ��ǰ�����Ϊʮ��·��
			if (coordinates.hashCode() % 2 == 0) {
				// System.out.println(difference);
				switch (difference) {
				// ��ת
				case 1:
					System.out.println("Path:" + "��ת");
					// client.right(SWERVE_SPEED);
					break;
				// ��ת2
				case 2:
					System.out.println("Path:" + "��ת2��");
					// client.rightChangeDirection();
					break;
				// ��ת
				case -1:
					System.out.println("Path:" + "��ת");
					// client.left(SWERVE_SPEED);
					break;
				// ��ת2
				case -2:
					System.out.println("Path:" + "��ת2��");
					// client.leftChangeDirection();
					break;

				default:
					break;
				}
				// ���µ�ǰ����
			} else {// ��ǰ������ʮ��·��
				// System.out.println(difference);
				// ��ǰ����ȡģ2=0��ʾ��ʼΪ����
				if (currentDirections % 2 == 0) {// ��ǰ����Ϊ����
					if (Math.abs(difference) == 2) {// �������ת����
						System.out.println("Path:" + "����Ѱ��������ת");
						// client.right(SWERVE_SPEED);
					} else {// ѭ�����ϰ�ת
						if (difference == 1) {
							System.out.println("Path:" + "����Ѱ�������Ұ�ת");
							// client.rightCodeDisk(SWERVE_SPEED,
							// SWERVE_ON_LINE_MIDDLE);
						} else if (difference == -1) {
							System.out.println("Path:" + "����Ѱ���������ת");
							// client.leftCodeDisk(SWERVE_SPEED,
							// SWERVE_ON_LINE_MIDDLE);
						}
					}
				} else {// ��ǰ����Ϊ����
					if (Math.abs(difference) == 2) {// ���籱ת����
						System.out.println("Path:" + "����Ѱ��������ת");
						// client.right(SWERVE_SPEED);
					} else {// ѭ�����ϰ�ת
						if (difference == 1) {
							System.out.println("Path:" + "����Ѱ�������Ұ�ת");
							// client.rightCodeDisk(SWERVE_SPEED,
							// SWERVE_ON_LINE_MIDDLE);
						} else if (difference == -1) {
							System.out.println("Path:" + "����Ѱ���������ת");
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
	 * �ж��Ƿ�Ϊ��Ե�㣨�����ֲ���Ϊ1��7�Ĳ��֣�������A��G����ת�����Ϊ1
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
	 *            ����string���ͷ��򣨶���������
	 * @param currentDirections
	 *            ת��Ϊ��Ӧ��hashCode
	 * @return
	 */
	private static int initDirection(String toward) {
		if ("��".equals(toward)) {
			return 8592;
		} else if ("��".equals(toward)) {
			return 8593;
		} else if ("��".equals(toward)) {
			return 8594;
		} else if ("��".equals(toward)) {
			return 8595;
		}

		return 0;
	}

	/**
	 * ����ת���������2������3��ת�䣩��������޸�Ϊת��1�μ������
	 * 
	 * @param difference
	 *            ����hashCode��ֵ
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
