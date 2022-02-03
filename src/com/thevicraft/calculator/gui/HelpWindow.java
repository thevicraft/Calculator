package com.thevicraft.calculator.gui;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.table.*;

@SuppressWarnings("serial")
public class HelpWindow extends JFrame {
	public Font normal; // = new Font("Tahoma", Font.BOLD, 12);

	private static String label = "<html><body>Help Menu:<br>" + "<br>" + "DEL - delete last character<br>"
			+ "AC - delete all<br>" + "<br>"
			+ "E - * 10 ^ (times ten to the power of x; e.g.: 2E3 equals 2 * 10 ^ 3 equals 2*1000 equals 2000)<br>"
			+ "<br>" + "ANS - uses result of last calculation<br>" + "<br>"
			+ "(-) - mathematical sign (not the same as minus operator)<br>" + "<br>"
			+ "M - cycles page 1 and 2 of sin,cos,tan,asin,acos,atan,log,root...<br>" + "<br>" + "<br>" + "<br>";
	// <html><body>Textzeile1<br>Textzeile2</body></html>
	private int FRAME_WIDTH;
	private int FRAME_HEIGHT;

	private JPanel mainPanel;

	private JLabel mainLabel;

	private float sizeFactor;

	private JTable table;

	private Color colorMode;

	private Color textColor;
	
	private JScrollPane scrollPane;

	Object data[][] = { { ",", "Syntax", "Decimal Dot" }, { ".", "Syntax", "Decimal Dot" },
			// { ",", "Syntax", "Decimal Dot" },
			{ ";", "Syntax", "Argument Separator" },

			{ "+", "Operator", "Addition" }, { "-", "Operator", "Subtraction" }, { "*", "Operator", "Multiplication" },
			{ "/", "Operator", "Division" }, { "^", "Operator", "Exponentiation" }, { "!", "Operator", "Factorial" },
			{ "#", "Operator", "Modulo Function" }, { "sqrt()", "Function", "Square Root" },
			{ "sin()", "Function", "Sinus Function" }, { "cos()", "Function", "Cosinus Function" },
			{ "tan()", "Function", "Tangens Function" }, { "asin()", "Function", "sis⁻¹" },
			{ "acos()", "Function", "cos⁻¹" }, { "atan()", "Function", "tan⁻¹" },
			{ "log(b; x)", "Function", "Logarithm to base b" }, { "ln(x)", "Function", "Logarithm to base ℯ" },
			{ "min(a; b)", "Function", "Returns lower Number" }, { "max(a; b)", "Function", "Returns higher Number" },
			{ "gcd(a; b; c)", "Function", "Greatest common divisor" }, { "int(a; x; b; c)", "Function", "Integral" },
			{ "der(f; x)", "Derivation", "Derivation of f (returns f')" },
			{ "der(f; x)", "Syntax", "der( f(x) ; \"x\")" }, { "der(f; x)", "Example", "der( x⁵-2*x² ; x )" },
			{ "der(f; x)", "Note 1", "Insert a number after x and you get" },
			{ "der(f; x)", "Note 1", "the Derivation on that point" },
			{ "der(f; x)", "Note 2", "Leave x alone, then it will" },
			{ "der(f; x)", "Note 2", "draw the Graph in 'Draw Graph'" }, { "...", "...", "..." }

	};

	String column[] = { "Key Word", "Category", "Describtion" };

	public HelpWindow(String titel, int width, int height, float factor, Color mode) {
		FRAME_HEIGHT = (int) (height * factor);
		FRAME_WIDTH = (int) (width * factor);
		colorMode = mode;
		textColor = mode == GuiTaschenrechner.dark ? GuiTaschenrechner.bright : GuiTaschenrechner.dark;
		sizeFactor = factor;
		setTitle(titel);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(new FlowLayout());
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setResizable(true);
		initPanel();
		initComponents();
//		mainPanel.add(mainLabel);
		add(mainPanel);
		setLocationRelativeTo(null);
		setColorOfComponents(mode);

		JScrollPane scroller = new JScrollPane();
		scroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		//scroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroller.getViewport().add(table);
		//scroller.setPreferredSize(new Dimension(FRAME_WIDTH - 10, FRAME_HEIGHT-50));
		scroller.getViewport().setBackground(mode);
		scroller.setPreferredSize(new Dimension(FRAME_WIDTH-10, FRAME_HEIGHT-40));
		//viewport.add(mainLabel);
		//viewport.add(table);
		//viewport.setLayout(new GridLayout(2,1));
		//viewport.setPreferredSize(new Dimension(FRAME_WIDTH - 50, FRAME_HEIGHT - 10));
		mainPanel.add(scroller);
		setVisible(true);
//		scrollPane = new JScrollPane(table);
//		mainPanel.add(scrollPane);
//
//		setVisible(true);
	}

	private void initPanel() {
		mainPanel = new JPanel();
		mainPanel.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT - 10));

	}

	private void initComponents() {
		normal = new Font("System", Font.BOLD, (int) (9 * sizeFactor));
		mainLabel = new JLabel(label);
		mainLabel.setPreferredSize(new Dimension(FRAME_WIDTH - 20, 1500));
		mainLabel.setBackground(Color.black);
		mainLabel.setFont(normal);
		// mainLabel.setText(label);

		// table.sizeColumnsToFit(100);
		// table.setPreferredSize(new Dimension(FRAME_WIDTH-150, 400));

		DefaultTableModel model = new DefaultTableModel(data, column);
		DefaultTableModel dm = new DefaultTableModel() {
			public Class<String> getColumnClass(int columnIndex) {
				return String.class;
			}

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		dm.setDataVector(data, column);
	    table = new JTable(dm){
	        @Override
	        public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
	            Component component = super.prepareRenderer(renderer, row, column);
	            int rendererWidth = component.getPreferredSize().width;
	            TableColumn tableColumn = getColumnModel().getColumn(column);
	            tableColumn.setPreferredWidth(Math.max(rendererWidth + getIntercellSpacing().width, tableColumn.getPreferredWidth()));
	            return component;
	         }
	     };
//		TableCellRenderer tableRenderer;
//		table = new JTable(new JTableButtonModel());
//		tableRenderer = table.getDefaultRenderer(JButton.class);
//		table.setDefaultRenderer(JButton.class, new JTableButtonRenderer(tableRenderer));

//	     TableCellRenderer tableRenderer;
//	      table = new JTable(new JTableButtonModel());
//	      tableRenderer = table.getDefaultRenderer(JButton.class);
//	      table.setDefaultRenderer(JButton.class, new JTableButtonRenderer(tableRenderer));
//	    table.getModel().setValueAt(new JButton("d"), 5, 2);
		table.setFocusable(false);
		table.setDragEnabled(false);
		table.setEnabled(false);
		table.setFont(normal);
		table.setGridColor(Color.green);
		table.setRowHeight((int) (30 * sizeFactor));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		JTableHeader header = table.getTableHeader();
		header.setBackground(Color.pink);
		header.setForeground(Color.yellow);
//        for (int i = 0; i < 3; i++) {
//        	TableColumn column = null;
//            column = table.getTableHeader().getColumnModel().getColumn(i);//tcm.getColumn(i);
//            DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
//            tcr.setHorizontalAlignment(SwingConstants.LEADING);
//            tcr.setForeground(Color.yellow);
//            column.setCellRenderer(tcr);
//        }

		Color[] colors = { Color.red, textColor, textColor };
		int[] layout = { SwingConstants.CENTER, SwingConstants.CENTER, SwingConstants.LEADING };
		Font[] fonts = { normal, normal, normal };

		for (int i = 0; i < 3; i++) {
			TableColumn column = null;
			column = table.getColumnModel().getColumn(i);
			DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
			tcr.setHorizontalAlignment(layout[i]);
			tcr.setForeground(colors[i]);
			tcr.setFont(fonts[i]); // does not work
			column.setCellRenderer(tcr);
		}
	}

	private void setColorOfComponents(Color d) {
		getContentPane().setBackground(d);
		mainPanel.setBackground(d);
		mainLabel.setBackground(d);
		table.setBackground(d);

		if (d.equals(GuiTaschenrechner.dark)) {
			d = GuiTaschenrechner.bright;
		} else {
			d = GuiTaschenrechner.dark;
		}
		mainLabel.setForeground(d);
	}

}

//class JTableButtonRenderer implements TableCellRenderer {
//	private TableCellRenderer defaultRenderer;
//
//	public JTableButtonRenderer(TableCellRenderer renderer) {
//		defaultRenderer = renderer;
//	}
//
//	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
//			int row, int column) {
//		if (value instanceof Component)
//			return (Component) value;
//		return defaultRenderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
//	}
//}

//@SuppressWarnings("serial")
//class JTableButtonModel extends AbstractTableModel {
//	private Object[][] text = { { ",", "Syntax", "Decimal Dot" }, { ".", "Syntax", "Decimal Dot" },
//			// { ",", "Syntax", "Decimal Dot" },
//			{ ";", "Syntax", "Argument Separator" },
//
//			{ "+", "Operator", "Addition" }, { "-", "Operator", "Subtraction" }, { "*", "Operator", "Multiplication" },
//			{ "/", "Operator", "Division" }, { "^", "Operator", "Exponentiation" }, { "!", "Operator", "Factorial" },
//			{ "#", "Operator", "Modulo Function" }, { "sqrt()", "Function", "Square Root" },
//			{ "sin()", "Function", "Sinus Function" }, { "cos()", "Function", "Cosinus Function" },
//			{ "tan()", "Function", "Tangens Function" }, { "asin()", "Function", "sis⁻¹" },
//			{ "acos()", "Function", "cos⁻¹" }, { "atan()", "Function", "tan⁻¹" },
//			{ "log(b; x)", "Function", "Logarithm to base b" }, { "ln(x)", "Function", "Logarithm to base ℯ" },
//			{ "min(a; b)", "Function", "Returns lower Number" }, { "max(a; b)", "Function", "Returns higher Number" },
//			{ "gcd(a; b; c)", "Function", "Greatest common divisor" }, { "int(a; x; b; c)", "Function", "Integral" },
//			{ "der(f; x)", "Derivation", "Derivation of f (returns f')" },
//			{ "der(f; x)", "Syntax", "der( f(x) ; \"x\")" }, { "der(f; x)", "Example", "der( x⁵-2*x² ; x )" },
//			{ "der(f; x)", "Note 1", "Insert a number after x and you get" },
//			{ "der(f; x)", "Note 1", "the Derivation on that point" },
//			{ "der(f; x)", "Note 2", "Leave x alone, then it will" },
//			{ "der(f; x)", "Note 2", "draw the Graph in 'Draw Graph'" }, { "...", "...", "..." }
//
//	};
//	public Object[][] rows;
//
//	public JTableButtonModel() {
//		rows = text;
//		for (int i = 0; i < text.length; i++) {
//			rows[i][2] = new JButton(text[i][2].toString());
//		}
//	}
//
//	private String[] columns = { "Key Word", "Category", "Describtion" };
//
//	public String getColumnName(int column) {
//		return columns[column];
//	}
//
//	public int getRowCount() {
//		return rows.length;
//	}
//
//	public int getColumnCount() {
//		return columns.length;
//	}
//
//	public Object getValueAt(int row, int column) {
//		return rows[row][column];
//	}
//
//	public boolean isCellEditable(int row, int column) {
//		return false;
//	}
//
//	@SuppressWarnings({ "unchecked", "rawtypes" })
//	public Class getColumnClass(int column) {
//		return getValueAt(0, column).getClass();
//	}
//}
