
public class Node<T>
{
	public Edge edgeInfo;	//从出发点到达该节点的边信息
	public Node next;		//构造邻接表是用以连接链表
	public Node()
	{
		this.edgeInfo = null;
		this.next = null;
	}
	public Node(Edge edgeInfo)	//构造方法
	{
		this.edgeInfo = edgeInfo;
		this.next = null;
	}
}
