package modelo;

import java.math.BigDecimal;

public class TipoOcupacion {
	private int Tipo_ocupacionId;
	private String Ocupacion;
	private BigDecimal Precio;
	
	
	public TipoOcupacion(int tipo_ocupacionId, String ocupacion) {
		Tipo_ocupacionId = tipo_ocupacionId;
		Ocupacion = ocupacion;
	}

	public TipoOcupacion(String ocupacion) {
		Ocupacion = ocupacion;
	}
	
	public TipoOcupacion() {
	}

	public int getTipo_ocupacionId() {
		return Tipo_ocupacionId;
	}

	public void setTipo_ocupacionId(int tipo_ocupacionId) {
		Tipo_ocupacionId = tipo_ocupacionId;
	}

	public String getOcupacion() {
		return Ocupacion;
	}

	public void setOcupacion(String ocupacion) {
		Ocupacion = ocupacion;
	}
	
	public BigDecimal getPrecio() {
		return Precio;
	}

	public void setPrecio(BigDecimal precio) {
		Precio = precio;
	}

	@Override
	public String toString() {
		return this.Ocupacion;
	}
}
