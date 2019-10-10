package com.cqvie.wy.floyd;

/** 
 * 核心代码:生成最短路径 
 * @author 小宇 
 */  
public class GetShortestPath { 
	
	public static String[][] result = new String[48][48];
	public static String[] string=
		{
			"A1","B1","C1","D1","E1","F1","G1",
			"A2","B2","C2","D2","E2","F2","G2",
			"A3","B3","C3","D3","E3","F3","G3",
			"A4","B4","C4","D4","E4","F4","G4",
			"A5","B5","C5","D5","E5","F5","G5",
			"A6","B6","C6","D6","E6","F6","G6",
			"A7","B7","C7","D7","E7","F7","G7"
			};
      
    //Dijkstra算法  
    public static void Dijkstra(MGraph mgraph,int v0)  
    {  
        final int INFINITY = 65535;  
        //用于存储最短路径下标数组  
        int[] pathMatirx = new int[48];  
        //用于存储到各点最短路径权值  
        int[] shortPathTable = new int[48];  
        int min,v,w,k = 0;  
//        finalGot[n] = 8表示求得v0 - vn最短路径  
        int[] finalGot = new int[48];  
        //初始化数据  
        for(v = 0; v  < mgraph.n; v++)  
        {  
            //全部顶点初始化为未知最短路径状态  
            finalGot[v] = 0;  
            //将与与v0有连线的点加上权值  
            shortPathTable[v] = mgraph.edges[v0][v];  
            //初始化路径数组为0  
            pathMatirx[v] = 0;  
        }  
        //v0至v0路径为0  
        shortPathTable[v0] = 0;  
        //v0至v0不须求路径  
        finalGot[v0] = 1;  
        for(v= 1; v < mgraph.n; v++)  
        {  
            min = INFINITY;  
            //寻找距v0最近顶点  
            for(w = 0; w < mgraph.n; w++)  
            {  
                if(finalGot[w] == 0 && shortPathTable[w] < min)  
                {  
                    k = w;  
                    min = shortPathTable[w];  
                }  
            }  
            //将找到的顶点标记为1  
            finalGot[k] = 1;  
            //修正当前最短路径及距离  
            for(w = 0; w < mgraph.n; w++)  
            {  
                //如果经过v顶点的路径比现在这条路径距离短的话  
                if(finalGot[w] == 0 && (min + mgraph.edges[k][w] < shortPathTable[w]))  
                {  
                    shortPathTable[w] = min + mgraph.edges[k][w];  
                    pathMatirx[w] = k;  
                }  
            }  
        }  
        //输出  
        System.out.println("Dijkstra算法求得最短路径:  ");  
        for(v = 1; v < mgraph.n; v++)  
        {  
            k = v;  
            System.out.print(k + "->");  
            while(k != v0)  
            {  
                k = pathMatirx[k];  
                System.out.print(k + "->");  
            }  
            System.out.println();  
        }  
    }  
      
    //-----------------------------------------------------  
      
    //弗洛伊德算法算法基本思想与Dijkstra算法相似  
    public static void Floyd(MGraph mgraph)  
    {  
        int[][] shortPathTable = new int[55][55];  
        int[][] pathMatirx = new int[55][55];  
        int v, w, k;  
        for(v = 0; v < mgraph.n; v++)  
        {  
            for(w = 0; w < mgraph.n; w++)  
            {  
                shortPathTable[v][w] = mgraph.edges[v][w];  
                pathMatirx[v][w] = w;  
            }  
        }  
        for(k = 0; k < mgraph.n; k++)  
        {//1  
            for(v = 0; v < mgraph.n; v++)  
            {//2  
                for(w = 0; w < mgraph.n; w++)  
                {//3  
                    if(shortPathTable[v][w] > shortPathTable[v][k] + shortPathTable[k][w])  
                    {  
                        shortPathTable[v][w] = shortPathTable[v][k] + shortPathTable[k][w];  
                        pathMatirx[v][w] = pathMatirx[v][k];  
                    }                         
                }//3  
            }//2  
        }//1  
        //显示  
//        System.out.println("Floyd算法求得最短路径：");  
        for(v = 0; v < mgraph.n; v++)  
        {  
            for(w = v + 1; w < mgraph.n; w++)  
            {  
            	if (shortPathTable[v][w]==mgraph.NULL) {
            		//如果距离为NULL（1000）则跳出此次循环进入下一次循环
					continue;
				}
//                System.out.print("v" + v + "->v" + w + "weight:" + shortPathTable[v][w] + "  ");  
                k = pathMatirx[v][w];  
//                System.out.print("path:" + v);
//                result[v][w] = v+"->"+w+" 距离为："+shortPathTable[v][w]+"，"+" path: "+String.valueOf(v)+"->";
                result[v][w] = string[v]+"->"+string[w]+" distance:"+shortPathTable[v][w]+","+"path: "+String.valueOf(string[v])+"->";
                while(k != w )  
                {  
//                    System.out.print("->" + k);
//                    result[v][w] += String.valueOf(k)+"->";
                    result[v][w] += String.valueOf(string[k])+"->";
                    k = pathMatirx[k][w];  
                }  
//                System.out.println("->" + w);
//                result[v][w] += String.valueOf(w);
                result[v][w] += String.valueOf(string[w]);
            }  
//            System.out.println();  
        }  
    }  
}  