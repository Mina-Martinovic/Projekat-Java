package Stack;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;
import javax.swing.JToggleButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class FrmStack extends JFrame {

	private JPanel contentPane;
	private DefaultListModel<String> dlmPravougaonici = new DefaultListModel<String>();
	private Stack<Rectangle1> stkPravougaonici = new Stack<Rectangle1>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmStack frame = new FrmStack();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmStack() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 639, 417);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		// centralni panel
		JPanel pnlCenter = new JPanel();
		contentPane.add(pnlCenter, BorderLayout.CENTER);
		GridBagLayout gbl_pnlCenter = new GridBagLayout();
		gbl_pnlCenter.columnWidths = new int[] { 0, 0, 0, 0, 0, 0 };
		gbl_pnlCenter.rowHeights = new int[] { 33, 0, 0, 0, 0, 0, 0 };
		gbl_pnlCenter.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_pnlCenter.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		pnlCenter.setLayout(gbl_pnlCenter);


		// scroll i lista
		JScrollPane scrollPanePravouganici = new JScrollPane();
		GridBagConstraints gbc_scrollPanepravouganici = new GridBagConstraints();
		gbc_scrollPanepravouganici.fill = GridBagConstraints.BOTH;
		gbc_scrollPanepravouganici.gridx = 4;
		gbc_scrollPanepravouganici.gridy = 5;
		pnlCenter.add(scrollPanePravouganici, gbc_scrollPanepravouganici);

		JList lstPravougaonici = new JList();
		scrollPanePravouganici.setViewportView(lstPravougaonici);
		lstPravougaonici.setModel(dlmPravougaonici);

		// donji panel
		JPanel pnlSouth = new JPanel();
		contentPane.add(pnlSouth, BorderLayout.SOUTH);

		
		JButton btnDodajPravougaonik = new JButton("Dodaj pravougaonik:");
		btnDodajPravougaonik.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DlgStack dlgUnos=new DlgStack();
				dlgUnos.setVisible(true);
				
				if(dlgUnos.isOk) {
					Point1 P1 = new Point1(Integer.parseInt(dlgUnos.xKoordinata.getText()),Integer.parseInt(dlgUnos.yKoordinata.getText()));
					Rectangle1 R = new Rectangle1(P1,Integer.parseInt(dlgUnos.sirina.getText()),Integer.parseInt(dlgUnos.visina.getText()));
					
					stkPravougaonici.push(R);
					
					dlmPravougaonici.removeAllElements();
					for(int i=stkPravougaonici.size()-1; i>=0; i--) {
						
						dlmPravougaonici.addElement(stkPravougaonici.get(i).toString());
				}

				}
			}
		});
		pnlSouth.add(btnDodajPravougaonik);
		
		JButton btnObrisiPravougaonik = new JButton("Obrisi pravougaonik");
		btnObrisiPravougaonik.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DlgStack dlgPromena=new DlgStack();
				
				
				String[] split = dlmPravougaonici.getElementAt(0).split(" ");
				dlgPromena.xKoordinata.setText(split[0]);
				dlgPromena.yKoordinata.setText(split[1]);
				dlgPromena.sirina.setText(split[2]);
				dlgPromena.visina.setText(split[3]);
				dlgPromena.setVisible(true);
				dlmPravougaonici.removeElementAt(0);
			
				
			}
		});
		pnlSouth.add(btnObrisiPravougaonik);

		// gornji panel
		JPanel pnlNorth = new JPanel();
		contentPane.add(pnlNorth, BorderLayout.NORTH);
		pnlNorth.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblNaziv = new JLabel("Zadatak 1:Stack");
		lblNaziv.setHorizontalAlignment(SwingConstants.CENTER);
		pnlNorth.add(lblNaziv);
		pnlNorth.setBackground(Color.cyan);
	}

}

