package com.cqvie.wy.floyd;	

public class FloydRun {

	/**
	 * 
	 * Floyd小车行进
	 * @param startPoint
	 *            起点
	 * @param endPoint
	 *            终点
	 * @param startDirection起始坐标方向
	 * @param endDirection终点坐标方向
	 * @param client恢复通行
	 * @param noEntryPoints
	 *            禁止通行的点集
	 */
	public static void run(String startPoint, String endPoint,
			String startDirection, String endDirection, String... noEntryPoints) {

		if (startPoint.equals(endPoint) && startDirection.equals(endDirection)) {// 如果起点和终点坐标一样并且起点与终点方向一样就不执行Floyd

			return;
		} else {
			// 初始化矩阵
			CarShortestWay.arrayInit();
			// 设置避开点
			CarShortestWay.setMainCarCoordinates(noEntryPoints);
			// 得到方向指令
			String command = CarShortestWay.getPath(new PathPoints(startPoint,
					endPoint));

			System.out.println("=======指令====" + command);

			// 坐标点集
			String[] pointCollect = CarShortestWay.result3;
			// 行动
			if (null == pointCollect) {
				// 如果起点终点相同
				Path.getParsingPath20170505(command,
						new String[] { startPoint }, startDirection,
						endDirection);
			} else {
				Path.getParsingPath20170505(command, pointCollect,
						startDirection, endDirection);
			}

			// 恢复避开点（不再需要恢复，因为改为每次调用arrayInit时自动初始化矩阵）
			// CarShortestWay.refreshCarCoordinates(noEntryPoints);
		}

	}

}
