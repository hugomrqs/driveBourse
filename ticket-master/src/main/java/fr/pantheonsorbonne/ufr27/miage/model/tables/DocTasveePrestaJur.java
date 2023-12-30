package fr.pantheonsorbonne.ufr27.miage.model.tables;

import jakarta.persistence.*;

import java.io.File;

@Entity
@Table(name = "DocTasveePrestaJur")
public class DocTasveePrestaJur {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "idPrestaJur", nullable = false)
        protected int idPrestaJur;

        @Id
        @JoinColumn(name = "idTasvee", nullable = false)
        protected int idTasvee;

        @Column(name = "analyJur", nullable = false)
        protected File analyJur;

        public int getIdPrestaJur() { return this.idPrestaJur; }

        public void setIdPrestaJur(int idPrestaJur) { this.idPrestaJur = idPrestaJur; }

        public int getIdTasvee() { return this.idTasvee; }

        public void setIdTasvee(int idTasvee) { this.idTasvee = idTasvee; }

        public File getAnalyJur() { return this.analyJur; }

        public void setanalyJur(File analyJur) { this.analyJur = analyJur; }



}
