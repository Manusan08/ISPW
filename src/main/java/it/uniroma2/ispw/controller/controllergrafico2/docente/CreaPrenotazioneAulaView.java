package it.uniroma2.ispw.controller.controllergrafico2.docente;

import it.uniroma2.ispw.bean.LoginBean;
import it.uniroma2.ispw.controller.controllergrafico2.TemplateView;
import it.uniroma2.ispw.bean.AulaBean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class CreaPrenotazioneAulaView extends TemplateView {
    @Override
    public LoginBean control() {
        int choice;
        boolean cond = true;

        while (cond) {
            choice = this.userChoice();
            switch (choice) {
                case 1 -> inserisciCampiDiricerca();
                case 2 -> cond = false;
                default -> System.out.println("riprova");
            }
        }
        return null;
    }
        public AulaBean inserisciCampiDiricerca () {
            AulaBean aulaBean = new AulaBean();
            Scanner scanner = new Scanner(System.in);
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

            try {
                System.out.print("Inserisci il nome del docente: ");
                aulaBean.setNomeDocente(scanner.nextLine());

                System.out.print("Inserisci l'orario della lezione (dd/MM/yyyy HH:mm): ");
                String orarioLezioneInput = scanner.nextLine();
                Date orarioLezione = dateFormat.parse(orarioLezioneInput);
                aulaBean.setOrarioLezione((java.sql.Date) orarioLezione);

                System.out.print("Inserisci la materia: ");
                aulaBean.setMateria(scanner.nextLine());

                System.out.print("Inserisci l'ID dell'aula: ");
                aulaBean.setIdAula(scanner.nextLine());

                System.out.print("Inserisci il numero di posti: ");
                aulaBean.setPosti(Integer.parseInt(scanner.nextLine()));

                System.out.print("Inserisci la descrizione: ");
                aulaBean.setDescrizione(scanner.nextLine());

            } catch (ParseException e) {
                System.out.println("Formato data/ora non valido");
            } catch (NumberFormatException e) {
                System.out.println("Formato numero di posti non valido");
            }

            return aulaBean;
        }

        @Override
        protected List<String> getOptions () {
            return List.of();
        }

        @Override
        protected String getHeader () {
            return "";
        }

        @Override
        public void update (String...msg){

        }
    }
