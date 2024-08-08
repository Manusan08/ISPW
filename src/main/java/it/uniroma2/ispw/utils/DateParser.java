package it.uniroma2.ispw.utils;

import it.uniroma2.ispw.utils.exception.InvalidDataException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateParser {

    private  DateParser(){}


    public static java.sql.Date parseStringToDate(String str) throws InvalidDataException {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = null;
            try {
                date=formatter.parse(str);
            } catch (ParseException e) {
                throw new InvalidDataException("formato data non valido");
            }
            return (java.sql.Date) date;
    }
}
