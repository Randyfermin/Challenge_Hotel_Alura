package views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import Utils.NumberRenderer;
import dao.HuespedDAO;
import dao.ReservasDAO;
import factory.ConnectionFactory;
import modelo.Huesped;
import modelo.Reservas;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.awt.Color;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTabbedPane;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.PatternSyntaxException;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class Busqueda extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHuespedes;
	private JTable tbReservas;
	private DefaultTableModel modelotbReserva;
	private DefaultTableModel modelotbHuesped;
	private JLabel labelAtras;
	private JLabel labelExit;
	int xMouse, yMouse;
	
	private Huesped huesped = new Huesped();
	private Reservas reserva = new Reservas();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Busqueda frame = new Busqueda();
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
	public Busqueda() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				cargarTablaHuesped();
				cargarTablaReserva();
			}
		});
		setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/imagenes/lupa2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);
		
		txtBuscar = new JTextField();
		txtBuscar.setBounds(632, 115, 253, 28);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);
				
		JLabel lblNewLabel_4 = new JLabel("SISTEMA DE BÚSQUEDA");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setForeground(new Color(12, 138, 199));
		lblNewLabel_4.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblNewLabel_4.setBounds(170, 62, 715, 42);
		contentPane.add(lblNewLabel_4);
		
		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBackground(new Color(12, 138, 199));
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(20, 169, 865, 328);
		contentPane.add(panel);
		
		String[] ReservascolumnNames = {"Código de Reserva",
				"Fecha Check In",
				"Fecha Check Out",
				"Valor",
				"Forma de Pago"};
		
		tbReservas = new JTable()
		{
			private static final long serialVersionUID = 1L;

	        public boolean isCellEditable(int row, int column) {                
	                return false;               
	        };
	        
		};
		
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
		
		modelotbReserva = (DefaultTableModel) tbReservas.getModel();
		
		for (String name : ReservascolumnNames)
    	{
			modelotbReserva.addColumn(name);
    	}
		
		
		TableColumnModel modelColtbReservas = tbReservas.getColumnModel();
		modelColtbReservas.getColumn(3).setCellRenderer(NumberRenderer.getCurrencyRenderer());
		
		
		JScrollPane scroll_table = new JScrollPane(tbReservas);
		panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/imagenes/reservado.png")), scroll_table, null);
		scroll_table.setVisible(true);
		
		int[] ReservascolumnWith = {130,200,200,140,220};
		
		for (int i = 0; i < 5; i++) {
			
			tbReservas.getColumn(ReservascolumnNames[i]).setPreferredWidth(ReservascolumnWith[i]);
			tbReservas.getColumn(ReservascolumnNames[i]).setMinWidth(ReservascolumnWith[i]);
			tbReservas.getColumn(ReservascolumnNames[i]).setMaxWidth(ReservascolumnWith[i]);	
		}
		
		//cargarTablaReserva();
		
		String[] HuespedescolumnNames = {"Código de Huesped",
				"Nombre",
				"Apellido",
				"Fecha de Nacimiento",
				"Nacionalidad",
				"Teléfono",
				"Código de Reserva"};
		
		tbHuespedes = new JTable()
		{
			private static final long serialVersionUID = 1L;

	        public boolean isCellEditable(int row, int column) {                
	                return false;               
	        };
		};
		
		tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 16));
				
		modelotbHuesped = (DefaultTableModel) tbHuespedes.getModel();
		for (String name : HuespedescolumnNames)
    	{
			modelotbHuesped.addColumn(name);
    	}

		
		JScrollPane scroll_tableHuespedes = new JScrollPane(tbHuespedes);
		panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/imagenes/pessoas.png")), scroll_tableHuespedes, null);
		scroll_tableHuespedes.setVisible(true);
		
		int[] HuespedescolumnWith = {120,125,125,120,140,100,125};
		
		for (int i = 0; i < 7; i++) {
			
			tbHuespedes.getColumn(HuespedescolumnNames[i]).setPreferredWidth(HuespedescolumnWith[i]);
			tbHuespedes.getColumn(HuespedescolumnNames[i]).setMinWidth(HuespedescolumnWith[i]);
			tbHuespedes.getColumn(HuespedescolumnNames[i]).setMaxWidth(HuespedescolumnWith[i]);
		}
			
		//cargarTablaHuesped();
		
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/Ha-100px.png")));
		lblNewLabel_2.setBounds(20, 51, 104, 107);
		contentPane.add(lblNewLabel_2);
		
		JPanel header = new JPanel();
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);
			     
			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);
		
		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				 btnAtras.setBackground(Color.white);
			     labelAtras.setForeground(Color.black);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);
		
		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);
		
		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) { //Al usuario pasar el mouse por el botón este cambiará de color
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) { //Al usuario quitar el mouse por el botón este volverá al estado original
				 btnexit.setBackground(Color.white);
			     labelExit.setForeground(Color.black);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(Color.WHITE);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);
		
		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		
		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		separator_1_2.setBounds(632, 148, 253, 10);
		contentPane.add(separator_1_2);
		
		JPanel btnEditar = new JPanel();
		btnEditar.setLayout(null);
		btnEditar.setBackground(new Color(12, 138, 199));
		btnEditar.setBounds(635, 508, 122, 35);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btnEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
					if (panel.getSelectedIndex() == 0)
	                {
		        		DefaultTableModel modeltbReservas = (DefaultTableModel) tbReservas.getModel();
				        int row = tbReservas.getSelectedRow();
				        if (row >= 0)
				        {
				        	String LocalizadorReserva = modeltbReservas.getValueAt(tbReservas.convertRowIndexToModel(row), 0).toString();
				        	ReservasDAO reservasDAO = new ReservasDAO(new ConnectionFactory().recuperaConexion());
				    		    
				        	huesped.setReservas(reservasDAO.getReserva(LocalizadorReserva));
				    		
		                	EditorReservas editorReservas = new EditorReservas();
		                	editorReservas.setHuesped(huesped);
		                
		                	editorReservas.setReserva_Precio(new BigDecimal(modeltbReservas.getValueAt(tbReservas.convertRowIndexToModel(row), 3).toString()));
		                	editorReservas.setVisible(true);
							dispose();
		                	
				        }
	                	
	                }
	                else
	                {
	                	DefaultTableModel modeltbHuespedes = (DefaultTableModel) tbHuespedes.getModel();
	                	int row = tbHuespedes.getSelectedRow();
	    		        if (row >= 0)
	    		        {
	    		        	huesped.setId(Integer.valueOf(modeltbHuespedes.getValueAt(tbHuespedes.convertRowIndexToModel(row), 0).toString()));
							huesped.setNombre(modeltbHuespedes.getValueAt(tbHuespedes.convertRowIndexToModel(row), 1).toString());
							huesped.setApellido(modeltbHuespedes.getValueAt(tbHuespedes.convertRowIndexToModel(row), 2).toString());
							
							String vFechaNac = modeltbHuespedes.getValueAt(tbHuespedes.convertRowIndexToModel(row), 3).toString();
							
							huesped.setFechaNacimiento(Date.valueOf(vFechaNac));
							
							huesped.setNacionalidad(modeltbHuespedes.getValueAt(tbHuespedes.convertRowIndexToModel(row), 4).toString());
							huesped.setTelefono(modeltbHuespedes.getValueAt(tbHuespedes.convertRowIndexToModel(row), 5).toString());
											
							EditorHuesped editorHuesped = new EditorHuesped();
							editorHuesped.setHuesped(huesped);
							editorHuesped.setVisible(true);
							dispose();
	    		        }
			        		
	                }
			}
		});
		contentPane.add(btnEditar);
		
		JLabel lblEditar = new JLabel("EDITAR");
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 122, 35);
		btnEditar.add(lblEditar);
		
		JPanel btnEliminar = new JPanel();
		btnEliminar.setLayout(null);
		btnEliminar.setBackground(new Color(12, 138, 199));
		btnEliminar.setBounds(767, 508, 122, 35);
		btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//SE CREO UN SQL TRIGGER PARA DESHABILITAR EL HUESPED CON LA RESERVA
				//ESTO PERMITE HACER UNA SOLA LLAMADA AL reservasDAO UTLIZANDO EL LOCALIZADOR DE RESERVAS
				
				if (panel.getSelectedIndex() == 0)
                {
					int row = tbReservas.getSelectedRow();
			        if (row >= 0)
			        {
			        	DefaultTableModel modeltbReservas = (DefaultTableModel) tbReservas.getModel();
			        	reserva.setLocalizaReserva(modeltbReservas.getValueAt(tbReservas.convertRowIndexToModel(row), 0).toString());
			        		
			        }	
                }
                else
                {
                	int row = tbHuespedes.getSelectedRow();
			        if (row >= 0)
			        {
			        	DefaultTableModel modeltbHuespedes = (DefaultTableModel) tbHuespedes.getModel();
			        	reserva.setLocalizaReserva(modeltbHuespedes.getValueAt(tbHuespedes.convertRowIndexToModel(row), 6).toString());
			        	
			        }
                }
				
	        	ReservasDAO reservasDAO = new ReservasDAO(new ConnectionFactory().recuperaConexion());
	        	reserva.setReservas_ID(reservasDAO.getReservasIdFromLocalizador(reserva.getLocalizaReserva()));
	        	
				reservasDAO = new ReservasDAO(new ConnectionFactory().recuperaConexion());
				reservasDAO.DesabilitarReserva(reserva);
            			        		
	        	Exito exito = new Exito(Busqueda.this);
				exito.setVisible(true);
	            dispose();
			}
		});
		contentPane.add(btnEliminar);
		
		JLabel lblEliminar = new JLabel("ELIMINAR");
		lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminar.setForeground(Color.WHITE);
		lblEliminar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEliminar.setBounds(0, 0, 122, 35);
		btnEliminar.add(lblEliminar);
		
		setResizable(false);
		
		panel.addChangeListener(new ChangeListener()
		{

			@Override
			public void stateChanged(ChangeEvent e) {
				txtBuscar.setText("");
			}
			
		});
		
		
		//==========UTILIZAR UN FILTRO COMO UNA BUSQUEDA. 
		
		txtBuscar.getDocument().addDocumentListener(new DocumentListener() {
			  @Override
			  public void changedUpdate(DocumentEvent e) {
				  actualizarTablaViews();
			  }
			  @Override
			  public void removeUpdate(DocumentEvent e) {
				  actualizarTablaViews();
			  }
			  @Override
			  public void insertUpdate(DocumentEvent e) {
				  actualizarTablaViews();
			  }

			  private void actualizarTablaViews() {
		        
		    	TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(((DefaultTableModel) ((panel.getSelectedIndex() == 0)? tbReservas.getModel(): tbHuespedes.getModel()))); 
	                RowFilter<TableModel, Object> rf = null;
	                
	                List<RowFilter<Object,Object>> rfs = 
	                
	                		new ArrayList<RowFilter<Object,Object>>();

	                try {
	                    String text = txtBuscar.getText();
	                    String[] textArray = text.split(" ");

	                    for (int i = 0; i < textArray.length; i++) {
	                        
	                    	rfs.add(RowFilter.regexFilter("(?i)" + textArray[i], 0, 2, 6));
	                    	
	                    }

	                    rf = RowFilter.andFilter(rfs);

	                } catch (PatternSyntaxException e) {
	                    return;
	                }

	                sorter.setRowFilter(rf);
	                if (panel.getSelectedIndex() == 0)
	                {
	                	tbReservas.setRowSorter(sorter);
	                }
	                else
	                {
	                	tbHuespedes.setRowSorter(sorter);
	                }
			    }
			});
	}
		
		private void cargarTablaHuesped() {
			

			HuespedDAO huespedDAO = new HuespedDAO(new ConnectionFactory().recuperaConexion());
			
			var huespedes = huespedDAO.getHuespedesList();
			
			modelotbHuesped = (DefaultTableModel) tbHuespedes.getModel();
			modelotbHuesped.getDataVector().removeAllElements();
			modelotbHuesped.fireTableDataChanged(); // notifies the JTable that the model has changed
			
			huespedes.forEach(huesped -> modelotbHuesped.addRow(new Object[] { 	
											huesped.getId(), 
											huesped.getNombre(), 
											huesped.getApellido(), 
											huesped.getFechaNacimiento(), 
											huesped.getNacionalidad(),
											huesped.getTelefono(),
											huesped.getLocalizadorReserva()}));
		
	}

		private void cargarTablaReserva() {
						
			ReservasDAO reservasDAO = new ReservasDAO(new ConnectionFactory().recuperaConexion());
			var reservas = reservasDAO.getReservasList();
			
			modelotbReserva = (DefaultTableModel) tbReservas.getModel();
			modelotbReserva.getDataVector().removeAllElements();
			modelotbReserva.fireTableDataChanged(); // notifies the JTable that the model has changed
			reservas.forEach(reserva -> modelotbReserva.addRow(new Object[] { 	
														reserva.getLocalizaReserva(), 
														reserva.getFechaEntrada(), 
														reserva.getFechaSalida(), 
														reserva.getValor(), 
														reserva.getFormaPago()}));
		
	}

		//Código que permite mover la ventana por la pantalla según la posición de "x" y "y"
	 	private void headerMousePressed(java.awt.event.MouseEvent evt) {
	        xMouse = evt.getX();
	        yMouse = evt.getY();
	    }

	    private void headerMouseDragged(java.awt.event.MouseEvent evt) {
	        int x = evt.getXOnScreen();
	        int y = evt.getYOnScreen();
	        this.setLocation(x - xMouse, y - yMouse);
	    }
	    
	    
}
