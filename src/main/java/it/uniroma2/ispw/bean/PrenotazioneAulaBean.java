package it.uniroma2.ispw.bean;


import java.util.Date;

public class PrenotazioneAulaBean {
        private AulaBean aula;
        private String nomeMateria;
        private String nomeProfessore;
        private Date orario;

        // Getters e Setters
        public AulaBean getAula() {
            return aula;
        }

        public void setAula(AulaBean aula) {
            this.aula = aula;
        }

        public String getNomeMateria() {
            return nomeMateria;
        }

        public void setNomeMateria(String nomeMateria) {
            this.nomeMateria = nomeMateria;
        }

        public String getNomeProfessore() {
            return nomeProfessore;
        }

        public void setNomeProfessore(String nomeProfessore) {
            this.nomeProfessore = nomeProfessore;
        }

        public Date getOrario() {
            return orario;
        }

        public void setOrario(Date orario) {
            this.orario = orario;
        }

    public void setIdPrenotazione(String id) {

    }

    public void setDescrizione(String message) {
    }
}

