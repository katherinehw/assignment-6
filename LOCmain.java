package herman;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileSystemView;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;

public class LOCmain {

	private JFrame frame;
	private JTextField textFieldOut;
	private JButton btnChooseFile;
	private JButton btnCalcLoc;
	private JFileChooser choose;
	private File file;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LOCmain window = new LOCmain();
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
	public LOCmain() {
		initialize();
		createEvents();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Find Lines of Code");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(146, 19, 151, 16);
		frame.getContentPane().add(lblNewLabel);

		btnChooseFile = new JButton("Choose File");
		btnChooseFile.setBounds(160, 59, 117, 29);
		frame.getContentPane().add(btnChooseFile);

		btnCalcLoc = new JButton("How Many Lines of Code?");
		btnCalcLoc.setBounds(112, 109, 222, 29);
		frame.getContentPane().add(btnCalcLoc);

		textFieldOut = new JTextField();
		textFieldOut.setBounds(160, 180, 130, 26);
		frame.getContentPane().add(textFieldOut);
		textFieldOut.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("lines");
		lblNewLabel_1.setBounds(297, 185, 61, 16);
		frame.getContentPane().add(lblNewLabel_1);
	}

	private void createEvents() {
		btnChooseFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chooseFile();

			}
		});

		btnCalcLoc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calcLoc();

			}
		});
	}

	private void chooseFile() {
		// choose file code:
		File F = new File("/Users/KatherineHerman-Williams/eclipse-workspace/");
		choose = new JFileChooser(F, FileSystemView.getFileSystemView());

		int i = choose.showOpenDialog(null);

		if (i == JFileChooser.APPROVE_OPTION) {
			file = new File(choose.getSelectedFile().getAbsolutePath());

		} else {
			JOptionPane.showMessageDialog(null, "Please choose a file.");
		}
	}

	private void calcLoc() {
		LineCounter count = new LineCounter(this.file);
		try {
			textFieldOut.setText(String.valueOf(count.search()));
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}

	}

}
