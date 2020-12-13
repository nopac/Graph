import java.awt.Font;
import java.awt.MouseInfo;
import java.util.Random;
import java.util.Stack;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.Light.Point;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

public class GraphTest_2 extends Application
{
	public static void main(String[] args)
	{
		launch(args);
	}

	@Override
	public void start(final Stage primaryStage) throws Exception
	{
		// TODO Auto-generated method stub
		Object[] chars = {'A','B','C','D','E','F'};
		Edge e1 = new Edge(0,1,20);
		Edge e2 = new Edge(1,2,15);
		Edge e3 = new Edge(1,3,37);
		Edge e4 = new Edge(2,3,18);
		Edge e5 = new Edge(3,0,23);
		Edge e6 = new Edge(2,4,5);
		Edge e7 = new Edge(3,5,13);
		Edge e8 = new Edge(4,3,11);
		Edge e9 = new Edge(4,5,8);
		Edge e10 = new Edge(2,5,19);
		Edge e11 = new Edge(0,2,38);
		final Edge[] edges = {e1,e2,e3,e4,e5,e6,e7,e8,e9,e10,e11};
		final Graph graphs = new Graph(chars,edges);
		final Group group = new Group();
		Scene scene = new Scene(group);
		final Button flush = new Button("Reset");
		final Button BFS = new Button("BFS");
		final Button DFS = new Button("DFS");
		final Button Prim = new Button("Prim");
		final Button Floyd = new Button("Floyd");
		final Circle circles[] = new Circle[graphs.vertexCounts];
		final Label labels[] = new Label[graphs.vertexCounts];
		final Line[][] lines = new Line[graphs.vertexCounts][graphs.vertexCounts];
		final Label[][] label2 = new Label[graphs.vertexCounts][graphs.vertexCounts];
		final Random rand = new Random();
		for(int i=0;i<circles.length;i++)
		{	
			labels[i] = new Label(String.valueOf(graphs.vertexes.get(i)));
			labels[i].setLayoutX(rand.nextInt(800)+50);
			labels[i].setLayoutY(rand.nextInt(800)+50);
			javafx.scene.text.Font font = new javafx.scene.text.Font(25);
			labels[i].setFont(font);
			circles[i] = new Circle(30,Color.GREENYELLOW);
			circles[i].setCenterX(labels[i].getLayoutX()+7);
			circles[i].setCenterY(labels[i].getLayoutY()+20);
			for(int j = 0;j<graphs.vertexCounts;j++)
			{
				lines[i][j] = new Line();
				label2[i][j] = new Label();
			}
		}
		
		for(int i=0;i<graphs.vertexCounts;i++)
		{
			for(int j=0;j<graphs.vertexCounts;j++)
			{
				if(graphs.edges[i][j] > 0 && graphs.edges[i][j] < 65535)
				{
					Line line;
					Label label = new Label(String.valueOf(graphs.edges[i][j]));
					javafx.scene.text.Font font = new javafx.scene.text.Font(25);
					label.setFont(font);
					line = new Line(circles[i].getCenterX(),circles[i].getCenterY(),circles[j].getCenterX(),circles[j].getCenterY());
					label.setLayoutX((line.getEndX()+line.getStartX())/2);
					label.setLayoutY((line.getEndY()+line.getStartY())/2);
					lines[i][j] = line;
					label2[i][j] = label;
					group.getChildren().add(line);
					group.getChildren().add(label);
				}
			}
		}
		
		for(int i=0;i<graphs.vertexCounts;i++)
		{
			final int temp = i;
			circles[i].setOnMouseDragged(new EventHandler<MouseEvent>()
			{
				public void handle(MouseEvent event)
				{
					// TODO Auto-generated method stub
				    circles[temp].setCenterX(event.getSceneX());
					circles[temp].setCenterY(event.getSceneY());
					labels[temp].setLayoutX(event.getSceneX()-7);
					labels[temp].setLayoutY(event.getSceneY()-20);
					javafx.scene.text.Font font = new javafx.scene.text.Font(25);
					labels[temp].setFont(font);
					for(int j = 0;j<graphs.vertexCounts;j++)
					{
						if(graphs.edges[temp][j] > 0 && graphs.edges[temp][j] < 65535)
						{
							
							lines[temp][j].setStartX(event.getSceneX());
							lines[temp][j].setStartY(event.getSceneY());
							lines[temp][j].setEndX(circles[j].getCenterX());
							lines[temp][j].setEndY(circles[j].getCenterY());
						}
						if(graphs.edges[j][temp] > 0 && graphs.edges[j][temp] < 65535)
						{
							lines[j][temp].setEndX(event.getSceneX());
							lines[j][temp].setEndY(event.getSceneY());
							lines[j][temp].setStartX(circles[j].getCenterX());
							lines[j][temp].setStartY(circles[j].getCenterY());
						}
						label2[temp][j].setLayoutX((lines[temp][j].getStartX()+lines[temp][j].getEndX())/2);
						label2[temp][j].setLayoutY((lines[temp][j].getStartY()+lines[temp][j].getEndY())/2);
						label2[j][temp].setLayoutX((lines[j][temp].getStartX()+lines[j][temp].getEndX())/2);
						label2[j][temp].setLayoutY((lines[j][temp].getStartY()+lines[j][temp].getEndY())/2);		
					}
					
				}
			});
		}	
		
		flush.setPrefHeight(60);
		flush.setPrefWidth(150);
		flush.setLayoutX(50);
		flush.setLayoutY(850);
		flush.setOnAction(new EventHandler<ActionEvent>()
		{
			
			public void handle(ActionEvent arg0)
			{
				// TODO Auto-generated method stub
				group.getChildren().clear();
				for(int i=0;i<circles.length;i++)
				{	
					circles[i].setRadius(30);
					circles[i].setFill(Color.GREENYELLOW);
					circles[i].setCenterX((double)rand.nextInt(800)+50);
					circles[i].setCenterY((double)rand.nextInt(800)+50);
					labels[i].setText((String.valueOf(graphs.vertexes.get(i))));
					labels[i].setLayoutX(circles[i].getCenterX()-7);
					labels[i].setLayoutY(circles[i].getCenterY()-20);
					javafx.scene.text.Font font = new javafx.scene.text.Font(25);
					labels[i].setFont(font);
				}
				for(int i=0;i<graphs.vertexCounts;i++)
				{
					for(int j=0;j<graphs.vertexCounts;j++)
					{
						if(graphs.edges[i][j] > 0 && graphs.edges[i][j] < 65535)
						{
							Line line = null;
							if(graphs.edges[i][j] > 0 && graphs.edges[i][j] < 65535)
							{
								line = new Line(circles[i].getCenterX(),circles[i].getCenterY(),circles[j].getCenterX(),circles[j].getCenterY());
							}
							lines[i][j] = line;
							Label label = new Label(String.valueOf(graphs.edges[i][j]));
							label.setLayoutX((line.getEndX()+line.getStartX())/2);
							label.setLayoutY((line.getEndY()+line.getStartY())/2);
							javafx.scene.text.Font font = new javafx.scene.text.Font(25);
							label.setFont(font);
							label2[i][j] = label;
							group.getChildren().add(line);
							group.getChildren().add(label);
						}
					}
				}
			
				group.getChildren().addAll(circles);
				group.getChildren().addAll(labels);
				group.getChildren().addAll(flush,BFS,DFS,Prim,Floyd);
			}
		});
		
		
		BFS.setPrefHeight(60);
		BFS.setPrefWidth(150);
		BFS.setLayoutX(250);
		BFS.setLayoutY(850);
		BFS.setOnAction(new EventHandler<ActionEvent>()
		{

			public void handle(ActionEvent event)
			{
				// TODO Auto-generated method stub
				boolean[] next = new boolean[graphs.vertexCounts];
				for(boolean i : next)
				{
					i = false;
				}
				for(int i=0;i<graphs.adjacencyList.size();i++)
				{
					Node p = (Node) graphs.adjacencyList.get(i);
					p = p.next;
					while(p != null && next[p.edgeInfo.to] != true)
					{
						System.out.print(graphs.vertexes.get(p.edgeInfo.from)+"--"+p.edgeInfo.weight+"-->"+graphs.vertexes.get(p.edgeInfo.to)+"  ");
						next[p.edgeInfo.to] = true;
						lines[p.edgeInfo.from][p.edgeInfo.to].setStrokeWidth(6);
						p = p.next;
					}
					System.out.println("\n");
				}
			}
		});
		
		
		DFS.setPrefHeight(60);
		DFS.setPrefWidth(150);
		DFS.setLayoutX(450);
		DFS.setLayoutY(850);
		DFS.setOnAction(new EventHandler<ActionEvent>()
		{

			public void handle(ActionEvent event)
			{
				// TODO Auto-generated method stub
				boolean[] next = new boolean[graphs.vertexCounts];
				Stack stack = new Stack();
				int flag = 0;
				int count = 0;
				for(boolean i : next)
				{
					i = false;
				}
				stack.push(0);
				next[0] = true;
				for(int i=0;i<graphs.vertexCounts;)		//从下标0出发
				{
					for(int j=0;j<graphs.vertexCounts;j++)
					{
						if(next[j] !=true && graphs.edges[i][j] > 0 && graphs.edges[i][j] < 65535)
						{
							next[j] = true;
							System.out.print(graphs.vertexes.get(i)+" --"+graphs.edges[i][j]+"--> "+graphs.vertexes.get(j)+"  ");
							lines[i][j].setStrokeWidth(6);
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
					if(count == graphs.vertexCounts-1)
					{
						break;
					}
				}
			}
		});
		
		Prim.setPrefHeight(60);
		Prim.setPrefWidth(150);
		Prim.setLayoutX(650);
		Prim.setLayoutY(850);
		Prim.setOnAction(new EventHandler<ActionEvent>()
		{

			public void handle(ActionEvent event)
			{
				// TODO Auto-generated method stub
				int count = 0;
				int edgeArray[][] = new int[graphs.vertexCounts][graphs.vertexCounts];
				Edge minEdge = new Edge(0,0,65535);
				boolean[] next = new boolean[graphs.vertexCounts];
				for(int i=0;i<graphs.vertexCounts;i++)			//初始化next数组，复制邻阶矩阵
				{
					next[i] = false;
					for(int j=0;j<graphs.vertexCounts;j++)
					{
						edgeArray[i][j] = graphs.edges[i][j];
					}
				}
				next[0] = true;
				ArrayList<Edge> result = new ArrayList<Edge>();
				ArrayList<Integer> nodes = new ArrayList<Integer>();
				nodes.add(0);
				while(count < graphs.vertexCounts)
				{
					for(int k : nodes)
					{
						for(int j = 0;j<graphs.vertexCounts;j++)
						{
							if(next[j] !=true && edgeArray[k][j] > 0 && minEdge.weight > edgeArray[k][j])
							{
								minEdge = new Edge(k,j,edgeArray[k][j]);
							}
						}
						
					}
					edgeArray[minEdge.from][minEdge.to] = 0;
					result.add(minEdge);
					next[minEdge.to] = true;
					System.out.println(graphs.vertexes.get(minEdge.from));
					lines[minEdge.from][minEdge.to].setStrokeWidth(6);
					nodes.add(minEdge.to);
					minEdge = new Edge(0,0,65535);
					count++;
				}
				for(int j = 0;j<result.size();j++)
				{
					System.out.println(graphs.vertexes.get(result.get(j).from)+"--"+result.get(j).weight+"-->"+graphs.vertexes.get(result.get(j).to)+"\n");
				}
			}
		});
		
		
		Floyd.setPrefHeight(60);
		Floyd.setPrefWidth(150);
		Floyd.setLayoutX(850);
		Floyd.setLayoutY(850);
		Floyd.setOnAction(new EventHandler<ActionEvent>()
		{

			public void handle(ActionEvent event)
			{
				// TODO Auto-generated method stub
				System.out.println("\n\nFloyd:");
				graphs.Floyed();
			}
		});
		
		group.getChildren().addAll(circles);
		group.getChildren().addAll(labels);
		group.getChildren().addAll(flush,BFS,DFS,Prim,Floyd);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Graph");
		primaryStage.setWidth(1050);
		primaryStage.setHeight(1000);
		primaryStage.show();
	}
}
