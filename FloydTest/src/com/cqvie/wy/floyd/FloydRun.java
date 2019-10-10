package com.cqvie.wy.floyd;	

public class FloydRun {

	/**
	 * 
	 * FloydС���н�
	 * @param startPoint
	 *            ���
	 * @param endPoint
	 *            �յ�
	 * @param startDirection��ʼ���귽��
	 * @param endDirection�յ����귽��
	 * @param client�ָ�ͨ��
	 * @param noEntryPoints
	 *            ��ֹͨ�еĵ㼯
	 */
	public static void run(String startPoint, String endPoint,
			String startDirection, String endDirection, String... noEntryPoints) {

		if (startPoint.equals(endPoint) && startDirection.equals(endDirection)) {// ��������յ�����һ������������յ㷽��һ���Ͳ�ִ��Floyd

			return;
		} else {
			// ��ʼ������
			CarShortestWay.arrayInit();
			// ���ñܿ���
			CarShortestWay.setMainCarCoordinates(noEntryPoints);
			// �õ�����ָ��
			String command = CarShortestWay.getPath(new PathPoints(startPoint,
					endPoint));

			System.out.println("=======ָ��====" + command);

			// ����㼯
			String[] pointCollect = CarShortestWay.result3;
			// �ж�
			if (null == pointCollect) {
				// �������յ���ͬ
				Path.getParsingPath20170505(command,
						new String[] { startPoint }, startDirection,
						endDirection);
			} else {
				Path.getParsingPath20170505(command, pointCollect,
						startDirection, endDirection);
			}

			// �ָ��ܿ��㣨������Ҫ�ָ�����Ϊ��Ϊÿ�ε���arrayInitʱ�Զ���ʼ������
			// CarShortestWay.refreshCarCoordinates(noEntryPoints);
		}

	}

}
