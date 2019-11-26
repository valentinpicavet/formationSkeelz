package Dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


public class ModuleDto {

	@NotNull(message = "{module.code.notnull}")
	private Integer code;

	private int version;

	private Integer duree;

	private Integer ordre;

	private Long filiereId;
	
	private Long formateurId;
	
	@NotNull(message = "{module.matiere.notnull}")
	@NotEmpty(message = "{module.matiere.notnull}")
	private String nomMatiereNiveau;
	
	private String nomSalleCapacite;
	

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

	

	public String getNomMatiereNiveau() {
		return nomMatiereNiveau;
	}

	public void setNomMatiereNiveau(String nomMatiereNiveau) {
		this.nomMatiereNiveau = nomMatiereNiveau;
	}

	public String getNomSalleCapacite() {
		return nomSalleCapacite;
	}

	public void setNomSalleCapacite(String nomSalleCapacite) {
		this.nomSalleCapacite = nomSalleCapacite;
	}


	
	
	
	

}
