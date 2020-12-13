
public class Edge
{
	public int from;	//出发点
	public int to;		//到达点
	public int weight;	//权重
	public Edge()	//构造方法
	{
		this.from = 0;
		this.to = 0;
		this.weight = 0;
	}
	public Edge(int from,int to,int weight)
	{
		this.from = from;
		this.to = to;
		this.weight = weight;
	}
	public Edge(int from,int to)
	{
		this.from = from;
		this.to = to;
		this.weight = 0;
	}
}
