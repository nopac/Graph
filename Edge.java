
public class Edge
{
	public int from;	//������
	public int to;		//�����
	public int weight;	//Ȩ��
	public Edge()	//���췽��
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
