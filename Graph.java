import java.util.ArrayList;
import java.util.Stack;
import stack.build.*;
public class Graph<T>
{
	public int vertexCounts;				//顶点数
	public ArrayList<T> vertexes;			//节点集
	public ArrayList<Node> adjacencyList;	//邻接表
	public int[][] edges;					//边集，邻接矩阵
	public static final int MAX_WEIGHT = 0x0000ffff;	//无穷大常数
	public Graph(T[] vertexes,Edge[] edges)	//构造函数
	{
		this.vertexCounts = vertexes.length;
		this.vertexes = new ArrayList<T>();
		for(T i:vertexes)
		{
			this.vertexes.add(i);
		}
		System.out.println(this.vertexes);
		this.edges = new int[this.vertexCounts][this.vertexCounts];			
		this.setEdges(edges);
		this.setAdjacencyList(edges);
	}
	
	private void setEdges(Edge[] edges)				//建立邻接矩阵
	{
		for(int i=0;i<edges.length;i++)
		{
			this.edges[edges[i].from][edges[i].to] = edges[i].weight;
		}
		for(int i=0;i<this.vertexCounts;i++)
		{
			for(int j=0;j<this.vertexCounts;j++)
			{
				if(i != j && this.edges[i][j] == 0)
				{
					this.edges[i][j] = MAX_WEIGHT;
				}
			}
		}
	}
	
	private void setAdjacencyList(Edge[] edges)		//建立邻接表
	{
		this.adjacencyList = new ArrayList<Node>();
		for(int i=0;i<this.vertexCounts;i++)
		{
			this.adjacencyList.add(new Node());
		}
		for(int i=0;i<edges.length;i++)
		{
			Node node = new Node(edges[i]);
			node.next = this.adjacencyList.get(edges[i].from).next;
			this.adjacencyList.get(edges[i].from).next = node;
		}
	}
	
	public void BFS()	//广度优先遍历，基于邻接表
	{
		boolean[] next = new boolean[this.vertexCounts];
		for(boolean i : next)
		{
			i = false;
		}
		for(int i=0;i<this.adjacencyList.size();i++)
		{
			Node p = this.adjacencyList.get(i).next;
			while(p != null && next[p.edgeInfo.to] != true)
			{
				System.out.print(this.vertexes.get(p.edgeInfo.from)+"--"+p.edgeInfo.weight+"-->"+this.vertexes.get(p.edgeInfo.to)+"  ");
				next[p.edgeInfo.to] = true;
				p = p.next;
			}
			System.out.println("\n");
		}
	}
	
	public void DFS()		//深度优先遍历，基于邻接矩阵
	{
		boolean[] next = new boolean[this.vertexCounts];
		Stack stack = new Stack();
		int flag = 0;
		int count = 0;
		for(boolean i : next)
		{
			i = false;
		}
		stack.push(0);			
		next[0] = true;
		for(int i=0;i<this.vertexCounts;)		//从下标0（A）出发
		{
			for(int j=0;j<this.vertexCounts;j++)
			{
				if(next[j] !=true && this.edges[i][j] > 0 && this.edges[i][j] < MAX_WEIGHT)
				{
					next[j] = true;
					System.out.print(this.vertexes.get(i)+" --"+edges[i][j]+"--> "+this.vertexes.get(j)+"  ");
					count++;
					stack.push(j);
					i = j;
					flag = 1;
					break;
				}
			}
			if(flag == 0 && !stack.isEmpty())
			{
				next[i] = true;
				int index = (Integer) stack.pop();
				System.out.println("\n");
				i =  index;
			}
			flag = 0;
			if(count == this.vertexCounts-1)
			{
				break;
			}
		}
	}
	
	
	public void Prim()	//使用Prim算法输出最小生成树的边集
	{
		int count = 0;
		int edgeArray[][] = new int[this.vertexCounts][this.vertexCounts];
		Edge minEdge = new Edge(0,0,MAX_WEIGHT);
		boolean[] next = new boolean[this.vertexCounts];
		for(int i=0;i<this.vertexCounts;i++)			//初始化next数组，复制邻阶矩阵
		{
			next[i] = false;
			for(int j=0;j<this.vertexCounts;j++)
			{
				edgeArray[i][j] = this.edges[i][j];
			}
		}
		next[0] = true;
		ArrayList<Edge> result = new ArrayList<Edge>();			//最小生成树的边集合
		ArrayList<Integer> nodes = new ArrayList<Integer>();	//已选定的节点
		nodes.add(0);
		while(count < this.vertexCounts)
		{
			for(int k : nodes)
			{
				for(int j = 0;j<this.vertexCounts;j++)
				{
					if(next[j] !=true && edgeArray[k][j] > 0 && minEdge.weight > edgeArray[k][j])
					{
						minEdge = new Edge(k,j,edgeArray[k][j]);
					}
				}
				
			}
			edgeArray[minEdge.from][minEdge.to] = 0;
			result.add(minEdge);
			next[minEdge.from] = true;
			nodes.add(minEdge.to);		//加入新节点
			minEdge = new Edge(0,0,MAX_WEIGHT);		//重置
			count++;
		}
		for(int j = 0;j<result.size();j++)
		{
			System.out.println(this.vertexes.get(result.get(j).from)+"--"+result.get(j).weight+"-->"+this.vertexes.get(result.get(j).to)+"\n");
		}
	}
	
	
	public void Floyed()//使用Floyd算法输出两点之间的最短路径
	{
		int edgeArray[][] = new int[this.vertexCounts][this.vertexCounts];
		for(int i=0;i<this.vertexCounts;i++)			//复制邻阶矩阵
		{
			for(int j=0;j<this.vertexCounts;j++)
			{
				edgeArray[i][j] = this.edges[i][j];
			}
		}
		
		for(int vertex=0;vertex<this.vertexCounts;vertex++)
		{
			for(int i=0;i<this.vertexCounts;i++)
			{
				for(int j=0;j<this.vertexCounts;j++)
				{
					if(edgeArray[i][j] >= edgeArray[i][vertex] + edgeArray[vertex][j])
					{
						edgeArray[i][j] = edgeArray[i][vertex] + edgeArray[vertex][j];
					}
				}
			}
		}
		
		System.out.print("     ");
		for(int i = 0;i<this.vertexCounts;i++)
		{
			System.out.print(this.vertexes.get(i)+"     ");
		}
		
		for(int i = 0;i<this.vertexCounts;i++)
		{
			System.out.print("\n"+this.vertexes.get(i)+"  ");
			for(int j=0;j<6;j++)
			{
				if(edgeArray[i][j] == 65535)
				{
					System.out.print("  X  ");
				}
				else
				{
					System.out.printf("  "+edgeArray[i][j]+"  ");
				}
			}
			System.out.println();
		}
	}
	
}
