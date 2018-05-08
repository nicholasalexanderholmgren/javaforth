package edu.mccc.cos210.ds.demo.binarytree;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.lang.reflect.Field;
import javax.swing.JPanel;
import edu.mccc.cos210.ds.IMap;
import edu.mccc.cos210.ds.Map;
import edu.mccc.cos210.ds.binarytree.IBinaryTreeNode;

public class BinaryTreePanel extends JPanel {
    private static final long serialVersionUID = 1L;
	private IBinaryTreeNode<?> tree;
    private int gridwidth;
    private int gridheight;
    private IMap<IBinaryTreeNode<?>, Point> coordinates =
            new Map<IBinaryTreeNode<?>, Point>();
    public BinaryTreePanel(IBinaryTreeNode<?> tree, int gridwidth, int gridheight) {
        this.tree = tree;
        this.gridwidth = gridwidth;
        this.gridheight = gridheight;
    }
    public void setTree(IBinaryTreeNode<?> root) {
        tree = root;
        repaint();
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (tree == null) {
            return;
        }
        Graphics2D g2d = (Graphics2D) g.create();
        tree.traverseInorder(
        	new IBinaryTreeNode.Visitor() {
        		private int x = gridwidth;
				@Override
				public <E> void visit(IBinaryTreeNode<E> node) {
					coordinates.put(node, new Point(x, gridheight * (depth(node) + 1)));
					x += gridwidth;
				}
        	}
        );
        tree.traversePostorder(
        	new IBinaryTreeNode.Visitor() {
        		@Override
        		public <E> void visit(IBinaryTreeNode<E> node) {
        			String data = node.getData().toString();
        			Point center = (Point) coordinates.get(node);
        			if (node.getParent() != null) {
        				Point parentPoint = (Point) coordinates.get(node.getParent());
        				g2d.setColor(Color.BLACK);
        				g2d.drawLine(center.x, center.y, parentPoint.x, parentPoint.y);
        			}
        			FontMetrics fm = g.getFontMetrics();
        			Rectangle r = fm.getStringBounds(data, g).getBounds();
        			r.setLocation(center.x - r.width / 2, center.y - r.height / 2);
        			Color color = getNodeColor(node);
        			Color textColor =
        				(color.getRed() + color.getBlue() + color.getGreen() < 382) ? Color.WHITE : Color.BLACK;
        			g2d.setColor(color);
        			g2d.fillRect(r.x - 2, r.y - 2, r.width + 4, r.height + 4);
        			g2d.setColor(textColor);
        			g2d.drawString(data, r.x, r.y + r.height - r.height / 4);
         		}
        	}
        );
		g2d.dispose();
    }
    private Color getNodeColor(IBinaryTreeNode<?> node) {
        try {
            Field field = node.getClass().getDeclaredField("color");
            return (Color) field.get(node);
        } catch (Exception ex) {
        	System.out.println(ex.getMessage());
            return Color.YELLOW;
        }
    }
    private int depth(IBinaryTreeNode<?> node) {
        return (node.getParent() == null) ? 0 : 1 + depth(node.getParent());
    }
}