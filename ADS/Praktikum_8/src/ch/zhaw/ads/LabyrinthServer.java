package ch.zhaw.ads;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

public class LabyrinthServer implements CommandExecutor{

    ServerGraphics graphics;
    Graph<LabyrinthNode<Edge>, Edge> labyrinthGraph;
    final double SCALE = 11;
    private LabyrinthNode<Edge> exitNode;

    public LabyrinthServer() {
        graphics = new ServerGraphics();
        labyrinthGraph = new AdjListGraph<LabyrinthNode<Edge>, Edge>(LabyrinthNode.class, Edge.class);
    }

    public String drawBlackTriangle() {
        graphics.setColor(Color.black);
        graphics.drawLine(0.7,0.1,0.9,0.1);
        graphics.drawLine(0.7,0.1,0.8,0.3);
        graphics.drawLine(0.9,0.1,0.8,0.3);
        String triangleXML = graphics.getTrace();
        graphics.clear();
        return triangleXML;
    }

    public String drawRedRectangle() {
        graphics.setColor(Color.red);
        graphics.fillRect(0.25,0.25,0.25,0.25);
        String rectanbleXML = graphics.getTrace();
        graphics.clear();
        return rectanbleXML;
    }

    public String searchExit(LabyrinthNode<Edge> startingNode) {
        if(navigate(startingNode)) {
            graphics.clear();
            LabyrinthNode<Edge> pathNode = this.exitNode;
            while(pathNode.getPrevious() != null) {
                System.out.println(pathNode.name);
                graphics.setColor(Color.red);
                drawPath(graphics, pathNode.name, pathNode.getPrevious().name, true);
                pathNode = pathNode.getPrevious();
            }
            return graphics.getTrace();
        }
        return null;
    }

    public boolean navigate(LabyrinthNode<Edge> currentNode) {
        currentNode.setMark(true);
        String[] coordinates = currentNode.name.split("-");
        int x = Integer.parseInt(coordinates[0]);
        int y = Integer.parseInt(coordinates[1]);
        if(x == 3 && y == 0) {
            this.exitNode = currentNode;
            return true;
        } else {
            for(Edge<LabyrinthNode> edge : currentNode.getEdges()) {
                if(!(edge.getDest().getMark())) {
                    if(navigate(edge.getDest())) {
                        edge.getDest().setPrevious(currentNode);
                        return true;
                    }
                }
            }
        }
        currentNode.setMark(false);
        return false;
    }

    public String parseInput(String input) {
        Reader inputRader = new StringReader(input);
        BufferedReader bReader = new BufferedReader(inputRader);
        String nodeCoordinates;
        graphics.clear();
        graphics.setColor(Color.black);
        graphics.fillRect(0.0,0.0,1.0,1.0);
        graphics.setColor(Color.white);
        try {
            while ((nodeCoordinates = bReader.readLine()) != null) {
                String[] nodes = nodeCoordinates.split(" ");

                //Add Nodes to graph
                labyrinthGraph.addNode(nodes[0]);
                labyrinthGraph.addNode(nodes[1]);

                labyrinthGraph.addEdge(nodes[0],nodes[1],0);
                labyrinthGraph.addEdge(nodes[1],nodes[0],0);

                //Draw Nodes to canvas
                drawPath(graphics, nodes[0], nodes[1], false);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        String labyrinthXML = graphics.getTrace();
        String exitPath = searchExit(labyrinthGraph.findNode("0-6"));
        return labyrinthXML + exitPath;
    }

    private void drawPath(ServerGraphics g, String from, String
            to, boolean mouse) {
        double xh0 = from.charAt(0) - '0';
        double yh0 = from.charAt(2) - '0';
        double xh1 = to.charAt(0) - '0';
        double yh1 = to.charAt(2) - '0';
        double x0 = Math.min(xh0,xh1)/SCALE;
        double y0 = Math.min(yh0,yh1)/SCALE;
        double x1 = Math.max(xh0,xh1)/SCALE;
        double y1 = Math.max(yh0,yh1)/SCALE;
        double w = 1/SCALE;
        if (mouse) g.drawLine(x0+w/2,y0+w/2,x1+w/2,y1+w/2);
        else {
            if (y0 == y1)
                g.fillRect(x0,y0,x1-x0+w,w);
            else
                g.fillRect(x0,y0,w,y1-y0+w);
        }
    }

    @Override
    public String execute(String command) throws Exception {
        return parseInput(command);
    }


}
