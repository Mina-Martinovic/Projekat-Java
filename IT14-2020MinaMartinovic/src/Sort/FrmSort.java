package Sort;

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

public class FrmSort extends JFrame {

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
					FrmSort frame = new FrmSort();
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
	public FrmSort() {
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
		JScrollPane scrollPanePravougaonici = new JScrollPane();
		GridBagConstraints gbc_scrollPanePravougaonici = new GridBagConstraints();
		gbc_scrollPanePravougaonici.fill = GridBagConstraints.BOTH;
		gbc_scrollPanePravougaonici.gridx = 4;
		gbc_scrollPanePravougaonici.gridy = 5;
		pnlCenter.add(scrollPanePravougaonici, gbc_scrollPanePravougaonici);

		JList lstPravougaonici = new JList();
		scrollPanePravougaonici.setViewportView(lstPravougaonici);
		lstPravougaonici.setModel(dlmPravougaonici);

		// gornji panel
		JPanel pnlSouth = new JPanel();
		contentPane.add(pnlSouth, BorderLayout.SOUTH);

		
		JButton btnDodajPravougaonik = new JButton("Dodaj pravougaonik:");
		btnDodajPravougaonik.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DlgSort dlgUnos=new DlgSort();
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
				
				DlgSort dlgPromena=new DlgSort();
				
				
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

		
		JButton btnSortirajPravougaonike= new JButton("Sortiraj pravougaonike");
		btnSortirajPravougaonike.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Sortirani pravougaonici:");
				
				Collections.sort(stkPravougaonici);
				System.out.println(stkPravougaonici);
				
				dlmPravougaonici.removeAllElements();
				for(int i=0; i<stkPravougaonici.size(); i++) {
					
					dlmPravougaonici.addElement(stkPravougaonici.get(i).toString());
				}
			}
		});
		pnlSouth.add(btnSortirajPravougaonike);

		
		
		// donji panel
		JPanel pnlNorth = new JPanel();
		contentPane.add(pnlNorth, BorderLayout.NORTH);
		pnlNorth.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblNaziv = new JLabel("Zadatak 1");
		lblNaziv.setHorizontalAlignment(SwingConstants.CENTER);
		pnlNorth.add(lblNaziv);
		pnlNorth.setBackground(Color.cyan);
	}

}

