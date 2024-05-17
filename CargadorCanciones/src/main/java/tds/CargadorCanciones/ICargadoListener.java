package tds.CargadorCanciones;

import java.util.EventListener;

public interface ICargadoListener extends EventListener {
	public void enteradoCarga(CancionesEvent cEvent);
}

