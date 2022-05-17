package ui;


import java.time.format.DateTimeFormatter;


public class Global {
	public static final String formatoFecha="d/M/yyyy";
	public static final String formatoHora="HH:mm:ss";
	public static final String formatoFechaHora=formatoFecha+" "+formatoHora;
	
	public static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(formatoFecha);
	public static DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(formatoHora);
	public static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(formatoFecha+" "+formatoHora);

}
