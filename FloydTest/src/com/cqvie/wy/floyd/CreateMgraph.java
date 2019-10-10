package com.cqvie.wy.floyd;

/** 
 * �˴������������ڽӾ��� 
 * ����ʾ 
 * @author С�� 
 */  
  
public class CreateMgraph {  
    //���ݶ�ά���飬�����ڽӾ���  
    public void createMat(MGraph g, int A[][], int n)  
    {                
        int i, j;  
        g.n = n;  
        g.e = 0;  
        for(i = 0; i < n; i++)  
            for(j = 0; j < n; j++)  
            {  
                g.edges[i][j] = A[i][j];  
                if(g.edges[i][j] != MGraph.NULL)  
                    g.e++;  
            }  
    }  
    //����ڽӾ���  
    public void DispMat(MGraph g)  
    {  
        int i, j;  
        for(i = 0; i < g.n; i++)  
        {  
            for(j = 0; j < g.n; j++)  
            {  
                if(g.edges[i][j] == MGraph.NULL)  
                    System.out.print("-" + " ");  
                else  
                    System.out.print(g.edges[i][j] + " ");  
            }  
            System.out.println();  
        }  
    }  
}  