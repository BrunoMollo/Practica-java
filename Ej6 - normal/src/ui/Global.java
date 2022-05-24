package ui;


import java.time.ZoneId;
import java.time.format.DateTimeFormatter;


public class Global {
	public static final String formatoFecha="d-M-yyyy";
	public static final String formatoHora="HH:mm:ss";
	public static final String formatoFechaHora=formatoFecha+" "+formatoHora;
	
	public static final ZoneId usoHorarioCliente=ZoneId.of("UTC-3");
	
	public static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(formatoFecha).withZone(usoHorarioCliente);
	public static DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(formatoHora).withZone(usoHorarioCliente);
	public static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(formatoFecha+" "+formatoHora).withZone(usoHorarioCliente);

}
