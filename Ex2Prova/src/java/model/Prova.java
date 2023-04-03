package model;


import java.util.Date;
import java.util.Objects;


public class Prova{
    private String numeroMatricula;
    private String nomedoAluno;
    private Date dataProva;
    private double nota;
   

    public Prova() {
    }

    public Prova(String numeroMatricula, String nomedoAluno, Date dataProva, double nota) {
        this.numeroMatricula = numeroMatricula;
        this.nomedoAluno = nomedoAluno;
        this.dataProva = dataProva;
        this.nota = nota;
    }
    
    
    
    public String getNumeroMatricula() {
        return numeroMatricula;
    }

    public void setNumeroMatricula(String numeroMatricula) {
        this.numeroMatricula = numeroMatricula;
    }

    public String getNomedoAluno() {
        return nomedoAluno;
    }

    public void setNomedoAluno(String nomedoAluno) {
        this.nomedoAluno = nomedoAluno;
    }

    public Date getDataProva() {
        return dataProva;
    }

    public void setDataProva(Date dataProva) {
        this.dataProva = dataProva;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Prova other = (Prova) obj;
        if (Double.doubleToLongBits(this.nota) != Double.doubleToLongBits(other.nota)) {
            return false;
        }
       
        if (!Objects.equals(this.numeroMatricula, other.numeroMatricula)) {
            return false;
        }
        if (!Objects.equals(this.nomedoAluno, other.nomedoAluno)) {
            return false;
        }
        return Objects.equals(this.dataProva, other.dataProva);
    }
    
    
    
}
