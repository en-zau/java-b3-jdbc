package eosi.b32023.bo;

public class Fournisseur {

    private int id;
    private String nom;

    public Fournisseur(String nom) {
        this.nom = nom;
    }

    public Fournisseur(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
