package it.uniroma2.ispw.model.login.dao;


import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import it.uniroma2.ispw.bean.LoginBean;
import it.uniroma2.ispw.bean.UserBean;
import it.uniroma2.ispw.enums.Role;
import it.uniroma2.ispw.model.login.LoginModel;
import it.uniroma2.ispw.utils.CSVManager;
import it.uniroma2.ispw.utils.exception.ItemNotFoundException;


import java.io.*;


public class LoginFS implements LoginDAO {
    private static final String CSV_FILE_NAME = CSVManager.getCsvDir() + "login.csv";
    private final File file;
    private static final int INDEX_RUOLO = 0;
    private static final int INDEX_EMAIL = 1;
    private static final int INDEX_PWD = 2;

    public LoginFS() throws IOException {
        this.file = new File(CSV_FILE_NAME);

        if(!file.exists()) {
            boolean isFileCreated = file.createNewFile();
            if(!isFileCreated) throw new IOException("Impossibile dialogare con il file");
        }
    }




    @Override
    public LoginModel auth(LoginModel loginM) throws ItemNotFoundException {
        LoginModel u = null;
        CSVReader csvReader = null;
        try {
            csvReader = new CSVReader(new BufferedReader(new FileReader(this.file)));
            String[] rcrd;
            int emIndex = INDEX_EMAIL;
            int pwdIndex = INDEX_PWD;


            while ((rcrd = csvReader.readNext()) != null) {
                //check if the user exists
                if (rcrd[emIndex].equals(loginM.getEmail()) &&
                        rcrd[pwdIndex].equals(loginM.getPassword())) {
                    u = setUtenteFromRecord(rcrd);
                    break;
                }
            }

        } catch (CsvValidationException | IOException e) {
            throw new RuntimeException(e);
        } finally {
            CSVManager.closeCsvReader(csvReader);
        }

        if(u == null) throw new ItemNotFoundException("Credenziali errate");

        return u;
    }

    private LoginModel setUtenteFromRecord(String[] rcrd) {


        Role role = Role.valueOf(rcrd[INDEX_RUOLO]);

        String email = rcrd[INDEX_EMAIL];
        String pwd = rcrd[INDEX_PWD];




        return new LoginModel( email, pwd, role);
    }
}
