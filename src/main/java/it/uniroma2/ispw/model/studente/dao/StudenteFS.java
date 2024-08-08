package it.uniroma2.ispw.model.studente.dao;

import com.opencsv.CSVReader;
import it.uniroma2.ispw.model.docente.DocenteModel;
import it.uniroma2.ispw.model.studente.StudenteModel;
import it.uniroma2.ispw.utils.CSVManager;
import it.uniroma2.ispw.utils.exception.ItemNotFoundException;
import it.uniroma2.ispw.utils.exception.SystemException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class StudenteFS implements StudenteDAO {
    private static final String CSV_FILE_NAME = CSVManager.getCsvDir() + "utente.csv";
    private final File file;

    private static final int INDEX_EMAIL = 0;
    private static final int INDEX_MAT = 1;

    public StudenteFS() throws IOException {
        this.file = new File(CSV_FILE_NAME);

        if (!file.exists()) {
            boolean isFileCreated = file.createNewFile();
            if (!isFileCreated) throw new IOException("Impossibile dialogare con il file");
        }
    }

    @Override
    public StudenteModel getStudentebyEmail(String email) throws ItemNotFoundException {
        StudenteModel u = null;
        CSVReader csvReader = null;
        try {
            csvReader = new CSVReader(new BufferedReader(new FileReader(this.file)));
            String[] rcrd = {};

            while ((rcrd = csvReader.readNext()) != null) {
                //check if the user exists
                if (rcrd[INDEX_EMAIL].equals(email)) {
                    u = setStudenteFromRecord(rcrd);
                }
            }

        } catch (Exception e) {

            throw new ItemNotFoundException("Utente non esistente.");
        } finally {
            CSVManager.closeCsvReader(csvReader);
        }

        if (u == null) throw new ItemNotFoundException("Utente non esistente");

        return u;
    }


    private StudenteModel setStudenteFromRecord(String[] rcrd) {


        String email = rcrd[INDEX_EMAIL];
        int mat = Integer.parseInt(rcrd[INDEX_MAT]);


        return new StudenteModel(email, mat);
    }
}

