package ui;


import java.time.format.DateTimeFormatter;


public class Global {
	public static final String esquemaFormatoFecha="yyyy-MM-dd HH:mm:ss";
	public static DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern(esquemaFormatoFecha);
}
