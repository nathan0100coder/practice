package practice.datastruc.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class GraphUsingMatrix {
    private ArrayList<String> vertexList;  //存储顶点集合
    private int[][] edges;   //存储图对应的邻接矩阵
    private int numOfEdges;  //表示边的个数
    private boolean[] isVisited;//定义一个数组，用于存放是否节点已经访问过

    public static void main(String[] args) {
        //测试
        int n=5;
        String[] Vertexs={"A","B","C","D","E"};
        //创建图对象
        GraphUsingMatrix GraphUsingMatrix =new GraphUsingMatrix(n);
        //循环添加顶点
        for(String vertex:Vertexs){
            GraphUsingMatrix.insertVertex(vertex);
        }
        //添加边
        //A-B A-C B-C B-D B-E
        GraphUsingMatrix.insertEdge(0,1,1);
        GraphUsingMatrix.insertEdge(0,2,1);
        GraphUsingMatrix.insertEdge(1,2,1);
        GraphUsingMatrix.insertEdge(1,3,1);
        GraphUsingMatrix.insertEdge(1,4,1);

        //显示图的邻接矩阵
        GraphUsingMatrix.showGraph();

        //进行测试
        //Graph.dfs();
        GraphUsingMatrix.bfs();
    }

    //获取节点的第一个邻接节点的下标
    /**
     * @param index
     * @return  如果存在就返回对应的下标，不存在返回-1
     */
    public int getFirstNeighbor(int index){
        for(int j=0;j<vertexList.size();j++){
            if(edges[index][j]>0){
                return j;
            }
        }
        return -1;
    }

    //根据前一个邻接节点的下标来获取下一个邻接节点
    public int getNextNeighbor(int V1,int V2){
        for(int j=V2+1;j<vertexList.size();j++){
            if(edges[V1][j]>0){
                return j;
            }
        }
        return -1;
    }

    //深度优先遍历
    public void dfs(boolean[] isVisited,int i){
        //输出这个节点
        System.out.print(vertexList.get(i)+" ->");
        //将这个节点设置为已经访问过了
        isVisited[i]=true;
        //得到下一个邻接节点
        int next=getFirstNeighbor(i);
        //如果得到的值不为-1，说明有下一个邻接节点
        while(next!=-1){
            //并且没有访问过
            if(!isVisited[next]){
                    dfs(isVisited,next);
            }
            //如果next节点已经访问过了
            next=getNextNeighbor(i,next);
        }
    }

    //对dfs进行重载
    public void dfs(){
        for(int i=0;i<vertexList.size();i++){
            if(!isVisited[i]){
                dfs(isVisited,i);
            }
        }
    }

    //广度优先遍历
    public void bfs(boolean[] isVisited,int index){
        int u;  //表示队列的头结点对应下标
        int w;  //邻接节点w
        //队列，记录节点访问的顺序
        LinkedList queue=new LinkedList();
        //访问节点，输出节点的信息
        System.out.print(vertexList.get(index)+" --> ");
        //将该节点加入到队列中
        queue.addLast(index);
        //将其设置为已访问
        isVisited[index]=true;
        while(!queue.isEmpty()){
            //取出队列的头结点下标
            u = (Integer)queue.removeFirst();
            //得到vertexList[index]的邻接节点
            w=getFirstNeighbor(u);
            //如果存在邻接节点
            while(w!=-1){
                //如果没有被访问过
                if(!isVisited[w]){
                    System.out.print(vertexList.get(w)+" --> ");
                    isVisited[w]=true;
                    //入队列
                    queue.addLast(w);
                }
                //以u为前驱点，找w后面的下一个邻接节点
                w=getNextNeighbor(u,w);  //体现了广度优先
            }
        }
    }

    //遍历所有节点，都进行广度优先遍历
    public void bfs(){
        for(int i=0;i<vertexList.size();i++){
            if(!isVisited[i]){
                bfs(isVisited,i);
            }
        }
    }

    //构造器
    public GraphUsingMatrix(int n){
        //初始化矩阵和vertexList
        edges=new int[n][n];
        vertexList=new ArrayList<String>();
        numOfEdges=0;
        isVisited=new boolean[n];
    }

    //返回节点的个数
    public int getNumOfVertex(){
        return vertexList.size();
    }

    //得到边的数目
    public int getNumOfEdges(){
        return numOfEdges;
    }

    //返回节点i(下标)对应的数据 0->A 1->B 2->C
    public String getValueByIndex(int i){
        return vertexList.get(i);
    }

    //返回V1和V2的权值
    public int getWeight(int v1,int v2){
        return edges[v1][v2];
    }

    //显示图对应的矩阵
    public void showGraph(){
        for(int[] link:edges){
            System.out.println(Arrays.toString(link));
        }
    }



    //插入节点
    public void insertVertex(String vertex){
        vertexList.add(vertex);
    }

    //添加边

    /**
     * @param v1  表示点的下标，即第几个顶点  "A"->"B"  "A"=0  "B"=1
     * @param v2  第二个顶点对应的下标
     * @param weight
     */
    public void insertEdge(int v1,int v2,int weight){
        edges[v1][v2]=weight;
        edges[v2][v1]=weight;
        numOfEdges++;
    }

}