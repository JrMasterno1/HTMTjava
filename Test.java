import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

public class Test {

	private JFrame frame;
	private JTextField value;
	private JTextField result;
	private JComboBox BoxResult;
	private JComboBox BoxValue;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Test window = new Test();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Test() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public static int convertToIntegerFromBinary(String b){
		   String number = b.substring(1);

		   return (b.charAt(0) == '0'?1:-1)*Integer.parseInt(number, 2);
		}
	private void initialize() {
		frame = new JFrame("IEEE 754 Converter");
		frame.setBounds(100, 100, 879, 489);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		value = new JTextField();
		value.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		value.setBounds(192, 62, 505, 44);
		frame.getContentPane().add(value);
		value.setColumns(10);
		
		result = new JTextField();
		result.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		result.setBounds(192, 150, 505, 44);
		frame.getContentPane().add(result);
		result.setColumns(10);
		result.setEditable(false);
		//------------------
		JButton Button = new JButton("Convert !");
		Button.setFont(new Font("Times New Roman", Font.BOLD, 20));
		Button.setBounds(325, 283, 188, 93);
		frame.getContentPane().add(Button);
		
		BoxResult = new JComboBox();
		BoxResult.setFont(new Font("Times New Roman", Font.BOLD, 20));
		BoxResult.setModel(new DefaultComboBoxModel(new String[] {"Binary", "Unsigned Decimal", "Hex", "Float", "Signed Decimal"}));
		BoxResult.setBounds(39, 150, 103, 44);
		frame.getContentPane().add(BoxResult);
		
		BoxValue = new JComboBox();
		BoxValue.setFont(new Font("Times New Roman", Font.BOLD, 20));
		BoxValue.setModel(new DefaultComboBoxModel(new String[] {"Binary", "Unsigned Decimal", "Hex", "Float", "Signed Decimal"}));
		BoxValue.setBounds(39, 62, 103, 44);
		frame.getContentPane().add(BoxValue);
		
		
		Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String v = value.getText();
				String vChoice = (String) BoxValue.getItemAt(BoxValue.getSelectedIndex());
				String rChoice = (String) BoxResult.getItemAt(BoxResult.getSelectedIndex());
				
				
				if(vChoice == "Binary") {
					int intBits = Integer.parseInt(v, 2);
					switch(rChoice) {
					case "Binary":
						result.setText(v);
						break;
					case "Float":
						float f = Float.intBitsToFloat(intBits);
						result.setText(String.valueOf(f));
						break;
					case "Hex":
						result.setText(Integer.toHexString(intBits).toUpperCase());
						break;
					case "Unsigned Decimal":
						result.setText(Integer.toString(intBits));
						break;
					case "Signed Decimal":
						result.setText(Integer.toString(convertToIntegerFromBinary(v)));
						break;
					}
				}
				else if (vChoice == "Unsigned Decimal") {
					int unsDec = Integer.parseInt(v);
					switch(rChoice) {
					case "Binary":
						result.setText(Integer.toBinaryString(unsDec));
						break;
					case "Hex":
						result.setText(Integer.toHexString(unsDec).toUpperCase());
						break;
					default:
						result.setText(v);
						break;
					}
				}
				else if (vChoice == "Hex") {
					int unsDec = Integer.parseInt(v, 16);
					switch(rChoice) {
					case "Binary":
						result.setText(Integer.toBinaryString(unsDec));
						break;
					case "Unsigned Decimal":
						result.setText(Integer.toString(unsDec));
						break;
					case "Signed Decimal":
						result.setText(Integer.toString((short)unsDec));
						break;
					case "Float":
						float f = Float.intBitsToFloat(unsDec);
						result.setText(Float.toString(f));
						break;
					default:
						result.setText(v);
						break;
					}
				}
				else if (vChoice == "Float") {
						float f = Float.parseFloat(v);
						switch(rChoice) {
						case "Float":
							result.setText(v);
							break;
						case "Binary":
							int bits = Float.floatToIntBits(f);
							result.setText(Integer.toBinaryString(bits));
							break;
						case "Hex":
							int bit2 = Float.floatToIntBits(f);
							result.setText(Integer.toHexString(bit2).toUpperCase());
							break;
						case "Unsigned Decimal":
							int bits1 = Float.floatToIntBits(f);
							result.setText(Integer.toString(bits1));
							break;
						case "Signed Decimal":
							int bit3 = Float.floatToIntBits(f);
							result.setText(Integer.toString(convertToIntegerFromBinary(Integer.toBinaryString(bit3))));
							break;
						}
				}
				else if (vChoice == "Signed Decimal") {
					int sDec = Integer.parseInt(v);
					switch(rChoice) {
					case "Binary":
						result.setText(Integer.toBinaryString(sDec));
						break;
					case "Float":
						float f = Float.parseFloat(v);
						result.setText(Float.toString(f));
						break;
					case "Unsigned Decimal":
						long udec = Integer.toUnsignedLong(sDec);
						result.setText(Long.toString(udec));
						break;
					case "Hex":
						result.setText(Integer.toHexString(sDec).toUpperCase());
					}
				}
			}
		});

	}
}
