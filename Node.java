
public class Node<T>
{
	public Edge edgeInfo;	//�ӳ����㵽��ýڵ�ı���Ϣ
	public Node next;		//�����ڽӱ���������������
	public Node()
	{
		this.edgeInfo = null;
		this.next = null;
	}
	public Node(Edge edgeInfo)	//���췽��
	{
		this.edgeInfo = edgeInfo;
		this.next = null;
	}
}
