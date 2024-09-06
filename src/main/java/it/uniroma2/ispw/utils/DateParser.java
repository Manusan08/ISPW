package it.uniroma2.ispw.utils;

import it.uniroma2.ispw.utils.exception.FormatoDataNonValidoException;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateParser {

    private  DateParser(){}


    public static java.sql.Date parseStringToDate(String str) throws FormatoDataNonValidoException {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date date;
            try {
                date=  formatter.parse(str);
            } catch (ParseException e) {
                throw new FormatoDataNonValidoException("formato data non valido");
            }
            return new java.sql.Date(date.getTime());
    }
}
