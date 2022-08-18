package dev.carlvs.customer.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "tb_regions")
public class Region implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	
	public Region(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public Region() {
    }

}
