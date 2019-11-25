package Dto;

import sopra.formation.model.NiveauMatiere;

public class ModuleDto {

	private Integer code;

	private int version;

	private Integer duree;

	private Integer ordre;

	private Long filiereId;
	
	private Long formateurId;
	
	private String nomMatiere;
	
	private NiveauMatiere niveauMatiere;
	
	private String nomSalle;
	
	private Integer capaciteSalle;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public Integer getDuree() {
		return duree;
	}

	public void setDuree(Integer duree) {
		this.duree = duree;
	}

	public Integer getOrdre() {
		return ordre;
	}

	public void setOrdre(Integer ordre) {
		this.ordre = ordre;
	}
	
	public Long getFiliereId() {
		return filiereId;
	}

	public void setFiliereId(Long filiereId) {
		this.filiereId = filiereId;
	}

	public Long getFormateurId() {
		return formateurId;
	}

	public void setFormateurId(Long formateurId) {
		this.formateurId = formateurId;
	}

	public String getNomMatiere() {
		return nomMatiere;
	}

	public void setNomMatiere(String nomMatiere) {
		this.nomMatiere = nomMatiere;
	}



	public NiveauMatiere getNiveauMatiere() {
		return niveauMatiere;
	}

	public void setNiveauMatiere(NiveauMatiere niveauMatiere) {
		this.niveauMatiere = niveauMatiere;
	}

	public String getNomSalle() {
		return nomSalle;
	}

	public void setNomSalle(String nomSalle) {
		this.nomSalle = nomSalle;
	}

	public Integer getCapaciteSalle() {
		return capaciteSalle;
	}

	public void setCapaciteSalle(Integer capaciteSalle) {
		this.capaciteSalle = capaciteSalle;
	}
	
	
	
	

}
