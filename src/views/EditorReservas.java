package views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

import dao.ReservasDAO;
import dao.TipoOcupacionDAO;
import factory.ConnectionFactory;
import modelo.Huesped;
import modelo.Reservas;
import modelo.TipoOcupacion;

import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.Toolkit;
import java.beans.PropertyChangeListener;
import java.math.BigDecimal;
import java.sql.Date;
import java.beans.PropertyChangeEvent;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;


@SuppressWarnings("serial")
public class EditorReservas extends JFrame {

	private JPanel contentPane;
	public static JTextField txtValor;
	public static JDateChooser txtFechaEntrada;
	public static JDateChooser txtFechaSalida;
	public static JComboBox<String> txtFormaPago;
	public static JComboBox<TipoOcupacion> txtTipoOcupacion;
	public static JTextField txtReservaLoc;
	
	int xMouse, yMouse;
	private JLabel labelExit;
	private JLabel labelAtras;
	private Huesped huesped = new Huesped();
	private Busqueda busqueda = new Busqueda();
	
	public Huesped getHuesped() {
		return huesped;
	}

	public void setHuesped(Huesped huesped) {
		
		this.huesped.setReservas(new Reservas());
		this.huesped.setReservas(huesped.getReservas());
		
		presentarDatosReserva();
	}

	private void presentarDatosReserva() {
		
		txtTipoOcupacion.setSelectedIndex(this.huesped.getReservas().getTipo_ocupacionId());
		
		txtFechaEntrada.setDate(this.huesped.getReservas().getFechaEntrada());
		txtFechaSalida.setDate(this.huesped.getReservas().getFechaSalida());
		txtValor.setText(String.valueOf(this.huesped.getReservas().getValor()));
		
		txtFormaPago.setSelectedItem(this.huesped.getReservas().getFormaPago());
		
		txtReservaLoc.setText(this.huesped.getReservas().getLocalizaReserva());
		
		
		
	}

	private BigDecimal reserva_Precio;
	
	public BigDecimal getReserva_Precio() {
		return reserva_Precio;
	}

	public void setReserva_Precio(BigDecimal reserva_Precio) {
		this.reserva_Precio = reserva_Precio;
	}

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditorReservas frame = new EditorReservas();
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
	public EditorReservas() {
		super("Reserva");
		huesped.setReservas(new Reservas());
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(EditorReservas.class.getResource("/imagenes/aH-40px.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 560);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.control);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		setUndecorated(true);
		
		
		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 910, 560);
		contentPane.add(panel);
		panel.setLayout(null);
		
		// Código que crea los elementos de la interfáz gráfica
		
		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(SystemColor.textHighlight);
		separator_1_2.setBounds(527, 242, 289, 2);
		separator_1_2.setBackground(SystemColor.textHighlight);
		panel.add(separator_1_2);
		
		JSeparator separator_1_3 = new JSeparator();
		separator_1_3.setForeground(SystemColor.textHighlight);
		separator_1_3.setBackground(SystemColor.textHighlight);
		separator_1_3.setBounds(527, 494, 289, 2);
		panel.add(separator_1_3);
		
		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setForeground(SystemColor.textHighlight);
		separator_1_1.setBounds(527, 329, 289, 11);
		separator_1_1.setBackground(SystemColor.textHighlight);
		panel.add(separator_1_1);
		
		JLabel lblTipoDeOcupacion = new JLabel("TIPO DE OCUPACION");
		lblTipoDeOcupacion.setForeground(SystemColor.textInactiveText);
		lblTipoDeOcupacion.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblTipoDeOcupacion.setBounds(527, 88, 373, 24);
		panel.add(lblTipoDeOcupacion);
		
		JSeparator separator_1_3_1 = new JSeparator();
		separator_1_3_1.setForeground(SystemColor.textHighlight);
		separator_1_3_1.setBackground(SystemColor.textHighlight);
		separator_1_3_1.setBounds(527, 159, 289, 2);
		panel.add(separator_1_3_1);
		
		txtTipoOcupacion = new JComboBox<TipoOcupacion>();
		txtTipoOcupacion.setFont(new Font("Dialog", Font.PLAIN, 16));
		txtTipoOcupacion.setBorder(new LineBorder(new Color(255, 255, 255), 1, true));
		txtTipoOcupacion.setBackground(Color.WHITE);
		txtTipoOcupacion.setBounds(527, 123, 289, 38);
		
		TipoOcupacionDAO tipoOcupacionDAO = new TipoOcupacionDAO(new ConnectionFactory().recuperaConexion());
		
        // CARGA LOS TIPOS DE OCUPACION DESDE LA DATABASE
        var tipoOcupacion = tipoOcupacionDAO.getTipoOcupacionList();
        
        txtTipoOcupacion.addItem(new TipoOcupacion(0, "Elige un Tipo de Ocupación"));
        
        tipoOcupacion.forEach(ocupacion -> txtTipoOcupacion.addItem(ocupacion));
        
        txtTipoOcupacion.addActionListener(new ActionListener() {//add actionlistner to listen for change
            @Override
            public void actionPerformed(ActionEvent e) {    
              //get the selected item    
              //Activa el evento, después del usuario seleccionar las fechas se debe calcular el valor de la reserva
				var tipoOcupacion = (TipoOcupacion) txtTipoOcupacion.getSelectedItem();
				if (tipoOcupacion.getTipo_ocupacionId() != 0)
				{
					setReserva_Precio(tipoOcupacionDAO.getTipoOcupacionPrecio(tipoOcupacion.getTipo_ocupacionId()));
				}
				if(txtFechaEntrada.getDate()!=null && txtFechaSalida.getDate() != null)
				{
					Date EntradasqlDate = new Date(EditorReservas.txtFechaEntrada.getDate().getTime());
			        Date SalidasqlDate = new Date(EditorReservas.txtFechaSalida.getDate().getTime());
					BigDecimal ValorTotal = getReserva_Precio().multiply(new BigDecimal(daysBetween(EntradasqlDate, SalidasqlDate)));
					huesped.getReservas().setValor(ValorTotal);
					txtValor.setText(ValorTotal.toPlainString());
				}
            }
        });
		panel.add(txtTipoOcupacion);
		
		txtReservaLoc = new JTextField("MNBVC");
		txtReservaLoc.setEditable(false);
		txtReservaLoc.setHorizontalAlignment(SwingConstants.CENTER);
		txtReservaLoc.setForeground(new Color(12, 138, 199));
		txtReservaLoc.setFont(new Font("Dialog", Font.BOLD, 20));
		txtReservaLoc.setBounds(791, 47, 109, 42);
		panel.add(txtReservaLoc);
		
		JLabel lblCheckIn = new JLabel("FECHA DE CHECK IN");
		lblCheckIn.setForeground(SystemColor.textInactiveText);
		lblCheckIn.setBounds(527, 187, 373, 14);
		lblCheckIn.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		panel.add(lblCheckIn);
		
		JLabel lblCheckOut = new JLabel("FECHA DE CHECK OUT");
		lblCheckOut.setForeground(SystemColor.textInactiveText);
		lblCheckOut.setBounds(527, 269, 373, 14);
		lblCheckOut.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		panel.add(lblCheckOut);
		
		JLabel lblFormaPago = new JLabel("FORMA DE PAGO");
		lblFormaPago.setForeground(SystemColor.textInactiveText);
		lblFormaPago.setBounds(527, 423, 373, 24);
		lblFormaPago.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		panel.add(lblFormaPago);
				
		JLabel lblTitulo = new JLabel("SISTEMA DE RESERVAS");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(527, 47, 254, 42);
		lblTitulo.setForeground(new Color(12, 138, 199));
		lblTitulo.setFont(new Font("Roboto", Font.BOLD, 20));
		panel.add(lblTitulo);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 482, 560);
		panel_1.setBackground(new Color(12, 138, 199));
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel logo = new JLabel("");
		logo.setBounds(197, 68, 104, 107);
		panel_1.add(logo);
		logo.setIcon(new ImageIcon(EditorReservas.class.getResource("/imagenes/Ha-100px.png")));
		
		JLabel imagenFondo = new JLabel("");
		imagenFondo.setBounds(0, 140, 500, 409);
		panel_1.add(imagenFondo);
		imagenFondo.setBackground(Color.WHITE);
		imagenFondo.setIcon(new ImageIcon(EditorReservas.class.getResource("/imagenes/reservas-img-3.png")));
		
		JLabel lblValor = new JLabel("VALOR DE LA RESERVA");
		lblValor.setForeground(SystemColor.textInactiveText);
		lblValor.setBounds(537, 351, 363, 14);
		lblValor.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		panel.add(lblValor);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(SystemColor.textHighlight);
		separator_1.setBounds(527, 407, 289, 2);
		separator_1.setBackground(SystemColor.textHighlight);
		panel.add(separator_1);
												
		// Componentes para dejar la interfaz con estilo Material Design
		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuPrincipal principal = new MenuPrincipal();
				principal.setVisible(true);
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				 btnexit.setBackground(new Color(12, 138, 199));
			     labelExit.setForeground(Color.white);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(new Color(12, 138, 199));
		btnexit.setBounds(856, 0, 53, 36);
		panel_1.add(btnexit);
		
		labelExit = new JLabel("X");
		labelExit.setForeground(Color.WHITE);
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		
		JPanel header = new JPanel();
		header.setBounds(0, 0, 910, 36);
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
		panel.add(header);
		
		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				busqueda = new Busqueda();
				busqueda.setVisible(true);
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
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		
		
		//Campos que guardaremos en la base de datos
		Calendar c = Calendar.getInstance();
		txtFechaEntrada = new JDateChooser();
		txtFechaEntrada.setMinSelectableDate(c.getTime());
		txtFechaEntrada.getCalendarButton().setBackground(SystemColor.textHighlight);
		txtFechaEntrada.getCalendarButton().setIcon(new ImageIcon(EditorReservas.class.getResource("/imagenes/icon-reservas.png")));
		txtFechaEntrada.getCalendarButton().setFont(new Font("Roboto", Font.PLAIN, 12));
		txtFechaEntrada.setBounds(527, 209, 289, 35);
		txtFechaEntrada.getCalendarButton().setBounds(268, 0, 21, 33);
		txtFechaEntrada.setBackground(Color.WHITE);
		txtFechaEntrada.setBorder(new LineBorder(SystemColor.window));
		txtFechaEntrada.setDateFormatString("yyyy-MM-dd");
		txtFechaEntrada.setFont(new Font("Roboto", Font.PLAIN, 18));
		txtFechaEntrada.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				//Activa el evento, después del usuario seleccionar las fechas se debe calcular el valor de la reserva
				if(txtFechaEntrada.getDate()!=null)
				{
					Date EntradasqlDate = new Date(EditorReservas.txtFechaEntrada.getDate().getTime());
			        
					huesped.getReservas().setFechaEntrada(EntradasqlDate);
					
				}
			}
		});
		panel.add(txtFechaEntrada);

		txtFechaSalida = new JDateChooser();
		txtFechaSalida.setMinSelectableDate(c.getTime());
		txtFechaSalida.getCalendarButton().setIcon(new ImageIcon(EditorReservas.class.getResource("/imagenes/icon-reservas.png")));
		txtFechaSalida.getCalendarButton().setFont(new Font("Roboto", Font.PLAIN, 11));
		txtFechaSalida.setBounds(527, 294, 289, 35);
		txtFechaSalida.getCalendarButton().setBounds(267, 1, 21, 31);
		txtFechaSalida.setBackground(Color.WHITE);
		txtFechaSalida.setFont(new Font("Roboto", Font.PLAIN, 18));
		
		txtFechaSalida.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				//Activa el evento, después del usuario seleccionar las fechas se debe calcular el valor de la reserva
				if(txtFechaEntrada.getDate()!=null && txtFechaSalida.getDate() != null)
				{
					Date EntradasqlDate = new Date(EditorReservas.txtFechaEntrada.getDate().getTime());
			        Date SalidasqlDate = new Date(EditorReservas.txtFechaSalida.getDate().getTime());
					
					huesped.getReservas().setFechaSalida(SalidasqlDate);
					
					if(getReserva_Precio() != null )
					{
						BigDecimal ValorTotal = getReserva_Precio().multiply(new BigDecimal(daysBetween(EntradasqlDate, SalidasqlDate)));
						huesped.getReservas().setValor(ValorTotal);
						txtValor.setText(ValorTotal.toPlainString());
					}
			    }
			}
		});
		txtFechaSalida.setDateFormatString("yyyy-MM-dd");
		txtFechaSalida.getCalendarButton().setBackground(SystemColor.textHighlight);
		txtFechaSalida.setBorder(new LineBorder(new Color(255, 255, 255), 0));
		panel.add(txtFechaSalida);

		txtValor = new JTextField();
		txtValor.setBackground(SystemColor.text);
		txtValor.setForeground(Color.BLACK);
		txtValor.setBounds(527, 376, 289, 33);
		txtValor.setEditable(false);
		txtValor.setFont(new Font("Roboto Black", Font.BOLD, 17));
		txtValor.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		panel.add(txtValor);
		txtValor.setColumns(10);


		txtFormaPago = new JComboBox<String>();
		txtFormaPago.setBounds(527, 458, 289, 38);
		txtFormaPago.setBackground(SystemColor.text);
		txtFormaPago.setBorder(new LineBorder(new Color(255, 255, 255), 1, true));
		txtFormaPago.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtFormaPago.setModel(new DefaultComboBoxModel<String>(new String[] {"Tarjeta de Crédito", "Tarjeta de Débito", "Dinero en efectivo"}));
		panel.add(txtFormaPago);

		JPanel btnsiguiente = new JPanel();
		btnsiguiente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if (EditorReservas.txtFechaEntrada.getDate() != null && EditorReservas.txtFechaSalida.getDate() != null) {	
					
					String formaDePago = txtFormaPago.getSelectedItem().toString();
					BigDecimal valor = new BigDecimal(txtValor.getText());
					
					Date EntradasqlDate = new Date(EditorReservas.txtFechaEntrada.getDate().getTime());
			        Date SalidasqlDate = new Date(EditorReservas.txtFechaSalida.getDate().getTime());
			        int TipoOcupacion = txtTipoOcupacion.getSelectedIndex();
			        
			        huesped.getReservas().setFechaEntrada(EntradasqlDate);
			        huesped.getReservas().setFechaSalida(SalidasqlDate);
			        huesped.getReservas().setValor(valor);
			        huesped.getReservas().setFormaPago(formaDePago);
			        huesped.getReservas().setTipo_ocupacionId(TipoOcupacion);
			        
			        ReservasDAO reservasDAO = new ReservasDAO(new ConnectionFactory().recuperaConexion());
			        reservasDAO.UpdateReserva(huesped.getReservas());
			        //System.out.println(huesped.getReservas());
					
					Exito exito = new Exito(busqueda);
					exito.setVisible(true);
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Debes llenar todos los campos.");
				}
			}						
		});
		btnsiguiente.setLayout(null);
		btnsiguiente.setBackground(SystemColor.textHighlight);
		btnsiguiente.setBounds(527, 514, 122, 35);
		panel.add(btnsiguiente);
		btnsiguiente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		
		JLabel lblSiguiente = new JLabel("GUARDAR");
		lblSiguiente.setHorizontalAlignment(SwingConstants.CENTER);
		lblSiguiente.setForeground(Color.WHITE);
		lblSiguiente.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblSiguiente.setBounds(0, 0, 122, 35);
		btnsiguiente.add(lblSiguiente);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(791, 47, 1, 40);
		panel.add(separator);
		
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
		    
		    private static long daysBetween(Date one, Date two) {
		        long difference =  (one.getTime()-two.getTime())/86400000;
		        return Math.abs(difference);
		    }
}
