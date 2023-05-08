package modelo;

import java.math.BigDecimal;
import java.sql.Date;
//import java.util.Date;

public class Reservas {
	private int Reservas_ID;
	private Date FechaEntrada;
	private Date FechaSalida;
	private BigDecimal Valor;
	private String FormaPago;
	private String LocalizaReserva;
	private int Tipo_ocupacionId;
	
		
	public Reservas() {
	}

	public Reservas(Date fechaEntrada, 
					Date fechaSalida, 
					BigDecimal valor, 
					String formaPago, 
					String localizaReserva, 
					int tipo_ocupacionId) {
		FechaEntrada = fechaEntrada;
		FechaSalida = fechaSalida;
		Valor = valor;
		FormaPago = formaPago;
		LocalizaReserva = localizaReserva;
		Tipo_ocupacionId = tipo_ocupacionId;
	}
	
	
	public Reservas(int reservas_ID, 
					Date fechaEntrada, 
					Date fechaSalida, 
					BigDecimal valor, 
					String formaPago,
					String localizaReserva, 
					int tipo_ocupacionId) {
		Reservas_ID = reservas_ID;
		FechaEntrada = fechaEntrada;
		FechaSalida = fechaSalida;
		Valor = valor;
		FormaPago = formaPago;
		LocalizaReserva = localizaReserva;
		Tipo_ocupacionId = tipo_ocupacionId;
	}

	public void setReservas_ID(int reservas_ID) {
		Reservas_ID = reservas_ID;
	}

	public int getReservas_ID() {
		return Reservas_ID;
	}

	public Date getFechaEntrada() {
		return FechaEntrada;
	}

	public void setFechaEntrada(Date fechaEntrada) {
		FechaEntrada = fechaEntrada;
	}

	public Date getFechaSalida() {
		return FechaSalida;
	}

	public void setFechaSalida(Date fechaSalida) {
		FechaSalida = fechaSalida;
	}

	public BigDecimal getValor() {
		return Valor;
	}

	public void setValor(BigDecimal valor) {
		Valor = valor;
	}

	public String getFormaPago() {
		return FormaPago;
	}

	public void setFormaPago(String formaPago) {
		FormaPago = formaPago;
	}

	public String getLocalizaReserva() {
		return LocalizaReserva;
	}

	public void setLocalizaReserva(String localizaReserva) {
		LocalizaReserva = localizaReserva;
	}

	@Override
	public String toString() {
		return "Reservas [Reservas_ID=" + Reservas_ID + ", FechaEntrada=" + FechaEntrada + ", FechaSalida="
				+ FechaSalida + ", Valor=" + Valor + ", FormaPago=" + FormaPago + ", LocalizaReserva=" + LocalizaReserva
				+ ", Tipo_ocupacionId=" + Tipo_ocupacionId + "]";
	}

	public int getTipo_ocupacionId() {
		return Tipo_ocupacionId;
	}

	public void setTipo_ocupacionId(int tipo_ocupacionId) {
		this.Tipo_ocupacionId = tipo_ocupacionId;
	}	
}
